package com.webjjang.member.service;

import com.webjjang.main.controller.Service;
import com.webjjang.member.dao.MemberDAO;
import com.webjjang.member.vo.LoginVO;

public class MemberLoginService implements Service {

	//dao 필요, 생성 후 넣어줌 -> 1.생성자 2.setter()
	private MemberDAO dao;
	
	@Override
	public void setDAO(Object dao) {
		this.dao = (MemberDAO)dao;
	} 
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		//글 보기와 글 수정 진행, 글보기(list->view)는 조회수 1 증가, 수정(update->view)은 증가 안함/데이터 두개가 넘어옴 (클래스,배열(O)) 
		//obj[0] - no / obj[1] - inc
		return dao.login((LoginVO)obj);
		
	}

}
