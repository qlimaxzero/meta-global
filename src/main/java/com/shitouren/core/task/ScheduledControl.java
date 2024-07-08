package com.shitouren.core.task;

import com.shitouren.core.aop.ClusterScheduler;
import com.shitouren.core.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
@ConditionalOnProperty("spring.scheduling.enable")
@Slf4j
@ClusterScheduler
public class ScheduledControl {

    @Resource
    MineService mineService;
    @Resource
    HomeService homeService;
    @Resource
    InviteService inviteService;
    @Resource
    MiningService miningService;
    @Resource
    AssetService assetService;
    @Resource
    UserGrantService userGrantService;

    @Scheduled(cron = "1 0 0 * * ?")
//    @Scheduled(cron = "1 0/1 * * * ?")
    public void miningRewardTask() {
        miningService.miningRewardTask();
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void unfrozenAmtTask() {
        assetService.unfrozenAmtTask();
    }

    @Scheduled(cron = "0 0/2 * * * ?")
    public void exchangeQueryTask() {
        assetService.exchangeQueryTask();
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    public void airdropQueryTask() {
        // 空投需主动查询触发算力更新事件
        userGrantService.airdropQueryTask(300);
    }

    /**
     * 检查交易 未指定时间付款
     */
//    @Scheduled(cron = "0 0/5 * * * ?")
//    public void checkTradeStatus() {
//        mineService.checkTradeStatus();
//    }

    /**
     * 检查出签
     */
//    @Scheduled(cron = "0 */5 * * * ?")
//    public void checkqian() {
//        mineService.checkqian();
//    }

    /**
     * 已出售藏品上链
     */
//    @Scheduled(cron = "0 */5 * * * ?")
//    public void upmintnfts() {
//        homeService.upmintnfts();
//    }

    /**
     * 藏品上链结果查询, 并设置classid和hashs及藏品编号
     */
//    @Scheduled(cron = "0 */5 * * * ?")
//    public void upmintnft1() {
//        homeService.upmintnft1();
//    }

//    @Scheduled(fixedDelay = 600000)
//    public void archiveTransfer() {
//        invokingService.archiveTransfer();
//    }

    //处理未及时取消的订单
    @Scheduled(cron = "30 0/10 * * * ?")
    public void checkOverDueOrders() {
        mineService.checkOverDueOrders();
    }

    /**
     * 藏品持有x天后增加y积分
     * 增加二次执行兜底
     */
//    @Scheduled(cron = "0 15 3,5 * * ? ")
//    public void addPointsByHoldingDays() {
//        mineService.addPointsByHoldingDays();
//    }


}
