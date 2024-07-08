package com.shitouren.core.service;

import cn.hutool.json.JSONUtil;
import com.shitouren.core.autogenerate.bean.*;
import com.shitouren.core.autogenerate.dao.*;
import com.shitouren.core.bean.eums.BalanceTypeEnum;
import com.shitouren.core.bean.eums.OrderStatusEnum;
import com.shitouren.core.bean.eums.PointsTypeEnum;
import com.shitouren.core.config.redis.CloudRedisTemplate;
import com.shitouren.core.constant.BusinessConstant;
import com.shitouren.core.model.dto.VirtualCoinDTO;
import com.shitouren.core.service.processor.BalanceRecordProcessor;
import com.shitouren.core.service.processor.TaskProcessor;
import com.shitouren.core.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class HeePayServiceImpl implements HeePayService {

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
    @Autowired(required = false)
    MoneyrecordDao moneyrecordDao;
    @Autowired(required = false)
    YuanyuzhouDao yuanyuzhouDao;
    @Autowired(required = false)
    UserService userService;
    @Autowired
    CloudRedisTemplate cloudRedisTemplate;
    @Autowired
    BalanceRecordProcessor balanceRecordProcessor;
    @Resource
    TaskProcessor taskProcessor;

    @Override
    public void updPay(String orderNo, BigDecimal money, VirtualCoinDTO coinDTO) {//
        MyOrderExample myOrderExample = new MyOrderExample();
        MyOrderExample.Criteria criteria = myOrderExample.createCriteria().andOrdernoEqualTo(orderNo);
        if (coinDTO != null) {
            criteria.andOrdertypeEqualTo(OrderStatusEnum.CALLBACK.getStatus());
        } else {
            criteria.andOrdertypeEqualTo(OrderStatusEnum.WAIT.getStatus());
        }
        List<MyOrder> myOrders = myOrderDao.selectByExample(myOrderExample);
        log.info("orderNo {} list.size {}", orderNo, myOrders.size());
        if (!myOrders.isEmpty()) {
            MyOrder myOrder = myOrders.get(0);
            log.info("{} realPayAmt {}", JSONUtil.toJsonStr(myOrder), money);

            Users users = userDao.selectByPrimaryKey(myOrder.getUserid());
            BigDecimal oldMoney = users.getMoney();
            BigDecimal oldBalance = users.getBalance();

            if (myOrder.getYuanyuzhouid() != 0) {
                Yuanyuzhou yuanyuzhou = yuanyuzhouDao.selectByPrimaryKey(myOrder.getYuanyuzhouid());
                if (yuanyuzhou != null && yuanyuzhou.getUserId() == 0) {
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

            List<Task> taskList = taskProcessor.selectByUserState(users.getUserId(), PointsTypeEnum.BUY_FIRST.getType());
            if (taskList.isEmpty() && myOrder.getIstype() == 1) {
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

            //TODO 暂时注释此处逻辑, 后续可清除
            //如果用户余额大于0，增加用户余额抵扣记录
            /*if (users.getBalance().compareTo(new BigDecimal(0)) > 0) {
                //增加用户余额扣除记录
                balanceRecordProcessor.add(myOrder.getUserid(), users.getBalance().negate(), BalanceTypeEnum.BUY, "购买藏品抵扣", String.valueOf(myOrder.getId()));

                //更新用户余额
                users.setBalance(new BigDecimal(0));
            }*/

            // 理论上实付金额应为订单金额, 实际情况会存在使用优惠券等等
            //TODO 存在bug, 折扣比例在订单发生后调整时, 会出现实际支付金额大于或小于此处重新计算后的预期支付金额, 需将实际支付金额持久化
            BigDecimal realExpPayAmt = myOrder.getPrice();
            if (myOrder.getBuycard() > 0) {
                if (myOrder.getIstype() == 1) {//藏品+出售
                    Collection collection = collectionDao.selectByPrimaryKey(myOrder.getCollid());
                    realExpPayAmt = collection.getPrice().multiply(collection.getBuycardsize());
                } else {
                    Blindbox collection = blindboxDao.selectByPrimaryKey(myOrder.getCollid());
                    realExpPayAmt = collection.getPrice().multiply(collection.getBuycardsize());
                }
            }
            // 应扣余额=实际应付金额-用户实际支付
            BigDecimal expDeductBal = realExpPayAmt.subtract(money);
            int compare = expDeductBal.compareTo(BigDecimal.ZERO);
            if (compare > 0) {
                users.setBalance(users.getBalance().subtract(expDeductBal));

                String name = myOrder.getIstype() == 1 ?
                        collectionDao.selectByPrimaryKey(myOrder.getCollid()).getName()
                        :
                        blindboxDao.selectByPrimaryKey(myOrder.getCollid()).getName();
                balanceRecordProcessor.add(myOrder.getUserid(), expDeductBal.negate(), BalanceTypeEnum.BUY,
                        myOrder.getId(), name + " 抵扣", users.getBalance());
            } else if (compare < 0) {
                log.error("Impossible: {}, expDeductBal: {}", JSONUtil.toJsonStr(myOrder), expDeductBal);
            }
            userService.updateUserByCAS(users, oldMoney, oldBalance);

            myOrder.setOrdertype(OrderStatusEnum.COMPLETE.getStatus());
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
                    hideRecord.setUserGrantId(userGrant.getId());
                }
                hideRecordDao.insertSelective(hideRecord);
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

            if (coinDTO != null) {
                myOrder.setTxid(coinDTO.getTxid());
                myOrder.setFromAddr(coinDTO.getFromAddr());
            }
            myOrder.setSuccessTime(new Date());
            myOrderDao.updateByPrimaryKeySelective(myOrder);

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

            //删除 redis控制订单超时
            cloudRedisTemplate.delete("OrderExpireImpl" + BusinessConstant.ORDER_EXPIRE_KEY + myOrder.getId());
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
    public void notifyRecharge(String orderNo, BigDecimal money) {
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
        CreatorlistExample creatorlistExample = new CreatorlistExample();
        creatorlistExample.createCriteria().andNoEqualTo(orderNo);
        List<Creatorlist> creatorlists = creatorlistDao.selectByExample(creatorlistExample);
        if (creatorlists.size() > 0) {
            Creatorlist creatorlist = creatorlists.get(0);
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

