package com.zuoqing.demo.controller;

import com.zuoqing.demo.Properties.GirlProperties;
import com.zuoqing.demo.serviceimpl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class LoginController {

    @Autowired
    protected LoginServiceImpl LoginService;

    @Autowired
    private GirlProperties girlProperties;

    @GetMapping(value = "userlogin")
    public String userlogin(){
        return LoginService.login("zuoqing","123");
    }

    @GetMapping(value = "getGirl")
    public String getGirl(){
        return girlProperties.getCupSize()+girlProperties.getAge()+"-----";
    }
}
