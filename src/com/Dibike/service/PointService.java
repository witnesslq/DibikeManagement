package com.Dibike.service;

import com.Dibike.entity.Point;

public interface PointService {

	Point findByMemberID(String memberID);

	void savePoint(Point point);
	
}
