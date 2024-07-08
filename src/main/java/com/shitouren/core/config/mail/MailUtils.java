package com.shitouren.core.config.mail;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;


@Component
@Slf4j
public class MailUtils {

    @Resource
    private MailProperties mailProperties;

    public static final String subject = "MetaAI";

    public static final String content = "【MetaAI】Your verification code is %s, valid for five minutes.";

    public void sendCode(String to, String code){
        log.info("to {} code {}", to, code);
        sendMail(to, subject, String.format(content, code), false, null);
    }

    public void sendMail(String to, String subject, String content, boolean isHtml, File... files) {
        MailUtil.send(this.randomGetAccount(), to, subject, content, isHtml, files);
    }

    private MailAccount randomGetAccount() {
        return RandomUtil.randomEle(mailProperties.getAccounts());
    }

}
