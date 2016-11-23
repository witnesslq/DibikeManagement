package com.Dibike.service;

import java.util.List;
import java.util.Map;

import com.Dibike.entity.Manage;

public interface ManageService {
	/**
	 * 根据管理员登录账号name查询管理员信息
	 * @param name
	 * @return
	 */
	public Manage findByName(String name);
	/**
	 * 查询除了超级管理员之外的所有用户的用户名和角色名
	 * @param 
	 * @return
	 */
	public List<Map<String, String>> selectAll();
	/**
	 * 修改个人信息
	 * @param 
	 * @return
	 */
	public void updateManage(Manage manage);
	

}
