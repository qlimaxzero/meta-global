package com.shitouren.core.bean.eums;

import cn.hutool.core.util.IdUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IdEnum {

    RECHARGE("RC", "充值") {
        @Override
        public String getRid() {
            return super.getRid() + IdUtil.objectId();
        }
    },
    BLIND_BOX("BB", "盲盒购买") {
        @Override
        public String getRid() {
            return super.getRid() + IdUtil.objectId();
        }
    }
    ;

    private String rid;
    private String desc;

}
