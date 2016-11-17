package com.Dibike.service;

import java.util.List;

import com.Dibike.entity.ActionCloumn;
import com.Dibike.entity.Manage;
import com.Dibike.entity.Manage_role;

public interface ManageService {
	/**
	 * 根据管理员登录账号name查询管理员信息
	 * @param name
	 * @return
	 */
	public Manage findByName(String name);
	
	/**
	 * 根据用户id manageID查询权限栏信息
	 * @param manageID
	 * @return
	 */
	public List<ActionCloumn> findByManageID(String manageID);
	public List<?> selectAll();
	
	public void updateManage_role(Manage_role manage_role);

	public Manage_role findByManage_roleID(String manageID);

}
