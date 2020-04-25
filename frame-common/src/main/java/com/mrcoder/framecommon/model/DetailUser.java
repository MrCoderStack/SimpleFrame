package com.mrcoder.framecommon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Description: 用户详细信息
 * @author: mrcoder
 */
@Getter
@Setter
@Accessors(chain = true)
public class DetailUser {

    private static final long serialVersionUID = 5541956614405827585L;

    // 用户ID
    @JsonProperty("id")
    private Long userId;

    // 用户昵称
    @JsonProperty("nickname")
    private String nickName;

    // 用户全名
    @JsonProperty("fullname")
    private String fullName;

    // 用户登录密码
    @JsonProperty("password")
    private String accessToken;


}
