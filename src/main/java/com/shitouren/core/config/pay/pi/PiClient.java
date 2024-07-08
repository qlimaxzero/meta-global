package com.shitouren.core.config.pay.pi;

import cn.hutool.http.*;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;


@Slf4j
@Component
public class PiClient {

    @Resource
    private PiProperties piProperties;

    private static final int timeout = 60 * 1000;

    public JSONObject auth(String accessToken) {
        String rs = request(Method.GET, "Bearer " + accessToken, piProperties.getAuthUrl(), null);
        return JSONUtil.parseObj(rs);
    }

    public JSONObject paymentCreate(String uid, BigDecimal amt, String memo, String metadata) {
        JSONObject object = new JSONObject();
        object.set("uid", uid);
        object.set("amount", amt);
        object.set("memo", memo);
        object.set("metadata", metadata);
        JSONObject req = new JSONObject();
        req.set("payment", object);
        String rs = request(Method.POST, this.keyAuth(), piProperties.getPaymentCreateUrl(), req.toString());
        return JSONUtil.parseObj(rs);
    }

    public JSONObject paymentInfo(String paymentId) {
        String rs = request(Method.GET, this.keyAuth(),
                piProperties.getPaymentInfoUrl().replace("{paymentId}", paymentId), null);
        return JSONUtil.parseObj(rs);
    }

    public JSONObject paymentApprove(String paymentId) {
        String rs = request(Method.POST, this.keyAuth(),
                piProperties.getPaymentApproveUrl().replace("{paymentId}", paymentId), null);
        return JSONUtil.parseObj(rs);
    }

    public JSONObject paymentComplete(String paymentId, String txId) {
        String rs = request(Method.POST, this.keyAuth(),
                piProperties.getPaymentCompleteUrl().replace("{paymentId}", paymentId),
                "{\"txid\":\"" + txId + "\"}");
        return JSONUtil.parseObj(rs);
    }

    public JSONObject paymentCancel(String paymentId, String txId) {
        String rs = request(Method.POST, this.keyAuth(),
                piProperties.getPaymentCancelUrl().replace("{paymentId}", paymentId),
                "{\"txid\":\"" + txId + "\"}");
        return JSONUtil.parseObj(rs);
    }

    public JSONArray paymentIncompleteList() {
        String rs = request(Method.GET, this.keyAuth(),
                piProperties.getPaymentIncompleteUrl(), null);
        return JSONUtil.parseObj(rs).getJSONArray("incomplete_server_payments");
    }

    public JSONObject blockChainTransQuery(String link) {
        String rs = request(Method.GET, null, link, null);
        return JSONUtil.parseObj(rs);
    }

    private String request(Method method, String auth, String url, String body) {
        long t = System.currentTimeMillis();
        try (HttpResponse response = HttpUtil.createRequest(method == null ? Method.GET : method, url)
                .auth(auth)
                .body(body, body == null ? null : ContentType.JSON.toString())
                .timeout(timeout)
                .execute()) {

            log.info("req: url={}, body={}, resp: status={}, body={} rt={}ms", url, body,
                    response.getStatus(), response.body(), (System.currentTimeMillis() - t));

            if (response.getStatus() == 401) {
                throw new CloudException(ExceptionConstant.授权失败);
            }

            if (!response.isOk()) {
                throw new CloudException(ExceptionConstant.网络异常);
            }

            return response.body();
        } catch (CloudException e) {
            throw e;
        } catch (Exception e) {
            log.error("req: url={}, body={}, rt={}ms, resp error!", url, body, (System.currentTimeMillis() - t), e);
            throw new CloudException(ExceptionConstant.网络异常);
        }
    }

    private String keyAuth() {
        return "Key " + piProperties.getServerAccessKey();
    }

}
