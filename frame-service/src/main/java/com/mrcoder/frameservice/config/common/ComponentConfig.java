package com.mrcoder.frameservice.config.common;

import com.mrcoder.framecommon.aop.RepeatSubmitAop;
import com.mrcoder.framecommon.config.validator.ValidatorConfig;
import com.mrcoder.framecommon.exception.GlobalExpectionHandler;
import com.mrcoder.framecommon.utils.NumberGeneratorUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Description: 常用组件配置类
 * @author: mrcoder
 */
@Configuration
public class ComponentConfig {

    /**
     * 注入HibernateValidator参数校验配置类
     *
     * @return
     */
    @Bean
    public ValidatorConfig validatorConfig() {
        return new ValidatorConfig();
    }

    /**
     * 注入全局异常处理器
     *
     * @return
     */
    @Bean
    public GlobalExpectionHandler globalExpectionHandler() {
        return new GlobalExpectionHandler();
    }

    /**
     * 唯一编号生成器配置
     *
     * @param redisTemplate
     */
    @Bean
    public NumberGeneratorUtil initOrderIdGenerator(RedisTemplate<String, Object> redisTemplate) {
        Long machineCodeSeed = redisTemplate.opsForValue().increment(NumberGeneratorUtil.NUMBER_GENERATOR_MACHINE_CODE_KEY, 1L);
        // 初始化机器码
        NumberGeneratorUtil.configureMachineCode(machineCodeSeed);
        return null;
    }

    /**
     * 防止重复提交切面配置
     *
     * @return
     */
    @Bean
    @ConditionalOnClass({RedisTemplate.class})
    public RepeatSubmitAop repeatSubmitAop() {
        return new RepeatSubmitAop();
    }

}

