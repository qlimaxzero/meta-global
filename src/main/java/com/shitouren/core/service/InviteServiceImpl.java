package com.shitouren.core.service;


import com.shitouren.core.mp.mapper.InviteeSuccRecordMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class InviteServiceImpl implements InviteService {

    private final UserService userService;
    private final DictService dictService;
    private final InviteeSuccRecordMapper inviteeSuccRecordMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invitationRewardGrant(Integer inviterId, Integer inviteeId) {
        /*Integer count = inviteeSuccRecordMapper.count(inviterId, inviteeId);
        if (count > 0) {
            return;
        }

        BigDecimal rewardPower = new BigDecimal(dictService.getValue(DictConst.INVITATION_POWER_REWARD));
        Users inviter = userService.getUserById(inviterId);
        BigDecimal newInvitationPower = DecimalUtil.add(inviter.getInvitationPower(), rewardPower);
        int i = userService.updateInvitationPowerByCAS(inviterId, newInvitationPower, inviter.getInvitationPower());
        AssertUtil.isTrue(i == 1, ExceptionConstant.更新失败);

        InviteeSuccRecord entity = new InviteeSuccRecord();
        entity.setInviterId(inviterId);
        entity.setInviteeId(inviteeId);
        entity.setPower(rewardPower);
        entity.setStatus(Constants.INVITATION_POWER_INIT);
        entity.setCreateTime(LocalDateTime.now());
        inviteeSuccRecordMapper.insert(entity);*/
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invitationRewardResetTask() {
        /*long invitePowerDuration = Long.parseLong(dictService.getValue(DictConst.INVITATION_POWER_DURATION));
        LambdaQueryWrapper<InviteeSuccRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(InviteeSuccRecord::getStatus, Constants.INVITATION_POWER_INIT);
        queryWrapper.le(InviteeSuccRecord::getCreateTime, LocalDateTime.now().minusSeconds(invitePowerDuration));
        List<InviteeSuccRecord> records = inviteeSuccRecordMapper.selectList(queryWrapper);
        log.info("records.size{}", records.size());

        BigDecimal resetPower = new BigDecimal(dictService.getValue(DictConst.INVITATION_POWER_RESET));
        records.forEach(x -> {
            BigDecimal power = DecimalUtil.sub(x.getPower(), resetPower);
            Users inviter = userService.getUserById(x.getInviterId());
            BigDecimal newInvitationPower = DecimalUtil.sub(inviter.getInvitationPower(), power);
            int i = userService.updateInvitationPowerByCAS(x.getInviterId(), newInvitationPower,
                    inviter.getInvitationPower());
            if (i == 0) {
                log.error("uid {} newInvitationPower {}, oldInvitationPower {}", x.getInviterId(), newInvitationPower,
                        inviter.getInvitationPower());
            }
        });*/
    }

}
