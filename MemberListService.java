package com.webjjang.member.service;

import com.webjjang.main.controller.Service;
import com.webjjang.member.dao.MemberDAO;


public class MemberListService implements Service {

	//dao 필요, 생성 후 넣어줌 -> 1.생성자 2.setter()
	private MemberDAO dao;
	
	@Override
	public void setDAO(Object dao) {
		this.dao = (MemberDAO)dao;
	} 
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
	
		return dao.list();
		
	}

}
