package cn.teamstack.common.core.bean;

import java.io.Serializable;
import java.util.List;

public class PageData<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 9043039608333744258L;
    // 总数
    private long total = 0;

    // 分页数据
    private List<T> data;


    public PageData(long total, List<T> data) {
        super();
        this.total = total;
        this.data = data;
    }

    public PageData() {
        super();
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
