package com.Dibike.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.Dibike.common.Result;
import com.Dibike.service.MemberService;

/**
 * @author wjf
 * @version 鍒涘缓鏃堕棿锛�016-10-18 涓嬪崍6:14:56
 */
@Controller
@RequestMapping("/member")
public  class MemberController  {
	private static Logger logger = Logger.getLogger(MemberController.class);
	@Resource 
	private MemberService memberService;
	
	@RequestMapping(value = "/countMember")
	@ResponseBody
	public Result countMember(){
		logger.info("-------用户显示------");
		Result result = new Result();
		List<String> weekCount = memberService.countMemberByWeek();
		List<String> monthCount = memberService.countMemberByMonth();
		List<List<String>> list = new ArrayList<List<String>>();
		list.add(weekCount);
		list.add(monthCount);
		result.setData(list);
		return result;
	}
	
	
}

























