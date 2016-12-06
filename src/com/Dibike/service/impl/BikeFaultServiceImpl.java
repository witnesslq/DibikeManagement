package com.Dibike.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Dibike.common.BaseDao;
import com.Dibike.entity.Bike_fault;
import com.Dibike.service.BikeFaultService;

@Service("bikeFaultService")
public class BikeFaultServiceImpl implements BikeFaultService{

	@Resource
	private BaseDao<Bike_fault> baseDao;
	@Override
	public List<Bike_fault> findByStatus(String status) {
		List<Bike_fault> list = baseDao.find("from Bike_fault where status='"+status+"'");
		if (list.size() == 0) {
			return null;
		} else {
			return list;
		}
	}

	@Override
	public void updateBike_fault(Bike_fault bike_fault) {
		baseDao.update(bike_fault);
		
	}

	@Override
	public Bike_fault findByMemberID(String memberID) {
		List<Bike_fault> list = baseDao.find("from Bike_fault where memberID='"+memberID+"'");
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

}
