package com.zuoqing.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;


/**   
* @Title: CityRestController.java 
* @Package com.bamboo.springboot.controller 
* @Description: 用户权限认证管理控制器
* @author bamboo  <a href="mailto:zjcjava@163.com?subject=hello,bamboo&body=Dear Bamboo:%0d%0a描述你的问题：">Bamboo</a>   
* @date 2017年4月21日 下午5:51:36 
* @version V1.0   
*/
@Controller
@RequestMapping(value = "/pc")

public class PCAdminController {
	
	 private static Logger logger = LoggerFactory.getLogger(PCAdminController.class);

    @RequestMapping(value = "/login")
    public String test(ModelMap modelMap){

       /* HashMap<String,String> dccItem = new HashMap<>();
        dccItem.put("1","银行111");
        dccItem.put("2","4S");
        modelMap.addAttribute("name","李文涛");
        modelMap.addAttribute("dccItem",dccItem);*/

        return "login";
    }

    @RequestMapping(value = "/admin_main")
    public String admin_main(ModelMap modelMap){

        HashMap<String,String> dccItem = new HashMap<>();
        dccItem.put("1","银行111");
        dccItem.put("2","4S");
        modelMap.addAttribute("name","李文涛");
        modelMap.addAttribute("dccItem",dccItem);

        return "main";
    }

    @RequestMapping(value = "/qqq")
    @ResponseBody
    public String qqq(ModelMap modelMap){

        return "test111";

    }

}

