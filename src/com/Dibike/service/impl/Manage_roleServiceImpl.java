package com.Dibike.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Dibike.common.BaseDao;
import com.Dibike.entity.Manage_role;
import com.Dibike.service.Manage_roleService;

@Service("manage_roleService")
public class Manage_roleServiceImpl implements Manage_roleService{
	@Resource
	private BaseDao<Manage_role> baseDao;
	
	public Manage_role findByManage_roleID(String manageID) {
		List<Manage_role> lists = baseDao.find("from Manage_role where manageID='"+manageID+"'");
		if(lists.size()==0){
			return null;
		}else{
			return lists.get(0);
		}
	}
	public void updateManage_role(Manage_role manage_role) {
		baseDao.update(manage_role);
	}

}
