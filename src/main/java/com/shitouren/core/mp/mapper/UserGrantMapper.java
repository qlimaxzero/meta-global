package com.shitouren.core.mp.mapper;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shitouren.core.autogenerate.bean.UserGrant;
import com.shitouren.core.common.Constants;
import com.shitouren.core.utils.DecimalUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public interface UserGrantMapper extends BaseMapper<UserGrant> {

    default int updateUsageById(int usage, Integer id) {
        UserGrant grant = new UserGrant();
        grant.setUsage(usage);
        grant.setId(id);
        return this.updateById(grant);
    }

    default List<UserGrant> selectByUid(Integer uid) {
        LambdaQueryWrapper<UserGrant> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserGrant::getUserid, uid);
        return this.selectList(queryWrapper);
    }

    default List<Integer> selectCollIdByUid(Integer uid) {
        QueryWrapper<UserGrant> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("distinct collid");
        queryWrapper.eq("userid", uid);
        queryWrapper.orderByAsc("collid");
        List<Object> objects = this.selectObjs(queryWrapper);
        return objects.stream().map(o -> (Integer) o).collect(Collectors.toList());
    }

    default List<Integer> selectUidByState(Integer state, LocalDateTime dateTime) {
        QueryWrapper<UserGrant> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("distinct userid");
        queryWrapper.eq("state", state);
        queryWrapper.ge("createtime", dateTime);
        List<Object> objects = this.selectObjs(queryWrapper);
        return objects.stream().map(o -> (Integer) o).collect(Collectors.toList());
    }

    default UserGrant addNFT(Integer uid, Integer collId, BigDecimal buyAmt, Integer source, BigDecimal power,
                           Byte display, BigDecimal health) {
        UserGrant entity = new UserGrant();
        entity.setUserid(uid);
        entity.setCollid(collId);
        entity.setType(Constants.NFT_TYPE_INIT);
        entity.setTruenumber(RandomUtil.randomNumbers(10));
        entity.setBuyprice(buyAmt);
        entity.setPaytype(0);
        entity.setState(source);
        entity.setPower(power);
        entity.setDisplay(display);
        entity.setHealth(health);
        entity.setTradeTime(new Date());
        entity.setCreatetime(new Date());
        return insert(entity) == 1 ? entity : null;
    }

    default UserGrant getDisplayedGrant(Integer uid) {
        LambdaQueryWrapper<UserGrant> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserGrant::getUserid, uid);
        queryWrapper.eq(UserGrant::getDisplay, Constants.DISPLAY_ON);
        return selectOne(queryWrapper);
    }

    default BigDecimal sumTotalPower(Integer uid) {
        QueryWrapper<UserGrant> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", uid);
        queryWrapper.eq("type", 0);
        queryWrapper.select("IFNULL(sum(power), 0) as power");
        List<Object> objects = selectObjs(queryWrapper);
        if (objects.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return DecimalUtil.of(objects.get(0));
    }
}
