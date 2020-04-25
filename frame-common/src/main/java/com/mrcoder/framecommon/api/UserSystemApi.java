package com.mrcoder.framecommon.api;

import javax.annotation.PostConstruct;

import com.mrcoder.framecommon.exception.BusinessException;
import com.mrcoder.framecommon.model.DetailUser;
import com.mrcoder.framecommon.utils.RestTemplateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 用户系统API
 * @author: mrcoder
 */
@Slf4j
public class UserSystemApi {

    @Value("${userSystem.apiHost}")
    private String userSystemApiHost;

    @PostConstruct
    public void init() {
        log.info("UserInterfaceApi init [userSystemApiHost: {}] ......", userSystemApiHost);
    }

    @Autowired(required = false)
    private RestTemplateUtil restTemplateUtil;

    @Autowired(required = false)
    private RestTemplate restTemplate;

    // 根据accessToken获取用户ID URL
    private static final String QUERY_USER_BY_ACCESSTOKEN_URL = "/auth/v1/user/";


    /**
     * 获取用户信息
     *
     * @param accessToken
     * @return
     */
    public DetailUser getUserDetailByAccessToken(String accessToken) {
        if (StringUtils.isBlank(accessToken)) {
            log.error("根据accessToken获取用户ID==>>参数异常，accessToken为空");
            throw new BusinessException("根据Token获取用户ID==>>参数异常，accessToken为空");
        }

        DetailUser response = new DetailUser();
        response.setUserId(1l);
        response.setFullName("测试用户");
        response.setNickName("测试用户nick");
        response.setAccessToken(accessToken);
        return response;

//        DetailUser response = null;
//        try {
//            response = this.restTemplateUtil.syncGet(this.userSystemApiHost + QUERY_USER_BY_ACCESSTOKEN_URL + accessToken, DetailUser.class);
//            if (response != null) { // 成功的Code
//                return response;
//            }
//            log.error("根据accessToken获取用户失败, [accessToken: {}, response: {}]", accessToken, JSON.toJSONString(response));
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("根据accessToken获取用户异常, [accessToken: {}, errMsg: {}]", accessToken, e.getMessage());
//        }
//        return null;
    }
}

