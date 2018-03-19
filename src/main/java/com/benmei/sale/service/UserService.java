package com.benmei.sale.service;

import com.benmei.sale.common.RetInfo;
import com.benmei.sale.dto.Page;
import com.benmei.sale.dto.SaleInsert;
import com.benmei.sale.dto.UserLogin;
import com.benmei.sale.entity.SrUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    public List<SrUser> selectAll();

    public RetInfo login(UserLogin userLogin);

    public RetInfo index();

    public RetInfo Detail(Page page);

    public RetInfo insertSaleData(SaleInsert saleInsert);

    public RetInfo updateSaleData(SaleInsert saleInsert);

    public RetInfo deleteSaleData(Integer sale_id);

    public RetInfo updateUserDetail(String user_id, String user_name, String user_password, MultipartFile user_head_portrait);

}
