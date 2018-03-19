package com.benmei.sale.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/a",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String test1(){
        return "aaa";
    }

    @RequestMapping(value = "/b",method = {RequestMethod.GET,RequestMethod.POST})
    public  String test2(){
        return "test";
    }
}
