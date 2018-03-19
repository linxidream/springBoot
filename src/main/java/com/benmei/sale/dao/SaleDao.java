package com.benmei.sale.dao;

import com.benmei.sale.dto.*;
import com.benmei.sale.entity.SrSale;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SaleDao {

    public List<UserSale> findUserSale();

    public List<UserSaleCount> getUserSaleDayCount();

    public List<UserSaleCount> getUserSaleWeekCount();

    public List<UserSaleCount> getUserSaleMonthCount();

    public List<PersonalSaleRecord> findAllSaleByUserId(@Param("page") Page page);

    public Integer findAllSaleByUserIdCount(@Param("page") Page page);

    public Integer insertSaleRecord(@Param("pojo") SrSale srSale);

    public Integer updateSaleRecord(@Param("pojo") SrSale srSale);

}
