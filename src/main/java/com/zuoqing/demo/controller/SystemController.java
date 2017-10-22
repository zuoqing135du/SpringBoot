package com.zuoqing.demo.controller;


import com.zuoqing.demo.entity.Menu;
import com.zuoqing.demo.service.SystemService;
import com.zuoqing.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统设置相关
 */

@RestController
@RequestMapping(value = "system")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @GetMapping(value = "getMenuList")
    public Object getMenuList(){

        List<Menu> list = systemService.getMenuList();
        return ResultUtil.success(list);
    }


}
