package com.Dibike.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Dibike.common.BaseDao;
import com.Dibike.entity.Bike;
import com.Dibike.service.BikeService;

@Service("bikeService")
public class BikeServiceImpl implements BikeService {
	@Resource
	private BaseDao<Bike> baseDao;

	@Override
	public Bike findByBikeNO(String bikeNO) {
		List<Bike> list = baseDao.find("from Bike where bikeNO='" + bikeNO + "'");
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public void upBike(Bike bike) {
		baseDao.update(bike);
	}
}
