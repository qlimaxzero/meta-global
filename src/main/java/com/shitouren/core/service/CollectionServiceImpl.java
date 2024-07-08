package com.shitouren.core.service;

import cn.hutool.core.date.DateUtil;
import com.shitouren.core.common.Constants;
import com.shitouren.core.autogenerate.bean.Collection;
import com.shitouren.core.autogenerate.bean.*;
import com.shitouren.core.autogenerate.dao.*;
import com.shitouren.core.bean.eums.ImgEnum;
import com.shitouren.core.bean.eums.UsageEnum;
import com.shitouren.core.common.DictConst;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.config.redis.CloudRedisTemplate;
import com.shitouren.core.service.processor.HideRecordProcessor;
import com.shitouren.core.service.processor.TaskProcessor;
import com.shitouren.core.utils.*;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired(required = false)
    UsersDao userDao;
    @Autowired
    DictService dictService;
    @Autowired(required = false)
    CollectionDao collectionDao;
    @Autowired(required = false)
    YuanyuzhouDao yuanyuzhouDao;
    @Autowired(required = false)
    GrantDao grantDao;
    @Autowired(required = false)
    UserGrantDao userGrantDao;
    @Autowired(required = false)
    HideRecordDao hideRecordDao;
    @Autowired
    private CloudRedisTemplate cloudRedisTemplate;
    @Resource
    private HideRecordProcessor hideRecordProcessor;
    @Resource
    private TaskProcessor taskProcessor;

    @Override
    //@Cacheable(value = "showColl", key = "#userid")
    public List showColl(int userid) {
        List list = new ArrayList();
        UserGrantExample userGrantExample = new UserGrantExample();
        userGrantExample.setOrderByClause("id desc");
        List<Integer> integers = new ArrayList<>();
        integers.add(3);
        integers.add(4);
        userGrantExample.createCriteria().andUseridEqualTo(userid).andTypeNotIn(integers);
        userGrantExample.isDistinct();
        List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);
        if (userGrants.size() == 0) {
            return list;
        }
        final List<Integer> collIds = userGrants.stream()
                                                .map(UserGrant::getCollid)
                                                .distinct()
                                                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(collIds)) {
            return list;
        }
        CollectionExample ex = new CollectionExample();
        ex.createCriteria()
                .andIdIn(collIds);
        final List<Collection> collections = collectionDao.selectByExampleWithBLOBs(ex);
        final Map<Integer, Collection> m = collections.stream()
                .collect(Collectors.toMap(Collection::getId,
                        Function.identity(),
                        (k1, k2) -> k1));
        for (UserGrant grant : userGrants) {
            final Collection collection = m.get(grant.getCollid());
            if (collection != null) {
                Map map = new HashMap();
                map.put("id", grant.getId());//id
                map.put("no", grant.getTruenumber());//编号
                map.put("name", collection.getName());//名称
                map.put("img", ImgEnum.img.getUrl() + collection.getImg());//图片
                map.put("cimg", ImgEnum.img.getUrl() + collection.getCreatorimg());//创作者头像
                map.put("creator", collection.getCreator());//发行者
                map.put("collid", grant.getCollid());//发行者
                map.put("type", grant.getType());
                if (grant.getHashs() != null || grant.getHashs() != "") {
                    map.put("state", 1);
                } else {
                    map.put("state", 0);
                }
                if (grant.getType() != 0 && grant.getType() != -1) {
                    map.put("price", grant.getSellprice());
                } else {
                    map.put("price", "");
                }
                list.add(map);
            }
        }
        return list;
    }

    @Override
    //@Cacheable(value = "showinfo", key = "#userid+'-'+#collid")
    public List showinfo(int userid,int collid) {
        List list = new ArrayList();
        UserGrantExample userGrantExample = new UserGrantExample();
        userGrantExample.setOrderByClause("id desc");
        List<Integer> integers = new ArrayList<>();
        integers.add(3);
        integers.add(4);
        userGrantExample.createCriteria().andUseridEqualTo(userid).andCollidEqualTo(collid).andTypeNotIn(integers);
        userGrantExample.isDistinct();
        List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);
        if (userGrants.size() == 0) {
            return list;
        }
        for (UserGrant grant : userGrants) {
            Collection collection = collectionDao.selectByPrimaryKey(grant.getCollid());
            if (collection != null) {
                Map map = new HashMap();
                map.put("countDownDays", this.calcCountDownDays(collection, grant));
                map.put("id", grant.getId());//id
                map.put("no", grant.getTruenumber());//编号
                map.put("name", collection.getName());//名称
                map.put("img", ImgEnum.img.getUrl() + collection.getImg());//图片
                map.put("cimg", ImgEnum.img.getUrl() + collection.getCreatorimg());//创作者头像
                map.put("creator", collection.getCreator());//发行者
                map.put("collid", grant.getCollid());//发行者
                map.put("type", grant.getType());
                if (grant.getHashs() != null || grant.getHashs() != "") {
                    map.put("state", 1);
                } else {
                    map.put("state", 0);
                }
                if (grant.getType() != 0 && grant.getType() != -1) {
                    map.put("price", grant.getSellprice());
                } else {
                    map.put("price", "");
                }
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 计算赠送积分倒计时天数
     */
    private Object calcCountDownDays(Collection collection, UserGrant grant) {
        boolean isOpen = collection.getPointsDays() > 0 && collection.getPointsNum() > 0;
        if (!isOpen) {
            return -1;
        }
        // 控制单个藏品上架时不展示倒计时
        if (grant.getType() != 0) {
            return "X";
        }

        Date beginDate = DateUtils.getDayBegin();
        Date tradeTime = taskProcessor.getLastTaskTradeTimeByHOLD(grant);
        if (tradeTime == null || tradeTime.before(grant.getTradeTime())) {
            tradeTime = grant.getTradeTime();
        }

        int days = (int) DateUtil.betweenDay(tradeTime, beginDate, false);
        return collection.getPointsDays() - days % collection.getPointsDays();
    }

    @Override
    public List search(int userid, String search) {
        List list = new ArrayList();
        CollectionExample collectionExample = new CollectionExample();
        collectionExample.createCriteria().andNameLike("%" + search + "%");
        List<Collection> collections = collectionDao.selectByExample(collectionExample);
        if (collections.size() == 0) {
            return null;
        }
        List<Integer> integers1 = new ArrayList<>();
        for (Collection collection : collections) {
            integers1.add(collection.getId());
        }
        UserGrantExample userGrantExample = new UserGrantExample();
        List<Integer> integers = new ArrayList<>();
        integers.add(3);
        integers.add(4);
        userGrantExample.createCriteria().andUseridEqualTo(userid).andCollidIn(integers1).andTypeNotIn(integers);
        List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);
        if (userGrants.size() == 0) {
            return list;
        }
        for (UserGrant grant : userGrants) {
            Map map = new HashMap();
            map.put("id", grant.getId());//id
            map.put("no", grant.getTruenumber());//编号
            Collection collection = collectionDao.selectByPrimaryKey(grant.getCollid());
            map.put("name", collection.getName());//名称
            map.put("img", ImgEnum.img.getUrl() + collection.getImg());//图片
            map.put("cimg", ImgEnum.img.getUrl() + collection.getCreatorimg());//创作者头像
            map.put("creator", collection.getCreator());//发行者
            map.put("type", grant.getType());
            if (grant.getType() != 0 && grant.getType() != -1) {
                map.put("price", grant.getSellprice());
            } else {
                map.put("price", "");
            }
            list.add(map);
        }
        return list;
    }

    @Override
    //@Cacheable(value = "collDetails", key = "#userid+''+#id")
    public Map details(int userid,int id) {
        Map map = new HashMap();
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);

        //判断藏品所有者
        if (userGrant.getUserid() != userid) {
            throw new CloudException(ExceptionConstant.违规操作即将封号);
        }

        if (userGrant.getType() == -1) {
            throw new CloudException(ExceptionConstant.藏品暂未发放成功);
        }
        Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
        map.put("id", userGrant.getId());//id
        map.put("img", ImgEnum.img.getUrl() + collection.getImg1());//图片
        map.put("name", collection.getName());//名称
        map.put("no", userGrant.getTruenumber());//编号
        map.put("type", userGrant.getType());
        Users user = userDao.selectByPrimaryKey(userGrant.getUserid());
        map.put("collector", user.getNickName());//收藏着
        map.put("hash", userGrant.getHashs());//hash值
        map.put("creator", collection.getCreator());//创作者
        map.put("buyprice", userGrant.getBuyprice());//购买价格
        map.put("video", ImgEnum.img.getUrl() + collection.getVideo());
        map.put("date", DateUtils.getDateToStr(collection.getPublishtime(), DateUtils.DATETIME_FORMAT));//创作者
        map.put("video", ImgEnum.img.getUrl() + collection.getVideo());
        map.put("link", collection.getLink());
        map.put("details", collection.getDetails());//详情

        map.put("yuanyuzhouid", userGrant.getYuanyuzhouid());//元宇宙ID
        if(userGrant.getYuanyuzhouid() > 0){
            map.put("yuanyuzhou", yuanyuzhouDao.selectByPrimaryKey(userGrant.getYuanyuzhouid()));//元宇宙ID
        }
        return map;
    }

    @Override
    public String Copylink(int userid) {
        Users user = userDao.selectByPrimaryKey(userid);
        return "http:///#/?registerCode=" + user.getUserId();
    }

    @Override
    public Map certificate(int userid, int id) {
        Map map = new HashMap();
        Users user = userDao.selectByPrimaryKey(userid);
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);
        //判断藏品所有者
        if (userGrant.getUserid() != userid) {
            throw new CloudException(ExceptionConstant.违规操作即将封号);
        }

        Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
        map.put("name", collection.getName());//名称
        map.put("creator", collection.getCreator());//创作者
        map.put("publisher", collection.getPublisher());
        map.put("collector", user.getNickName());//收藏着
        map.put("introduce", collection.getIntroduce());//收藏着
        map.put("hash", userGrant.getHashs());//hash值
        map.put("date", DateUtils.getDateToStr(userGrant.getCreatetime(), DateUtils.DATETIME_FORMAT));//创作者
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void marketUp(Integer userid, Integer id, BigDecimal price, String pass) {
        int ejkg = Integer.parseInt(dictService.getValue("ejkg"));
        if (ejkg == 0) {
            throw new CloudException(ExceptionConstant.暂未开放);
        }
//        Users user = userDao.getUserById(userid);
//        if (StringUtil.getLength(user.getTradePassWord()) == 0) {
//            throw new CloudException(ExceptionConstant.请先设置操作密码);
//        }
//        if (!user.getTradePassWord().equals(MD5Util.MD5Encode(pass))) {
//            throw new CloudException(ExceptionConstant.操作密码错误);
//        }
        if (price.compareTo(new BigDecimal(0)) < 1) {
            throw new CloudException(ExceptionConstant.价格有误);
        }

        val lockKey = String.format(Constants.META_KEY + "marketUp#%s#%s", userid, id);
        val lockValue = UUID.randomUUID().toString();
        final boolean isLocked = cloudRedisTemplate.getLock(lockKey, lockValue, 3);
        if (!isLocked) {
            throw new CloudException(ExceptionConstant.频率太高请稍等);
        }

        final UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);
        AssertUtil.notNull(userGrant, ExceptionConstant.参数异常);
        AssertUtil.isFalse(Constants.INIT_NFT_LIST.contains(userGrant.getCollid()), ExceptionConstant.NFT_CANNOT_UP);
        //判断藏品所有者
        if (!Objects.equals(userGrant.getUserid(), userid)) {
            throw new CloudException(ExceptionConstant.违规操作即将封号);
        }

        if (userGrant.getType() == -1) {
            throw new CloudException(ExceptionConstant.藏品暂未发放成功);
        }

        BigDecimal healthDefault = dictService.getDecimalValue(DictConst.HEALTH_DEFAULT);
        AssertUtil.isFalse(userGrant.getHealth().compareTo(healthDefault) < 0, ExceptionConstant.NFT_HEALTH_LACK);
        AssertUtil.isFalse(Objects.equals(userGrant.getDisplay(), Constants.DISPLAY_ON), ExceptionConstant.NFT_IN_USE);

        userGrant.setSellprice(price);
        userGrant.setType(1);
        userGrant.setSjtime(new Date());
        userGrantDao.updateByPrimaryKeySelective(userGrant);

        final boolean isUnlocked = cloudRedisTemplate.releaseLock(lockKey, lockValue);
        if (!isUnlocked) {
            throw new CloudException(ExceptionConstant.频率太高请稍等);
        }
    }

    @Override
    @CacheEvict(value = "showColl", key = "#userid")
    public void Passon(int userid, int id, String phone, String pass) {
        int zzkg = Integer.parseInt(dictService.getValue("zzkg"));
        if (zzkg == 0) {
            throw new CloudException(ExceptionConstant.暂未开放);
        }
        Users user = userDao.selectByPrimaryKey(userid);
        if (StringUtil.getLength(user.getTradePassWord()) == 0) {
            throw new CloudException(ExceptionConstant.请先设置操作密码);
        }
        if (!user.getTradePassWord().equals(MD5Util.MD5Encode(pass))) {
            throw new CloudException(ExceptionConstant.操作密码错误);
        }
        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andPhoneNumberEqualTo(phone);
        List<Users> users = userDao.selectByExample(usersExample);
        if (CollectionUtils.isEmpty(users)) {
            throw new CloudException(ExceptionConstant.查无此人);
        }

        final String lockKey = String.format("Passon#%s#%s", userid, id);
        final String lockValue = UUID.randomUUID().toString();
        final boolean   isLocked      = cloudRedisTemplate.getLock(lockKey, lockValue, 3);
        if (!isLocked) {
            throw new CloudException(ExceptionConstant.频率太高请稍等);
        }
        final UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);
        // UserGrant userGrant = userGrantDao.lockByPrimaryKey(id);
        if (UsageEnum.isUsed(userGrant.getUsage())) {
            throw new CloudException(ExceptionConstant.模块正在元宇宙中使用);
        }
        if (userGrant.getType() != 0) {
            log.error("藏品未处于下架状态,不可转赠, userId = {}", userid);
            throw new CloudException(ExceptionConstant.藏品未处于下架状态);
        }
        if (userGrant.getHashs() == null || Objects.equals(userGrant.getHashs(), "")) {
            throw new CloudException(ExceptionConstant.暂时无法转赠);
        }
        if (userid != userGrant.getUserid()) {
            throw new CloudException(ExceptionConstant.违规操作即将封号);
        }
        Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
        if(collection.getSendtype().equals(0)){
            throw new CloudException(ExceptionConstant.暂时无法转赠);
        }

        if (userGrant.getCollid() == Constants.YUANYUZHOU_COLL_ID) {
            Yuanyuzhou yuanyuzhou = yuanyuzhouDao.selectByPrimaryKey(userGrant.getYuanyuzhouid());
            if (yuanyuzhou == null) {
                throw new CloudException(ExceptionConstant.元宇宙场景不存在);
            }
            if (yuanyuzhou.getPid() != 0) {
                throw new CloudException(ExceptionConstant.元宇宙ID拆分之后才能转增);
            }
            Integer tmpUid = yuanyuzhou.getUserId();
            yuanyuzhou.setUserId(users.get(0).getUserId());
            yuanyuzhou.setNickName(users.get(0).getNickName());
            yuanyuzhouDao.updateByPrimaryKeySelective(yuanyuzhou);
            log.info("change yuanyuzhouId {} from {} to {}", userGrant.getYuanyuzhouid(), tmpUid, yuanyuzhou.getUserId());
        }

        userGrant.setUserid(users.get(0).getUserId());
        userGrant.setTradeTime(new Date());
        userGrantDao.updateByPrimaryKeySelective(userGrant);

        HideRecord hideRecord = new HideRecord();
        hideRecord.setUserid(userid);
        hideRecord.setImg(collection.getImg());
        hideRecord.setName(collection.getName());
        hideRecord.setPrice(userGrant.getBuyprice());
        hideRecord.setNo(userGrant.getTruenumber());
        hideRecord.setMs("转赠成功");
        hideRecord.setGetuid(users.get(0).getUserId());
        hideRecord.setCreateTime(new Date());
        hideRecord.setType(0);//0.黄的1.绿2.红
        hideRecord.setUserGrantId(userGrant.getId());
        hideRecordDao.insertSelective(hideRecord);

        HideRecord hideRecord11 = new HideRecord();
        hideRecord11.setUserid(users.get(0).getUserId());
        hideRecord.setGetuid(userid);
        hideRecord11.setImg(collection.getImg());
        hideRecord11.setName(collection.getName());
        hideRecord11.setPrice(userGrant.getBuyprice());
        hideRecord11.setNo(userGrant.getTruenumber());
        hideRecord11.setMs("转入成功");
        hideRecord11.setCreateTime(new Date());
        hideRecord11.setType(0);//0.黄的1.绿2.红
        hideRecord11.setUserGrantId(userGrant.getId());
        hideRecordDao.insertSelective(hideRecord11);
        final boolean   isUnLocked      = cloudRedisTemplate.releaseLock(lockKey, lockValue);
        if (!isUnLocked) {
            throw new CloudException(ExceptionConstant.频率太高请稍等);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void marketDown(Integer userid, Integer id) {
        String lockKey = String.format(Constants.META_KEY + "marketDown#%s#%s", userid, id);
        String lockValue = UUID.randomUUID().toString();
        final boolean isLocked = cloudRedisTemplate.getLock(lockKey, lockValue, 4);
        if (!isLocked) {
            throw new CloudException(ExceptionConstant.频率太高请稍等);
        }
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);
        //判断藏品所有者
        if (!Objects.equals(userGrant.getUserid(), userid)) {
            throw new CloudException(ExceptionConstant.违规操作即将封号);
        }
        if (userGrant.getType() != 1) {
            throw new CloudException(ExceptionConstant.正在交易无法撤回);
        }
        userGrant.setType(0);//设置成下架
        userGrantDao.updateByPrimaryKeySelective(userGrant);

        final boolean isUnLocked = cloudRedisTemplate.releaseLock(lockKey, lockValue);
        if (!isUnLocked) {
            throw new CloudException(ExceptionConstant.频率太高请稍等);
        }
    }

    @Override
    public Map canshu() {
        Map map = new HashMap();
        map.put("fwfsxf", dictService.getValue("fwfsxf"));
        map.put("fwfbs", dictService.getValue("fwfbs"));
        return map;
    }

    @Override
    public BigDecimal sellmoney(int logUserPid, BigDecimal money) {
        if (money.compareTo(new BigDecimal(0)) < 1) {
            throw new CloudException(ExceptionConstant.价格错误);
        }
        BigDecimal fwfsxf = new BigDecimal(dictService.getValue("fwfsxf")).multiply(new BigDecimal(0.01));
        BigDecimal fwfbs = new BigDecimal(dictService.getValue("fwfbs")).multiply(new BigDecimal(0.01));
        BigDecimal total = new BigDecimal(1).subtract(fwfbs.add(fwfsxf));
        //BigDecimal end = money.multiply(total).setScale(2, BigDecimal.ROUND_CEILING);
        return DecimalUtil.multiply(money, total);
    }

}
