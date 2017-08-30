package com.zuoqing.demo.dao;

import com.zuoqing.demo.entity.UPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UPermissionDao extends BaseDao<UPermission, Long> {



   /**
    * 根据用户id获取用户权限
    * @param
    */
    List<UPermission> findPermissionByUid(Long id);
}
