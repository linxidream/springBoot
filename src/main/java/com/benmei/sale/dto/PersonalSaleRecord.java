package com.benmei.sale.dto;

import com.benmei.sale.entity.SrCategory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class PersonalSaleRecord {
    private Integer sale_id;
    private String category_name;
    private BigDecimal sale_money;
    private Timestamp update_time;
    private String sale_comment;

    public Integer getSale_id() {
        return sale_id;
    }

    public void setSale_id(Integer sale_id) {
        this.sale_id = sale_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public BigDecimal getSale_money() {
        return sale_money;
    }

    public void setSale_money(BigDecimal sale_money) {
        this.sale_money = sale_money;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    public String getSale_comment() {
        return sale_comment;
    }

    public void setSale_comment(String sale_comment) {
        this.sale_comment = sale_comment;
    }

}
