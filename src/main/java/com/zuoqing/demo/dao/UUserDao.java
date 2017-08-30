package com.zuoqing.demo.dao;

import com.zuoqing.demo.entity.UUser;
import org.apache.ibatis.annotations.Mapper;


public interface UUserDao extends BaseDao<UUser, Long> {

   /**
    * 增加对象
    * @param obj
    */
   public UUser findByName(String name);
}
