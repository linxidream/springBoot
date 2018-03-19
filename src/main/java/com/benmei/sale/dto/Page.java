package com.benmei.sale.dto;

public class Page {
    private Integer user_id;
    private Integer current_page = 1;
    private Integer page_size = 20;
    private Integer off_set;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(Integer current_page) {
        this.current_page = current_page;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }

    public Integer getOff_set() {
        return ( current_page - 1 ) * page_size;
    }

    public void setOff_set(Integer off_set) {
        this.off_set = off_set;
    }
}
