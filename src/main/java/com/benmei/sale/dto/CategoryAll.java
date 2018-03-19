package com.benmei.sale.dto;

import java.math.BigDecimal;

public class CategoryAll {
    private Integer category_id;
    private BigDecimal category_money;

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public BigDecimal getCategory_money() {
        return category_money;
    }

    public void setCategory_money(BigDecimal category_money) {
        this.category_money = category_money;
    }
}
