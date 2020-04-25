package com.mrcoder.frameservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.mrcoder.framecommon.model.PageBean;
import com.mrcoder.framecommon.model.QueryBase;
import com.mrcoder.frameservice.mapper.UserMapper;
import com.mrcoder.frameservice.model.po.User;
import com.mrcoder.frameservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User queryUserById(Long userId) {
        return userMapper.queryUserById(userId);
    }

    @Override
    public PageBean<User> queryUserList(QueryBase queryBase) {
        int total = 0;
        List<User> data = new ArrayList<User>();

        total = userMapper.countUserList(queryBase);
        if (total > 0) {
            data = userMapper.queryUserList(queryBase);
        }
        return new PageBean<User>(queryBase.getPageNum(), queryBase.getPageSize(), total, data);
    }

    @Override
    public void dealUserList(List<User> users) {
        log.info("UserService {}条数据，开始存储数据库！", users.size());
        log.info(JSON.toJSONString(users));
        log.info("UserService 存储数据库成功！");
    }
}
