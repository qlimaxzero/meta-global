package com.shitouren.core.service;

import com.alipay.api.AlipayApiException;
import com.shitouren.core.autogenerate.bean.Collection;
import com.shitouren.core.autogenerate.bean.*;
import com.shitouren.core.autogenerate.dao.*;
import com.shitouren.core.bean.eums.BalanceTypeEnum;
import com.shitouren.core.bean.eums.ImgEnum;
import com.shitouren.core.common.Constants;
import com.shitouren.core.common.DictConst;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.model.vo.*;
import com.shitouren.core.service.processor.BalanceRecordProcessor;
import com.shitouren.core.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class MarketServiceImpl implements MarketService {
    @Autowired(required = false)
    UsersDao userDao;
    @Autowired
    DictService dictService;
    @Autowired(required = false)
    BannerDao bannerDao;
    @Autowired(required = false)
    CollectionDao collectionDao;
    @Autowired(required = false)
    GrantDao grantDao;
    @Autowired(required = false)
    UserGrantDao userGrantDao;
    @Autowired(required = false)
    HideRecordDao hideRecordDao;
    @Autowired(required = false)
    ClassificationDao classificationDao;
    @Autowired(required = false)
    DealRecordDao dealRecordDao;
    @Autowired
    BalanceRecordProcessor balanceRecordProcessor;

    @Override
    public List<SellNFTVO> show(int userid, int type) {
        int ejkg = Integer.valueOf(dictService.getValue("ejkg"));
        if (ejkg == 0) {
            return null;
        }
        List<SellNFTVO> list = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        //integers.add(2);
        UserGrantExample userGrantExample1 = new UserGrantExample();
        userGrantExample1.createCriteria().andTypeIn(integers);
        if (type == 1) {
            userGrantExample1.setOrderByClause("sjtime desc");
        }
        if (type == 2) {
            userGrantExample1.setOrderByClause("sjtime asc");
        }
        if (type == 3) {
            userGrantExample1.setOrderByClause("sellprice desc");
        }
        if (type == 4) {
            userGrantExample1.setOrderByClause("sellprice asc");
        }
        List<UserGrant> userGrant = userGrantDao.selectByExample(userGrantExample1);
        //0.待上架1.已上架2.交易中3.已完成
        for (UserGrant grant : userGrant) {
            Collection collection = collectionDao.selectByPrimaryKey(grant.getCollid());
            list.add(SellNFTVO.builder()
                    .id(grant.getId())
                    .name(collection.getName())
                    .no(grant.getTruenumber())
                    .imgUrl(collection.getImg())
                    .price(grant.getSellprice())
                    .build());
        }
        return list;
    }

    @Override
    public TrialCalcVO trialCalcBuyer(Integer uid, Integer id) {
        UserGrant grant = userGrantDao.selectByPrimaryKey(id);
        AssertUtil.notNull(grant, ExceptionConstant.该藏品不存在);
        AssertUtil.isTrue(Objects.equals(grant.getType(), Constants.NFT_TYPE_UP), ExceptionConstant.藏品已下架);

        BigDecimal buyFeeRate = dictService.getDecimalValue(DictConst.MARKET_BUY_FEE_RATE);
        BigDecimal buyFee = DecimalUtil.multiply(grant.getSellprice(), buyFeeRate);
        return TrialCalcVO.builder()
                .serviceFee(buyFee)
                .serviceFeeRate(buyFeeRate)
                .sellPrice(grant.getSellprice())
                .realPrice(DecimalUtil.add(grant.getSellprice(), buyFee))
                .build();
    }

    @Override
    public TrialCalcVO trialCalcSeller(Integer uid, Integer id, BigDecimal sellPrice) {
        UserGrant grant = userGrantDao.selectByPrimaryKey(id);
        AssertUtil.notNull(grant, ExceptionConstant.该藏品不存在);
        AssertUtil.isTrue(grant.getType() == 0, ExceptionConstant.藏品未处于下架状态);

        BigDecimal sellFeeRate = dictService.getDecimalValue(DictConst.MARKET_SELL_FEE_RATE);
        BigDecimal sellFee = DecimalUtil.multiply(sellPrice, sellFeeRate);
        return TrialCalcVO.builder()
                .serviceFee(sellFee)
                .serviceFeeRate(sellFeeRate)
                .sellPrice(sellPrice)
                .realPrice(DecimalUtil.sub(sellPrice, sellFee))
                .build();
    }

    @Override
    public List classificationselect(int type) {
        int ejkg = Integer.valueOf(dictService.getValue("ejkg"));
        if (ejkg == 0) {
            return null;
        }
        List list = new ArrayList();
        CollectionExample collectionExample = new CollectionExample();
        collectionExample.createCriteria().andClassidEqualTo(type);
        List<Collection> collections = collectionDao.selectByExample(collectionExample);
        if (collections.size() == 0) {
            return null;
        }
        List<Integer> integers1 = new ArrayList<>();
        for (Collection collection : collections) {
            integers1.add(collection.getId());
        }
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        UserGrantExample userGrantExample1 = new UserGrantExample();
        userGrantExample1.createCriteria().andCollidIn(integers1).andTypeIn(integers);
        List<UserGrant> userGrant = userGrantDao.selectByExample(userGrantExample1);
        //0.待上架1.已上架2.交易中3.已完成
        for (UserGrant grant : userGrant) {
            Map map = new HashMap();
            map.put("id", grant.getId());//id
            map.put("no", grant.getTruenumber());//编号
            Collection collection = collectionDao.selectByPrimaryKey(grant.getCollid());
            if (collection != null) {
                map.put("name", collection.getName());//名称
                map.put("img", ImgEnum.img.getUrl() + collection.getImg());
                map.put("price", grant.getSellprice());
                map.put("type", grant.getType());
                list.add(map);
            }
        }
        return list;
    }

    @Override
    public List classification() {
        return classificationDao.selectByExample(new ClassificationExample());
    }

    @Override
    public List search(int userid, String search, int type) {
        int ejkg = Integer.valueOf(dictService.getValue("ejkg"));
        if (ejkg == 0) {
            return null;
        }
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
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        UserGrantExample userGrantExample1 = new UserGrantExample();
        userGrantExample1.createCriteria().andCollidIn(integers1).andTypeIn(integers);
        if (type == 1) {
            userGrantExample1.setOrderByClause("sjtime desc");
        }
        if (type == 2) {
            userGrantExample1.setOrderByClause("sjtime asc");
        }
        if (type == 3) {
            userGrantExample1.setOrderByClause("sellprice desc");
        }
        if (type == 4) {
            userGrantExample1.setOrderByClause("sellprice asc");
        }
        List<UserGrant> userGrant = userGrantDao.selectByExample(userGrantExample1);
        //0.待上架1.已上架2.交易中3.已完成
        for (UserGrant grant : userGrant) {
            Map map = new HashMap();
            map.put("id", grant.getId());//id
            map.put("no", grant.getTruenumber());//编号
            Collection collection = collectionDao.selectByPrimaryKey(grant.getCollid());
            map.put("name", collection.getName());//名称
            map.put("img", ImgEnum.img.getUrl() + collection.getImg());//图片
            map.put("price", grant.getSellprice());//图片
            map.put("type", grant.getType());//图片
            map.put("publisher", collection.getPublisher());//发行者
            map.put("cimg", ImgEnum.img.getUrl() + collection.getCreatorimg());//发行者
            list.add(map);
        }
        return list;
    }

    @Override
    public Map details(int uerid, int id) {
        Map map = new HashMap();
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);
        Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
        map.put("id", userGrant.getId());//id
        map.put("name", collection.getName());//名称
        map.put("img", ImgEnum.img.getUrl() + collection.getImg1());//图片
        map.put("imgs", ImgEnum.img.getUrl() + collection.getImg());//图片
        map.put("price", userGrant.getSellprice());//价格
        map.put("details", collection.getDetails());//详情
        map.put("no", userGrant.getTruenumber());//详情
        map.put("publisher", collection.getPublisher());//发行者
        map.put("creator", collection.getCreator());//创作者
        map.put("video", ImgEnum.img.getUrl() + collection.getVideo());
        DealRecordExample dealRecordExample = new DealRecordExample();
        dealRecordExample.setOrderByClause("id desc");
        dealRecordExample.createCriteria().andCollidEqualTo(collection.getId());

        List<DealRecord> dealRecordList = dealRecordDao.selectByExample(dealRecordExample);
        List list = new ArrayList();
        if (dealRecordList.size() > 0) {
            for (DealRecord dealRecord : dealRecordList) {
                Map map1 = new HashMap();
                map1.put("price", dealRecord.getPrice());
                map1.put("name", dealRecord.getNickName());
                map1.put("img", ImgEnum.img.getUrl() + dealRecord.getHeadPrtraits());
                map1.put("time", DateUtils.getDateToStr(dealRecord.getCreatetime(), DateUtils.DATETIME_FORMAT));
                list.add(map1);
            }
        }
        map.put("list", list);
        return map;
    }

    @Override
    public int Confirmorder(int userid, int id) {
        int ejkg = Integer.valueOf(dictService.getValue("ejkg"));
        if (ejkg == 0) {
            throw new CloudException(ExceptionConstant.暂未开放);
        }
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);
        if (userid == userGrant.getUserid()) {
            throw new CloudException(ExceptionConstant.该藏品是你的无法购买);
        }
        if (userGrant.getType() != 1) {
            throw new CloudException(ExceptionConstant.藏品已被交易);
        }
        UserGrant userGrants = new UserGrant();
        userGrants.setId(id);
        userGrants.setType(2);
        userGrants.setState(1);
        userGrants.setBuytime(new Date());
        userGrants.setOppositeuser(userid);
        int djs = Integer.parseInt(dictService.getValue("djs"));
        userGrants.setEndtime(DateUtils.getFrontMinute(new Date(), djs));
        userGrantDao.updateByPrimaryKeySelective(userGrants);
        return userGrant.getId();
    }

    @Override
    public Map Confirmorderdetails(int userid, int id) {
        Map map = new HashMap();
        String a = dictService.getValue("djs");
        int djs = Integer.valueOf(a);
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);
        Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
        map.put("id", userGrant.getId());
        map.put("name", collection.getName());
        map.put("pay", 0);
        map.put("buycard", 0);
        map.put("img", ImgEnum.img.getUrl() + collection.getImg());
        map.put("price", userGrant.getSellprice());
        map.put("orderno", userGrant.getTruenumber());
        map.put("createtime", DateUtils.getDateToStr(userGrant.getCreatetime(), DateUtils.DATETIME_FORMAT));
        long hm = DateUtils.dateDiff(new Date(), DateUtils.getFrontMinute(userGrant.getBuytime(), djs));
        if (hm < 5000) {
            userGrant.setType(1);
            userGrant.setOppositeuser(0);
            userGrantDao.updateByPrimaryKeySelective(userGrant);
            throw new CloudException(ExceptionConstant.订单已过期);
        }
        //String payType = dictService.getValue(DictConst.MARKET_PAY_TYPE);
        //map.put("pay", payType.split(","));
        map.put("surplustime", hm / 1000);
        map.put("paytype", userGrant.getPaytype());
        return map;
    }

    @Override
    public Map Payorder(int userid, int id, int type, String pass, int buycard) throws AlipayApiException {
        Map map = new HashMap();
        Users users = userDao.selectByPrimaryKey(userid);
        if (type == 1) {
            if (StringUtil.getLength(users.getTradePassWord()) == 0) {
                throw new CloudException(ExceptionConstant.请先设置操作密码);
            }
            if (!users.getTradePassWord().equals(MD5Util.MD5Encode(pass))) {
                throw new CloudException(ExceptionConstant.操作密码错误);
            }
        }
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);
        Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
        String ailay = "";
        //1.平台2.微信3.支付宝4.其他
        if (type == 1) {
            if (users.getBalance().compareTo(userGrant.getSellprice()) == -1) {
                throw new CloudException(ExceptionConstant.资产不足);
            }
            userGrant.setType(3);
            userGrant.setPaytype(type);
            userGrantDao.updateByPrimaryKeySelective(userGrant);
            UserGrant grant = new UserGrant();
            grant.setUserid(userGrant.getOppositeuser());
            grant.setCollid(userGrant.getCollid());
            grant.setTruenumber(userGrant.getTruenumber());
            grant.setHashs(userGrant.getHashs());
            grant.setTokenid(userGrant.getTokenid());
            grant.setTradeTime(new Date());
            grant.setCreatetime(new Date());
            grant.setBuyprice(userGrant.getSellprice());
            userGrantDao.insertSelective(grant);

            BigDecimal over = users.getBalance().subtract(userGrant.getSellprice());
            users.setBalance(over);
            userDao.updateByPrimaryKeySelective(users);
            balanceRecordProcessor.add(userid, userGrant.getSellprice().negate(), BalanceTypeEnum.TRADE, grant.getId(),
                    collection.getName(), over);
            HideRecord hideRecord = new HideRecord();
            hideRecord.setUserid(userid);
            hideRecord.setImg(collection.getImg());
            hideRecord.setName(collection.getName());
            hideRecord.setPrice(userGrant.getSellprice());
            hideRecord.setNo(grant.getTruenumber());
            hideRecord.setCreateTime(new Date());
            hideRecord.setMs("购买成功");
            hideRecord.setType(2);//0.黄的1.绿2.红
            hideRecordDao.insertSelective(hideRecord);

            DealRecord dealRecord = new DealRecord();
            dealRecord.setCollid(collection.getId());
            dealRecord.setCreatetime(new Date());
            dealRecord.setPrice(userGrant.getSellprice());
            dealRecord.setNickName(users.getNickName());
            dealRecord.setHeadPrtraits(users.getHeadPrtraits());
            dealRecordDao.insertSelective(dealRecord);

            //给对方加资产
            //总钱数
            BigDecimal zong = userGrant.getSellprice();
            //服务费手续费
            BigDecimal fwfsxf = new BigDecimal(dictService.getValue("fwfsxf")).divide(new BigDecimal(100)).setScale(3, BigDecimal.ROUND_CEILING);
            //服务费版税
            BigDecimal fwfbs = new BigDecimal(dictService.getValue("fwfbs")).divide(new BigDecimal(100)).setScale(3, BigDecimal.ROUND_CEILING);
            BigDecimal fwxsfkc = zong.multiply(fwfsxf).setScale(2, BigDecimal.ROUND_CEILING);
            BigDecimal fwfbskc = zong.multiply(fwfbs).setScale(2, BigDecimal.ROUND_CEILING);
            BigDecimal overb = zong.subtract(fwxsfkc.add(fwfbskc));
            Users users2 = userDao.selectByPrimaryKey(userGrant.getUserid());
            BigDecimal bigDecimal = users2.getBalance().add(overb);
            users2.setBalance(bigDecimal);
            userDao.updateByPrimaryKeySelective(users2);
            balanceRecordProcessor.add(userGrant.getUserid(), overb, BalanceTypeEnum.SALE, userGrant.getId(), bigDecimal);
        } else if (type == 2) {

        } else if (type == 3) {//支付宝
        } else if (type == 4) {
        } else if (type == 15) {
        } else {
            throw new CloudException(ExceptionConstant.暂未开放);
        }

        //return Integer.toString(id);
        return map;
    }

}
