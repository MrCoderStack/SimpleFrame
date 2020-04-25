package com.mrcoder.framecommon.auth;


import com.mrcoder.framecommon.model.DetailUser;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 当前登录用户工具类
 * @author: mrcoder
 */
@Slf4j
public class CurrentUserUtil {

    /**
     * 设置当前登录用户
     *
     * @param currUser
     */
    public static void setCurrentUser(DetailUser currUser) {
        log.info("保存当前登录用户......[userId: {}]", currUser.getUserId());
        UserThreadLocal.setCurrUser(currUser);
    }

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    public static DetailUser getCurrentUser() {
        return UserThreadLocal.getCurrUser();
    }

    /**
     * 获取当前登录用户的userID
     *
     * @return
     */
    public static Long getCurrUserId() {
        return getCurrentUser().getUserId();
    }

    /**
     * 获取当前登录用户的uesrName
     *
     * @return
     */
    public static String getCurrUserName() {
        return getCurrentUser().getFullName();
    }

    /**
     * 获取当前登录用户Token
     *
     * @return
     */
    public static String getAccessToken() {
        return getCurrentUser().getAccessToken();
    }


    /**
     * 移除当前登录用户
     */
    public static void removeCurrentUser() {
        if (getCurrentUser() != null) {
            log.info("移除当前登录用户......[userId: {}]", getCurrentUser().getUserId());
            UserThreadLocal.removeCurrUser();
        }
    }
}
