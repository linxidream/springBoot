package com.benmei.sale.dto;

import java.math.BigDecimal;

public class UserSaleCount {
    private String user_head_protrait;
    private String user_name;
    private BigDecimal user_sale_count;

    public String getUser_head_protrait() {
        return user_head_protrait;
    }

    public void setUser_head_protrait(String user_head_protrait) {
        this.user_head_protrait = user_head_protrait;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public BigDecimal getUser_sale_count() {
        return user_sale_count;
    }

    public void setUser_sale_count(BigDecimal user_sale_count) {
        this.user_sale_count = user_sale_count;
    }
}
