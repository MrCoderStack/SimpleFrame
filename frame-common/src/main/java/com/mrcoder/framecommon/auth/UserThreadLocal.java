package com.mrcoder.framecommon.auth;


import com.mrcoder.framecommon.model.DetailUser;

/**
 * @Description: 用户线程本地变量
 * @author: mrcoder
 */
public class UserThreadLocal {

    private static final ThreadLocal<DetailUser> USERLOCAL = new ThreadLocal<DetailUser>();

    /**
     * 将用户设置到ThreadLocal中
     *
     * @param user
     */
    public static void setCurrUser(DetailUser user) {
        USERLOCAL.set(user);
    }

    /**
     * 从ThreadLocal中获取当前用户
     *
     * @return
     */
    public static DetailUser getCurrUser() {
        return USERLOCAL.get();
    }

    /**
     * 从ThreadLocal中删除当前用户
     *
     * @return
     */
    public static void removeCurrUser() {
        USERLOCAL.remove();
    }
}