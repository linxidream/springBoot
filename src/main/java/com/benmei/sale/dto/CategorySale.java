package com.benmei.sale.dto;

import java.math.BigDecimal;

public class CategorySale {
    private String category_name;
    private BigDecimal category_money = BigDecimal.valueOf(0);

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public BigDecimal getCategory_money() {
        return category_money;
    }

    public void setCategory_money(BigDecimal category_money) {
        this.category_money = category_money;
    }
}
