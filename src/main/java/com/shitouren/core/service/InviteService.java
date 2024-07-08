package com.shitouren.core.service;



public interface InviteService {

    /**
     * 邀请奖励发放
     */
    void invitationRewardGrant(Integer inviterId, Integer inviteeId);

    /**
     * 邀请奖励重置任务
     */
    void invitationRewardResetTask();

}
