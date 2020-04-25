package com.mrcoder.frameservice.model.po;

import javax.persistence.Table;

import com.alibaba.excel.annotation.ExcelProperty;
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
@Table(name = "frame_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = -6525290662533134200L;

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

