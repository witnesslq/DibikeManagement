package com.Dibike.service;

import java.util.List;

import com.Dibike.entity.Member;

/**
 * @author wjf
 * @version 创建时间：2016-10-18 下午5:52:47
 */
public interface MemberService {
	/**
	 * 根据手机来查找
	 * @param phone
	 * @return
	 */
	public Member findByPhone(String phone);
	/**
	 * 保存用户
	 * @param member
	 */
	public void saveMember(Member member);
}
