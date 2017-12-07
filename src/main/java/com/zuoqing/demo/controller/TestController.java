package com.zuoqing.demo.controller;

import com.zuoqing.demo.dao.CityMapper;
import com.zuoqing.demo.entity.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zuoqing on 2017/12/7.
 */
@RestController
public class TestController {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    CityMapper cityDao;

    @RequestMapping(value = "getCity")
    public Object getCity(){

        return cityDao.selectById(1);
    }

    @RequestMapping(value = "getCityList")
    public Object getCitys(){

        return cityDao.selectList(2);
    }

    @RequestMapping(value = "getCityList2")
    public Object getCitys2(){
        logger.debug("开始查询城市cityDao.selectList2()");
        return cityDao.selectList2();
    }

    @RequestMapping(value = "selectList3")
    public Object selectList3(){
        logger.debug("开始查询城市cityDao.selectList2()");
        City city = new City();
        city.setProvinceId(3);
        city.setCityName("荆州");

        return cityDao.selectList3(city);
    }

    @RequestMapping(value = "insert2")
    public Object insert2(){
        City city = new City();
        List<City> allList =  cityDao.selectAllList();
        city.setId(allList.get(allList.size()-1).getId()+1);
        city.setProvinceId(3);
        city.setCityName("荆3");

        return cityDao.insert2(city);
    }

    int updateTime = 0;
    @RequestMapping(value = "updateCity")
    public Object updateCity(){
        City city = new City();
        city.setId(9);
        city.setProvinceId(3);
        city.setCityName("荆-"+updateTime++);
        city.setDescription("荆-"+updateTime++);

        return cityDao.updateCity(city);
    }

    @RequestMapping(value = "selectUserCityDTOList")
    public Object selectUserCityDTOList(int id){
        return cityDao.selectUserCityDTOList(id);
    }

}
