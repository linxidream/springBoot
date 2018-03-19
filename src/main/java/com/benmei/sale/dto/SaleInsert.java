package com.benmei.sale.dto;

public class SaleInsert {
    private Integer sale_id;
    private Integer category_id;
    private Integer user_id;
    private Double sale_money;
    private String sale_comment;

    public Integer getSale_id() {
        return sale_id;
    }

    public void setSale_id(Integer sale_id) {
        this.sale_id = sale_id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Double getSale_money() {
        return sale_money;
    }

    public void setSale_money(Double sale_money) {
        this.sale_money = sale_money;
    }

    public String getSale_comment() {
        return sale_comment;
    }

    public void setSale_comment(String sale_comment) {
        this.sale_comment = sale_comment;
    }
}
