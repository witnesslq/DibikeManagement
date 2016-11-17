package com.Dibike.controller;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Dibike.common.JsonFormat;
import com.Dibike.common.SHA1;
import com.Dibike.entity.Member;
import com.Dibike.entity.MemberInfo;
import com.Dibike.entity.Wallet;
import com.Dibike.service.MemberInfoService;
import com.Dibike.service.MemberService;
import com.Dibike.service.WalletService;
import com.cheng1gou.midplat.PubFun;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * @author wjf
 * @version 鍒涘缓鏃堕棿锛�016-10-18 涓嬪崍6:14:56
 */
@Controller
@RequestMapping("/member")
public  class memberController  {
	private static Logger logger = Logger.getLogger(memberController.class);
	@Resource
	private MemberInfoService memberInfoService;
	@Resource
	private MemberService memberService;
	@Resource
	private WalletService walletService;
	private Gson gson = new Gson();
	private JsonFormat jsonFormat = new JsonFormat();
	private SHA1 sha = new SHA1();
	
	@RequestMapping(value = "/login")
	public String userLogin(HttpServletResponse response,String a )  {
		logger.info("-------登录------");
		// String phone = this.getRequest().getParameter("phone").trim();
		Member member = memberService.findByPhone("10");
		response.setContentType("text/javascript");
		response.setCharacterEncoding("UTF-8");
		if (member == null || member.equals(null)) {
			String memberid = sha.getDigestOfString(PubFun.getAllCurrentTime().getBytes());// 鍔犲瘑
			Member mem = new Member();
			// mem.setPhone(phone);
			mem.setMemberID(memberid);
			mem.setCreateDate(PubFun.getCurrentDate()); // yyyy-MM-dd
			memberService.saveMember(mem);
			Wallet wallet = new Wallet();
			wallet.setCreateDate(PubFun.getCurrentDate());
			wallet.setMemberID(memberid);
			walletService.saveWallet(wallet);
			try {
				response.getWriter().print("12");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			MemberInfo memberInfo = memberInfoService.findByMemberID(member.getMemberID());
			Wallet wallet = walletService.findByMemberID(member.getMemberID());
			JsonObject jsonObj = new JsonObject();
			jsonObj.add("memberInfo", gson.toJsonTree(memberInfo));
			jsonObj.add("wallet", gson.toJsonTree(wallet));
			a = jsonFormat.ElementFormat("01001", "请求成功", jsonObj);
			try {
				
				response.getWriter().print(a);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		return null;
	}

	
}
