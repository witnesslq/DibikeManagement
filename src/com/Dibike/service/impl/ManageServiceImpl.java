package com.Dibike.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Dibike.common.BaseDao;
import com.Dibike.entity.Manage;
import com.Dibike.service.ManageService;

@Service("manageService")
public class ManageServiceImpl implements ManageService{

	@Resource
	private BaseDao<Manage> baseDao;
	
	public Manage findByName(String name) {
		List<Manage> lists = baseDao.find("from Manage where name='"+name+"'");
		if(lists.size() == 0){
			return null;
		}else{
			return lists.get(0);
		}
	}

	public List<Map<String, String>> selectAll(){
		List<?> stuList = baseDao.find("select m.name,r.name,mr.manageID "+
				"from Manage m,Manage_role mr,Role r "+
				"where m.manageID=mr.id and "+ 
							"mr.roleID=r.roleID and  "+
							"r.name <>'超级管理员'");
		List<Map<String, String>> list = new LinkedList<Map<String, String>>();
		   if(stuList != null && stuList.size()>0){
		    Map<String, String> map;
		    for(int i = 0; i < stuList.size();i++){
		    	map = new HashMap<String, String>();
		    	Object[] object = (Object[])stuList.get(i);
		    	String manage_name =  (String)object[0];
		    	String role_name =  (String)object[1];
		    	String manageID =  (String)object[2];
				map.put("manage_name",manage_name);
				map.put("role_name",role_name);
				map.put("manageID",manageID);
		    	list.add(map); 
		    }
		}
		return list;
	}

	@Override
	public void updateManage(Manage manage) {
		baseDao.update(manage);
	}
	

	

}
