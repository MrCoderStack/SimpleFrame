package com.mrcoder.frameservice.mapper;


import java.util.List;

import com.mrcoder.framecommon.model.QueryBase;
import com.mrcoder.frameservice.model.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @author mrcoder
 * @Description: User通用Mapper接口
 */
public interface UserMapper extends Mapper<User>, InsertListMapper<User> {

    @Select("select * from frame_user where id = #{userId}")
    User queryUserById(@Param("userId") Long userId);


    int countUserList(QueryBase queryBase);


    List<User> queryUserList(QueryBase queryBase);

}

