package com.dongxie.notify;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.codecentric.boot.admin.config.NotifierConfiguration;

@Configuration
@ConditionalOnProperty(prefix = "spring.boot.admin.notify.dingtalk", name = {
    "webhook-token"
})
@AutoConfigureBefore({
    NotifierConfiguration.NotifierListenerConfiguration.class,
    NotifierConfiguration.CompositeNotifierConfiguration.class
})
public class DingTalkNotifierConfiguration {

    @Value("${spring.boot.admin.notify.dingtalk.webhook-token}")
    private String webHookToken;

    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties(prefix = "spring.boot.admin.notify.dingtalk")
    public DingTalkStatusChangeNotifier dingTalkNotifier() {
        return new DingTalkStatusChangeNotifier(webHookToken);
    }
}
