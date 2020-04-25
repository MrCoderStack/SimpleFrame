package com.mrcoder.frameservice.model.po;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Description: 基础Entity类
 * @author: mrcoder
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 5408499522717153485L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public BaseEntity() {
        super();
    }

    public BaseEntity(Long id) {
        super();
        this.id = id;
    }

    public BaseEntity(Long id, Date createTime, Date updateTime) {
        super();
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public BaseEntity(Date createTime) {
        super();
        this.createTime = createTime;
    }

}
