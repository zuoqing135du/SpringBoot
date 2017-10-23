package com.zuoqing.demo.serviceimpl;

import com.zuoqing.demo.dao.MenuMapper;
import com.zuoqing.demo.dao.MenuRepository;
import com.zuoqing.demo.entity.Menu;
import com.zuoqing.demo.entity.MenuExample;
import com.zuoqing.demo.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemServiceImpl implements SystemService {


    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuMapper menuDao;


    @Override
    public List<Menu> getMenuList() {

//        List<Menu> menuList = menuRepository.findAll();

        List<Menu> menuList = menuDao.selectByExample(new MenuExample());


        return menuList;
    }
}
