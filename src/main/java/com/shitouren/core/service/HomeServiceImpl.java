package com.shitouren.core.service;
import java.util.Date;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.shitouren.core.common.Constants;
import com.shitouren.core.autogenerate.bean.Calendar;
import com.shitouren.core.autogenerate.bean.Collection;
import com.shitouren.core.autogenerate.bean.*;
import com.shitouren.core.autogenerate.dao.*;
import com.shitouren.core.bean.ContractServiceBean;
import com.shitouren.core.bean.eums.BalanceTypeEnum;
import com.shitouren.core.bean.eums.ImgEnum;
import com.shitouren.core.bean.eums.PointsTypeEnum;
import com.shitouren.core.config.TransactionProcessor;
import com.shitouren.core.config.pay.PayProperties;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.config.redis.CloudRedisTemplate;
import com.shitouren.core.constant.BusinessConstant;
import com.shitouren.core.model.vo.*;
import com.shitouren.core.service.processor.BalanceRecordProcessor;
import com.shitouren.core.service.processor.TaskProcessor;
import com.shitouren.core.utils.*;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.sf.json.JSONObject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.shitouren.core.bean.eums.OrderStatusEnum.CALLBACK;

@Slf4j
@Service
public class HomeServiceImpl implements HomeService {

    public static final Supplier<BigDecimal> creditSupplier = () -> BigDecimal.ONE;
    public static final String SUCESS_CODE = "10000";

    @Autowired(required = false)
    UsersDao userDao;
    @Autowired
    DictService dictService;
    @Autowired(required = false)
    BannerDao bannerDao;
    @Autowired(required = false)
    CollectionDao collectionDao;
    @Autowired(required = false)
    BlindboxDao blindboxDao;
    @Autowired(required = false)
    IssueDao issueDao;
    @Autowired(required = false)
    GrantDao grantDao;
    @Autowired(required = false)
    UserGrantDao userGrantDao;
    @Autowired(required = false)
    YuanyuzhouDao yuanyuzhouDao;
    @Autowired(required = false)
    MyboxDao myboxDao;
    @Autowired(required = false)
    BalanceRecordDao balanceRecordDao;
    @Autowired(required = false)
    HideRecordDao hideRecordDao;
    @Autowired(required = false)
    AgreementsDao agreementsDao;
    @Autowired(required = false)
    ApliaymhDao apliaymhDao;
    @Autowired(required = false)
    SignupDao signupDao;
    @Autowired(required = false)
    CalendarDao calendarDao;
    @Autowired(required = false)
    MyOrderDao myOrderDao;
    @Autowired(required = false)
    CommodityDao commodityDao;
    @Autowired(required = false)
    CancelRecordDao cancelRecordDao;
    @Autowired(required = false)
    LotteryDao lotteryDao;
    @Autowired(required = false)
    MoneyrecordDao moneyrecordDao;
    @Autowired(required = false)
    TaskDao taskDao;
    @Autowired(required = false)
    ChatDao chatDao;
    @Autowired(required = false)
    ShareimgDao shareimgDao;
    @Autowired(required = false)
    DealRecordDao dealRecordDao;
    @Autowired(required = false)
    LotterylistDao lotterylistDao;
    @Autowired(required = false)
    MessageDao messageDao;
    @Autowired(required = false)
    InvitelistDao invitelistDao;
    @Autowired(required = false)
    ClassificationDao classificationDao;
    @Autowired
    UserService userService;
    @Autowired
    CloudRedisTemplate cloudRedisTemplate;
    @Resource
    private TaskProcessor taskProcessor;

    @Resource
    ContractServiceBean contractServiceBean;

    @Resource
    private TransactionProcessor transactionProcessor;

    @Resource
    BalanceRecordProcessor balanceRecordProcessor;

    @Resource
    PayProperties payChannelConfig;

    @Resource
    private PiService piService;

    @Override
    public String bannerdetails(int id) {
        return bannerDao.selectByPrimaryKey(id).getContent();
    }

    @Override
    @DS("slave")
    @Cacheable(value = "getHomePageDetail", key = "#userid+'-'+#type")
    public Map getHomePageDetail(int userid, int type) {
        Users users = userDao.selectByPrimaryKey(userid);
        int djs = Integer.parseInt(dictService.getValue("bmdtq"));
        Map<String,Object>   maps  = new HashMap<>();
        maps.put("banners", getBanners());//轮播图
        maps.put("img",getShareImage());
        maps.put("mh", getMhList(users, djs));//盲盒
        maps.put("szcp", getSZCPList(type, users, djs));//数字藏品
//        maps.put("calendarl", this.calendarl());
        return maps;
    }

