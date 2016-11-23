package com.Dibike.controller;

import java.util.List;
import java.util.Map;

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
import com.Dibike.service.ActionCloumnService;
import com.Dibike.service.ManageService;
import com.Dibike.service.Manage_roleService;

@Controller
@RequestMapping("/manage")
public class ManageController {
	private static Logger logger = Logger.getLogger(ManageController.class);
	@Resource
	private ManageService manageService;
	@Resource
	private ActionCloumnService actionCloumnService;
	@Resource
	private Manage_roleService manage_roleService;
	
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
		//根据用户manageID显示权限栏
		List<ActionCloumn> lists = actionCloumnService.findByManageID(manageID);
		result.setStatus("0");
		result.setData(lists);
		return result;
	}
	@RequestMapping(value = "/role")
	@ResponseBody
	public Result manageRole(){
		logger.info("-------角色管理------");
		Result result = new Result();
		List<Map<String, String>> list = manageService.selectAll();
		result.setData(list);
		return result;
	}
	
	@RequestMapping(value = "/updateRole")
	@ResponseBody
	public Result updateRole(String manageID){
		logger.info("-------更改角色------");
		Result result = new Result();
		Manage_role manage_role =  manage_roleService.findByManage_roleID(manageID);
		if(manage_role.getRoleID().equals("2")){
			manage_role.setRoleID("3");
			manage_roleService.updateManage_role(manage_role);
			result.setStatus("0");
			result.setMsg("更改成功");
			result.setData("普通员工");
			return result;
		}
		if(manage_role.getRoleID().equals("3")){
			manage_role.setRoleID("2");
			manage_roleService.updateManage_role(manage_role);
			result.setStatus("0");
			result.setMsg("更改成功");
			result.setData("部门管理员");
			return result;
		}
		return null;
	}
	
	@RequestMapping("/updateManage")
	@ResponseBody
	public Result updateManage(String oldName,String newName){
		logger.info("-------修改个人信息------");
		Result result = new Result();
		Manage manage = manageService.findByName(oldName);
		manage.setName(newName);
		manageService.updateManage(manage);
		result.setStatus("0");
		result.setMsg("修改个人信息成功");
		result.setData(manage);
		return result;
	}
	
	@RequestMapping("/updateManagePassword")
	@ResponseBody
	public Result updateManagePassword(String name,String password){
		logger.info("-------修改密码------");
		Result result = new Result();
		Manage manage = manageService.findByName(name);
		String pwd = new SHA1().getDigestOfString(password.getBytes());
		manage.setPassword(pwd);
		manageService.updateManage(manage);
		result.setStatus("0");
		result.setMsg("修改密码成功");
		result.setData(manage);
		return result;
	}

}

























