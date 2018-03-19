package com.benmei.sale.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class UserSale {
    private String user_name;
    private BigDecimal sale_money;
    private Timestamp sale_time;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public BigDecimal getSale_money() {
        return sale_money;
    }

    public void setSale_money(BigDecimal sale_money) {
        this.sale_money = sale_money;
    }

    public Timestamp getSale_time() {
        return sale_time;
    }

    public void setSale_time(Timestamp sale_time) {
        this.sale_time = sale_time;
    }
}
