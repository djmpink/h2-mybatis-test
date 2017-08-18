package cn.teamstack.common.core.bean;

import java.io.Serializable;
import java.util.List;

public class PageRespnose<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<T> list;
    private long total;
    private int page;
    private int pageSize;

    public PageRespnose() {
    }

    public PageRespnose(List<T> list, long total) {
        this.list = list;
        this.total = total;
    }

    public PageRespnose(List<T> list, long total, int page, int pageSize) {
        this.list = list;
        this.total = total;
        this.page = page;
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
