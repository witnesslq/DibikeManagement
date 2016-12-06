package com.Dibike.service;

import java.util.List;

import com.Dibike.entity.Bike_fault;

public interface BikeFaultService {

	/**
	 * 根据车辆状态来查找
	 * @param status
	 * @return
	 */
	List<Bike_fault> findByStatus(String status);

	void updateBike_fault(Bike_fault bike_fault);

	Bike_fault findByMemberID(String memberID);

}
