package com.shitouren.core.config.mail;

import cn.hutool.extra.mail.MailAccount;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.List;


@ConfigurationProperties(prefix = "meta.mail")
@Data
@Slf4j
public class MailProperties {

    private int port;
    private String host;
    private boolean sslEnable;
    private boolean debug;

    private List<MailAccount> accounts;

    @PostConstruct
    public void afterPropertiesSet(){
        accounts.forEach(x -> {
            x.setAuth(true);
            x.setHost(host);
            x.setPort(port);
            x.setSslEnable(sslEnable);
            x.setUser(x.getFrom());
            x.setDebug(debug);
        });
        log.info("init {} mail account completed~", accounts.size());
    }

}
