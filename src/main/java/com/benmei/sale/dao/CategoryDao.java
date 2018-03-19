package com.benmei.sale.dao;

import com.benmei.sale.dto.CategoryAll;
import com.benmei.sale.dto.CategorySale;
import com.benmei.sale.entity.SrCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryDao {

    public  List<CategoryAll> findCategoryData();

    public List<SrCategory> findCatagoryList();

    public String findCategoryName(@Param("category_id") Integer category_id);
}
