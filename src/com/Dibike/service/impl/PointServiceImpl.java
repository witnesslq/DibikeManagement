package com.Dibike.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Dibike.common.BaseDao;
import com.Dibike.entity.Point;
import com.Dibike.service.PointService;

@Service("pointService")
public class PointServiceImpl implements PointService{
	@Resource
	private BaseDao<Point> baseDao;

	@Override
	public Point findByMemberID(String memberID) {
		List<Point> lists = baseDao.find("from Point where memberID ='"+memberID+"'");
		if(lists.size()==0){
			return null;
		}else{ 
			return lists.get(0);
		}
	}

	@Override
	public void savePoint(Point point) {
		baseDao.save(point);
		
	}

}
