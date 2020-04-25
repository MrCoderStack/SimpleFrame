package com.mrcoder.frameservice.model.po;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

/**
 * @Description: User实体类
 * @author: mrcoder
 */
@Getter
@Setter
@Data
@Table(name = "frame_user")
public class UserModel extends BaseEntity {

    private static final long serialVersionUID = 7831826477681486415L;

    @ExcelProperty(index = 1)
    private String name;

    @ExcelProperty(index = 2)
    private String nickName;

    @ExcelProperty(index = 3)
    private Integer age;

    @ExcelProperty(index = 4)
    private String email;

    @ExcelProperty(index = 5)
    private String phone;

    private Integer status;

}

