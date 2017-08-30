package com.zuoqing.demo.controller;

import com.zuoqing.demo.dao.CityMapper;
import com.zuoqing.demo.dao.GirlRepository;
import com.zuoqing.demo.entity.City;
import com.zuoqing.demo.entity.Girl;
import com.zuoqing.demo.entity.Result;
import com.zuoqing.demo.redis.RedisService;
import com.zuoqing.demo.service.GirlService;
import com.zuoqing.demo.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class GirlController {

    //Spring 自带的日志工具
    private final static Logger logger = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    protected GirlService girlService;

    /*@Resource
    private DemoGirlMapper demoGirlDao;*/

    @Resource
    private CityMapper cityDao;

    @GetMapping(value = "/girls")
    public City girlList() {

//        return demoGirlDao.selectByPrimaryKey(24);
        //mybatis的操作
        return cityDao.selectByPrimaryKey(1);
    }

    /*
    1.
    @RequestParam("cupSize") String cupSize,
    @RequestParam("age") Integer age
    Content type支持 form-data   ,   x-www-form-urlencoded

    2.
    String cupSize,
    Integer age
    Content type支持  x-www-form-urlencoded,或者不写

    3.
    @RequestBody Girl girl
    Content-type使用 application/json
    数据使用raw格式的 {"cupSize": "11","age": 11}
    */

    //增加用户
    @PostMapping(value = "addGirl")
    public Result<Girl> addGirl(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(girlRepository.save(girl));
    }

    @GetMapping(value = "/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id) {
        return girlRepository.findOne(id);

    }

    //更新
    @PutMapping(value = "/girls/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id,
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age
    ) {
        Girl girl = new Girl();
        girl.setId(id);
        girl.setAge(age);
        girl.setCupSize(cupSize);
        return girlRepository.save(girl);
    }

    @DeleteMapping(value = "/girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id) {
        girlRepository.delete(id);
    }


    //通过年龄查询
    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age) {
        return girlRepository.findByAge(age);
    }

    @PostMapping(value = "/girls/two")
    public void girlTwo() {
        girlService.insertTwo();
    }

    @GetMapping(value = "girls/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception {
        girlService.getAge(id);
    }

    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
    public String redisTest() {

        List<Girl> list = new ArrayList();
        Girl girl = new Girl();
        girl.setCupSize("C");
        girl.setAge(30);
        list.add(girl);
        redisService.set("girlList",list);

        StringBuffer sb = new StringBuffer();
        redisService.set("str", "str");
        sb.append("str=").append(redisService.get("str").toString()).append(",");
        redisService.hmSet("hmset", "key", "val");
        sb.append("hmset=").append(redisService.hmGet("hmset", "key")).append(",");
        redisService.lPush("list", "val");
        sb.append("list=").append(redisService.lRange("list", 0, 1).toString()).append(",");
        redisService.add("set", "val");
        sb.append("set=").append(redisService.setMembers("set").toString()).append(",");
        redisService.zAdd("zset", "val1", 1);
        redisService.zAdd("zset", "val2", 2);
        sb.append("zset=").append(redisService.rangeByScore("zset", 1, 2)).append(",");

        return sb.toString();
    }

    @RequestMapping(value = "/get", method = {RequestMethod.GET, RequestMethod.POST})
    public String redisTest2() {
        StringBuffer sb = new StringBuffer();
        redisService.set("str", "str");
        sb.append("str=").append(redisService.get("str").toString()).append(",");
        redisService.hmSet("hmset", "key", "val");
        sb.append("hmset=").append(redisService.hmGet("hmset", "key")).append(",");
        redisService.lPush("list", "val");
        sb.append("list=").append(redisService.lRange("list", 0, 1).toString()).append(",");
        redisService.add("set", "val");
        sb.append("set=").append(redisService.setMembers("set").toString()).append(",");
        redisService.zAdd("zset", "val1", 1);
        redisService.zAdd("zset", "val2", 2);
        sb.append("zset=").append(redisService.rangeByScore("zset", 1, 2)).append(",");
        return sb.toString();
    }


}




















