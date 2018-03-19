package com.benmei.sale.dto;

import com.benmei.sale.entity.SrUser;

import java.util.List;

public class IndexData {
    private SrUser srUser;
    private List<UserSale> userSaleList;
    private List<CategorySale> categorySaleList;
    private List<UserSaleCount> userDayList;
    private List<UserSaleCount> userWeekList;
    private List<UserSaleCount> userMonthList;

    public SrUser getSrUser() {
        return srUser;
    }

    public void setSrUser(SrUser srUser) {
        this.srUser = srUser;
    }

    public List<UserSale> getUserSaleList() {
        return userSaleList;
    }

    public void setUserSaleList(List<UserSale> userSaleList) {
        this.userSaleList = userSaleList;
    }

    public List<CategorySale> getCategorySaleList() {
        return categorySaleList;
    }

    public void setCategorySaleList(List<CategorySale> categorySaleList) {
        this.categorySaleList = categorySaleList;
    }

    public List<UserSaleCount> getUserDayList() {
        return userDayList;
    }

    public void setUserDayList(List<UserSaleCount> userDayList) {
        this.userDayList = userDayList;
    }

    public List<UserSaleCount> getUserWeekList() {
        return userWeekList;
    }

    public void setUserWeekList(List<UserSaleCount> userWeekList) {
        this.userWeekList = userWeekList;
    }

    public List<UserSaleCount> getUserMonthList() {
        return userMonthList;
    }

    public void setUserMonthList(List<UserSaleCount> userMonthList) {
        this.userMonthList = userMonthList;
    }

}

