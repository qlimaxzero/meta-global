package com.shitouren.core.service.processor;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.shitouren.core.autogenerate.bean.Task;
import com.shitouren.core.autogenerate.bean.TaskExample;
import com.shitouren.core.autogenerate.bean.UserGrant;
import com.shitouren.core.autogenerate.dao.TaskDao;
import com.shitouren.core.bean.eums.PointsTypeEnum;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 任务管理器
 */

@Slf4j
@Component
public class TaskProcessor {

    @Resource
    private TaskDao taskDao;

    @Transactional(rollbackFor = Exception.class)
    public void addTask(int logUserPid, BigDecimal amount, PointsTypeEnum typeEnum, BigDecimal currentPoints) {
        addTask(logUserPid, amount, typeEnum, null, null, null, currentPoints);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addTask(int logUserPid, BigDecimal points, PointsTypeEnum typeEnum, Integer days, String otherId,
                        String remark, BigDecimal currentPoints) {
        val task = new Task();
        task.setUserid(logUserPid);
        task.setCreattime(new Date());
        task.setState(typeEnum.getType());
        task.setPrice(points);
        task.setDay(days);
        task.setOtherId(otherId);
        task.setRemark(remark);
        task.setCurrentPoints(currentPoints);
        taskDao.insertSelective(task);
        log.info("用户积分添加, record = {}", JSON.toJSONString(task));
    }

    /**
     * 获取用户持有奖励的task列表最后一条记录
     */
    public Task getLastTaskByHOLD(UserGrant userGrant) {
        TaskExample taskExample = new TaskExample();
        taskExample.createCriteria()
                .andUseridEqualTo(userGrant.getUserid())
                .andStateEqualTo(PointsTypeEnum.HOLD.getType())
                .andOtherIdEqualTo(userGrant.getId().toString());
        List<Task> tasks = taskDao.selectByExample(taskExample);
        if (tasks.isEmpty()) {
            return null;
        }
        return tasks.get(tasks.size() - 1);
    }

    public Date getLastTaskTradeTimeByHOLD(UserGrant userGrant) {
        Task task = this.getLastTaskByHOLD(userGrant);
        if (task == null) {
            return null;
        }

        return DateUtil.parseDateTime(task.getRemark());
    }

    public List<Task> selectByUserState(Integer userId, Integer type) {
        TaskExample example = new TaskExample();
        example.createCriteria().andUseridEqualTo(userId).andStateEqualTo(type);
        return taskDao.selectByExample(example);
    }

}