    @NotNull
    private List getSZCPList(int type, Users users, int djs) {
        List szcp_list = new ArrayList();
        //数字藏品
        IssueExample issueExample = new IssueExample();
        issueExample.createCriteria().andTypeEqualTo(1)
                    .andIstypeEqualTo(1)
//                    .andIdNotEqualTo(58)
                    .andEndtimeGreaterThan(DateUtils.getDayBegin());
        issueExample.setOrderByClause("releasetime desc");
        List<Issue> issues = issueDao.selectByExample(issueExample);
        final List<Integer> cids = issues.stream()
                                            .map(Issue::getCollid)
                                            .collect(Collectors.toList());
        final List<Collection> collections = batchGetCollections(cids);
        final Map<Integer, Collection> id2CollMap = collections.stream()
                                                            .collect(Collectors.toMap(Collection::getId,
                                                                                      Function.identity(),
                                                                                      (k1, k2) -> k1));
        List<Signup> signups = getSignups(issues);

        for (Issue issue : issues) {
            final Collection collection = id2CollMap.get(issue.getCollid());
            if (collection != null && collection.getType() == 0) {
                if (type != 0) {
                    if (collection.getClassid() != type) {
                        continue;
                    }
                }
                Map map = new HashMap();
                map.put("colltype", issue.getIstype());//id
                map.put("paytype", collection.getPaytype());
                map.put("id", issue.getId());//id
                map.put("name", collection.getName());//名称
                map.put("img", ImgEnum.img.getUrl() + collection.getImg());//图片
                map.put("limits", collection.getLimits());//限量
                map.put("price", collection.getPrice());//价格
                map.put("cimg", ImgEnum.img.getUrl() + collection.getCreatorimg());//创作者头像
                map.put("publisher", collection.getCreator());//发行者
                if (issue.getGinsengtype() == 1) {//参与形式(1.出售2.抽签)
                    map.put("type", 0);//已开售
                    map.put("time", "已开售");
                    //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                    int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
                    if (a == 1) {//1.未开售
                        if (users.getWhitelist() > 0) {
                            long hms = DateUtils.dateDiff(new Date(), issue.getReleasetime());
                            if (hms / 1000 < djs * 60) {
                                map.put("type", 0);//1.已开售
                            } else {
                                long hm = DateUtils.dateDiff(new Date(), issue.getReleasetime());
                                if (hm / 1000 > djs * 60) {
                                    map.put("type", 1);//1.未开售
                                    map.put("second", (DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000) - (djs * 60));//1.未开售
                                    map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                                } else {
                                    map.put("type", 0);//1.未开售
                                }
                            }
                        } else {
                            map.put("type", 1);//1.未开售
                            map.put("second", DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000);//1.未开售
                            map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                        }
                    } else {
                        //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                        if (DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
                            map.put("type", 2);//2.已售完
                            map.put("time", "已结束");
                        } else {
                            if (issue.getSold() >= issue.getPresale()) {
                                map.put("type", 3);//2.已售完
                                map.put("time", "已售罄");
                            }
                        }
                    }
                } else if (issue.getGinsengtype() == 2) {//参与形式(1.出售2.抽签)
                    map.put("type", 0);//已开售
                    map.put("time", "已开售");
                    //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                    int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
                    if (a == 1) {//1.未开售
                        if (users.getWhitelist() > 0) {
                            long hms = DateUtils.dateDiff(new Date(), issue.getReleasetime());
                            if (hms / 1000 < djs * 60) {
                                map.put("type", 0);//1.已开售
                            } else {
                                long hm = DateUtils.dateDiff(new Date(), issue.getReleasetime());
                                if (hm / 1000 > djs * 60) {
                                    map.put("type", 1);//1.未开售
                                    map.put("second", (DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000) - (djs * 60));//1.未开售
                                    map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                                } else {
                                    map.put("type", 0);//1.未开售
                                }
                            }
                        } else {
                            map.put("type", 1);//1.未开售
                            map.put("second", DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000);//1.未开售
                            map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                        }
                    } else {
                        //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                        if (DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
                            map.put("type", 2);//2.已售完
                            map.put("time", "已结束");
                        } else {
                            if (issue.getSold() >= issue.getPresale()) {
                                map.put("type", 3);//2.已售完
                                map.put("time", "已售罄");
                            }
                        }
                    }

                    final long sum = signups.stream()
                                            .filter(x -> Objects.equals(x.getIsid(), issue.getId())
                                                         && x.getBegintime() != null
                                                         && x.getBegintime()
                                                             .after(issue.getReleasetime()))
                                            .count();

                    final long hitCount = signups.stream()
                                                 .filter(x -> x.getIsid()
                                                               .equals(issue.getId())
                                                              && x.getBegintime() != null
                                                              && x.getBegintime()
                                                                  .after(issue.getReleasetime())
                                                              && x.getType() == 2)
                                                 .count();

                    if (hitCount > 0) {
                        map.put("describe", "共有 " + sum + " 报名， " + hitCount + " 中签");
                    } else {
                        map.put("describe", "共有 " + sum + " 报名。");
                    }

                }
                log.info("map = {}", JSON.toJSONString(map));
                szcp_list.add(map);
            }
        }
        return szcp_list;
    }

    private List getMhList(Users users, int djs) {

        //数字藏品
        List<Issue> szcpIssues = getIssues(2);

        final Map<Integer, Blindbox> is2BlindboxMap = getid2BlindBoxMap(szcpIssues);

        List mh_list = new ArrayList();

        final List<Signup> signups = getSignups(szcpIssues);
        for (Issue issue : szcpIssues) {
            // Blindbox blindbox = blindboxDao.selectByPrimaryKey(issue.getCollid());
            final Blindbox blindbox = is2BlindboxMap.get(issue.getCollid());
            if (blindbox != null) {
                Map map = new HashMap();
                map.put("colltype", issue.getIstype());//id
                map.put("id", issue.getId());//id
                map.put("name", blindbox.getName());//名称
                map.put("img", ImgEnum.img.getUrl() + blindbox.getImg());//图片
                map.put("limits", blindbox.getLimits());//限量
                map.put("price", blindbox.getPrice());//价格
                map.put("cimg", ImgEnum.img.getUrl() + blindbox.getCreatorimg());//创作者头像
                map.put("publisher", blindbox.getPublisher());//发行者
                map.put("ginsengtype", issue.getGinsengtype());
                if (issue.getGinsengtype() == 1) {//参与形式(1.出售2.抽签)
                    map.put("type", 0);//已开售
                    //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                    int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
                    if (a == 1) {//1.未开售
                        if (users.getWhitelist() > 0) {
                            long hms = DateUtils.dateDiff(new Date(), issue.getReleasetime());
                            if (hms / 1000 < djs * 60) {
                                map.put("type", 0);//1.已开售
                            } else {
                                long hm = DateUtils.dateDiff(new Date(), issue.getReleasetime());
                                if (hm / 1000 > djs * 60) {
                                    map.put("type", 1);//1.未开售
                                    map.put("second", (DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000) - (djs * 60));//1.未开售
                                    map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                                } else {
                                    map.put("type", 0);//1.未开售
                                }
                            }
                        } else {
                            map.put("type", 1);//1.未开售
                            map.put("second", DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000);//1.未开售
                            map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                        }
                    } else {
                        //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                        if (DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
                            map.put("type", 2);//2.已售完
                            map.put("time", "已售完");
                        } else {
                            if (issue.getSold() >= issue.getPresale()) {
                                map.put("type", 3);//2.已售完
                                map.put("time", "已售罄");
                            }
                        }
                    }
                } else if (issue.getGinsengtype() == 2) {//参与形式(1.出售2.抽签)
                    map.put("type", 0);//已开售
                    //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                    int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
                    if (a == 1) {//1.未开售
                        if (users.getWhitelist() > 0) {
                            long hms = DateUtils.dateDiff(new Date(), issue.getReleasetime());
                            if (hms / 1000 < djs * 60) {
                                map.put("type", 0);//1.已开售
                            } else {
                                long hm = DateUtils.dateDiff(new Date(), issue.getReleasetime());
                                if (hm / 1000 > djs * 60) {
                                    map.put("type", 1);//1.未开售
                                    map.put("second", (DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000) - (djs * 60));//1.未开售
                                    map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                                } else {
                                    map.put("type", 0);//1.未开售
                                }
                            }
                        } else {
                            map.put("type", 1);//1.未开售
                            map.put("second", DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000);//1.未开售
                            map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                        }
                    } else {
                        //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                        if (DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
                            map.put("type", 2);//2.已售完
                            map.put("time", "已结束");
                        } else {
                            if (issue.getSold() >= issue.getPresale()) {
                                map.put("type", 3);//2.已售完
                                map.put("time", "已售罄");
                            }
                        }
                    }
                    final long sum = signups.stream()
                                            .filter(x -> Objects.equals(x.getIsid(), issue.getId())
                                                         && x.getBegintime() != null
                                                         && x.getBegintime()
                                                             .after(issue.getReleasetime()))
                                            .count();

                    final long hitCount = signups.stream()
                                                 .filter(x -> x.getIsid()
                                                               .equals(issue.getId())
                                                              && x.getBegintime() != null
                                                              && x.getBegintime()
                                                                  .after(issue.getReleasetime())
                                                              && x.getType() == 2)
                                                 .count();

                    if (hitCount > 0) {
                        map.put("describe", "共有 " + sum + " 报名， " + hitCount + " 中签");
                    } else {
                        map.put("describe", "共有 " + sum + " 报名。");
                    }
                }
                mh_list.add(map);
            }
        }

        return mh_list;
    }

    private List<Signup> getSignups(List<Issue> szcpIssues) {
        final List<Integer> issueIds1 = szcpIssues.stream()
                                                  .map(Issue::getId)
                                                  .distinct()
                                                  .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(issueIds1)) {
            return Lists.newArrayList();
        }

        SignupExample ex11 = new SignupExample();
        ex11.createCriteria()
            .andIsidIn(issueIds1);
        return signupDao.selectByExample(ex11);
    }

    @NotNull
    private Map<Integer, Blindbox> getid2BlindBoxMap(List<Issue> szcpIssues) {
        final List<Integer> collids = szcpIssues.stream()
                                                .map(Issue::getCollid)
                                                .distinct()
                                                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(collids)) {
            return Maps.newHashMap();
        }
        BlindboxExample ex = new BlindboxExample();
        ex.createCriteria()
          .andIdIn(collids);
        final List<Blindbox> blindboxes = blindboxDao.selectByExampleWithBLOBs(ex);
        return blindboxes.stream()
                         .collect(Collectors.toMap(Blindbox::getId,
                                                   Function.identity(),
                                                   (k1, k2) -> k1));
    }

    private List<Issue> getIssues(int isType) {
        IssueExample issueExample1 = new IssueExample();
        issueExample1.createCriteria()
                     .andTypeEqualTo(1)
                     .andIstypeEqualTo(isType)
                     .andEndtimeGreaterThan(DateUtils.getDayBegin());
        issueExample1.setOrderByClause("releasetime desc");
        return issueDao.selectByExample(issueExample1);
    }

    @NotNull
    @Cacheable("getShareImage")
    public  String getShareImage() {
        Shareimg shareimg = shareimgDao.selectByPrimaryKey(1);
        return ImgEnum.img.getUrl() + shareimg.getImg();
    }

    @NotNull
    @Cacheable(value = "getBanners")
    public List<Map<String, Object>> getBanners() {
        return bannerDao.selectByExample(new BannerExample())
                        .stream()
                        .map(banner -> {
                            Map<String, Object> map = new HashMap<>();
                            map.put("id", banner.getId());
                            map.put("img", banner.getImg());
                            return map;
                        })
                        .collect(Collectors.toList());
    }

    @Override
    @DS("slave")
    public Map show() {
        Map  maps = new HashMap();

        List        list2   = new ArrayList();
        List<Issue> issues1 = getIssues(2);
        for (Issue issue : issues1) {
            Blindbox blindbox = blindboxDao.selectByPrimaryKey(issue.getCollid());
            if (blindbox != null) {
                Map map = new HashMap();
                map.put("colltype", issue.getIstype());//id
                map.put("id", issue.getId());//id
                map.put("name", blindbox.getName());//名称
                map.put("img", ImgEnum.img.getUrl() + blindbox.getImg());//图片
                map.put("limits", blindbox.getLimits());//限量
                map.put("price", blindbox.getPrice());//价格
                map.put("cimg", ImgEnum.img.getUrl() + blindbox.getCreatorimg());//创作者头像
                map.put("publisher", blindbox.getPublisher());//发行者
                if (issue.getGinsengtype() == 1) {//参与形式(1.出售2.抽签)
                    map.put("type", 0);//已开售
                    map.put("time", "已开售");
                    //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                    int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
                    if (a == 1) {//1.未开售
                        map.put("type", 1);
                        map.put("second", DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000);//1.未开售
                        map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                    } else {
                        //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                        if (DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
                            map.put("type", 2);//2.已售完
                            map.put("time", "已售完");
                        } else {
                            if (issue.getSold() == issue.getPresale()) {
                                map.put("type", 3);//2.已售完
                                map.put("time", "已售罄");
                            }
                        }
                    }
                } else if (issue.getGinsengtype() == 2) {//参与形式(1.出售2.抽签)
                    if (issue.getGinsengtype() == 1) {//参与形式(1.出售2.抽签)
                        map.put("type", 0);//已开售
                        map.put("time", "已开售");
                        //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                        int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
                        if (a == 1) {//1.未开售
                            map.put("type", 1);
                            map.put("second", DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000);//1.未开售
                            map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                        } else {
                            //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                            if (DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
                                map.put("type", 2);//2.已售完
                                map.put("time", "已结束");
                            } else {
                                if (issue.getSold() == issue.getPresale()) {
                                    map.put("type", 3);//2.已售完
                                    map.put("time", "已售罄");
                                }
                            }
                        }
                        SignupExample signupExample = new SignupExample();
                        signupExample.createCriteria().andIsidEqualTo(issue.getId()).andBegintimeGreaterThan(issue.getReleasetime());
                        List<Signup> signups = signupDao.selectByExample(signupExample);
                        SignupExample signupExample1 = new SignupExample();
                        signupExample1.createCriteria().andIsidEqualTo(issue.getId()).andBegintimeGreaterThan(issue.getReleasetime()).andTypeEqualTo(2);
                        List<Signup> signups1 = signupDao.selectByExample(signupExample1);
                        if (signups1.size() > 0) {
                            map.put("describe", "共有 " + signups.size() + " 报名， " + signups1 + " 中签");
                        } else {
                            map.put("describe", "共有 " + signups.size() + " 报名。");
                        }
                    }
                }
                list2.add(map);
            }
        }
        //maps.put("mh", list2);//数字藏品
        List list1 = new ArrayList();
        //数字藏品
        List<Issue> issues = getIssues(1);
        for (Issue issue : issues) {
            if (issue.getIstype() == 1) {//藏品
                Collection collection = collectionDao.selectByPrimaryKey(issue.getCollid());
                if (collection != null) {
                    Map map = new HashMap();
                    map.put("colltype", issue.getIstype());//id
                    map.put("id", issue.getId());//id
                    map.put("name", collection.getName());//名称
                    map.put("img", ImgEnum.img.getUrl() + collection.getImg());//图片
                    map.put("limits", collection.getLimits());//限量
                    map.put("price", collection.getPrice());//价格
                    map.put("cimg", ImgEnum.img.getUrl() + collection.getCreatorimg());//创作者头像
                    map.put("publisher", collection.getCreator());//发行者
                    if (issue.getGinsengtype() == 1) {//参与形式(1.出售2.抽签)
                        map.put("type", 0);//已开售
                        map.put("time", "已开售");
                        map.put("second", 0);//已开售
                        //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                        int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
                        if (a == 1) {//1.未开售
                            map.put("type", 1);
                            map.put("second", DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000);//1.未开售
                            map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                        } else {
                            //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                            if (DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
                                map.put("type", 2);//2.已售完
                                map.put("time", "已结束");
                            } else {
                                if (issue.getSold() == issue.getPresale()) {
                                    map.put("type", 3);//2.已售完
                                    map.put("time", "已售罄");
                                }
                            }
                        }
                    } else if (issue.getGinsengtype() == 2) {//参与形式(1.出售2.抽签)
                        if (issue.getGinsengtype() == 1) {//参与形式(1.出售2.抽签)
                            map.put("type", 0);//已开售
                            map.put("second", 0);//已开售
                            //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                            int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
                            if (a == 1) {//1.未开售
                                map.put("type", 1);
                                map.put("second", DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000);//1.未开售
                                map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                            } else {
                                //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                                if (DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
                                    map.put("type", 2);//2.已售完
                                    map.put("time", "已结束");
                                } else {
                                    if (issue.getSold() == issue.getPresale()) {
                                        map.put("type", 3);//2.已售完
                                        map.put("time", "已售罄");
                                    }
                                }
                            }
                            SignupExample signupExample = new SignupExample();
                            signupExample.createCriteria().andIsidEqualTo(issue.getId()).andBegintimeGreaterThan(issue.getReleasetime());
                            List<Signup> signups = signupDao.selectByExample(signupExample);
                            SignupExample signupExample1 = new SignupExample();
                            signupExample1.createCriteria().andIsidEqualTo(issue.getId()).andBegintimeGreaterThan(issue.getReleasetime()).andTypeEqualTo(2);
                            List<Signup> signups1 = signupDao.selectByExample(signupExample1);
                            if (signups1.size() > 0) {
                                map.put("describe", "共有 " + signups.size() + " 报名， " + signups1 + " 中签");
                            } else {
                                map.put("describe", "共有 " + signups.size() + " 报名。");
                            }
                        }
                    }
                    list1.add(map);
                } else if (issue.getIstype() == 2) {//盲盒
                    Blindbox blindbox = blindboxDao.selectByPrimaryKey(issue.getCollid());
                    if (blindbox != null) {
                        Map map = new HashMap();
                        map.put("colltype", issue.getIstype());//id
                        map.put("id", issue.getId());//id
                        map.put("name", blindbox.getName());//名称
                        map.put("img", ImgEnum.img.getUrl() + blindbox.getImg());//图片
                        map.put("limits", blindbox.getLimits());//限量
                        map.put("price", blindbox.getPrice());//价格
                        map.put("cimg", ImgEnum.img.getUrl() + blindbox.getCreatorimg());//创作者头像
                        map.put("publisher", blindbox.getPublisher());//发行者
                        if (issue.getGinsengtype() == 1) {//参与形式(1.出售2.抽签)
                            map.put("type", 0);//已开售
                            map.put("time", "已开售");
                            map.put("second", 0);//已开售
                            //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                            int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
                            if (a == 1) {//1.未开售
                                map.put("type", 1);
                                map.put("second", DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000);//1.未开售
                                map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                            } else {
                                //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                                if (DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
                                    map.put("type", 2);//2.已售完
                                    map.put("time", "已售完");
                                } else {
                                    if (issue.getSold() == issue.getPresale()) {
                                        map.put("type", 3);//2.已售完
                                        map.put("time", "已售罄");
                                    }
                                }
                            }
                        } else if (issue.getGinsengtype() == 2) {//参与形式(1.出售2.抽签)
                            if (issue.getGinsengtype() == 1) {//参与形式(1.出售2.抽签)
                                map.put("type", 0);//已开售
                                //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                                int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
                                if (a == 1) {//1.未开售
                                    map.put("type", 1);
                                    map.put("second", DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000);//1.未开售
                                    map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                                } else {
                                    //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                                    if (DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
                                        map.put("type", 2);//2.已售完
                                        map.put("time", "已结束");
                                    } else {
                                        if (issue.getSold() == issue.getPresale()) {
                                            map.put("type", 3);//2.已售完
                                            map.put("time", "已售罄");
                                        }
                                    }
                                }
                                SignupExample signupExample = new SignupExample();
                                signupExample.createCriteria().andIsidEqualTo(issue.getId()).andBegintimeGreaterThan(issue.getReleasetime());
                                List<Signup> signups = signupDao.selectByExample(signupExample);
                                SignupExample signupExample1 = new SignupExample();
                                signupExample1.createCriteria().andIsidEqualTo(issue.getId()).andBegintimeGreaterThan(issue.getReleasetime()).andTypeEqualTo(2);
                                List<Signup> signups1 = signupDao.selectByExample(signupExample1);
                                if (signups1.size() > 0) {
                                    map.put("describe", "共有 " + signups.size() + " 报名， " + signups1 + " 中签");
                                } else {
                                    map.put("describe", "共有 " + signups.size() + " 报名。");
                                }
                            }
                        }
                        list1.add(map);
                    }
                }
            }
        }
        maps.put("szcp", list1);//数字藏品

        List list333 = new ArrayList();
        CommodityExample commodityExample = new CommodityExample();
        commodityExample.createCriteria().andStateEqualTo(1);
        commodityExample.setOrderByClause("id desc");
        List<Commodity> commodityList = commodityDao.selectByExample(commodityExample);
        if (commodityList.size() > 0) {
            for (Commodity commodity : commodityList) {
                Map map = new HashMap();
                map.put("id", commodity.getId());
                map.put("name", commodity.getName());
                map.put("img", ImgEnum.img.getUrl() + commodity.getImg());
                map.put("price", commodity.getPrice());
                map.put("sold", commodity.getSold());
                //map.put("describes",commodity.getDescribes());
                list333.add(map);
            }

        }
        //MessageExample messageExample=new MessageExample();
        List<Message> messageList = messageDao.selectByExample(new MessageExample());
        if (messageList.size() > 0) {

        }


        return maps;
    }

    @DS("slave")
    @Override
    public List calendarl() {
        List list = new ArrayList();
        CalendarExample calendarExample = new CalendarExample();
        calendarExample.setOrderByClause("id desc");
        calendarExample.createCriteria();
        List<Calendar> calendars = calendarDao.selectByExample(calendarExample);
        for (Calendar calendar : calendars) {
            Map map = new HashMap();
            map.put("id", calendar.getId());//id
            map.put("name", calendar.getName());//名称
            map.put("img", ImgEnum.img.getUrl() + calendar.getImg());//图片
            map.put("limits", calendar.getLimits());//限量
            map.put("price", calendar.getPrice());//价格
            map.put("date", DateUtils.getDateToStr(calendar.getSaletime(), DateUtils.TIME_FORMAT3));//时间
            map.put("date1", DateUtils.getDateToStr(calendar.getSaletime(), DateUtils.TIME_FORMAT4));//时间
            list.add(map);
        }
        return list;
    }

    @Override
    @DS("slave")
    public List calendarls(int type, String neir) {
        List list = new ArrayList();
        CalendarExample calendarExample = new CalendarExample();
//        calendarExample.createCriteria().andSaletimeGreaterThan(DateUtils.getDayEnd()).andTypeEqualTo(type).andNameLike("%" + neir + "%");
        calendarExample.createCriteria().andSaletimeGreaterThan(DateUtils.getDayEnd()).andTypeEqualTo(type).andNameLike("%" + neir + "%");
        List<Calendar> calendars = calendarDao.selectByExample(calendarExample);
        for (Calendar calendar : calendars) {
            Map map = new HashMap();
            map.put("id", calendar.getId());//id
            map.put("name", calendar.getName());//名称
            map.put("img", ImgEnum.img.getUrl() + calendar.getImg());//图片
            map.put("limits", calendar.getLimits());//限量
            map.put("price", calendar.getPrice());//价格
            map.put("date", DateUtils.getDateToStr(calendar.getSaletime(), DateUtils.TIME_FORMAT3));//时间
            map.put("date1", DateUtils.getDateToStr(calendar.getSaletime(), DateUtils.TIME_FORMAT4));//时间
            list.add(map);
        }
        return list;
    }

    @Override
    @DS("slave")
    public Map details(int uerid, int id) {
        Users users = userDao.selectByPrimaryKey(uerid);
        Map map = new HashMap();
        Issue issue = issueDao.selectByPrimaryKey(id);
        map.put("type", 0);//已开售
        //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
        int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
        if (a == 1) {//1.未开售
            if (users.getWhitelist() > 0) {
                long hms = DateUtils.dateDiff(new Date(), issue.getReleasetime());
                int djs = Integer.parseInt(dictService.getValue("bmdtq"));
                if (hms / 1000 < djs * 60) {
                    map.put("type", 0);//1.已开售
                } else {
                    long hm = DateUtils.dateDiff(new Date(), issue.getReleasetime());
                    if (hm / 1000 > djs * 60) {
                        map.put("type", 1);//1.未开售
                        map.put("second", (DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000) - (djs * 60));//1.未开售
                        map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                    } else {
                        map.put("type", 0);//1.未开售
                    }
                }
            } else {
                map.put("type", 1);//1.未开售
                map.put("second", DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000);//1.未开售
                map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
            }
        } else {
            //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
            if (DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
                map.put("type", 2);//2.已售完
                map.put("time", "已结束");
            } else {
                if (issue.getSold() == issue.getPresale()) {
                    map.put("type", 3);//2.已售完
                    map.put("time", "已售罄");
                }
            }
        }
        if (issue.getIstype() == 1) {//藏品
            Collection collection = collectionDao.selectByPrimaryKey(issue.getCollid());
            map.put("paytype", collection.getPaytype());
            map.put("id", issue.getId());//id
            map.put("name", collection.getName());//名称
            map.put("img", ImgEnum.img.getUrl() + collection.getImg1());//图片
            map.put("imgs", ImgEnum.img.getUrl() + collection.getImg());//图片
            map.put("limits", collection.getLimits());//限量
            map.put("price", collection.getPrice());//价格
            map.put("details", collection.getDetails());//详情
            map.put("publisher", collection.getPublisher());//发行者
            map.put("creator", collection.getCreator());//创作者
            map.put("video", collection.getVideo());
            map.put("cimg", collection.getCreatorimg());//发行者
            map.put("ginsengtype", issue.getGinsengtype());
            if (issue.getGinsengtype() == 1) {//参与形式(1.出售2.抽签)
                Map map1 = new HashMap();
                map1.put("type", 0);
                map1.put("list", null);
                map1.put("ginsengtype", issue.getGinsengtype());
                map.put("jggb", map1);//发行者
            } else {
                List list = new ArrayList();
                Map map1 = new HashMap();
                SignupExample signupExample = new SignupExample();
                signupExample.createCriteria().andIsidEqualTo(issue.getId()).andBegintimeGreaterThan(DateUtils.getFrontMinute(issue.getReleasetime(), -1));
                long sig = signupDao.countByExample(signupExample);
                if (sig > 0) {
                    map1.put("type", 1);
                } else {
                    map1.put("type", 0);
                }
                signupExample = new SignupExample();
                signupExample.createCriteria().andIsidEqualTo(issue.getId()).andBegintimeGreaterThanOrEqualTo(DateUtils.getFrontMinute(issue.getReleasetime(), -1));
                signupExample.setOrderByClause("createtime desc");
                signupExample.setStartRow(1);
                signupExample.setPageSize(6);
                List<Signup> signups = signupDao.selectByExample(signupExample);
                for (Signup signup : signups) {
                    Users users1 = userDao.selectByPrimaryKey(signup.getUserid());
                    if (users1 != null) {
                        Map map2 = new HashMap();
                        map2.put("id", sig);
                        map2.put("avatar", ImgEnum.img.getUrl() + users1.getHeadPrtraits());//头像
                        map2.put("nickname", users1.getNickName());//昵称
                        map2.put("type", signup.getType());//昵称
                        map2.put("time", DateUtils.getDateToStr(signup.getCreatetime(), DateUtils.DATETIME_FORMAT));//昵称
                        list.add(map2);
                        sig--;
                    }
                }
                map1.put("list", list);

                map.put("jggb", map1);//发行者
            }
        } else if (issue.getIstype() == 2) {//盲盒
            Blindbox blindbox = blindboxDao.selectByPrimaryKey(issue.getCollid());
            map.put("paytype", blindbox.getPaytype());
            map.put("id", issue.getId());//id
            map.put("name", blindbox.getName());//名称
            map.put("img", ImgEnum.img.getUrl() + blindbox.getImg1());//图片
            map.put("imgs", ImgEnum.img.getUrl() + blindbox.getImg());//图片
            map.put("limits", blindbox.getLimits());//限量
            map.put("price", blindbox.getPrice());//价格
            map.put("details", blindbox.getDetails());//详情
            map.put("cimg", ImgEnum.img.getUrl() + blindbox.getCreatorimg());//创作者头像
            map.put("publisher", blindbox.getPublisher());//发行者
            map.put("surplus", issue.getPresale() - issue.getSold());//剩余
            map.put("ginsengtype", issue.getGinsengtype());
            cn.hutool.json.JSONArray collArr = JSONUtil.parseArray(blindbox.getProbably());
            List list1 = new ArrayList();
            for (Object object : collArr) {
                cn.hutool.json.JSONObject jsonObject = (cn.hutool.json.JSONObject) object;
                Map map1 = new HashMap();
                Collection collection = collectionDao.selectByPrimaryKey(jsonObject.getInt("collId"));
                if (collection != null) {
                    map1.put("name", collection.getName());
                    map1.put("img", ImgEnum.img.getUrl() + collection.getImg());
                    list1.add(map1);
                }
            }
            map.put("probably", list1);//剩余
            if (issue.getGinsengtype() == 1) {//参与形式(1.出售2.抽签)
                Map map1 = new HashMap();
                map1.put("type", 0);
                map1.put("list", null);

                map.put("jggb", map1);//发行者
            } else {
                List list = new ArrayList();
                Map map1 = new HashMap();
                SignupExample signupExample = new SignupExample();
                signupExample.createCriteria().andIsidEqualTo(issue.getId()).andBegintimeGreaterThan(DateUtils.getFrontMinute(issue.getReleasetime(), -1));
                long sig = signupDao.countByExample(signupExample);
                if (sig > 0) {
                    map1.put("type", 1);
                } else {
                    map1.put("type", 0);
                }
                signupExample = new SignupExample();
                signupExample.createCriteria().andIsidEqualTo(issue.getId()).andBegintimeGreaterThan(DateUtils.getFrontMinute(issue.getReleasetime(), -1));
                signupExample.setOrderByClause("createtime desc");
                signupExample.setStartRow(1);
                signupExample.setPageSize(6);
                List<Signup> signups = signupDao.selectByExample(signupExample);
                for (Signup signup : signups) {
                    Users users1 = userDao.selectByPrimaryKey(signup.getUserid());
                    Map map2 = new HashMap();
                    map2.put("id", sig);
                    map2.put("avatar", ImgEnum.img.getUrl() + users1.getHeadPrtraits());//头像
                    map2.put("nickname", users1.getNickName());//昵称
                    map2.put("type", signup.getType());//昵称
                    map2.put("time", DateUtils.getDateToStr(signup.getCreatetime(), DateUtils.DATETIME_FORMAT));//昵称
                    list.add(map2);
                    sig--;
                }
                map1.put("list", list);
                map1.put("ginsengtype", issue.getGinsengtype());
                map.put("jggb", map1);//发行者
            }
        }
        map.put("totalScore", users.getMoney() == null ? 0: users.getMoney().intValue());//总积分
        map.put("needScore", Integer.valueOf(dictService.getValue("NEEDED_SCORE")));//所需积分
        map.put("orderno", System.currentTimeMillis() + ((int) (Math.random() * 1000)) + "");//发行者
        return map;
    }

    @Override
    @DS("slave")
    public Map viewall(int userid, int id, int StartRow) {
        Map map = new HashMap();
        List list = new ArrayList();
        Issue issue = issueDao.selectByPrimaryKey(id);
        SignupExample signupExample = new SignupExample();
        signupExample.createCriteria().andIsidEqualTo(issue.getId()).andBegintimeGreaterThan(DateUtils.getFrontMinute(issue.getReleasetime(), -1));
        long sig = signupDao.countByExample(signupExample);
        map.put("count", sig);
        signupExample = new SignupExample();
        signupExample.createCriteria().andIsidEqualTo(issue.getId()).andBegintimeGreaterThan(DateUtils.getFrontMinute(issue.getReleasetime(), -1));
        signupExample.setOrderByClause("createtime desc");
        signupExample.setPageNo(StartRow);
        signupExample.setPageSize(10);
        List<Signup> signups = signupDao.selectByExample(signupExample);
        for (Signup signup : signups) {
            Users users1 = userDao.selectByPrimaryKey(signup.getUserid());
            Map map2 = new HashMap();
            map2.put("avatar", ImgEnum.img.getUrl() + users1.getHeadPrtraits());//头像
            map2.put("nickname", users1.getNickName());//昵称
            map2.put("type", signup.getType());//昵称
            map2.put("time", DateUtils.getDateToStr(signup.getCreatetime(), DateUtils.DATETIME_FORMAT));//昵称
            list.add(map2);
        }
        map.put("list", list);
        return map;
    }

    @Value("${face.appkey:8799202bdee2080669711f13eb692d46}")
    private String faceAppKey;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int Confirmorder(int userid, int id, String orderno, String faceToken) {
        String lockKey = "HomeService#Confirmorder#" + userid + "#" + id + "#" + orderno;
        log.info("lockKey is {}", lockKey);
        String lockValue = UUID.randomUUID().toString();
        final boolean isLocked = cloudRedisTemplate.getLock(lockKey, lockValue, 3);
        if (!isLocked) {
            throw new CloudException(ExceptionConstant.服务器限流);
        }

        int reid;
        Users users = userDao.selectByPrimaryKey(userid);
        //低于50积分，没有购买权限(新增下单条件)
//        BigDecimal totalScore = users.getMoney() == null ? BigDecimal.ZERO: users.getMoney();
//        if (totalScore.compareTo(new BigDecimal("50")) < 0) {
//            throw new CloudException(ExceptionConstant.积分不足);
//        }
        int neededScore = Integer.parseInt(dictService.getValue("NEEDED_SCORE"));
        BigDecimal userPoints = users.getMoney() == null ? BigDecimal.ZERO : users.getMoney();
        if (userPoints.intValue() < neededScore) {
            throw new CloudException(ExceptionConstant.积分不足);
        }

        Issue issue = issueDao.selectByPrimaryKey(id);
        if (issue.getSold() >= issue.getPresale()) {
            throw new CloudException(ExceptionConstant.库存不足);
        }

        if (issue.getType() == 0) {
            throw new CloudException(ExceptionConstant.已下架);
        }
//        if (StringUtil.getLength(orderno) < 9) {
        if (!Pattern.matches("^\\d{13}$", orderno)) {
            throw new CloudException(ExceptionConstant.编号有误订单生成失败);
        }
        final Date endTime = issue.getEndtime();
        final int  isSaleFinished     = DateUtils.compareTo(new Date(), endTime, DateUtils.DATETIME_FORMAT);
        if (-1 == isSaleFinished) {
            throw new CloudException(ExceptionConstant.活动已结束);
        }


        //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
        int bb = 0;
        //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
        int aaa = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
        if (aaa == 1) {
            long hms = DateUtils.dateDiff(new Date(), issue.getReleasetime());
            int djs = Integer.parseInt(dictService.getValue("bmdtq"));
            if (hms / 1000 < djs * 60) {
                if (users.getWhitelist() > 0) {
                    bb = 1;
                    int nowwhite = users.getWhitelist() - 1;
                    users.setWhitelist(nowwhite);
                    userDao.updateByPrimaryKeySelective(users);
                } else {
                    throw new CloudException(ExceptionConstant.违规操作即将封号);
                }
            } else {
                throw new CloudException(ExceptionConstant.违规操作即将封号);
            }
        }
        if (issue.getIstype() == 1) {//藏品
            Collection collection = collectionDao.selectByPrimaryKey(issue.getCollid());
            if (collection.getId().equals(Constants.YUANYUZHOU_COLL_ID)) {
                if (users.getTudicard().compareTo(0) > 0) {
                    users.setTudicard(users.getTudicard() - 1);
                    userDao.updateByPrimaryKeySelective(users);
                    collection.setPrice(new BigDecimal(String.valueOf(collection.getPrice().multiply(new BigDecimal(dictService.getValue("tudicard_sale"))))));
                }
            }
            if (collection.getMustcomid() != 0) {
                //
                UserGrantExample userGrantExample = new UserGrantExample();
                userGrantExample.createCriteria().andCollidEqualTo(collection.getMustcomid()).andTypeEqualTo(0);
                List<UserGrant> userGrantList = userGrantDao.selectByExample(userGrantExample);
                if (userGrantList.size() == 0) {
                    throw new CloudException(ExceptionConstant.条件不足);
                }
            }

            MyOrderExample myOrderEx = new MyOrderExample();
            myOrderEx.createCriteria().andUseridEqualTo(userid).andOrdertypeIn(ListUtil.of(0, -1));
            List<MyOrder> myOrde = myOrderDao.selectByExample(myOrderEx);
            if (myOrde.size() > 0) {
                throw new CloudException(ExceptionConstant.有未支付订单);
            }
            List<Integer> integers = new ArrayList<>();
            integers.add(-1);
            integers.add(0);
            integers.add(1);
            MyOrderExample myOrderExample = new MyOrderExample();
            myOrderExample.createCriteria().andUseridEqualTo(userid).andCollidEqualTo(issue.getCollid()).andOrdertypeIn(integers);
            List<MyOrder> myOrders = myOrderDao.selectByExample(myOrderExample);
            if (bb == 0) {
                if (myOrders.size() >= issue.getLimitcount()) {
                    throw new CloudException(ExceptionConstant.该藏品已达限购量);
                }
            }

            final int updateSuccessCnt     = issueDao.increaseSoldByPrimaryKey(id, 1);
            if (updateSuccessCnt == 0) {
                log.error("发售数量更新失败, orderno = {}", orderno);
                throw new CloudException(ExceptionConstant.库存不足);
            }
            MyOrder myOrder = new MyOrder();
            myOrder.setOrderno(orderno);
            myOrder.setUserid(userid);
            myOrder.setCollid(issue.getCollid());
            myOrder.setIssueId(id);
            myOrder.setIstype(issue.getIstype());
            myOrder.setGinsengtype(issue.getGinsengtype());
            myOrder.setPrice(collection.getPrice());
            myOrder.setCreatetime(new Date());
            myOrder.setCyid(id);
            int djs = Integer.parseInt(dictService.getValue("djs"));
            myOrder.setEndtime(DateUtils.getFrontMinute(new Date(), djs));
            myOrderDao.insertSelective(myOrder);
            reid = myOrder.getId();
        } else {
            Blindbox blindbox = blindboxDao.selectByPrimaryKey(issue.getCollid());
            MyOrderExample myOrderEx = new MyOrderExample();
            myOrderEx.createCriteria().andUseridEqualTo(userid).andCollidEqualTo(issue.getCollid()).andOrdertypeEqualTo(0);
            List<MyOrder> myOrde = myOrderDao.selectByExample(myOrderEx);
            if (myOrde.size() > 0) {
                throw new CloudException(ExceptionConstant.有未支付订单);
            }
            List<Integer> integers = new ArrayList<>();
            integers.add(-1);
            integers.add(0);
            integers.add(1);
            MyOrderExample myOrderExample = new MyOrderExample();
            myOrderExample.createCriteria().andUseridEqualTo(userid).andCollidEqualTo(issue.getCollid()).andOrdertypeIn(integers);
            List<MyOrder> myOrders = myOrderDao.selectByExample(myOrderExample);
            if (bb == 0) {
                if (myOrders.size() >= issue.getLimitcount()) {
                    throw new CloudException(ExceptionConstant.该藏品已达限购量);
                }
            }

            final int updateSuccessCnt     = issueDao.increaseSoldByPrimaryKey(id, 1);
            if (updateSuccessCnt == 0) {
                log.error("发售数量更新失败, orderno = {}", orderno);
                throw new CloudException(ExceptionConstant.库存不足);
            }
            MyOrder myOrder = new MyOrder();
            myOrder.setOrderno(orderno);
            myOrder.setUserid(userid);
            myOrder.setCollid(issue.getCollid());
            myOrder.setIssueId(id);
            myOrder.setIstype(issue.getIstype());
            myOrder.setGinsengtype(issue.getGinsengtype());
            myOrder.setPrice(blindbox.getPrice());
            myOrder.setCreatetime(new Date());
            myOrder.setCyid(id);
            int djs = Integer.valueOf(dictService.getValue("djs"));
            myOrder.setEndtime(DateUtils.getFrontMinute(new Date(), djs));
            myOrderDao.insertSelective(myOrder);
            reid = myOrder.getId();
        }

        //生成订单后，扣除10积分
        if (neededScore > 0) {
            BigDecimal decimal = new BigDecimal(neededScore).negate();
            int i = userDao.updateScoreByPrimaryKey(userid, decimal);
            taskProcessor.addTask(userid, decimal, PointsTypeEnum.BUY_COLL, users.getMoney().add(decimal));
            if (i == 0) {
                throw new CloudException(ExceptionConstant.积分扣除失败);
            }
        }

        final boolean isUnLocked = cloudRedisTemplate.releaseLock(lockKey, lockValue);
        if (!isUnLocked) {
            throw new CloudException(ExceptionConstant.服务器限流);
        }

        //redis乐观锁控制预售数量
        // cloudRedisTemplate.watchLock(BusinessConstant.ISSUE_KEY + issue.getId(), 1, true);

        //redis 控制订单超时
        cloudRedisTemplate.set("OrderExpireImpl" + BusinessConstant.ORDER_EXPIRE_KEY + reid, userid, 60 * 15);


        return reid;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map Confirmorderdetails(int userid, int id) {


        String lockKey = "Confirmorderdetails#" + userid + "#" + id;
        String lockValue = UUID.randomUUID()
                               .toString();
        final boolean isLocked = cloudRedisTemplate.getLock(lockKey, lockValue, 3);
        if (!isLocked) {
            throw new CloudException(ExceptionConstant.服务器限流);
        }


        Users users = userDao.selectByPrimaryKey(userid);

        Map map = new HashMap();
        map.put("pay", 0);//发行者
        map.put("buycard", 0);//发行者
        MyOrder myOrder = myOrderDao.selectByPrimaryKey(id);
        //比较两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
        if (DateUtils.compareTo(new Date(), myOrder.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
            if (myOrder.getOrdertype() == 1) {
                throw new CloudException(ExceptionConstant.订单已支付);
            }
            myOrder.setOrdertype(2);
            myOrderDao.updateByPrimaryKeySelective(myOrder);
            throw new CloudException(ExceptionConstant.订单已过期);
        }

        map.put("yuanyuzhouid", myOrder.getYuanyuzhouid());
        if (myOrder.getIstype() == 1) {
            Collection collection = collectionDao.selectByPrimaryKey(myOrder.getCollid());
            map.put("paytype", collection.getPaytype());
            map.put("id", myOrder.getId());
            map.put("name", collection.getName());
            map.put("img", ImgEnum.img.getUrl() + collection.getImg());
            map.put("price", myOrder.getPrice());
            map.put("orderno", myOrder.getOrderno());
            map.put("publisher", collection.getPublisher());//发行者
            map.put("createtime", DateUtils.getDateToStr(myOrder.getCreatetime(), DateUtils.DATETIME_FORMAT));
            map.put("cimg", ImgEnum.img.getUrl() + collection.getCreatorimg());//发行者
            map.put("ordertype", myOrder.getOrdertype());
            map.put("surplustime", DateUtils.dateDiff(new Date(), myOrder.getEndtime()) / 1000);


            String s = collection.getPaytype();
            String[] as = s.split(",");
            List list = new ArrayList();
            for (int i = 0; i < as.length; i++) {
                list.add(as[i]);
            }
            map.put("pay", list);


            if (collection.getBuycard() == 1) {
                if (users.getBuycard() > 0) {
                    map.put("buycard", 1);
                } else {
                    map.put("buycard", 0);
                }

            } else {
                map.put("buycard", 0);
            }

            map.put("buycardsize", collection.getBuycardsize());
            map.put("buycardmoney", collection.getPrice().subtract(collection.getPrice().multiply(collection.getBuycardsize())));
        } else {
            Blindbox collection = blindboxDao.selectByPrimaryKey(myOrder.getCollid());
            map.put("buycardsize", collection.getBuycardsize());
            map.put("pay", collection.getPaytype());

            if (collection.getBuycard() == 1) {
                if (users.getBuycard() > 0) {
                    map.put("buycard", 1);
                } else {
                    map.put("buycard", 0);
                }

            } else {
                map.put("buycard", 0);
            }
            map.put("buycardmoney", collection.getPrice().subtract(collection.getPrice().multiply(collection.getBuycardsize())));
            //map.put("buycard", collection.getBuycard());
            map.put("paytype", collection.getPaytype());
            map.put("id", myOrder.getId());
            map.put("name", collection.getName());
            map.put("img", ImgEnum.img.getUrl() + collection.getImg());
            map.put("price", myOrder.getPrice());
            map.put("orderno", myOrder.getOrderno());
            map.put("publisher", collection.getPublisher());//发行者
            map.put("createtime", DateUtils.getDateToStr(myOrder.getCreatetime(), DateUtils.DATETIME_FORMAT));
            map.put("cimg", ImgEnum.img.getUrl() + collection.getCreatorimg());//发行者
            map.put("ordertype", myOrder.getOrdertype());//发行者
            map.put("surplustime", DateUtils.dateDiff(new Date(), myOrder.getEndtime()) / 1000);
        }
        final boolean isUnLocked = cloudRedisTemplate.releaseLock(lockKey, lockValue);
        if (!isUnLocked) {
            throw new CloudException(ExceptionConstant.服务器限流);
        }

        return map;
    }

    @DS("slave")
    @Override
    public int ordertype(int id) {
        MyOrder myOrder = myOrderDao.selectByPrimaryKey(id);
        return myOrder.getOrdertype();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    @DS("master") public Map Payorder(int userid, int id, int type, String pass, int buycard, String outOrderNo) throws AlipayApiException {

        String key = "OrderExpireImpl" + BusinessConstant.ORDER_EXPIRE_KEY + id;
        //查看当前订单是否正常
        int value = cloudRedisTemplate.get(key) == null ? 0 : (int) cloudRedisTemplate.get(key);
        if (value != userid) {
            throw new CloudException(ExceptionConstant.订单不存在);
        }

        Users users = userDao.selectByPrimaryKey(userid);
        if (type == 1) {
            if (StringUtil.getLength(users.getTradePassWord()) == 0) {
                throw new CloudException(ExceptionConstant.请先设置操作密码);
            }
            if (!users.getTradePassWord().equals(MD5Util.MD5Encode(pass))) {
                throw new CloudException(ExceptionConstant.操作密码错误);
            }
        }
        Map map = new HashMap();
        MyOrder myOrder = myOrderDao.selectByPrimaryKey(id);
        if (myOrder == null) {
            throw new CloudException(ExceptionConstant.订单不存在);
        }
        if (myOrder.getOrdertype() == 2) {
            throw new CloudException(ExceptionConstant.订单已过期);
        }
        if (myOrder.getOrdertype() == 1) {
            throw new CloudException(ExceptionConstant.订单已支付);
        }
        myOrder.setBuycard(buycard);
        BigDecimal totalmoney = myOrder.getPrice();
        String ailay = "";
        //1.平台2.微信3.支付宝4.其他
        String lockKey = "payorder_" + userid;
        String lockValue = UUID.randomUUID().toString();
        boolean isLockSuccess = cloudRedisTemplate.getLock(lockKey, lockValue, 5);
        if (!isLockSuccess) {
            throw new CloudException(ExceptionConstant.频率太高请稍等);
        }
        log.info("pay-order lock successfully");
        if (type == 1) {
            payOrderByBalance(users, myOrder, totalmoney, buycard, map);
        } else if (type == 2) {
        } else if (type == 3) {//支付宝
        } else if (type == 4) {
        } else if (type == 5) {
        } else if (type == 15) {
        } else if (type == 10) { // PI
            if (buycard == 1) {
                BigDecimal buycardmoney;
                map.put("collectionid", myOrder.getCollid());
                if (myOrder.getIstype() == 1) {//藏品+出售
                    Collection collection = collectionDao.selectByPrimaryKey(myOrder.getCollid());
                    buycardmoney = collection.getPrice().multiply(collection.getBuycardsize());
                } else {
                    Blindbox collection = blindboxDao.selectByPrimaryKey(myOrder.getCollid());
                    buycardmoney = collection.getPrice().multiply(collection.getBuycardsize());
                }

                if (users.getBuycard() >= 1) {
                    totalmoney = buycardmoney;
                } else {
                    throw new CloudException(ExceptionConstant.无可用购物券);
                }
                if (totalmoney.compareTo(BigDecimal.ZERO) < 0) {
                    throw new CloudException(ExceptionConstant.不可使用购物券);
                }
            }

            piService.approve(myOrder.getId(), outOrderNo, totalmoney);
            // 更新支付报文
            myOrder.setOutOrderNo(outOrderNo);
            myOrder.setOrdertype(CALLBACK.getStatus());
        } else {
            throw new CloudException(ExceptionConstant.暂未开放);
        }
        if (buycard == 1) {
            myOrder.setBuycard(1);
        }
        myOrder.setPaytype(type);
        myOrderDao.updateByPrimaryKeySelective(myOrder);

        boolean isReleaseLockSuccess = cloudRedisTemplate.releaseLock(lockKey, lockValue);
        if (!isReleaseLockSuccess) {
            log.info("pay-order release lock failed, transaction rollback.");
            throw new CloudException(ExceptionConstant.频率太高请稍等);
        }
        return map;
    }

    //余额支付
    private void payOrderByBalance(Users users, MyOrder myOrder, BigDecimal totalmoney, int buycard,
     Map map) {
        if (users.getBalance().compareTo(totalmoney) == -1) {
            throw new CloudException(ExceptionConstant.资产不足);
        }
        myOrder.setOrdertype(1);
        myOrder.setGrants(2);
        myOrder.setSuccessTime(new Date());
        String desc;
        if (myOrder.getIstype() == 1) {//藏品
            map.put("type", 1);
            Collection collection = collectionDao.selectByPrimaryKey(myOrder.getCollid());
            desc = collection.getName();
            BigDecimal buycardmoney = collection.getPrice().multiply(collection.getBuycardsize());
            if (buycard == 1) {
                if (users.getBuycard() > 0) {
                    totalmoney = buycardmoney;
                    int nowbuycard = users.getBuycard() - 1;
                    users.setBuycard(nowbuycard);
                } else {
                    throw new CloudException(ExceptionConstant.无可用购物券);
                }
            }
            if (totalmoney.compareTo(new BigDecimal(0)) == -1) {
                throw new CloudException(ExceptionConstant.不可使用购物券);
            }

            if (myOrder.getGinsengtype() == 2) {
                Signup signup = new Signup();
                signup.setUserid(users.getUserId());
                signup.setIsid(myOrder.getCyid());
                Issue issue = issueDao.selectByPrimaryKey(myOrder.getCyid());
                signup.setBegintime(issue.getReleasetime());
                signup.setIsid(myOrder.getCyid());
                signup.setCreatetime(new Date());
                signup.setMyorderid(myOrder.getId());
                signupDao.insertSelective(signup);
                map.put("type", 0);
            } else {
                UserGrant userGrant = new UserGrant();
                userGrant.setUserid(users.getUserId());
                userGrant.setCollid(collection.getId());
                userGrant.setTruenumber("0");
                userGrant.setTradeTime(new Date());
                userGrant.setCreatetime(new Date());
                userGrant.setBuyprice(myOrder.getPrice());
                userGrantDao.insertSelective(userGrant);
            }
            DealRecord dealRecord = new DealRecord();
            dealRecord.setCollid(collection.getId());
            dealRecord.setCreatetime(new Date());
            dealRecord.setPrice(myOrder.getPrice());
            dealRecord.setNickName(users.getNickName());
            dealRecord.setHeadPrtraits(users.getHeadPrtraits());
            dealRecordDao.insertSelective(dealRecord);


            map.put("collectionid", collection.getId());
        } else {
            Blindbox collection = blindboxDao.selectByPrimaryKey(myOrder.getCollid());
            desc = collection.getName();
            BigDecimal buycardmoney = collection.getPrice().multiply(collection.getBuycardsize());
            if (buycard == 1) {
                if (users.getBuycard() > 0) {
                    totalmoney = buycardmoney;
                    int nowbuycard = users.getBuycard() - 1;
                    users.setBuycard(nowbuycard);
                } else {
                    throw new CloudException(ExceptionConstant.无可用购物券);
                }
            }
            if (totalmoney.compareTo(new BigDecimal(0)) == -1) {
                throw new CloudException(ExceptionConstant.不可使用购物券);
            }

            map.put("type", 0);
            if (myOrder.getGinsengtype() == 2) {
                Signup signup = new Signup();
                signup.setUserid(users.getUserId());
                signup.setIsid(myOrder.getCyid());
                Issue issue = issueDao.selectByPrimaryKey(myOrder.getCyid());
                signup.setBegintime(issue.getReleasetime());
                signup.setIsid(myOrder.getCyid());
                signup.setCreatetime(new Date());
                signup.setMyorderid(myOrder.getId());
                signupDao.insertSelective(signup);
            } else {
                Mybox mybox = new Mybox();
                mybox.setUserid(myOrder.getUserid());
                mybox.setNo(myOrder.getOrderno());
                mybox.setBoxid(myOrder.getCollid());
                mybox.setOrderid(myOrder.getId());
                myboxDao.insertSelective(mybox);
            }
        }
        map.put("user_grantid", myOrder.getId());
        BigDecimal over = users.getBalance().subtract(totalmoney);
        users.setBalance(over);

        balanceRecordProcessor.add(users.getUserId(), totalmoney.negate(), BalanceTypeEnum.BUY, myOrder.getId(), desc, over);
        TaskExample taskExample1122 = new TaskExample();
        taskExample1122.createCriteria().andUseridEqualTo(users.getUserId()).andStateEqualTo(2);
        List<Task> taskList1122 = taskDao.selectByExample(taskExample1122);
        if (taskList1122.size() == 0 && myOrder.getIstype() == 1) {
            BigDecimal followaccountmoney = new BigDecimal(dictService.getValue("buygoods"));
            users.setMoney(users.getMoney().add(followaccountmoney));
            taskProcessor.addTask(users.getUserId(), followaccountmoney, PointsTypeEnum.BUY_FIRST, users.getMoney());
            // todo 后续可删
            Moneyrecord moneyrecord = new Moneyrecord();
            moneyrecord.setCreattime(new Date());
            moneyrecord.setUserid(users.getUserId());
            moneyrecord.setName("购买首发藏品");
            moneyrecord.setCount(followaccountmoney);
            moneyrecordDao.insertSelective(moneyrecord);
        }
        userDao.updateByPrimaryKeySelective(users);
        if (myOrder.getIstype() == 1) {//藏品+出售
            Collection collection = collectionDao.selectByPrimaryKey(myOrder.getCollid());
            HideRecord hideRecord = new HideRecord();
            hideRecord.setUserid(users.getUserId());
            hideRecord.setImg(collection.getImg());
            hideRecord.setName(collection.getName());
            hideRecord.setPrice(collection.getPrice());
            hideRecord.setNo(myOrder.getOrderno());
            hideRecord.setCreateTime(new Date());
            if (myOrder.getGinsengtype() == 2) {
                hideRecord.setMs("参与成功");
            } else {
                hideRecord.setMs("购买成功");
            }
            hideRecord.setType(2);//0.黄的1.绿2.红
            hideRecordDao.insertSelective(hideRecord);
        } else {
            Blindbox collection = blindboxDao.selectByPrimaryKey(myOrder.getCollid());
            HideRecord hideRecord = new HideRecord();
            hideRecord.setUserid(users.getUserId());
            hideRecord.setImg(collection.getImg());
            hideRecord.setName(collection.getName());
            hideRecord.setPrice(collection.getPrice());
            hideRecord.setNo(myOrder.getOrderno());
            hideRecord.setCreateTime(new Date());
            if (myOrder.getGinsengtype() == 2) {
                hideRecord.setMs("参与成功");
            } else {
                hideRecord.setMs("购买成功");
            }
            hideRecord.setType(2);//0.黄的1.绿2.红
            hideRecordDao.insertSelective(hideRecord);
        }
        //查询上级
        if (users.getInvitationId() != null) {
            Users topusers = userDao.selectByPrimaryKey(users.getInvitationId());
            if (topusers != null) {
                BigDecimal yjfh = new BigDecimal(dictService.getValue("yjfh"));
                BigDecimal commission = myOrder.getPrice().multiply(yjfh);
                BigDecimal topmoney = commission.add(topusers.getBalance());
                topusers.setBalance(topmoney);
                userDao.updateByPrimaryKeySelective(topusers);

                balanceRecordProcessor.add(topusers.getUserId(), commission, BalanceTypeEnum.COMMISSION, myOrder.getId(), topmoney);
            }
        }

        //余额支付成功，删除订单缓存
        cloudRedisTemplate.delete("OrderExpireImpl" + BusinessConstant.ORDER_EXPIRE_KEY + myOrder.getId());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
  @DS("master")  public void cancelorder(int userid, int id) {

        String lockKey = "homeservice.cancelorder#" + userid + "#" + id;
        String lockValue = UUID.randomUUID()
                               .toString();
        final boolean isLocked = cloudRedisTemplate.getLock(lockKey, lockValue, 3);
        if (!isLocked) {
            throw new CloudException(ExceptionConstant.服务器限流);
        }

        MyOrder myOrder = myOrderDao.selectByPrimaryKey(id);

        //判断订单是否已经取消
        if (myOrder.getOrdertype() == 2) {
            throw new CloudException("订单已取消");
        }
        myOrder.setOrdertype(2);
        myOrderDao.updateByPrimaryKeySelective(myOrder);
        int issueId = myOrder.getIssueId();
        if (userid == myOrder.getUserid()) {
            if (myOrder.getIstype() == 1) {
                //藏品还未发放 删除 退次数
                Collection collections = collectionDao.selectByPrimaryKey(myOrder.getCollid());
                if (collections != null) {
                    IssueExample issueExample = new IssueExample();
                    issueExample.createCriteria().andCollidEqualTo(myOrder.getCollid()).andIdEqualTo(issueId);
                    List<Issue> issues = issueDao.selectByExample(issueExample);

                    // transactionProcessor.doTransaction(() -> {
                        if (CollectionUtils.isNotEmpty(issues)) {
                            final int updateSuccessCnt = issueDao.increaseSoldByPrimaryKey(issueId, -1);
                            if (0 == updateSuccessCnt) {
                                throw new CloudException(ExceptionConstant.库存不足);
                            }
                            CancelRecord cancelRecord = new CancelRecord();
                            cancelRecord.setUserid(userid);
                            cancelRecord.setImg(collections.getImg());
                            cancelRecord.setName(collections.getName());
                            cancelRecord.setNo(myOrder.getOrderno());
                            cancelRecord.setPrice(myOrder.getPrice());
                            cancelRecordDao.insertSelective(cancelRecord);
                        }
                    // });

                }
            } else {
                //藏品还未发放 删除 退次数
                Blindbox collection = blindboxDao.selectByPrimaryKey(myOrder.getCollid());
                if (collection != null) {
                    IssueExample issueExample = new IssueExample();
                    issueExample.createCriteria()
                                .andCollidEqualTo(myOrder.getCollid())
                                .andIdEqualTo(issueId);
                    ;
                    List<Issue> issues = issueDao.selectByExample(issueExample);
                    // transactionProcessor.doTransaction(() -> {
                        if (CollectionUtils.isNotEmpty(issues)) {
                            final int updateSuccessCnt = issueDao.increaseSoldByPrimaryKey(issueId, -1);
                            if (0 == updateSuccessCnt) {
                                throw new CloudException(ExceptionConstant.库存不足);
                            }

                            CancelRecord cancelRecord = new CancelRecord();
                            cancelRecord.setUserid(userid);
                            cancelRecord.setImg(collection.getImg());
                            cancelRecord.setName(collection.getName());
                            cancelRecord.setNo(myOrder.getOrderno());
                            cancelRecord.setPrice(myOrder.getPrice());
                            cancelRecordDao.insertSelective(cancelRecord);
                        }
                    // });

                }
            }

            //返还用户5积分
            userService.returnPoints(userid);

            final boolean isUnLocked = cloudRedisTemplate.releaseLock(lockKey, lockValue);
            if (!isUnLocked) {
                throw new CloudException(ExceptionConstant.服务器限流);
            }


            cloudRedisTemplate.delete("OrderExpireImpl" + BusinessConstant.ORDER_EXPIRE_KEY + id);
            //redis乐观锁控制预售数量
            // cloudRedisTemplate.watchLock(BusinessConstant.ISSUE_KEY + issueId, 1, false);

        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void mintnft(int collectionid, int user_grantid) {
        // String address = dictService.getValue("address");
        //String privateKey = dictService.getValue("privateKey");
        MyOrder myOrder = myOrderDao.selectByPrimaryKey(user_grantid);
        Collection collection = collectionDao.selectByPrimaryKey(collectionid);
        if (myOrder != null) {
            if (collection != null) {
                //发行成功为用户增加藏品
                UserGrant userGrant = new UserGrant();
                userGrant.setUserid(myOrder.getUserid());
                userGrant.setCollid(collectionid);
                userGrant.setTruenumber("");
                userGrant.setHashs("");
                userGrant.setBuytime(new Date());
                userGrant.setTradeTime(new Date());
                userGrant.setCreatetime(new Date());
                userGrant.setBuyprice(myOrder.getPrice());
                userGrantDao.insertSelective(userGrant);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void adduser(int uid, String address, String privateKey) {
        Users users = userDao.selectByPrimaryKey(uid);
        if (users != null) {
            users.setAddress(address);
            users.setPrivatekey(privateKey);
            userDao.updateByPrimaryKeySelective(users);
        }
    }

    @Override
    public synchronized Map hqaddress() {
        Map map = new HashMap();
        String data = HttpRequest.sendGet("http://123.56.186.63:7100/api/creatwallet", "");
        //{"address":"0x2B7c12CAb75Af6352fFA55B8902a741bF7B1711E","privateKey":"0x4910725c57db0ae24b59cea6ea988057a12697c45fa9b0c404549996b374fff0"}
        JSONObject jsonObject = JSONObject.fromObject(data);
        String address = jsonObject.get("address").toString();
        String privateKey = jsonObject.get("privateKey").toString();//a
        map.put("address", address);
        map.put("privateKey", privateKey);
        return map;
    }

    @Override
    @DS("slave") public String agreementss(int id) {
        return agreementsDao.selectByPrimaryKey(id).getContent();
    }

   @DS("slave") private List<Collection> batchGetCollections(List<Integer> collectionIds) {
        if (CollectionUtils.isEmpty(collectionIds)) {
            return Lists.newArrayList();
        }
        CollectionExample ex = new CollectionExample();
        ex.createCriteria()
          .andIdIn(collectionIds);
        return collectionDao.selectByExampleWithBLOBs(ex);
    }

    @Transactional(rollbackFor = Exception.class)
    public void upmintnfts() {
        UserGrantExample userGrantExample = new UserGrantExample();
        userGrantExample.createCriteria().andTruenumberEqualTo("0");
        userGrantExample.setOrderByClause("createtime desc");
        List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);

        final List<Integer> collIds = userGrants.stream()
                                                .map(UserGrant::getCollid)
                                                .distinct()
                                                .collect(Collectors.toList());
        final List<Collection> collections = batchGetCollections(collIds);

        final Map<Integer, Collection> id2CollectionMap = collections.stream()
                                                            .collect(Collectors.toMap(Collection::getId,
                                                                                      Function.identity(),
                                                                                      (k1, k2) -> k1));

        final List<Integer> userIds = userGrants.stream()
                                                .map(UserGrant::getUserid)
                                                .distinct()
                                                .collect(Collectors.toList());
        final Map<Integer, Users> id2UsrMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(userIds)) {
            UsersExample ex = new UsersExample();
            ex.createCriteria()
              .andUserIdIn(userIds);
            final List<Users> users = userDao.selectByExample(ex);
            final Map<Integer, Users> m = users.stream()
                                                     .collect(Collectors.toMap(Users::getUserId,
                                                                               Function.identity(),
                                                                               (k1, k2) -> k1));
            id2UsrMap.putAll(m);
        }


        final List<@Nullable UserGrant> userGrantList4Update = Lists.newArrayList();

        for (UserGrant userGrant : userGrants) {
            // Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
            final Collection collection = id2CollectionMap.get(userGrant.getCollid());
            if (collection != null) {
                //藏品NFT类别id
                String classid = collection.getContractaddress();
                //NFT名称
                String name = collection.getName();
                //操作 ID
                String opid = CodeGenerateUtils.generateOrderSn(userGrant.getId());
                // Users users = userDao.selectByPrimaryKey(userGrant.getUserid());
                final Users users = id2UsrMap.get(userGrant.getUserid());
                if (users != null && StringUtil.getLength(users.getAddress()) > 0) {
                    //NFT 接收者地址
                    String recipient = users.getAddress();
                    JSONObject   jsonObject = uploadingNFT(classid, name, opid, recipient, userGrant.getId());
                    final Object code       = jsonObject.get("code");
                    if (null != code && !Objects.equals(code, SUCESS_CODE)) {
                        continue;
                    }
                    String hash = jsonObject.get("hash") == null ? "" : jsonObject.get("hash").toString();
                    String nftid = jsonObject.get("nftid") == null ? "" : jsonObject.get("nftid").toString();
                    userGrant.setTruenumber(opid);
                    userGrant.setHashs(hash);
                    userGrant.setClassid(nftid);
                    userGrant.setTokenid(opid);
                    userGrantList4Update.add(userGrant);
                    // userGrantDao.updateByPrimaryKeySelective(userGrant);
                }
            }
        }

        // userGrantDao.updateByPrimaryKeySelective(userGrant);
        if (CollectionUtils.isNotEmpty(userGrantList4Update)) {
            userGrantDao.updateBatchByPrimaryKeySelective(userGrantList4Update);
        }
    }

    private JSONObject uploadingNFT(String classid, String name, String opid, String recipient, Integer userGrantId) {
        String reqData = "classid=" + classid + "&name=" + name + "&opid=" + opid + "&recipient=" + recipient;
        log.info("uploadingNFT reqParams = {}, userGrantId={}", reqData, userGrantId);
        String data = HttpRequest.sendPost(contractServiceBean.getIssue(), reqData);
        log.info("uploadingNFT response = {} userGrantId={}", data, userGrantId);
        return JSONObject.fromObject(data);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void upmintnft1() {
        UserGrantExample userGrantExample = new UserGrantExample();
        userGrantExample.createCriteria()
                .andHashsEqualTo("")
                .andTokenidNotEqualTo("");
        userGrantExample.setOrderByClause("createtime desc");
        List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);
        log.info("userGrants.size {}", userGrants.size());

        final List<Integer> collIds = userGrants.stream()
                                                .map(UserGrant::getCollid)
                                                .distinct()
                                                .collect(Collectors.toList());
        final List<Collection> collections = batchGetCollections(collIds);

        final Map<Integer, Collection> id2CollectionMap = collections.stream()
                                                                     .collect(Collectors.toMap(Collection::getId,
                                                                                               Function.identity(),
                                                                                               (k1, k2) -> k1));

        final List<Integer> userIds = userGrants.stream()
                                                .map(UserGrant::getUserid)
                                                .distinct()
                                                .collect(Collectors.toList());
        final Map<Integer, Users> id2UsrMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(userIds)) {
            UsersExample ex = new UsersExample();
            ex.createCriteria()
              .andUserIdIn(userIds);
            final List<Users> users = userDao.selectByExample(ex);
            final Map<Integer, Users> m = users.stream()
                                               .collect(Collectors.toMap(Users::getUserId,
                                                                         Function.identity(),
                                                                         (k1, k2) -> k1));
            id2UsrMap.putAll(m);
        }

        final List<@Nullable UserGrant> userGrants4Update = Lists.newArrayList();
        for (UserGrant userGrant : userGrants) {
            //操作 ID
            String opid = userGrant.getTokenid();
            final Users users = id2UsrMap.get(userGrant.getUserid());
            if (users != null && StringUtil.getLength(users.getAddress()) > 0 && opid != null && !opid.equals("")) {
                //NFT 接收者地址
                JSONObject jsonObject = queryLinkUp(opid);
                String hash = jsonObject.get("hash") == null ? "" : jsonObject.get("hash").toString();
                String nftid = jsonObject.get("nftid") == null ? "" : jsonObject.get("nftid").toString();
                if (hash != null && nftid != null) {
                    //获取藏品信息
                    final Collection collection = id2CollectionMap.get(userGrant.getCollid());
                    if (collection != null) {
                        UserGrantExample userGrantExample1 = new UserGrantExample();
                        userGrantExample1.createCriteria().andIdLessThan(userGrant.getId()).andCollidEqualTo(collection.getId());
                        long userGrantCount = userGrantDao.countByExample(userGrantExample1) + 1;
                        userGrant.setTruenumber(collection.getNosetup() + "/#" + userGrantCount);
                    }
                    userGrant.setHashs(hash);
                    userGrant.setClassid(nftid);
                    userGrants4Update.add(userGrant);
                }
            }
        }
        if (CollectionUtils.isNotEmpty(userGrants4Update)) {
            userGrantDao.updateBatchByPrimaryKeySelective(userGrants4Update);
        }

    }

    /**
     * 根据交易任务ID查询已经交易的链上信息,补填本地
     * @param opid
     * @return
     */
    private JSONObject queryLinkUp(String opid) {
        String reqData = "taskId=" + opid;
        log.info("queryLinkUp params = {}", reqData);
        String data = HttpRequest.sendPost(contractServiceBean.getUpLinkQuery(), reqData);
        log.info("queryLinkUp taskId={} result = {}", opid, data);
        return JSONObject.fromObject(data);
    }

    @DS("slave")
    @Override
    public Map sign(int logUserPid) {
        Map map = new HashMap();
        //查询连续签到的次数
        Date yestady = DateUtils.getBeginDayOfYesterday();
        Date todayinfo = DateUtils.getDayBegin();
        //查询又没大于昨天的签到
        TaskExample taskExample = new TaskExample();
        taskExample.setOrderByClause("id desc");
        taskExample.createCriteria().andCreattimeGreaterThanOrEqualTo(yestady).andUseridEqualTo(logUserPid)
                .andStateEqualTo(PointsTypeEnum.SIGN.getType());
        List<Task> taskList = taskDao.selectByExample(taskExample);

        //每日签到奖励
        final String daySignPrice = StringUtils.defaultString(dictService.getValue("daySignPrice"), "2");
        if (taskList.size() > 0) {
            Task task = taskList.get(0);
            int nowday = task.getDay();
            int maxday = Integer.parseInt(dictService.getValue("maxmoney"));
            int today = nowday + 1;
            if (today >= maxday) {
                map.put("continue", nowday);
                map.put("award", daySignPrice);

            } else {
                map.put("continue", nowday);
                map.put("award", daySignPrice);
            }
        } else {
            map.put("continue", 0);
            map.put("award", daySignPrice);
        }
        TaskExample taskExample777 = new TaskExample();
        taskExample777.createCriteria().andCreattimeGreaterThanOrEqualTo(todayinfo).andUseridEqualTo(logUserPid)
                .andStateEqualTo(PointsTypeEnum.SIGN.getType());
        List<Task> taskList777 = taskDao.selectByExample(taskExample777);
        if (taskList777.size() > 0) {
            map.put("todaysign", 1);
        } else {
            map.put("todaysign", 0);
        }

        //
        TaskExample taskExample11 = new TaskExample();
        taskExample11.createCriteria().andCreattimeGreaterThanOrEqualTo(todayinfo).andUseridEqualTo(logUserPid);
        List<Task> taskList11 = taskDao.selectByExample(taskExample11);
        BigDecimal todaymoney = new BigDecimal(0);
        if (taskList11.size() > 0) {
            for (Task task : taskList11) {
                todaymoney = todaymoney.add(task.getPrice());
            }
        }
        map.put("todaymoney", todaymoney);

        String invitefriend = dictService.getValue("invitefriend");
        map.put("invitefriend", invitefriend);
        //每日邀请好友奖励元气丸上线
        String maxInvitePerDay = dictService.getValue("maxInvitePerDay");
        map.put("maxInvitePerDay", maxInvitePerDay);
        String followaccount = dictService.getValue("followaccount");
        map.put("followaccount", followaccount);
        TaskExample taskExample112233 = new TaskExample();
        taskExample112233.createCriteria().andUseridEqualTo(logUserPid).andStateEqualTo(1);
        List<Task> taskList112233 = taskDao.selectByExample(taskExample112233);
        if (taskList112233.size() > 0) {
            map.put("followaccounttype", 1);
        } else {
            map.put("followaccounttype", 0);
        }

        String buygoods = dictService.getValue("buygoods");
        map.put("buygoods", buygoods);
        String register = dictService.getValue("register");
        map.put("register", register);
        TaskExample taskExample1122 = new TaskExample();
        taskExample1122.createCriteria().andUseridEqualTo(logUserPid).andStateEqualTo(3);
        List<Task> taskList1122 = taskDao.selectByExample(taskExample1122);
        if (taskList1122.size() > 0) {
            map.put("registertype", 1);
        } else {
            map.put("registertype", 0);
        }
        Users users = userDao.selectByPrimaryKey(logUserPid);
        map.put("money", users.getMoney());
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void followAccount(int userId, String followNumber) {

        // 查询是否已关注公众号并且加互斥锁
//        tryLockTask(userId);
        String lockKey = "followAccount" + userId;
        String lockValue = UUID.randomUUID().toString();
        boolean isLocked = cloudRedisTemplate.getLock(lockKey, lockValue, 3);
        if (!isLocked) {
            log.error("followAccount lock failed");
            throw new CloudException(ExceptionConstant.频率太高请稍等);
        }
        TaskExample ex = new TaskExample();
        ex.createCriteria().andUseridEqualTo(userId).andStateEqualTo(1);
        List<Task> tasks = taskDao.selectByExample(ex);
        if (CollectionUtils.isNotEmpty(tasks)) {
            throw new CloudException(ExceptionConstant.此任务已做);
        }


        BigDecimal followAccountMoney = new BigDecimal(dictService.getValue("followAccount"));
        String     followNumberConfig = dictService.getValue("follownumber");
        if (!followNumberConfig.equals(followNumber)) {
            throw new CloudException(ExceptionConstant.关注码错误);
        }

        Users user = userDao.selectByPrimaryKey(userId);
        userDao.addMoneySafely(followAccountMoney, userId, user.getMoney());
        taskProcessor.addTask(userId, followAccountMoney, PointsTypeEnum.FOLLOW, user.getMoney().add(followAccountMoney));

        boolean isUnLocked = cloudRedisTemplate.releaseLock(lockKey, lockValue);
        if (!isUnLocked) {
            log.info("followAccount failed, transaction rollback");
            throw new CloudException(ExceptionConstant.不能重复关注);
        }
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void daySign(int logUserPid) {
       //每日签到增加1积分
        Date today = new Date();
        BigDecimal addCredits = new BigDecimal(StringUtils.defaultString(dictService.getValue("daySignPrice"), "2"));//获取积分配置
        final String lockKey = "daySign" + logUserPid;
        final String lockValue = UUID.randomUUID()
                                     .toString();
        final boolean isLocked = cloudRedisTemplate.getLock(lockKey, lockValue, 3);
        if (!isLocked) {
            log.info("daySign lock failed");
            throw new CloudException(ExceptionConstant.不能重复签到);
        }
        val tasks = taskDao.getTodaySignRecords(logUserPid, today, PointsTypeEnum.SIGN.getType());//锁定当前查询记录
        if (CollectionUtils.isNotEmpty(tasks)) {
            throw new CloudException(ExceptionConstant.今日已签到);
        }
        //
        BigDecimal currentPoints = addUserMoney(logUserPid, addCredits);

        addMoneyRec(logUserPid, today, addCredits);

        saveTodaySignTask(logUserPid, today, addCredits, currentPoints);

        final boolean isUnLocked = cloudRedisTemplate.releaseLock(lockKey, lockValue);
        if (!isUnLocked) {
            log.info("daySign unlock failed");
            throw new CloudException(ExceptionConstant.不能重复签到);
        }
        log.info("用户积分添加成功, uid {} 添加 {} 积分", logUserPid, addCredits.doubleValue());
    }

    private BigDecimal addUserMoney(int logUserPid, BigDecimal addPrize) {
        Users user = userDao.selectByPrimaryKey(logUserPid);
        //添加用户积分
        userDao.addMoneySafely(addPrize, logUserPid, user.getMoney());
        return user.getMoney().add(addPrize);
    }

    private void saveTodaySignTask(int logUserPid, Date today, BigDecimal addPrize, BigDecimal currentPoints) {
        int days = 1;
        Date yesterday = DateUtils.getBeginDayOfYesterday();
        val signTasks = getYesterdayTask(logUserPid, yesterday);
        if (CollectionUtils.isNotEmpty(signTasks)) {
            val lastDayTask = signTasks.get(0);
            days = lastDayTask.getDay() == null ? 1 : lastDayTask.getDay() + 1;
        }
        taskProcessor.addTask(logUserPid, addPrize, PointsTypeEnum.SIGN, days, null, null, currentPoints);
    }

    private void addMoneyRec(int logUserPid, Date today, BigDecimal addPrize) {
        Moneyrecord moneyrecord = new Moneyrecord();
        moneyrecord.setCreattime(today);
        moneyrecord.setUserid(logUserPid);
        moneyrecord.setName("每日签到");
        moneyrecord.setCount(addPrize);
        moneyrecordDao.insertSelective(moneyrecord);
        log.info("每日签到资金流水添加成功");
    }

    @DS("slave")
    private List<Task> getYesterdayTask(int logUserPid, Date date) {

        TaskExample taskExample = new TaskExample();
        taskExample.createCriteria()
                   .andCreattimeGreaterThanOrEqualTo(date)
                   .andCreattimeLessThan(DateUtils.getDayBegin())
                   .andUseridEqualTo(logUserPid)
                   .andStateEqualTo(PointsTypeEnum.SIGN.getType());
        return taskDao.selectByExample(taskExample);
    }

    @Override
    @DS("slave")
    public Map nftgood(Integer logUserPid) {
        Users users = userDao.selectByPrimaryKey(logUserPid);
        Map maps = new HashMap();
        List list1 = new ArrayList();
        //数字藏品
        List<Issue> issues = getIssues(1);
        for (Issue issue : issues) {
            Collection collection = collectionDao.selectByPrimaryKey(issue.getCollid());
            if (collection != null && collection.getType() == 1) {
                Map map = new HashMap();
                map.put("colltype", issue.getIstype());//id
                map.put("id", issue.getId());//id
                map.put("name", collection.getName());//名称
                map.put("img", ImgEnum.img.getUrl() + collection.getImg());//图片
                map.put("limits", collection.getLimits());//限量
                map.put("price", collection.getPrice());//价格
                map.put("cimg", ImgEnum.img.getUrl() + collection.getCreatorimg());//创作者头像
                map.put("publisher", collection.getCreator());//发行者
                if (issue.getGinsengtype() == 1) {//参与形式(1.出售2.抽签)
                    map.put("type", 0);//已开售
                    map.put("time", "已开售");
                    //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                    int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
                    if (a == 1) {//1.未开售
                        if (users.getWhitelist() == 1) {
                            long hms = DateUtils.dateDiff(new Date(), issue.getReleasetime());
                            int djs = Integer.parseInt(dictService.getValue("bmdtq"));
                            if (hms / 1000 < djs * 60) {
                                map.put("type", 0);//1.已开售
                            } else {
                                long hm = DateUtils.dateDiff(new Date(), issue.getReleasetime());
                                if (hm / 1000 > djs * 60) {
                                    map.put("type", 1);//1.未开售
                                    map.put("second", DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000);//1.未开售
                                    map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                                } else {
                                    map.put("type", 0);//1.未开售
                                }
                            }
                        } else {
                            map.put("type", 1);//1.未开售
                            map.put("second", DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000);//1.未开售
                            map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                        }
                    } else {
                        //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                        if (DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
                            map.put("type", 2);//2.已售完
                            map.put("time", "已结束");
                        } else {
                            if (issue.getSold() == issue.getPresale()) {
                                map.put("type", 3);//2.已售完
                                map.put("time", "已售罄");
                            }
                        }
                    }
                } else if (issue.getGinsengtype() == 2) {//参与形式(1.出售2.抽签)
                    map.put("type", 0);//已开售
                    map.put("time", "已开售");
                    //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                    int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
                    if (a == 1) {//1.未开售
                        if (users.getWhitelist() == 1) {
                            long hms = DateUtils.dateDiff(new Date(), issue.getReleasetime());
                            int djs = Integer.parseInt(dictService.getValue("bmdtq"));
                            if (hms / 1000 < djs * 60) {
                                map.put("type", 0);//1.已开售
                            } else {
                                long hm = DateUtils.dateDiff(new Date(), issue.getReleasetime());
                                if (hm / 1000 > djs * 60) {
                                    map.put("type", 1);//1.未开售
                                    map.put("second", DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000);//1.未开售
                                    map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                                } else {
                                    map.put("type", 0);//1.未开售
                                }
                            }
                        } else {
                            map.put("type", 1);//1.未开售
                            map.put("second", DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000);//1.未开售
                            map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                        }
                    } else {
                        //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                        if (DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
                            map.put("type", 2);//2.已售完
                            map.put("time", "已结束");
                        } else {
                            if (issue.getSold() == issue.getPresale()) {
                                map.put("type", 3);//2.已售完
                                map.put("time", "已售罄");
                            }
                        }
                    }
                    SignupExample signupExample = new SignupExample();
                    signupExample.createCriteria().andIsidEqualTo(issue.getId()).andBegintimeGreaterThan(issue.getReleasetime());
                    List<Signup> signups = signupDao.selectByExample(signupExample);
                    SignupExample signupExample1 = new SignupExample();
                    signupExample1.createCriteria().andIsidEqualTo(issue.getId()).andBegintimeGreaterThan(issue.getReleasetime()).andTypeEqualTo(2);
                    List<Signup> signups1 = signupDao.selectByExample(signupExample1);
                    if (signups1.size() > 0) {
                        map.put("describe", "共有 " + signups.size() + " 报名， " + signups1 + " 中签");
                    } else {
                        map.put("describe", "共有 " + signups.size() + " 报名。");
                    }
                }
                list1.add(map);
            }
        }
        maps.put("szcp", list1);//数字藏品
        return maps;
    }

    @Override
    @DS("slave")
    public Map creatergood(Integer logUserPid) {
        Users users = userDao.selectByPrimaryKey(logUserPid);
        Map maps = new HashMap();
        List list1 = new ArrayList();
        //数字藏品
        List<Issue> issues = getIssues(1);
        for (Issue issue : issues) {
            Collection collection = collectionDao.selectByPrimaryKey(issue.getCollid());
            if (collection != null && collection.getType() == 2) {
                Map map = new HashMap();
                map.put("colltype", issue.getIstype());//id
                map.put("id", issue.getId());//id
                map.put("name", collection.getName());//名称
                map.put("img", ImgEnum.img.getUrl() + collection.getImg());//图片
                map.put("limits", collection.getLimits());//限量
                map.put("price", collection.getPrice());//价格
                map.put("cimg", ImgEnum.img.getUrl() + collection.getCreatorimg());//创作者头像
                map.put("publisher", collection.getCreator());//发行者
                if (issue.getGinsengtype() == 1) {//参与形式(1.出售2.抽签)
                    map.put("type", 0);//已开售
                    map.put("time", "已开售");
                    //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                    int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
                    if (a == 1) {//1.未开售
                        if (users.getWhitelist() == 1) {
                            long hms = DateUtils.dateDiff(new Date(), issue.getReleasetime());
                            int djs = Integer.parseInt(dictService.getValue("bmdtq"));
                            if (hms / 1000 < djs * 60) {
                                map.put("type", 0);//1.已开售
                            } else {
                                long hm = DateUtils.dateDiff(new Date(), issue.getReleasetime());
                                if (hm / 1000 > djs * 60) {
                                    map.put("type", 1);//1.未开售
                                    map.put("second", DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000);//1.未开售
                                    map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                                } else {
                                    map.put("type", 0);//1.未开售
                                }
                            }
                        } else {
                            map.put("type", 1);//1.未开售
                            map.put("second", DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000);//1.未开售
                            map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                        }
                    } else {
                        //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                        if (DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
                            map.put("type", 2);//2.已售完
                            map.put("time", "已结束");
                        } else {
                            if (issue.getSold() == issue.getPresale()) {
                                map.put("type", 3);//2.已售完
                                map.put("time", "已售罄");
                            }
                        }
                    }
                } else if (issue.getGinsengtype() == 2) {//参与形式(1.出售2.抽签)
                    map.put("type", 0);//已开售
                    map.put("time", "已开售");
                    //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                    int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
                    if (a == 1) {//1.未开售
                        if (users.getWhitelist() == 1) {
                            long hms = DateUtils.dateDiff(new Date(), issue.getReleasetime());
                            int djs = Integer.parseInt(dictService.getValue("bmdtq"));
                            if (hms / 1000 < djs * 60) {
                                map.put("type", 0);//1.已开售
                            } else {
                                long hm = DateUtils.dateDiff(new Date(), issue.getReleasetime());
                                if (hm / 1000 > djs * 60) {
                                    map.put("type", 1);//1.未开售
                                    map.put("second", DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000);//1.未开售
                                    map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                                } else {
                                    map.put("type", 0);//1.未开售
                                }
                            }
                        } else {
                            map.put("type", 1);//1.未开售
                            map.put("second", DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000);//1.未开售
                            map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                        }
                    } else {
                        //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                        if (DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
                            map.put("type", 2);//2.已售完
                            map.put("time", "已结束");
                        } else {
                            if (issue.getSold() == issue.getPresale()) {
                                map.put("type", 3);//2.已售完
                                map.put("time", "已售罄");
                            }
                        }
                    }
                    SignupExample signupExample = new SignupExample();
                    signupExample.createCriteria().andIsidEqualTo(issue.getId()).andBegintimeGreaterThan(issue.getReleasetime());
                    List<Signup> signups = signupDao.selectByExample(signupExample);
                    SignupExample signupExample1 = new SignupExample();
                    signupExample1.createCriteria().andIsidEqualTo(issue.getId()).andBegintimeGreaterThan(issue.getReleasetime()).andTypeEqualTo(2);
                    List<Signup> signups1 = signupDao.selectByExample(signupExample1);
                    if (signups1.size() > 0) {
                        map.put("describe", "共有 " + signups.size() + " 报名， " + signups1 + " 中签");
                    } else {
                        map.put("describe", "共有 " + signups.size() + " 报名。");
                    }
                }
                list1.add(map);
            }
        }
        maps.put("szcp", list1);//数字藏品
        return maps;
    }

    @Override
    @DS("slave")
    public List moneylist(int logUserPid) {
        List list = new ArrayList();
        TaskExample example = new TaskExample();
        example.setOrderByClause("creattime desc");
        example.createCriteria().andUseridEqualTo(logUserPid)
                .andCreattimeGreaterThan(DateUtils.getNextDay(new Date(), -180));
        List<Task> taskList = taskDao.selectByExample(example);
        for (Task task : taskList) {
            Map map = new HashMap();
            int price = task.getPrice().intValue();
            map.put("id", task.getId());
            map.put("count", price);
            map.put("current", task.getCurrentPoints());
            map.put("time", DateUtils.getDateToStr(task.getCreattime(), "yyyy-MM-dd HH:mm:ss"));
            String desc = PointsTypeEnum.getDescByType(task.getState());
            if (Objects.equals(PointsTypeEnum.HOLD.getType(), task.getState())) {
                Integer grantId = Integer.valueOf(task.getOtherId());
                UserGrant userGrant = userGrantDao.selectByPrimaryKey(grantId);
                if (userGrant != null) {
                    Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
                    if (collection != null) {
                        String sb = desc + "-" + collection.getName() + "(" + userGrant.getTruenumber() + ")";
                        map.put("name", sb);
                    }
                }
            } else if (Objects.equals(PointsTypeEnum.SHOP.getType(), task.getState())) {
                map.put("name", desc + (price > 0 ? "-奖励" : "-订单取消"));
            } else if (Objects.equals(PointsTypeEnum.PRODUCT.getType(), task.getState())) {
                map.put("name", desc + (price > 0 ? "-订单取消" : ""));
            } else if (Objects.equals(PointsTypeEnum.BUY_COLL.getType(), task.getState())) {
                map.put("name", desc + (price > 0 ? "-订单取消" : ""));
            } else {
                map.put("name", desc);
            }
            list.add(map);
        }
        return list;
    }

    @Override
    @DS("slave")
    public List balancelist(int logUserPid) {
        BalanceRecordExample balanceRecordExample = new BalanceRecordExample();
        balanceRecordExample.setOrderByClause("id desc");
        balanceRecordExample.createCriteria().andUseridEqualTo(logUserPid)
                .andCreatetimeGreaterThan(DateUtils.getNextDay(new Date(), -180));//展示半年内数据
        List<BalanceRecord> balanceRecordList = balanceRecordDao.selectByExample(balanceRecordExample);
        List list = new ArrayList();
        for (BalanceRecord balanceRecord : balanceRecordList) {
            Map map = new HashMap();
            map.put("id", balanceRecord.getId());
            String name = StrUtil.isBlank(balanceRecord.getName()) ? "" : ("-" + balanceRecord.getName());
            map.put("name", BalanceTypeEnum.getDescByType(balanceRecord.getType()) + name);
            map.put("count", balanceRecord.getCount().toString());
            map.put("current", balanceRecord.getCurrentBalance() == null ? null : balanceRecord.getCurrentBalance().toString());
            map.put("time", DateUtils.getDateToStr(balanceRecord.getCreatetime(), "yyyy-MM-dd HH:mm:ss"));
            list.add(map);
        }
        return list;
    }

    @Override
    @DS("slave")
    public Map version() {
        Map map = new HashMap();
        map.put("version", dictService.getValue("version"));
        map.put("link", dictService.getValue("link"));
        map.put("appdownurl", dictService.getValue("appdownurl"));
        return map;
    }

    @Override
    @DS("slave")
    public List ranklist() {
        //List list=new ArrayList();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        InvitelistExample invitelistExample = new InvitelistExample();
        List<Invitelist> invitelistList = invitelistDao.selectByExample(invitelistExample);
        if (invitelistList.size() > 0) {
            for (Invitelist invitelist : invitelistList) {
                Users users = userDao.selectByPrimaryKey(invitelist.getUid());
                int total = invitelist.getInvitennum() + invitelist.getWritenum();
                // Map map=new HashMap();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("img", ImgEnum.img.getUrl() + users.getHeadPrtraits());
                map.put("name", users.getNickName());
                map.put("phone", phoneMask(users.getPhoneNumber()));
                map.put("number", total);
                list.add(map);
            }
        }
        if (list.size() > 0) {
            Collections.sort(list, (map1, map2) -> {
                Integer value1 = (Integer) map1.get("number");
                Integer value2 = (Integer) map2.get("number");
                return value2.compareTo(value1);
            });
        }
        return list;
    }

    @Override
    public List<BlindBoxVO> blindbox(int logUserPid) {
        List<BlindBoxVO> resultList = new ArrayList<>();
        //数字藏品
        List<Issue> szcpIssues = getIssues(2);
        final Map<Integer, Blindbox> id2BlindBoxMap = getid2BlindBoxMap(szcpIssues);
        for (Issue issue : szcpIssues) {
            final Blindbox blindbox = id2BlindBoxMap.get(issue.getCollid());
            if (blindbox == null) {continue;}

            BlindBoxVO build = BlindBoxVO.builder()
                    .id(issue.getId())
                    .name(blindbox.getName())
                    .imgUrl(blindbox.getImg())
                    .price(blindbox.getPrice())
                    .limit(issue.getPresale())
                    .desc(blindbox.getDetails())
                    .payType(blindbox.getPaytype())
                    .build();

            resultList.add(build);
        }
        return resultList;
    }

    private void processTypeCommon(Users users, int djs, Issue issue, Map<String, Object> map, String sellType) {
        //参与形式(1.出售2.抽签)
        map.put("type", 0);//已开售
        //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
        int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
        if (a == 1) {//1.未开售
            if (users.getWhitelist() == 1) {
                long hms = DateUtils.dateDiff(new Date(), issue.getReleasetime());
                if (hms / 1000 < djs * 60L) {
                    map.put("type", 0);//1.已开售
                } else {
                    long hm = DateUtils.dateDiff(new Date(), issue.getReleasetime());
                    if (hm / 1000 > djs * 60L) {
                        map.put("type", 1);//1.未开售
                        map.put("second", DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000);//1.未开售
                        map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(),
DateUtils.TIME_FORMAT1) + " 开售");
                    } else {
                        map.put("type", 0);//1.未开售
                    }
                }
            } else {
                map.put("type", 1);//1.未开售
                map.put("second", DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000);//1.未开售
                map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " " +
                 "开售");
            }
        } else {
            //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
            if (DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
                map.put("type", 2);//2.已售完
                map.put("time", sellType);
            } else {
                if (issue.getSold() == issue.getPresale()) {
                    map.put("type", 3);//2.已售完
                    map.put("time", "已售罄");
                }
            }
        }
    }

    @Override
    @DS("slave")
    public List classification() {
        List list = new ArrayList();
        ClassificationExample classificationExample = new ClassificationExample();
        classificationExample.createCriteria().andTypeEqualTo(1);
        classificationExample.setOrderByClause("sort desc");
        List<Classification> classificationList = classificationDao.selectByExample(classificationExample);
        if (classificationList.size() > 0) {
            for (Classification classification : classificationList) {
                Map map = new HashMap();
                map.put("id", classification.getId());
                map.put("img", ImgEnum.img.getUrl() + classification.getImg());
                map.put("name", classification.getName());
                list.add(map);
            }
        }

        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void exchange(int logUserPid, int number) {
        BigDecimal jfgwq = new BigDecimal(dictService.getValue("jfgwq"));
        BigDecimal totalMoney = jfgwq.multiply(new BigDecimal(number));
        if (totalMoney.compareTo(BigDecimal.ZERO) < 1) {
            throw new CloudException(ExceptionConstant.金额不对);
        }
        val lockKey = "exchange#" + logUserPid;
        val lockValue = UUID.randomUUID().toString();
        final boolean isLocked = cloudRedisTemplate.getLock(lockKey, lockValue, 3);
        if (!isLocked) {
            throw new CloudException(ExceptionConstant.频率太高请稍等);
        }
        Users users = userDao.getUserById(logUserPid);
        BigDecimal money = users.getMoney();
        money = ObjectUtils.defaultIfNull(money, BigDecimal.ZERO);
        if (money
                 .compareTo(totalMoney) < 0) {
            throw new CloudException(ExceptionConstant.余额不足);
        }
        BigDecimal endmoney = money.subtract(totalMoney);
        int buycard = users.getBuycard() + number;
        users.setMoney(endmoney);
        users.setBuycard(buycard);
        userDao.updateByPrimaryKeySelective(users);
        taskProcessor.addTask(logUserPid, totalMoney.negate(), PointsTypeEnum.EXCHANGE_TICKET, endmoney);
        final boolean isUnLocked = cloudRedisTemplate.releaseLock(lockKey, lockValue);
        if (!isUnLocked) {
            throw new CloudException(ExceptionConstant.频率太高请稍等);
        }
    }

    @Override
    public void test() {
        List list2 = new ArrayList();
        //数字藏品
        List<Issue> issues1 = getIssues(2);
        for (Issue issue : issues1) {
            Blindbox blindbox = blindboxDao.selectByPrimaryKey(issue.getCollid());
            if (blindbox != null) {
                Map map = new HashMap();
                map.put("colltype", issue.getIstype());//id
                map.put("id", issue.getId());//id
                map.put("name", blindbox.getName());//名称
                map.put("img", ImgEnum.img.getUrl() + blindbox.getImg());//图片
                map.put("limits", blindbox.getLimits());//限量
                map.put("price", blindbox.getPrice());//价格
                map.put("cimg", ImgEnum.img.getUrl() + blindbox.getCreatorimg());//创作者头像
                map.put("publisher", blindbox.getPublisher());//发行者
                map.put("ginsengtype", issue.getGinsengtype());
                if (issue.getGinsengtype() == 1) {//参与形式(1.出售2.抽签)
                    map.put("type", 0);//已开售
                    //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                    int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
                    if (a == 1) {//1.未开售
                        map.put("type", 1);//1.未开售
                        map.put("second", DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000);//1.未开售
                        map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                    } else {
                        //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                        if (DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
                            map.put("type", 2);//2.已售完
                            map.put("time", "已售完");
                        } else {
                            if (issue.getSold() >= issue.getPresale()) {
                                map.put("type", 3);//2.已售完
                                map.put("time", "已售罄");
                            }
                        }
                    }
                } else if (issue.getGinsengtype() == 2) {//参与形式(1.出售2.抽签)
                    map.put("type", 0);//已开售
                    //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                    int a = DateUtils.compareTo(new Date(), issue.getReleasetime(), DateUtils.DATETIME_FORMAT);
                    if (a == 1) {//1.未开售
                        map.put("type", 1);//1.未开售
                        map.put("second", DateUtils.dateDiff(new Date(), issue.getReleasetime()) / 1000);//1.未开售
                        map.put("time", "敬请期待 " + DateUtils.getDateToStr(issue.getReleasetime(), DateUtils.TIME_FORMAT1) + " 开售");
                    } else {
                        //两个时间大小，前者大 = -1， 相等 =0，后者大 = 1
                        if (DateUtils.compareTo(new Date(), issue.getEndtime(), DateUtils.DATETIME_FORMAT) == -1) {
                            map.put("type", 2);//2.已售完
                            map.put("time", "已结束");
                        } else {
                            if (issue.getSold() >= issue.getPresale()) {
                                map.put("type", 3);//2.已售完
                                map.put("time", "已售罄");
                            }
                        }
                    }
                    SignupExample signupExample = new SignupExample();
                    signupExample.createCriteria().andIsidEqualTo(issue.getId()).andBegintimeGreaterThan(issue.getReleasetime());
                    List<Signup> signups = signupDao.selectByExample(signupExample);
                    SignupExample signupExample1 = new SignupExample();
                    signupExample1.createCriteria().andIsidEqualTo(issue.getId()).andBegintimeGreaterThan(issue.getReleasetime()).andTypeEqualTo(2);
                    List<Signup> signups1 = signupDao.selectByExample(signupExample1);
                    if (signups1.size() > 0) {
                        map.put("describe", "共有 " + signups.size() + " 报名， " + signups1 + " 中签");
                    } else {
                        map.put("describe", "共有 " + signups.size() + " 报名。");
                    }
                }
                list2.add(map);
            }
        }
    }

    @Override
    @DS("slave")
    public Map yuanyuzhou(int userid) {
        Map rmap = new HashMap();
        rmap.put("login_uid", userid);
        List<Yuanyuzhou> yuanyuzhouList = getYuanyuzhouList();
        rmap.put("list", yuanyuzhouList);

        rmap.put("collection", collectionDao.selectByPrimaryKey(Constants.YUANYUZHOU_COLL_ID));

        rmap.put("is_kaitongtudi", dictService.getValue("is_kaitongtudi"));
        return rmap;
    }


    @Cacheable("getYuanyuzhouList")
    public List<Yuanyuzhou> getYuanyuzhouList() {
        YuanyuzhouExample yuanyuzhouExample = new YuanyuzhouExample();
        yuanyuzhouExample.createCriteria().andUserIdGreaterThan(0);
        return yuanyuzhouDao.selectByExample(yuanyuzhouExample);
    }

    public static String phoneMask(String phone) {
        String res = phone;
        if (!StringUtil.isEmpty(phone)) {
            if (StringUtil.getLength(phone) > 10) {
                StringBuilder stringBuilder = new StringBuilder(phone);
                res = stringBuilder.replace(3, 7, "****").toString();
            }
        }
        return res;
    }


    @Override
    @DS("slave")
    public Map goodsdetails(int uid, int id) {
        Map map = new HashMap();
        Commodity commodity = commodityDao.selectByPrimaryKey(id);
        if (commodity != null) {


            map.put("id", commodity.getId());
            map.put("name", commodity.getName());
            map.put("img", ImgEnum.img.getUrl() + commodity.getImg());
            map.put("price", commodity.getPrice());
            map.put("stock", commodity.getStock());
            map.put("sold", commodity.getSold());
            map.put("content", commodity.getDetails());
            map.put("describes", commodity.getDescribes());

            String[] as = commodity.getMoreimg().split(",");
            List listnew = new ArrayList();
            for (int i = 0; i < as.length; i++) {
                listnew.add(ImgEnum.img.getUrl() + as[i]);

            }

            map.put("moreimg", listnew);
            //map.put("specifications",commodity.getSpecifications());
        }
        return map;
    }

    @DS("slave")
    public Lottery lotteryStart() {
        LotteryExample lotteryExample = new LotteryExample();
        lotteryExample.createCriteria().andIdIsNotNull();
        List<Lottery> allLottery = lotteryDao.selectByExample(lotteryExample);
        Integer allProbability = 0;
        for (Lottery lottery : allLottery) {
            allProbability = allProbability + lottery.getProbability();
        }
        Double random = Math.random();
        Double luckyNum = random * allProbability;
        Lottery lottery = new Lottery();
        for (Lottery lottery1 : allLottery) {
            luckyNum = luckyNum - lottery1.getProbability();
            if (luckyNum < 0) {
                lottery = lottery1;
                break;
            }
        }
        return lottery;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String lotteryDraw(int userid) {
        Users users = userDao.selectByPrimaryKey(userid);


        if (users.getLotterytimes() < 1) {
            throw new CloudException(ExceptionConstant.抽奖次数不够);
        }

        int lotterytimes = users.getLotterytimes() - 1;
        Users user1 = new Users();
        user1.setUserId(userid);
        user1.setLotterytimes(lotterytimes);

        userDao.updateByPrimaryKeySelective(user1);

        Lottery lottery = lotteryStart();
        //奖励为0 不增加积分
        if (lottery.getType() == 1) {

            Users users1 = userDao.selectByPrimaryKey(userid);
            int nowlotterytimes = users1.getLotterytimes() + 1;
            users1.setLotterytimes(nowlotterytimes);

            userDao.updateByPrimaryKeySelective(users1);
            Lotterylist lotterylist = new Lotterylist();
            lotterylist.setType(0);
            lotterylist.setUserid(userid);
            lotterylistDao.insertSelective(lotterylist);

        } else if (lottery.getType() == 2) {
            Lotterylist lotterylist = new Lotterylist();
            lotterylist.setType(1);
            lotterylist.setCommodityid(lottery.getCollid());
            lotterylist.setUserid(userid);
            lotterylistDao.insertSelective(lotterylist);


        } else if (lottery.getType() == 3) {
            Lotterylist lotterylist = new Lotterylist();
            lotterylist.setType(2);
            lotterylist.setCommodityid(lottery.getCollid());
            lotterylist.setUserid(userid);
            lotterylistDao.insertSelective(lotterylist);

        }
        return lottery.getDescribtion();

    }

    @Override
    public List goods() {
        List list = new ArrayList();
        CommodityExample commodityExample = new CommodityExample();
        commodityExample.createCriteria().andStateEqualTo(1);
        List<Commodity> commodityList = commodityDao.selectByExample(commodityExample);
        if (commodityList.size() > 0) {
            for (Commodity commodity : commodityList) {
                Map map = new HashMap();
                map.put("id", commodity.getId());
                map.put("name", commodity.getName());
                map.put("img", ImgEnum.img.getUrl() + commodity.getImg());
                map.put("price", commodity.getPrice());
                map.put("stock", commodity.getStock());
                list.add(map);
            }
        }
        return list;
    }

    @Override
    @DS("slave")
    public Map lottery(int logUserPid) {
        Map map = new HashMap();
        List<Lottery> lotteryList = lotteryDao.selectByExample(new LotteryExample());
        List list = new ArrayList();
        if (lotteryList.size() > 0) {
            for (Lottery lottery : lotteryList) {
                Map map1 = new HashMap();
                map1.put("id", lottery.getId());
                if (lottery.getType() == 2) {
                    Collection collection = collectionDao.selectByPrimaryKey(lottery.getCollid());
                    if (collection != null) {
                        map1.put("img", ImgEnum.img.getUrl() + collection.getImg());
                        map1.put("name", collection.getName());

                    }
                } else if (lottery.getType() == 3) {
                    Commodity commodity = commodityDao.selectByPrimaryKey(lottery.getCollid());
                    if (commodity != null) {
                        map1.put("img", ImgEnum.img.getUrl() + commodity.getImg());
                        map1.put("name", commodity.getName());

                    }
                }
                map1.put("type", lottery.getType());
                //map1.put("name",lottery.getDescribtion());
                list.add(map1);
            }
        }
        Users users = userDao.selectByPrimaryKey(logUserPid);
        String consume = dictService.getValue("consume");
        map.put("list", list);
        map.put("times", users.getLotterytimes());
        map.put("consume", consume);
        LotterylistExample lotterylistExample = new LotterylistExample();
        lotterylistExample.createCriteria().andUseridEqualTo(logUserPid);
        lotterylistExample.setOrderByClause("id desc");
        List<Lotterylist> lotterylistList = lotterylistDao.selectByExample(lotterylistExample);
        List newlist = new ArrayList();
        if (lotterylistList.size() > 0) {
            for (Lotterylist lotterylist : lotterylistList) {
                Map map1 = new HashMap();
                map1.put("type", lotterylist.getType());
                if (lotterylist.getType() == 1) {
                    Collection collection = collectionDao.selectByPrimaryKey(lotterylist.getCommodityid());
                    if (collection != null) {
                        map1.put("img", ImgEnum.img.getUrl() + collection.getImg());
                        map1.put("name", collection.getName());
                        map1.put("price", collection.getPrice());
                        map1.put("content", collection.getLimits());
                    }
                } else if (lotterylist.getType() == 2) {
                    Commodity commodity = commodityDao.selectByPrimaryKey(lotterylist.getCommodityid());
                    if (commodity != null) {
                        map1.put("img", ImgEnum.img.getUrl() + commodity.getImg());
                        map1.put("name", commodity.getName());
                        map1.put("price", commodity.getPrice());
                        map1.put("content", commodity.getDescribes());
                    }
                }
                newlist.add(map1);
            }
        }
        map.put("newlist", newlist);
        return map;
    }


    @Override
    @DS("slave")
    public List data() {
        List list = new ArrayList();
        CollectionExample collectionExample = new CollectionExample();
        collectionExample.createCriteria().andIsdeployEqualTo(1);
        collectionExample.setOrderByClause("id asc");
        List<Collection> collections = collectionDao.selectByExample(collectionExample);
        for (Collection collection : collections) {
            Map map = new HashMap();
            map.put("Name", collection.getName());
            UserGrantExample userGrantExample = new UserGrantExample();
            userGrantExample.createCriteria().andCollidEqualTo(collection.getId()).andTypeEqualTo(1).andSellpriceGreaterThan(new BigDecimal(0));
            List<UserGrant> userGrants = userGrantDao.selectByExample(userGrantExample);
            if (userGrants.size() > 0) {
                UserGrant userGrant = userGrants.get(0);
                if (userGrant.getSellprice().compareTo(new BigDecimal(0)) < 1) {
                    map.put("Price", new BigDecimal(0));
                } else {
                    map.put("Price", userGrant.getSellprice());
                }
            } else {
                map.put("Price", new BigDecimal(0));
            }
            map.put("Image", ImgEnum.img.getUrl() + collection.getImg());
            map.put("Url", collection.getUrl());
            map.put("Artist", "dopai");
            map.put("PublishQuantity", collection.getPublishquantity());
            map.put("PublishTime", DateUtils.getDateToStr(collection.getPublishtime(), DateUtils.DATETIME_FORMAT));
            list.add(map);
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void exchangeWhiteList(int logUserPid, int number) {
        BigDecimal whitelistNeededScore = new BigDecimal(dictService.getValue("WHITELIST_NEEDED_SCORE"));
        BigDecimal totalMoney = whitelistNeededScore.multiply(new BigDecimal(number));
        if (totalMoney.compareTo(BigDecimal.ZERO) < 1) {
            throw new CloudException(ExceptionConstant.违规操作即将封号);
        }
        val lockKey = "exchange#" + logUserPid;
        val lockValue = UUID.randomUUID().toString();
        final boolean isLocked = cloudRedisTemplate.getLock(lockKey, lockValue, 3);
        if (!isLocked) {
            throw new CloudException(ExceptionConstant.频率太高请稍等);
        }
        Users users = userDao.getUserById(logUserPid);
        BigDecimal money = users.getMoney();
        money = ObjectUtils.defaultIfNull(money, BigDecimal.ZERO);
        if (money
                .compareTo(totalMoney) < 0) {
            throw new CloudException(ExceptionConstant.积分不足);
        }
        BigDecimal endMoney = money.subtract(totalMoney);
        int whiteListNumber = users.getWhitelist() + number;
        users.setMoney(endMoney);
        users.setWhitelist(whiteListNumber);
        userDao.updateByPrimaryKeySelective(users);
        taskProcessor.addTask(logUserPid, totalMoney.negate(), PointsTypeEnum.EXCHANGE_TICKET, endMoney);
        final boolean isUnLocked = cloudRedisTemplate.releaseLock(lockKey, lockValue);
        if (!isUnLocked) {
            throw new CloudException(ExceptionConstant.频率太高请稍等);
        }
    }

    @Override
    public String check(String phone, String xmno) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(phone)) {
            Users user = userService.getUser(phone);
            if (user == null) {
                sb.append("用户不存在:").append(phone);
                return sb.toString();
            }
            sb.append("UID:").append(user.getUserId()).append("<br/>");
            UserGrantExample example = new UserGrantExample();
            example.createCriteria().andUseridEqualTo(user.getUserId());
            List<UserGrant> userGrants = userGrantDao.selectByExample(example);
            Map<Integer, List<UserGrant>> collect = userGrants.stream().collect(Collectors.groupingBy(UserGrant::getCollid));
            collect.forEach((x, y) -> {
                sb.append("名称:").append(collectionDao.selectByPrimaryKey(x).getName())
                        .append("(").append(x).append(")")
                        .append("数量:").append(y.size()).append("<br/>");
                sb.append("编号:");
                y.forEach(z -> {
                    sb.append(z.getTruenumber()).append(", ");
                });
                sb.append("<br/><br/>");
            });
        }
        return sb.toString();
    }

    @Override
    public cn.hutool.json.JSONObject getActivityData(String val) {
        String value = dictService.getValue(val);
        if (StrUtil.isBlank(value)) {
            return null;
        }
        cn.hutool.json.JSONObject obj = JSONUtil.parseObj(value);
        if (obj.isEmpty()) {
            return null;
        }

        DateTime startTime = DateUtil.parseDateTime(obj.getStr("startTime"));
        DateTime endTime = DateUtil.parseDateTime(obj.getStr("endTime"));
        if (DateUtil.isIn(new Date(), startTime, endTime)) {
            return obj;
        }
        return null;
    }

    private void assByXM(StringBuilder sb, List<HideRecord> hideRecords) {
        if (hideRecords == null || hideRecords.isEmpty()) {
            return;
        }
        hideRecords.forEach(x -> {
            sb.append("时间:").append(DateUtils.getDate(x.getCreateTime()));
            Users user1 = userDao.selectByPrimaryKey(x.getUserid());
            sb.append("\t原始:").append(ObjectUtil.defaultIfNull(user1.getPhoneNumber(), user1.getMailbox()));
            sb.append("\t目标:");
            if (x.getGetuid() != null && x.getGetuid() > 0) {
                Users user2 = userDao.selectByPrimaryKey(x.getGetuid());
                sb.append(ObjectUtil.defaultIfNull(user2.getPhoneNumber(), user2.getMailbox()));
            } else {
                sb.append("NULL");
            }

            sb.append("\tXM:").append(x.getNo());
            sb.append("\t描述:").append(x.getMs());
            if (x.getUserGrantId() != null && x.getUserGrantId() > 0) {
                UserGrant userGrant = userGrantDao.selectByPrimaryKey(x.getUserGrantId());
                String collName = ObjectUtil.defaultIfBlank(x.getName(), collectionDao.selectByPrimaryKey(userGrant.getCollid()).getName());
                sb.append("\t名称:").append(collName);
                sb.append("\t编号:").append(userGrant.getTruenumber());
                if (userGrant.getUserid().equals(x.getGetuid())) {
                    sb.append("\t转赠成功!").append("GrantId:").append(userGrant.getId());
                } else {
                    sb.append("\t转赠有变!<br/>");
                    HideRecordExample example = new HideRecordExample();
                    example.createCriteria()
                            .andUseridEqualTo(x.getGetuid())
                            .andUserGrantIdEqualTo(x.getUserGrantId());
                    List<HideRecord> records = hideRecordDao.selectByExample(example);
                    HideRecordExample example2 = new HideRecordExample();
                    example2.createCriteria()
                            .andUseridEqualTo(x.getGetuid())
                            .andCreateTimeGreaterThan(x.getCreateTime())
                            //.andNameEqualTo(collName)
                            //.andNoEqualTo(userGrant.getTruenumber())
                            .andUserGrantIdEqualTo(0);
                    records.addAll(hideRecordDao.selectByExample(example2));
                    Collections.sort(records, Comparator.comparing(HideRecord::getId));
                    assByXM(sb, records);
                }
            } else {
                sb.append("\t编号:").append(x.getNo());
            }
            sb.append("<br/>=====<br/><br/>");
        });
    }

}
