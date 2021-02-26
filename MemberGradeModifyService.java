package com.webjjang.member.service;

import com.webjjang.main.controller.Service;
import com.webjjang.member.dao.MemberDAO;
import com.webjjang.member.vo.MemberVO;

public class MemberGradeModifyService implements Service {

	private MemberDAO dao;
	
	// 실행서비스
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberGradModifyService.service().obj : " + obj);
		System.out.println("MemberGradModifyService.service().dao : " + obj);
		//dao가 null인 경우, gradeModify() 메소드 호출해서 사용하려고 하면 NullPointException 발생
		return dao.gradeModify((MemberVO)obj);
	}

	//setter
	@Override
	public void setDAO(Object dao) {
		// TODO Auto-generated method stub
		//Init.init() 객체 생성 후 조립할 때 실행 - 서버가 시작이 되면서 확인 - 이클립스 콘솔창 
		System.out.println("MemberGradeModifyService.setDAO().dao :" + dao);
		this.dao = (MemberDAO)dao;
	}

}
