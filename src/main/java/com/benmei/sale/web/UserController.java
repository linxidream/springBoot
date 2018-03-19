package com.benmei.sale.web;

import com.benmei.sale.common.RetInfo;
import com.benmei.sale.dto.*;
import com.benmei.sale.entity.SrUser;
import com.benmei.sale.service.UserService;
import com.benmei.sale.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户登录
 */
@Controller
@RequestMapping(value = "/user")
public class UserController
{

    @Resource
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    //log 记录方法入惨的前缀
    private static final String prefix_in = "\nparameters in -->> ";
    //log 记录方法出惨的前缀
    private static final String prefix_out = "\nparameters out -->>";

    /**
     * 测试
     * @return
     */
    @RequestMapping(value="/select",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public RetInfo getAllUser(){
        logger.info( prefix_in );
        RetInfo retInfo = new RetInfo();
        List<SrUser> userList = userService.selectAll();
        retInfo.setMark("1");
        retInfo.setObj(userList);
        retInfo.setTip("成功");
        logger.info( JsonUtil.toJson(retInfo) );
        logger.info( prefix_out);
        return retInfo;
    }

    /**
     * 用户登录
     * @param userLogin
     * @return
     */
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    @ResponseBody
    public RetInfo login(@RequestBody UserLogin userLogin){
        logger.info( prefix_in );
        RetInfo retInfo = new RetInfo();
        if(StringUtils.isBlank(userLogin.getName())){
            retInfo.setMark("-1");
            retInfo.setTip("用户名不能为空");
            return retInfo;
        }
        if(StringUtils.isBlank(userLogin.getName())){
            retInfo.setMark("-1");
            retInfo.setTip("密码不能为空");
            return retInfo;
        }
        logger.info( JsonUtil.toJson(userLogin) );
        retInfo = userService.login(userLogin);
        logger.info( JsonUtil.toJson(retInfo) );
        logger.info( prefix_out);
        return retInfo;
    }

    /**
     * 首页数据
     * @return
     */
    @RequestMapping(value = "/index",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public RetInfo index(){
        logger.info(prefix_in);
        RetInfo retInfo = new RetInfo();
        retInfo = userService.index();
        logger.info(JsonUtil.toJson(retInfo));
        logger.info(prefix_out);
        return retInfo;
    }

    /**
     * 详情
     * @param page
     * @return
     */
    @RequestMapping(value="/detail",method = {RequestMethod.POST})
    @ResponseBody
    public RetInfo userDetail(@RequestBody Page page){
        logger.info(prefix_in );
        RetInfo retInfo = new RetInfo();
        if(page.getUser_id() == null || page.getUser_id() == 0){
            retInfo.setMark("-1");
            retInfo.setTip("用户名不能为空");
            return retInfo;
        }
        logger.info( JsonUtil.toJson(page) );
        retInfo = userService.Detail(page);
        logger.info( JsonUtil.toJson(retInfo) );
        logger.info( prefix_out);
        return retInfo;
    }

    /**
     * 新增用户销售数据
     * @param saleInsert
     * @return
     */
    @RequestMapping(value="/sale/insert",method = {RequestMethod.POST})
    @ResponseBody
    public RetInfo saleInsert(@RequestBody SaleInsert saleInsert){
        logger.info( prefix_in );
        RetInfo retInfo = new RetInfo();
        if(saleInsert.getUser_id() == null || saleInsert.getUser_id() == 0){
            retInfo.setMark("-1");
            retInfo.setTip("用户id不能为空");
            return retInfo;
        }
        if(saleInsert.getCategory_id() == null || saleInsert.getCategory_id() == 0){
            retInfo.setMark("-1");
            retInfo.setTip("类别名不能为空");
            return retInfo;
        }
        if(saleInsert.getSale_money() == null || saleInsert.getSale_money() == 0){
            retInfo.setMark("-1");
            retInfo.setTip("销售金额不能为空");
            return retInfo;
        }
        logger.info( JsonUtil.toJson(saleInsert) );
        retInfo = userService.insertSaleData(saleInsert);
        logger.info( JsonUtil.toJson(retInfo) );
        logger.info( prefix_out);
        return retInfo;
    }

    /**
     * 修改用户销售数据
     * @param saleInsert
     * @return
     */
    @RequestMapping(value="/sale/update",method = {RequestMethod.POST})
    @ResponseBody
    public RetInfo saleUpdate(@RequestBody SaleInsert saleInsert){
        logger.info( prefix_in );
        RetInfo retInfo = new RetInfo();
        if(saleInsert.getSale_id() == null || saleInsert.getSale_id() == 0){
            retInfo.setMark("-1");
            retInfo.setTip("销售id不能为空");
            return retInfo;
        }
        if(saleInsert.getCategory_id() == null || saleInsert.getCategory_id() == 0){
            retInfo.setMark("-1");
            retInfo.setTip("类别名不能为空");
            return retInfo;
        }
        if(saleInsert.getSale_money() == null || saleInsert.getSale_money() == 0){
            retInfo.setMark("-1");
            retInfo.setTip("销售金额不能为空");
            return retInfo;
        }
        logger.info( JsonUtil.toJson(saleInsert) );
        retInfo = userService.updateSaleData(saleInsert);
        logger.info( JsonUtil.toJson(retInfo) );
        logger.info( prefix_out);
        return retInfo;
    }

    /**
     * 删除用户销售数据
     * @param saleDelete
     * @return
     */
    @RequestMapping(value="/sale/delete",method = {RequestMethod.POST})
    @ResponseBody
    public RetInfo saleDelete(@RequestBody SaleDelete saleDelete){
        logger.info( prefix_in );
        RetInfo retInfo = new RetInfo();
        if(saleDelete.getSale_id() == null || saleDelete.getSale_id() == 0){
            retInfo.setMark("-1");
            retInfo.setTip("销售id不能为空");
            return retInfo;
        }
        logger.info( JsonUtil.toJson(saleDelete) );
        retInfo = userService.deleteSaleData(saleDelete.getSale_id());
        logger.info( JsonUtil.toJson(retInfo) );
        logger.info( prefix_out);
        return retInfo;
    }

    /**
     * 修改用户信息
     * @param
     * @return
     */
    @RequestMapping(value="/update",method = {RequestMethod.POST})
    @ResponseBody
    public RetInfo userUpdate(MultipartHttpServletRequest request){
        logger.info( prefix_in );
        RetInfo retInfo = new RetInfo();
        String user_id = request.getParameter("user_id");
        String user_name = request.getParameter("user_name");
        String user_password = request.getParameter("user_password");
        MultipartFile user_head_portrait = request.getFile("user_head_portrait");
        if(StringUtils.isBlank(user_id)){
            retInfo.setMark("-1");
            retInfo.setTip("用户id不能为空");
            return retInfo;
        }
        logger.info( JsonUtil.toJson(request) );
        retInfo = userService.updateUserDetail(user_id,user_name,user_password,user_head_portrait);
        logger.info( JsonUtil.toJson(retInfo) );
        logger.info( prefix_out);
        return retInfo;
    }

}
