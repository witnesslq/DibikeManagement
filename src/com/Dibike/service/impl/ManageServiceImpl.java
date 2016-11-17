package com.Dibike.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Dibike.common.BaseDao;
import com.Dibike.entity.ActionCloumn;
import com.Dibike.entity.Manage;
import com.Dibike.entity.Manage_role;
import com.Dibike.service.ManageService;

@Service("manageService")
public class ManageServiceImpl implements ManageService{

	@Resource
	private BaseDao<Manage> baseDao;
	@Resource
	private BaseDao<ActionCloumn> baseDao1;
	@Resource
	private BaseDao<?> baseDao2;
	@Resource
	private BaseDao<Manage_role> baseDao3;
	
	
	public Manage findByName(String name) {
		// TODO Auto-generated method stub
		List<Manage> lists = baseDao.find("from Manage where name='"+name+"'");
		if(lists.size() == 0){
			return null;
		}else{
			return lists.get(0);
		}
		
	}

	@Override
	public List<ActionCloumn> findByManageID(String manageID) {
		List<ActionCloumn> lists = baseDao1.find("SELECT DISTINCT(ac) "+
				"from ActionCloumn ac,Action a,Action_role ar,Role r,Manage_role mr,Manage m "+
				"WHERE mr.manageID='"+manageID+"'and "+ 
							"r.roleID=mr.roleID and "+ 
							"ar.roleID=r.roleID and "+ 
							"a.actionID=ar.actionID and "+ 
							"ac.actionCloumnID=a.actionCloumnID");
		if(lists.size() == 0){
			return null;
		}else{
			return lists;
		}
		
		
	}
	
	public List<Map<String, String>> selectAll(){
		List<?> stuList = baseDao2.find("select m.name,r.name,mr.manageID "+
				"from Manage m,Manage_role mr,Role r "+
				"where m.manageID=mr.id and "+ 
							"mr.roleID=r.roleID and  "+
							"r.name <>'超级管理员'");
		List<Map<String, String>> list = new LinkedList<Map<String, String>>();;
		   if(stuList != null && stuList.size()>0){
		    Map<String, String> map;
		    for(int i = 0; i < stuList.size();i++){
		    	map = new HashMap<String, String>();
		    	Object[] object = (Object[])stuList.get(i);// 每行记录不在是一个对象 而是一个数组
		    	String manage_name =  (String)object[0];
		    	String role_name =  (String)object[1];
		    	String manageID =  (String)object[2];
				map.put("manage_name",manage_name);
				map.put("role_name",role_name);
				map.put("manageID",manageID);
		    	list.add(map); // 最终封装在list中 传到前台。
		    }
		}
		return list;
		
	}

	@Override
	public void updateManage_role(Manage_role manage_role) {
		baseDao3.update(manage_role);
	}

	@Override
	public Manage_role findByManage_roleID(String manageID) {
		List<Manage_role> lists = baseDao3.find("from Manage_role where manageID='"+manageID+"'");
		if(lists.size()==0){
			return null;
		}else{
			return lists.get(0);
		}
	}

	

}
