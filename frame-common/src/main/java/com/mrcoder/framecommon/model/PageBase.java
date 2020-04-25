package com.mrcoder.framecommon.model;

import java.io.Serializable;

/**
 * @Description: 分页基础信息类
 * @author: mrcoder
 */
public class PageBase implements Serializable {

    private static final long serialVersionUID = -2867841630953717768L;

    // 当前默认第一页
    private static final Integer DEFAULT_PAGENUM = Integer.valueOf(1);

    // 每页默认显示10条记录
    private static final Integer DEFAULT_PAGESIZE = Integer.valueOf(10);

    // 当前第几页
//	@Expose(serialize = false)
    private Integer pageNum = DEFAULT_PAGENUM;

    // 每页显示记录数
//	@Expose(serialize = false)
    private Integer pageSize = DEFAULT_PAGESIZE;

    @SuppressWarnings("unused")
    private Integer limit;

    @SuppressWarnings("unused")
    private Integer offset;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getLimit() {
        return getPageSize();
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return (getPageNum() - 1) * getPageSize();
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public PageBase(Integer pageNum, Integer pageSize) {
        super();
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageBase() {
        super();
    }

}