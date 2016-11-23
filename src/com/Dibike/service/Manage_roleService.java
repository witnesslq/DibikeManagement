package com.Dibike.service;

import com.Dibike.entity.Manage_role;

public interface Manage_roleService {
	/**
	 * 根据用户manageID查询用户角色信息
	 * @param manageID
	 * @return
	 */
	public Manage_role findByManage_roleID(String manageID);
	/**
	 * 修改用户角色信息
	 * @param manage_role
	 * @return
	 */
	public void updateManage_role(Manage_role manage_role);

}
