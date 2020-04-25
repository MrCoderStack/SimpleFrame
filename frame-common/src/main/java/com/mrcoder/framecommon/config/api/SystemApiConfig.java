package com.mrcoder.framecommon.config.api;

import com.mrcoder.framecommon.api.UserSystemApi;
import com.mrcoder.framecommon.utils.RestTemplateUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 第三方系统API配置类
 * @author: mrcoder
 */
@Configuration
public class SystemApiConfig {
    @Bean
    @ConditionalOnProperty("userSystem.apiHost")
    @ConditionalOnBean({RestTemplateUtil.class})
    public UserSystemApi userSystemApi() {
        return new UserSystemApi();
    }
}
