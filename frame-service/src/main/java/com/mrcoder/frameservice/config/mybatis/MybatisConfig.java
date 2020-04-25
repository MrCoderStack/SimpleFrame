package com.mrcoder.frameservice.config.mybatis;

import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.ExecutorType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tk.mybatis.mapper.autoconfigure.ConfigurationCustomizer;

/**
 * @Description: Mybatis配置类
 * @author: mrcoder
 */
@Configuration
public class MybatisConfig {

    // 当前环境
    @Value("${spring.profiles.active:default}")
    private String profile;

    /**
     * 自定义MyBatis的配置规则；给容器中添加一个ConfigurationCustomizer；
     *
     * @return
     */
    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return new ConfigurationCustomizer() {

            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setCacheEnabled(Boolean.TRUE);
                configuration.setLazyLoadingEnabled(Boolean.TRUE);
                configuration.setAggressiveLazyLoading(Boolean.TRUE);
                configuration.setUseGeneratedKeys(Boolean.TRUE);
                configuration.setDefaultExecutorType(ExecutorType.SIMPLE);
                configuration.setMapUnderscoreToCamelCase(Boolean.TRUE);// 开启驼峰映射
                configuration.setCallSettersOnNulls(Boolean.TRUE);// 开启null字段返回
                if (!profile.equalsIgnoreCase("prod")) { // 非生产环境打印SQL语句
                    configuration.setLogImpl(StdOutImpl.class);
                }
            }
        };

    }
}
