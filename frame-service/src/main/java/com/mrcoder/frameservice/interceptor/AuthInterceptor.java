package com.mrcoder.frameservice.interceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.mrcoder.framecommon.api.UserSystemApi;
import com.mrcoder.framecommon.auth.AuthCheck;
import com.mrcoder.framecommon.auth.CurrentUserUtil;
import com.mrcoder.framecommon.constants.ErrorCodeEnum;
import com.mrcoder.framecommon.exception.BusinessException;
import com.mrcoder.framecommon.model.DetailUser;
import com.mrcoder.framecommon.utils.RedisCacheUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 登录/权限拦截器
 * @author: mrcoder
 */
@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserSystemApi useSystemApi;


    @Value("${system.appName}")
    private String appName;

    @Autowired(required = false)
    private RedisCacheUtil redisCacheUtil;

    /**
     * 登录拦截
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (Pattern.matches("^.*monitor/.*$", request.getRequestURL().toString()) == false) {
            log.info(String.format("请求开始： %s >>> requestUrl： %s", request.getMethod(), request.getRequestURL().toString()));
        }

        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {

            AuthCheck authCheck = ((HandlerMethod) handler).getMethodAnnotation(AuthCheck.class);
            // 默认拦截所有的Controller接口【除去标注了AuthCheck(loginRequired = false)】
            if (authCheck == null || authCheck.loginRequired()) {
                // 进行登录验证() 权限验证(resourceCode有值)
                return this.checkLoginAndPermission(authCheck, request, response);
            }
        }
        return true;
    }

    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间，还可以进行一些资源清理，类似于try-catch-finally中的finally，但仅调用处理器执行链中
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object,
     * java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 移除当前登录用户
        CurrentUserUtil.removeCurrentUser();
        if (Pattern.matches("^.*monitor/.*$", request.getRequestURL().toString()) == false) {
            log.info(String.format("请求结束： %s >>> requestUrl： %s", request.getMethod(), request.getRequestURL().toString()));
        }
    }

    /**
     * 登录、权限验证 ( 登录验证【一定执行】、权限验证【authCheck为Null,或者resourceCode不为空则执行】 )
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    private boolean checkLoginAndPermission(AuthCheck authCheck, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorization = request.getHeader("Authorization");
        log.info("authorization: " + authorization);
        if (StringUtils.isNotBlank(authorization) && StringUtils.isNotBlank(authorization.replace("Bearer ", ""))) {
            String accessToken = authorization.replace("Bearer ", "");

            //登陆验证
            DetailUser currUser = this.useSystemApi.getUserDetailByAccessToken(accessToken);
            if (currUser == null) {
                log.error("根据userId获取用户基本详细失败");
                throw new BusinessException(ErrorCodeEnum.NOT_LOGIN, "根据user_id获取用户详细信息失败,请重新登录!");
            }
            currUser.setAccessToken(accessToken);
            // 保存当前登录用户
            CurrentUserUtil.setCurrentUser(currUser);
            // 判断缓存是否存在，不存在则添加
            if (redisCacheUtil.exists(String.valueOf(currUser.getUserId()))) {
                // 存在不处理
            } else {
                // 不存在添加，并设定缓存的失效时间为一天
                redisCacheUtil.set(String.valueOf(currUser.getUserId()), JSONObject.toJSONString(currUser), 86400L, TimeUnit.SECONDS);
            }
        } else {
            // 未登录, 直接抛出异常
            throw new BusinessException(ErrorCodeEnum.NOT_LOGIN, "用户未登录，请退出重新登录!");
        }
        return true;
    }

}

