package com.Dibike.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.Dibike.common.Result;
import com.Dibike.common.SHA1;
import com.Dibike.entity.ActionCloumn;
import com.Dibike.entity.Manage;
import com.Dibike.entity.Manage_role;
import com.Dibike.service.ManageService;

@Controller
@RequestMapping("/manage")
public class ManageController {
	private static Logger logger = Logger.getLogger(ManageController.class);
	@Resource
	private ManageService manageService;
	
	@RequestMapping(value = "/login")
	@ResponseBody
	public Result manageLogin(String username,String password){
		logger.info("-------登录------");
		Result result = new Result();
		Manage manage = manageService.findByName(username);
		if(username == null || password == null || manage == null 
				||!manage.getPassword().equals
				(new SHA1().getDigestOfString(password.getBytes()))){
			logger.info("-------登录失败-------");
			result.setStatus("1");
			result.setMsg("用户名或密码错误");
			return result;
		}
		logger.info("-------登录成功-------");
		//获取用户的manageID
		String manageID = manage.getManageID();
		List<ActionCloumn> lists = manageService.findByManageID(manageID);
		result.setStatus("0");
		result.setData(lists);
		return result;
	}
	@RequestMapping(value = "/role")
	@ResponseBody
	public Result manageRole(){
		logger.info("-------角色管理------");
		Result result = new Result();
		List<?> list = manageService.selectAll();
		result.setData(list);
		return result;
	}
	
	@RequestMapping(value = "/updateRole")
	@ResponseBody
	public Result updateRole(String manageID){
		logger.info("-------更改角色------");
		Result result = new Result();
		Manage_role manage_role =  manageService.findByManage_roleID(manageID);
		if(manage_role.getRoleID().equals("2")){
			manage_role.setRoleID("3");
			manageService.updateManage_role(manage_role);
		}
		if(manage_role.getRoleID().equals("3")){
			manage_role.setRoleID("2");
			manageService.updateManage_role(manage_role);
		}
		result.setStatus("0");
		result.setMsg("更改成功");
		result.setData(manage_role);
		return result;
	}
	

}
