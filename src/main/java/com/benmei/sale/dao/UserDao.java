package com.benmei.sale.dao;

import com.benmei.sale.dto.UserSale;
import com.benmei.sale.entity.SrUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    public List<SrUser> selectUser();

    public SrUser userLogin(@Param("user_name") String user_name);

    public SrUser selectUserDetail(@Param("user_id")Integer user_id);

    public Integer updateUserProperty(@Param("pojo") SrUser srUser);

}
