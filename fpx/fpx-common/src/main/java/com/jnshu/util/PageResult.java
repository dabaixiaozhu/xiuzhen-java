package com.jnshu.util;

import java.io.Serializable;
import java.util.List;

/**
 * @author L
 */
public class PageResult<T> implements Serializable {
    // 1.当前页数
    private int pageNum;
    // 2.每页显示条数
    private int size;
    // 3.总条数
    private int total;
    // 4.总页数
    private int pages;
    // 5.每页包含的具体内容
    private List<T> items;

    public PageResult() {
    }

    public PageResult(int pageNum, int size, int total, int pages, List<T> items) {
        this.pageNum = pageNum;
        this.size = size;
        this.total = total;
        this.pages = pages;
        this.items = items;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "pageNum=" + pageNum +
                ", size=" + size +
                ", total=" + total +
                ", pages=" + pages +
                ", items=" + items +
                '}';
    }
}
