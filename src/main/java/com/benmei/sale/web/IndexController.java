package com.benmei.sale.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class IndexController {

    Logger logger = LoggerFactory.getLogger(IndexController.class);

    //log 记录方法入惨的前缀
    private static final String prefix_in = "\nparameters in -->> ";
    //log 记录方法出惨的前缀
    private static final String prefix_out = "\nparameters out -->>";

    @RequestMapping
    public String index(){
        logger.info(prefix_in);
        return "index";
    }
}
