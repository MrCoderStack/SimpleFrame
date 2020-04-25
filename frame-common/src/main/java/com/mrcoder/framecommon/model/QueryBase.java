package com.mrcoder.framecommon.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Description: 查询条件基础类
 * @author: mrcoder
 */
@Getter
@Setter
@Accessors(chain = true)
public class QueryBase extends PageBase {

    private static final long serialVersionUID = 6092604828976224192L;

    private Long id;

    // 根据名称查询
    private String name;

    // 根据手机号查询
    private String mobile;

    // 根据类型查询
    private Integer type;

    // 根据状态查询
    private Integer status;

    // 开始时间
    private Date beginDate;

    // 结束时间
    private Date endDate;
}