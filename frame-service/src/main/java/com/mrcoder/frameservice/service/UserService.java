package com.mrcoder.frameservice.service;

import com.mrcoder.framecommon.model.PageBean;
import com.mrcoder.framecommon.model.QueryBase;
import com.mrcoder.frameservice.model.po.User;

import java.util.List;

public interface UserService {

    User queryUserById(Long userId);

    PageBean<User> queryUserList(QueryBase queryBase);

    void dealUserList(List<User> users);
}
