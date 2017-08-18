package cn.teamstack.common.core.bean;

import java.io.Serializable;


/**
 * 分页数据
 *
 * @author Administrator
 */
public class PageForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3630005834320961232L;
    protected int page = 1;
    protected int start;
    protected int limit = 1000;
    // 排序字段
    protected String sort;
    // 排序方向 desc asc
    protected String dir;

    private long total;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public boolean doPage() {
        return page > 0 && limit > 0;
    }

    public String getSortCluse() {
        if (sort == null || sort.trim().length() == 0) {
            return null;
        }
        if (dir == null || dir.trim().length() == 0) {
            return sort;
        }
        return sort + " " + dir;
    }
}
