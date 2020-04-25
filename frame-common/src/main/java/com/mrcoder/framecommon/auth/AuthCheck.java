package com.mrcoder.framecommon.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 权限验证自定义注解
 * @author: mrcoder
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {

    // 是否需要登录验证, 默认为true
    boolean loginRequired() default true;

    // 资源Code, loginRequired为True，并且resourceCode有值则表示需要进行权限验证【进行权限验证必须先进行登录验证】
    String resourceCode() default "";
}
