package com.zuoqing.demo.dao;

import com.zuoqing.demo.entity.URole;

import java.util.List;


public interface URoleDao extends BaseDao<URole, Long> {
	
	/**
	 * 根据用户ID获取该用户所在组的角色权限
	 * @param obj
	 */
	public List<URole> findRoleByUid(Long obj);
}
