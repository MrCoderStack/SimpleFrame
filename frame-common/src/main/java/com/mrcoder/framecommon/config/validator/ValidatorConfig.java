package com.mrcoder.framecommon.config.validator;

import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * @Description: HibernateValidator参数校验配置类
 * @author: mrcoder
 */
public class ValidatorConfig {

    /**
     * JSR和Hibernate validator的校验只能对Object的属性进行校验 不能对单个的参数进行校验 spring
     * 在此基础上进行了扩展，添加了MethodValidationPostProcessor拦截器 可以实现对方法参数的校验
     *
     * @return
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator(validator());
        return processor;
    }

    @Bean
    public static Validator validator() {
        // 快速返回模式，有一个验证失败立即返回错误信息
        return Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();
    }
}
