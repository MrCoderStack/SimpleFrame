package com.mrcoder.framecommon.model;

import java.io.Serializable;
import java.util.List;

import lombok.NoArgsConstructor;

/**
 * @Description: 分页数据对象
 * @author: mrcoder
 */
@NoArgsConstructor
public class PageBean<T> implements Serializable {

    private static final long serialVersionUID = -3682869799717135424L;

    // 默认当前页数
    public static final Integer DEFAULT_PAGE_NUM = 1;

    // 默认每页记录数
    public static final Integer DEFAULT_PAGE_SIZE = 10;

    // 最大每页记录数
    private static final Integer MAX_PAGE_SIZE = 1000;

    // 当前页
    public Integer pageNum = DEFAULT_PAGE_NUM;

    // 每页的记录条数
    public Integer pageSize;

    // 总记录数
    public Integer total;

    // 总页数
    public Integer totalPage;

    // 分页数据
    public List<T> list;

    public PageBean(Integer pageNum, Integer pageSize, Integer total, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
        setTotalPage();
    }

    public int getPageNum() {
        return pageNum;
    }

    /**
     * 设置当前页数
     *
     * @param pageNum
     */
    public void setCurrPage(Integer pageNum) {
        if (pageNum == null) {
            this.pageNum = DEFAULT_PAGE_NUM;
        } else if (pageNum > this.totalPage && this.totalPage != 0) {
            pageNum = totalPage;
        } else {
            this.pageNum = pageNum;
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public int getTotalPage() {
        return totalPage;
    }

    /**
     * 设置总页数
     *
     * @return
     */
    public void setTotalPage() {
        this.totalPage = (this.total - 1) / this.pageSize + 1;
    }

    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页显示记录数
     *
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        if (pageSize >= 1 && pageSize <= MAX_PAGE_SIZE) {
            this.pageSize = pageSize;
        } else {
            this.pageSize = MAX_PAGE_SIZE;
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setData(List<T> list) {
        this.list = list;
    }

    /**
     * 填充PageBean数据
     *
     * @param data  分页数据
     * @param total 总记录数
     * @return
     */
    public void completePage(List<T> data, int total) {
        this.setData(data);
        this.setTotal(total);
        this.setTotalPage();
    }

    @Override
    public String toString() {
        return "PageBean [pageNum=" + pageNum + ", pageSize=" + pageSize + ", total=" + total + ", totalPage=" + totalPage + ", list=" + list + "]";
    }

}