package com.shitouren.core.service;

import com.shitouren.core.autogenerate.bean.*;
import com.shitouren.core.autogenerate.dao.*;
import com.shitouren.core.bean.eums.BalanceTypeEnum;
import com.shitouren.core.bean.eums.PointsTypeEnum;
import com.shitouren.core.config.redis.CloudRedisTemplate;
import com.shitouren.core.constant.BusinessConstant;
import com.shitouren.core.service.processor.BalanceRecordProcessor;
import com.shitouren.core.service.processor.TaskProcessor;
import com.shitouren.core.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AliPayServiceImpl implements AliPayService {

    @Autowired
    DictService dictService;
    @Autowired(required = false)
    AliPayService aliPayService;
    @Autowired(required = false)
    DictDao dictDao;
    @Autowired(required = false)
    UsersDao userDao;
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
    MyboxDao myboxDao;
    @Autowired(required = false)
    ApliaymhDao apliaymhDao;
    @Autowired(required = false)
    BalanceRecordDao balanceRecordDao;
    @Autowired(required = false)
    HideRecordDao hideRecordDao;
    @Autowired(required = false)
    MyOrderDao myOrderDao;
    @Autowired(required = false)
    SignupDao signupDao;
    @Autowired(required = false)
    RechargeRecordDao rechargeRecordDao;
    @Autowired(required = false)
    CreatorlistDao creatorlistDao;
    @Autowired(required = false)
    CreaterDao createrDao;
    @Autowired(required = false)
    TaskDao taskDao;
    @Resource
    TaskProcessor taskProcessor;
    @Autowired(required = false)
    MoneyrecordDao moneyrecordDao;
    @Autowired(required = false)
    YuanyuzhouDao yuanyuzhouDao;
    @Autowired
    CloudRedisTemplate cloudRedisTemplate;
    @Resource
    BalanceRecordProcessor balanceRecordProcessor;

    @Override
    public void updPay(String orderNo, BigDecimal money) {//
        MyOrderExample myOrderExample = new MyOrderExample();
        myOrderExample.createCriteria().andOrdernoEqualTo(orderNo).andOrdertypeEqualTo(0);
        List<MyOrder> myOrders = myOrderDao.selectByExample(myOrderExample);
        if (myOrders.size() > 0) {
            MyOrder myOrder = myOrders.get(0);
            if (myOrder.getOrdertype() == 0) {
                Users users = userDao.selectByPrimaryKey(myOrder.getUserid());

                if(myOrder.getYuanyuzhouid() != 0){
                    Yuanyuzhou yuanyuzhou = yuanyuzhouDao.selectByPrimaryKey(myOrder.getYuanyuzhouid());
                    if(yuanyuzhou != null && yuanyuzhou.getUserId() == 0){
                        yuanyuzhou.setUserId(myOrder.getUserid());
                        yuanyuzhou.setNickName(users.getNickName());
                        yuanyuzhou.setIsGoumai(0);
                        yuanyuzhou.setTime(new Date());
                        yuanyuzhouDao.updateByPrimaryKeySelective(yuanyuzhou);
                    }
                }


                if (myOrder.getBuycard() > 0) {
                    int nowbuycard = users.getBuycard() - 1;
                    users.setBuycard(nowbuycard);
                }



                TaskExample taskExample1122 = new TaskExample();
                taskExample1122.createCriteria().andUseridEqualTo(users.getUserId()).andStateEqualTo(2);
                List<Task> taskList1122 = taskDao.selectByExample(taskExample1122);
                if (taskList1122.size() == 0 && myOrder.getIstype() == 1) {
                    BigDecimal followaccountmoney = new BigDecimal(dictService.getValue("buygoods"));
                    users.setMoney(users.getMoney().add(followaccountmoney));
                    taskProcessor.addTask(users.getUserId(), followaccountmoney, PointsTypeEnum.BUY_FIRST, users.getMoney());
                    Moneyrecord moneyrecord = new Moneyrecord();
                    moneyrecord.setCreattime(new Date());
                    moneyrecord.setUserid(users.getUserId());
                    moneyrecord.setName("购买首发藏品");
                    moneyrecord.setCount(followaccountmoney);
                    moneyrecordDao.insertSelective(moneyrecord);
                }

                userDao.updateByPrimaryKeySelective(users);
                myOrder.setOrdertype(1);
                if (myOrder.getIstype() == 1) {//藏品
                    Collection collection = collectionDao.selectByPrimaryKey(myOrder.getCollid());
                    HideRecord hideRecord = new HideRecord();
                    hideRecord.setUserid(myOrder.getUserid());
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
                    if (myOrder.getGinsengtype() == 2) {
                        Signup signup = new Signup();
                        signup.setUserid(myOrder.getUserid());
                        signup.setIsid(myOrder.getCyid());
                        Issue issue = issueDao.selectByPrimaryKey(myOrder.getCyid());
                        signup.setBegintime(issue.getReleasetime());
                        signup.setIsid(myOrder.getCyid());
                        signup.setCreatetime(new Date());
                        signup.setMyorderid(myOrder.getId());
                        signupDao.insertSelective(signup);
                    } else {
                        UserGrant userGrant = new UserGrant();
                        userGrant.setUserid(myOrder.getUserid());
                        userGrant.setCollid(collection.getId());
                        userGrant.setTruenumber("0");
                        userGrant.setTradeTime(new Date());
                        userGrant.setCreatetime(new Date());
                        userGrant.setBuyprice(myOrder.getPrice());
                        userGrant.setYuanyuzhouid(myOrder.getYuanyuzhouid());
                        userGrantDao.insertSelective(userGrant);
                        myOrder.setGrants(2);
                    }
                } else {
                    Blindbox collection = blindboxDao.selectByPrimaryKey(myOrder.getCollid());
                    HideRecord hideRecord = new HideRecord();
                    hideRecord.setUserid(myOrder.getUserid());
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
                    if (myOrder.getGinsengtype() == 2) {
                        Signup signup = new Signup();
                        signup.setUserid(myOrder.getUserid());
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
                myOrderDao.updateByPrimaryKeySelective(myOrder);

                //删除 redis控制订单超时
                cloudRedisTemplate.delete("OrderExpireImpl" + BusinessConstant.ORDER_EXPIRE_KEY + myOrder.getId());

                //查询上级
                if (users.getInvitationId() != null) {
                    Users topusers = userDao.selectByPrimaryKey(users.getInvitationId());
                    if (topusers != null) {
                        BigDecimal yjfh = new BigDecimal(dictService.getValue("yjfh"));
                        BigDecimal commission = myOrder.getPrice().multiply(yjfh);
                        BigDecimal topmoney = commission.add(topusers.getBalance());
                        topusers.setBalance(topmoney);
                        userDao.updateByPrimaryKeySelective(topusers);

                        balanceRecordProcessor.add(topusers.getUserId(), commission, BalanceTypeEnum.COMMISSION,
                                myOrder.getId(), topmoney);
                    }
                }
            }
        }
    }

    @Override
    public void notify11(String orderNo, BigDecimal money) {//
        //二级市场
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(Integer.parseInt(orderNo));
        if (userGrant != null) {
            userGrant.setType(3);
            userGrantDao.updateByPrimaryKeySelective(userGrant);
            Collection collection = collectionDao.selectByPrimaryKey(userGrant.getCollid());
            UserGrant newusg = new UserGrant();
            newusg.setUserid(userGrant.getOppositeuser());
            newusg.setCollid(userGrant.getCollid());
            newusg.setTruenumber(userGrant.getTruenumber());
            newusg.setHashs(userGrant.getHashs());
            newusg.setTokenid(userGrant.getTokenid());
            newusg.setTradeTime(new Date());
            newusg.setCreatetime(new Date());
            newusg.setBuyprice(userGrant.getSellprice());
            userGrantDao.insertSelective(newusg);

            HideRecord hideRecord = new HideRecord();
            hideRecord.setUserid(userGrant.getOppositeuser());
            hideRecord.setImg(collection.getImg());
            hideRecord.setName(collection.getName());
            hideRecord.setPrice(userGrant.getSellprice());
            hideRecord.setNo(userGrant.getTruenumber());
            if (StringUtil.getLength(userGrant.getTruenumber()) > 0) {
                hideRecord.setNo(userGrant.getTruenumber());
            }
            hideRecord.setCreateTime(new Date());
            hideRecord.setMs("购买成功");
            hideRecord.setType(2);//0.黄的1.绿2.红
            hideRecordDao.insertSelective(hideRecord);
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
        }
    }

    @Override
    public void updPay1(String orderNo, BigDecimal money) {
        RechargeRecord rechargeRecord = rechargeRecordDao.selectByPrimaryKey(Integer.parseInt(orderNo));
        if (rechargeRecord != null) {
            if (rechargeRecord.getType() == 0) {
                Users users = userDao.selectByPrimaryKey(rechargeRecord.getUserid());
                if (users != null) {
                    rechargeRecord.setType(1);
                    rechargeRecordDao.updateByPrimaryKeySelective(rechargeRecord);
                    BigDecimal bigDecimal = users.getBalance().add(money);
                    users.setBalance(bigDecimal);
                    userDao.updateByPrimaryKeySelective(users);
                    balanceRecordProcessor.add(rechargeRecord.getUserid(), money, BalanceTypeEnum.RECHARGE,
                            rechargeRecord.getId(), bigDecimal);
                }
            }
        }
    }

    @Override
    public void updPay2(String orderNo, BigDecimal money) {
        ApliaymhExample apliaymhExample = new ApliaymhExample();
        apliaymhExample.createCriteria().andOrdernoEqualTo(orderNo);
        List<Apliaymh> apliaymhList = apliaymhDao.selectByExample(apliaymhExample);
        if (apliaymhList.size() > 0) {
            Apliaymh apliaymh = apliaymhList.get(0);
            BigDecimal startmoney = apliaymh.getPrice();
            if (money.compareTo(startmoney) > -1) {
                apliaymh.setType(1);
                apliaymhDao.updateByPrimaryKeySelective(apliaymh);
                int id = apliaymh.getBoxid();
                Creatorlist creatorlist = creatorlistDao.selectByPrimaryKey(id);
                if (creatorlist != null) {
                    creatorlist.setType(3);
                    creatorlistDao.updateByPrimaryKeySelective(creatorlist);
                    CreaterExample createrExample = new CreaterExample();
                    createrExample.createCriteria().andUseridEqualTo(creatorlist.getUserid());
                    List<Creater> createrList = createrDao.selectByExample(createrExample);
                    //藏品库加藏品
                    Collection collection = new Collection();
                    collection.setImg(creatorlist.getImg());
                    collection.setName(creatorlist.getName());
                    collection.setMinname("");
                    collection.setLimits(creatorlist.getNumber());
                    collection.setPrice(creatorlist.getPrice());
                    collection.setImg1(creatorlist.getImg());
                    collection.setDetails(creatorlist.getContent());
                    if (createrList.size() > 0) {
                        Creater creater = createrList.get(0);
                        collection.setCreator(creater.getContent());
                    }
                    collection.setClassid(creatorlist.getClassid());
                    collection.setType(2);
                    collectionDao.insertSelective(collection);
                }
            }
        }
    }
}
