package cn.edots.ormosia.model;

import java.io.Serializable;
import java.util.List;

public class Pagination<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 4844808199476586493L;

    private int page = 1;
    private int size = 20;
    private long total;
    private int count;
    private List<T> domains;
    private String by;
    private boolean desc;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getDomains() {
        return domains;
    }

    public void setDomains(List<T> domains) {
        this.domains = domains;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public boolean isDesc() {
        return desc;
    }

    public void setDesc(boolean desc) {
        this.desc = desc;
    }
}
