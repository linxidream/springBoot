package com.benmei.sale.dto;

import java.math.BigDecimal;

public class UpdateSaleRecord {
    private Integer category_id;
    private BigDecimal sale_money;
    private String sale_comment;

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public BigDecimal getSale_money() {
        return sale_money;
    }

    public void setSale_money(BigDecimal sale_money) {
        this.sale_money = sale_money;
    }

    public String getSale_comment() {
        return sale_comment;
    }

    public void setSale_comment(String sale_comment) {
        this.sale_comment = sale_comment;
    }
}
