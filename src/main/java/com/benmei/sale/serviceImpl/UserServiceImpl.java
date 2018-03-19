package com.benmei.sale.serviceImpl;

import com.benmei.sale.common.RetInfo;
import com.benmei.sale.dao.CategoryDao;
import com.benmei.sale.dao.SaleDao;
import com.benmei.sale.dao.UserDao;
import com.benmei.sale.dto.*;
import com.benmei.sale.entity.SrCategory;
import com.benmei.sale.entity.SrSale;
import com.benmei.sale.entity.SrUser;
import com.benmei.sale.service.UserService;
import com.benmei.sale.util.AliyunOssUtil;
import com.benmei.sale.util.MD5;
import com.sun.org.apache.bcel.internal.generic.RET;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    private final CategoryDao categoryDao;

    private final SaleDao saleDao;

    private final String WAIJIAOJUN_SALE_RECORD = "sale_record/user/";

    private final String PREFIX = "http://file.waijiaojun.com/";

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public UserServiceImpl(UserDao userDao,CategoryDao categoryDao,SaleDao saleDao){
        this.userDao = userDao;
        this.categoryDao = categoryDao;
        this.saleDao = saleDao;
    }

    @Override
    public List<SrUser> selectAll() {
        List<SrUser> userList = userDao.selectUser();
        return userList;
    }

    @Override
    public RetInfo login(UserLogin userLogin) {
        RetInfo retInfo = new RetInfo();
        try {
            SrUser srUser = new SrUser();
            srUser = userDao.userLogin(userLogin.getName());
            if(srUser == null){
                retInfo.setMark("-1");
                retInfo.setTip("用户不存在");
                return retInfo;
            }
            if(MD5.code(userLogin.getPassword()).equals(srUser.getUser_password())){
                retInfo.setMark("0");
                retInfo.setTip("登陆成功");
                retInfo.setObj(srUser);
                return retInfo;
            }else{
                retInfo.setMark("-1");
                retInfo.setTip("密码错误");
                return retInfo;
            }
        }catch (Exception e){
            e.printStackTrace();
            retInfo.setMark("-1");
            retInfo.setTip("登录错误");
            return retInfo;
        }
    }

    @Override
    @Transactional
    public RetInfo index() {
        RetInfo retInfo = new RetInfo();
        try{
            IndexData indexData = new IndexData();
            //本月实时成交额
            List<UserSale> userSaleList = new ArrayList<>();
            userSaleList = saleDao.findUserSale();
            indexData.setUserSaleList(userSaleList);
            //业务模块
            List<CategorySale> categorySaleList = new ArrayList<>();

            List<CategoryAll> categoryAllList = new ArrayList<>();
            List<SrCategory> srCategoryList = new ArrayList<>();
            categoryAllList = categoryDao.findCategoryData();
            srCategoryList = categoryDao.findCatagoryList();
            for(SrCategory srCategory : srCategoryList){
                CategorySale categorySale = new CategorySale();
                categorySale.setCategory_name(srCategory.getCategory_name());
                for(CategoryAll categoryAll : categoryAllList){
                    if(categoryAll.getCategory_id() == srCategory.getCategory_id()){
                        categorySale.setCategory_money(categoryAll.getCategory_money());
                    }
                }
                categorySaleList.add(categorySale);
            }
            indexData.setCategorySaleList(categorySaleList);
            //日排行
            List<UserSaleCount> userDayList = new ArrayList<>();
            userDayList = saleDao.getUserSaleDayCount();
            indexData.setUserDayList(userDayList);
            //周排行
            List<UserSaleCount> userWeekList = new ArrayList<>();
            userWeekList = saleDao.getUserSaleWeekCount();
            indexData.setUserWeekList(userWeekList);
            //月排行
            List<UserSaleCount> userMonthList = new ArrayList<>();
            userMonthList = saleDao.getUserSaleMonthCount();
            indexData.setUserMonthList(userMonthList);
            retInfo.setMark("0");
            retInfo.setTip("成功");
            retInfo.setObj(indexData);
        }catch (Exception e){
            e.printStackTrace();
            retInfo.setMark("-1");
            retInfo.setTip("请求首页数据失败");
        }
        return retInfo;
    }

    @Override
    @Transactional
    public RetInfo Detail(Page page) {
        RetInfo retInfo = new RetInfo();
        try{
            Map<String,Object> map = new HashMap<>();
            SrUser srUser = new SrUser();
            List<PersonalSaleRecord> personalSaleRecord = new ArrayList<>();
            List<SrCategory> categoryList = new ArrayList<>();
            srUser = userDao.selectUserDetail(page.getUser_id());
            personalSaleRecord = saleDao.findAllSaleByUserId(page);
            categoryList = categoryDao.findCatagoryList();
            Integer totle_page = 0;
            totle_page = saleDao.findAllSaleByUserIdCount(page);
            map.put("totle_page",Math.ceil(totle_page/page.getPage_size().doubleValue()));
            map.put("sr_user",srUser);
            map.put("user_sale_list",personalSaleRecord);
            map.put("categoryList",categoryList);
            retInfo.setMark("0");
            retInfo.setTip("成功");
            retInfo.setObj(map);
        }catch (Exception e){
            e.printStackTrace();
            retInfo.setMark("-1");
            retInfo.setTip("请求个人数据失败");
        }
        return retInfo;
    }

    @Override
    @Transactional
    public RetInfo insertSaleData(SaleInsert saleInsert) {
        RetInfo retInfo = new RetInfo();
        try {
            Map<String,Object> map = new HashMap<>();
            SrSale srSale = new SrSale();
            srSale.setCategory_id(saleInsert.getCategory_id());
            srSale.setCategory_name(categoryDao.findCategoryName(saleInsert.getCategory_id()));
            srSale.setSale_money(BigDecimal.valueOf(saleInsert.getSale_money()));
            srSale.setUser_id(saleInsert.getUser_id());
            srSale.setSale_comment(saleInsert.getSale_comment());
            srSale.setSale_state("1");
            saleDao.insertSaleRecord(srSale);
            map.put("user_id",saleInsert.getUser_id());
            retInfo.setMark("0");
            retInfo.setTip("成功");
            retInfo.setObj(map);
        }catch (Exception e){
            e.printStackTrace();
            retInfo.setMark("-1");
            retInfo.setTip("新增个人销售数据失败");
        }
        return retInfo;
    }

    @Override
    @Transactional
    public RetInfo updateSaleData(SaleInsert saleInsert) {
        RetInfo retInfo = new RetInfo();
        try{
            SrSale srSale = new SrSale();
            srSale.setSale_id(saleInsert.getSale_id());
            srSale.setCategory_id(saleInsert.getCategory_id());
            srSale.setCategory_name(categoryDao.findCategoryName(saleInsert.getCategory_id()));
            srSale.setSale_money(BigDecimal.valueOf(saleInsert.getSale_money()));
            if(StringUtils.isNotBlank(saleInsert.getSale_comment())){
                srSale.setSale_comment(saleInsert.getSale_comment());
            }
            saleDao.updateSaleRecord(srSale);
            retInfo.setMark("0");
            retInfo.setTip("成功");
        }catch (Exception e){
            e.printStackTrace();
            retInfo.setMark("-1");
            retInfo.setTip("修改个人销售数据失败");
        }
        return retInfo;
    }

    @Override
    public RetInfo deleteSaleData(Integer sale_id) {
        RetInfo retInfo = new RetInfo();
        try {
            SrSale srSale = new SrSale();
            SrSale result = new SrSale();
            srSale.setSale_id(sale_id);
            srSale.setSale_state("0");
            saleDao.updateSaleRecord(srSale);
            retInfo.setMark("0");
            retInfo.setTip("成功");
        }catch (Exception e){
            e.printStackTrace();
            retInfo.setMark("-1");
            retInfo.setTip("删除个人销售数据失败");
        }
        return retInfo;
    }

    @Override
    public RetInfo updateUserDetail(String user_id, String user_name, String user_password, MultipartFile user_head_portrait) {
        RetInfo retInfo = new RetInfo();
        try {
            SrUser srUser = new SrUser();
            Map<String,Object> map = new HashMap<>();
            srUser.setUser_id(Integer.valueOf(user_id));
            if(StringUtils.isNotBlank(user_name)){
                srUser.setUser_name(user_name);
            }
            if(StringUtils.isNotBlank(user_password)){
                srUser.setUser_password(MD5.code(user_password));
            }
            if(user_head_portrait != null){
                String oname = user_head_portrait.getOriginalFilename();
                String newName = AliyunOssUtil.getRandomFileName(oname);
                String key = WAIJIAOJUN_SALE_RECORD + user_id + "_" + newName;
                AliyunOssUtil.upload(key, user_head_portrait.getInputStream(), user_head_portrait.getSize());
                StringBuffer strBuffer = new StringBuffer();
                strBuffer.append(PREFIX).append(key);
                srUser.setUser_head_portrait(strBuffer.toString());
            }
            userDao.updateUserProperty(srUser);
            map.put("user_id",srUser.getUser_id());
            retInfo.setMark("0");
            retInfo.setTip("成功");
            retInfo.setObj(map);
        }catch (IOException e){
            e.printStackTrace();
            retInfo.setMark("-1");
            retInfo.setTip("修改个人详情失败");
        }catch (Exception e){
            e.printStackTrace();
            retInfo.setMark("-1");
            retInfo.setTip("修改个人详情失败");
        }
       return retInfo;
    }
}
