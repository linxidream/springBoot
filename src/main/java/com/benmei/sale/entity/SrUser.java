package com.benmei.sale.entity;

public class SrUser {
    private Integer user_id; //用户id
    private String user_name;   //用户名
    private String user_password;   //密码
    private String user_head_portrait;  //用户头像
    private String user_type;   //用户类型  1：存在  0：删除

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_head_portrait() {
        return user_head_portrait;
    }

    public void setUser_head_portrait(String user_head_portrait) {
        this.user_head_portrait = user_head_portrait;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
}
