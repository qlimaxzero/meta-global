package com.shitouren.core.config.pay.local;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.config.pay.PayProperties;
import com.shitouren.core.utils.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Slf4j
@Component
public class LocalPayClient {

    @Resource
    private PayProperties payProperties;

    private static final int timeout = 5 * 1000;

    public String getAddress(BigDecimal amt) {
        String addr= request(Method.GET, payProperties.getAddressUrl() + amt, null).getStr("address");
        AssertUtil.notBlank(addr, ExceptionConstant.网络异常);
        return addr;
    }

    public String create(String receive, BigDecimal amt) {
        JSONObject ctx = new JSONObject();
        //ctx.set("send", send);
        ctx.set("recive", receive);
        ctx.set("amount", amt.toPlainString());
        JSONObject requestObj = new JSONObject();
        requestObj.set("created", ctx);
        String txId = request(Method.POST, payProperties.getCreateUrl(), requestObj.toString())
                .getStr("id");
        AssertUtil.notBlank(txId, ExceptionConstant.网络异常);
        return txId;
    }

    public boolean query(String txId) {
        JSONObject rs = request(Method.GET, payProperties.getQueryUrl() + txId, null);
        Integer confirmations = rs.getInt("confirmations", -1);
        Integer maxConfirmations = rs.getInt("max_confirmations", 999);//理论上一定有值
        return confirmations >= maxConfirmations;
    }

    private JSONObject request(Method method, String url, String req) {
        long t = System.currentTimeMillis();
        try (HttpResponse response = HttpUtil.createRequest(method == null ? Method.GET : method, url)
                .body(req, req == null ? null : ContentType.JSON.toString())
                .timeout(timeout)
                .execute()) {

            log.info("req: url={}, req: {} resp: status={}, body={} rt={}ms", url, req,
                    response.getStatus(), response.body(), (System.currentTimeMillis() - t));

            if (response.getStatus() == 401) {
                throw new CloudException(ExceptionConstant.网络异常);
            }

            if (!response.isOk()) {
                throw new CloudException(ExceptionConstant.网络异常);
            }

            JSONObject obj = JSONUtil.parseObj(response.body());
            if (obj.getInt("code") != 200) {
                throw new CloudException(ExceptionConstant.网络异常);
            }

            return obj.getJSONObject("result");
        } catch (CloudException e) {
            throw e;
        } catch (Exception e) {
            log.error("req: url={}, rt={}ms, resp error!", url, (System.currentTimeMillis() - t), e);
            throw new CloudException(ExceptionConstant.网络异常);
        }
    }


}
