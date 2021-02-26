package com.webjjang.member.service;

import com.webjjang.main.controller.Service;
import com.webjjang.member.dao.MemberDAO;



public class MemberViewService implements Service {

	private MemberDAO dao;
	
	@Override
	public void setDAO(Object dao) {
		// TODO Auto-generated method stub
		this.dao = (MemberDAO)dao;
		
		System.out.println("MemberViewService.setDAO.dao=" + dao );
	}

	@Override
	public Object service(Object obj) throws Exception {
		System.out.println("MemberViewService.setDAO.obj=" + obj );
		return dao.view((String)obj);
	
	}

	
}
