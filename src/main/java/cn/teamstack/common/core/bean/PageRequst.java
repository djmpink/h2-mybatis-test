package cn.teamstack.common.core.bean;

/**
 * 分页参数
 *
 * @author Administrator
 */
public class PageRequst extends BaseBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer page;
    private Integer pageSize;
    private Integer start;
    private Integer total;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
