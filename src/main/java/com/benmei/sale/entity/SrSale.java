package com.benmei.sale.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class SrSale {
    private Integer sale_id; //销售记录id
    private Integer user_id;    //用户id
    private Integer category_id;    //分类id
    private String category_name;   //分类名
    private BigDecimal sale_money;  //销售金额
    private String sale_comment;    //备注
    private Timestamp create_time;   //创建时间
    private Timestamp update_time;  //更新时间
    private String sale_state; //销售状态  1:存在   0：删除

    public Integer getSale_id() {
        return sale_id;
    }

    public void setSale_id(Integer sale_id) {
        this.sale_id = sale_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
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

    public String getSale_comment() {
        return sale_comment;
    }

    public void setSale_comment(String sale_comment) {
        this.sale_comment = sale_comment;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    public String getSale_state() {
        return sale_state;
    }

    public void setSale_state(String sale_state) {
        this.sale_state = sale_state;
    }
}
