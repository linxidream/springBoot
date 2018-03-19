package com.benmei.sale.common;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

/***
 *  @author lizhun
 *	control层和service层交互bean类
 */
public class RetInfo implements java.io.Serializable {
    //表示是否成功  0 成功; 1 server error; -1 client error
    public String mark;
    //成功失败的提示语
    public String tip;
    //返回的OBJECT，对应到前端所需要的bean类
    public Object obj = new ArrayList<>();

    public static class ReturnCode {
        public static final String SUCCESS = "0";
        public static final String SERVER_ERROR = "1";
        public static final String CLIENT_ERROR = "-1";

        public static final String SUCCESS_MSG_EN = "SUCCESS";
        public static final String SERVER_ERROR_MSG_EN = "Oops! the internet is abducted by aliens";
        public static final String CLIENT_ERROR_MSG_EN = "Client Error";


        public static final String SUCCESS_MSG_CN = "成功";
        public static final String SERVER_ERROR_MSG_CN = "网络被外星人劫持";
        public static final String CLIENT_ERROR_MSG_CN = "客户端错误";

        /**
         * 需要用户重新登录
         */
        public static final String NONE_TOKEN = "100";
        public static final String NONE_TOKEN_MSG_CN = "请重新登录";
        public static final String NONE_TOKEN_MSG_EN = "Please log in again!";


        //{"tip":"未登陆","mark":"101"}

    }

    /**
     * 创建一个成功返回信息
     *
     * @param obj 返回数据
     * @return
     */
    public static RetInfo getSuccessInfo(Object obj) {
        RetInfo retInfo = new RetInfo();
        retInfo.setMark(RetInfo.ReturnCode.SUCCESS);
        retInfo.setTip(ReturnCode.SUCCESS_MSG_CN);
        retInfo.setObj(obj);
        return retInfo;
    }

    /**
     * 创建一个成功返回信息
     *
     * @param msg 如果msg是空则用系统默认的tip:"SUCCESS"
     * @return
     */
    public static RetInfo getSuccessInfo(String msg) {
        RetInfo retInfo = new RetInfo();

        retInfo.setMark(RetInfo.ReturnCode.SUCCESS);
        if (StringUtils.isNotBlank(msg)) {
            retInfo.setTip(msg);
        } else {
            retInfo.setTip(ReturnCode.SUCCESS_MSG_CN);
        }
        return retInfo;
    }

    /**
     * 创建一个成功返回信息,用系统默认的tip:"SUCCESS"
     *
     * @return
     */
    public static RetInfo getSuccessInfo() {
        return getSuccessInfo(null);
    }


    /**
     * 创建一个客户端错误返回信息
     *
     * @param msg 如果msg是空则用系统默认的tip:"Client Error"
     * @return
     */
    public static RetInfo getClientErrorInfo(String code, String msg) {
        RetInfo retInfo = new RetInfo();

        retInfo.setMark(code);
        retInfo.setTip(msg);
        return retInfo;
    }

    /**
     * 创建一个客户端错误返回信息
     *
     * @param msg 如果msg是空则用系统默认的tip:"Client Error"
     * @return
     */
    public static RetInfo getClientErrorInfo(String msg) {
        RetInfo retInfo = new RetInfo();

        if (StringUtils.isNotBlank(msg)) {
            retInfo.setTip(msg);
        } else {
            retInfo.setTip(RetInfo.ReturnCode.SERVER_ERROR_MSG_CN);
        }
        retInfo.setMark(RetInfo.ReturnCode.CLIENT_ERROR);
        return retInfo;
    }

    /**
     * 创建一个客户端错误返回信息,用系统默认的tip:"Client Error"
     *
     * @return
     */
    public static RetInfo getClientErrorInfo() {
        return getClientErrorInfo(null);
    }

    /**
     * 创建一个服务端错误返回信息
     *
     * @return
     */
    public static RetInfo getServerErrorInfo() {
        RetInfo retInfo = new RetInfo();
        retInfo.setTip(ReturnCode.SERVER_ERROR_MSG_CN);
        retInfo.setMark(RetInfo.ReturnCode.SERVER_ERROR);
        return retInfo;
    }

    /**
     * 创建一个服务端错误返回信息
     *
     * @param msg 如果msg是空则用系统默认的tip:"Server Error"
     * @return
     */
    public static RetInfo getServerErrorInfo(String msg) {
        RetInfo retInfo = new RetInfo();
        if (StringUtils.isNotBlank(msg)) {
            retInfo.setTip(msg);
        } else {
            retInfo.setTip(RetInfo.ReturnCode.SERVER_ERROR_MSG_CN);
        }
        retInfo.setMark(RetInfo.ReturnCode.SERVER_ERROR);
        return retInfo;
    }

    /**
     * 创建一个服务端错误返回信息,用系统默认的tip:"Server Error"
     *
     * @param code
     * @param msg
     * @return
     */
    public static RetInfo getServerErrorInfo(String code, String msg) {
        RetInfo retInfo = new RetInfo();
        if (StringUtils.isNotBlank(msg)) {
            retInfo.setTip(msg);
        } else {
            retInfo.setTip(RetInfo.ReturnCode.SERVER_ERROR_MSG_CN);
        }

        if (StringUtils.isNotBlank(code)) {
            retInfo.setMark(code);
        } else {
            retInfo.setMark(RetInfo.ReturnCode.SERVER_ERROR);
        }

        retInfo.setMark(RetInfo.ReturnCode.SERVER_ERROR);
        return retInfo;
    }

    public Object getObj() {
        return obj;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

}
