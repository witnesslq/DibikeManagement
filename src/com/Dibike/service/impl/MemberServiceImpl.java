package com.Dibike.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Dibike.common.BaseDao;
import com.Dibike.entity.Member;
import com.Dibike.service.MemberService;

/**
 * @author wjf
 * @version 创建时间：2016-10-18 下午5:56:13
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService{
	@Resource
	private BaseDao<Member> baseDao;

	@Override
	@Transactional
	public Member findByPhone(String phone) {
		// TODO Auto-generated method stub
		
		List<Member> lists=baseDao.find("from Member where phone ='"+phone+"'");
		if(lists.size()==0){
			return null;
		}else{
			return lists.get(0);
		}
		
	}

	@Override
	public void saveMember(Member member) {
		// TODO Auto-generated method stub
		baseDao.save(member);
	}

}
