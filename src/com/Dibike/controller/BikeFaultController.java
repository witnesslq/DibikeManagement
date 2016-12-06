package com.Dibike.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Dibike.common.Result;
import com.Dibike.entity.Bike_fault;
import com.Dibike.entity.Point;
import com.Dibike.service.BikeFaultService;
import com.Dibike.service.PointService;

@Controller
@RequestMapping("/bikeFault")
public class BikeFaultController {
	private static Logger logger = Logger.getLogger(BikeFaultController.class);
	@Resource 
	private BikeFaultService bikeFaultService;
	
	@Resource 
	private PointService pointService;
	
	@RequestMapping(value = "/queryNoCheck")
	@ResponseBody
	public Result queryNoCheck(){
		logger.info("-------查询单车故障未审核------");
		Result result = new Result();
		String status = "R";
		List<Bike_fault> list = bikeFaultService.findByStatus(status);
		result.setData(list);
		result.setStatus("0");
		return result;
	}
	
	@RequestMapping(value = "/queryPass")
	@ResponseBody
	public Result queryPass(){
		logger.info("-------查询单车故障已审核------");
		Result result = new Result();
		String status = "O";
		List<Bike_fault> list = bikeFaultService.findByStatus(status);
		result.setData(list);
		result.setStatus("0");
		return result;
	}
	
	@RequestMapping(value = "/queryNoPass")
	@ResponseBody
	public Result queryNoPass(){
		logger.info("-------查询单车故障审核驳回------");
		Result result = new Result();
		String status = "N";
		List<Bike_fault> list = bikeFaultService.findByStatus(status);
		result.setData(list);
		result.setStatus("0");
		return result;
	}
	
	
	@RequestMapping(value = "/checkPass")
	@ResponseBody
	public Result checkPass(String memberID){
		logger.info("-------单车故障审核通过------");
		Result result = new Result();
		Bike_fault bike_fault = bikeFaultService.findByMemberID(memberID);
		bike_fault.setStatus("O");
		bikeFaultService.updateBike_fault(bike_fault);
		//审核通过的给上报该故障的用户积分加一分
		Point point = pointService.findByMemberID(memberID);
		point.setNumber(point.getNumber()+1);
		pointService.savePoint(point);
		result.setStatus("0");
		return result;
	}
	
	@RequestMapping(value = "/checkNoPass")
	@ResponseBody
	public Result checkNoPass(String memberID){
		logger.info("-------单车故障审核驳回------");
		Result result = new Result();
		Bike_fault bike_fault = bikeFaultService.findByMemberID(memberID);
		bike_fault.setStatus("N");
		bikeFaultService.updateBike_fault(bike_fault);
		result.setStatus("0");
		return result;
	}

}
