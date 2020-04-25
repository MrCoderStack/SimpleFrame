package com.mrcoder.framecommon.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * @Description: User实体类
 * @author: mrcoder
 */
@Getter
@Setter
@Data
public class User {

    private static final long serialVersionUID = 4373368355774438221L;

    private Long id;

    private String name;

    private String nickName;

    private Integer age;

    private String email;

    private String phone;

    private Integer status;

}

