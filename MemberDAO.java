package com.webjjang.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.member.vo.LoginVO;
import com.webjjang.member.vo.MemberVO;
import com.webjjang.util.db.DBInfo;
import com.webjjang.util.db.DBSQL;

public class MemberDAO {

	//필요한 객체 선언 
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//로그인 처리를 위한 메소드 작성 
	public LoginVO login(LoginVO vo) throws Exception {
		LoginVO loginVO = null;
		
		try {
			con=DBInfo.getConnection();
			pstmt = con.prepareStatement(DBSQL.MEMEBER_LOGIN);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			rs=pstmt.executeQuery();
			//데이터 담기
			if(rs!=null&&rs.next()) {
				loginVO = new LoginVO(); //위에 null 선언했으니까 객체 생성하고 담기 시작 
				loginVO.setId(rs.getString("id"));
				loginVO.setName(rs.getString("name"));
				loginVO.setGradeNo(rs.getInt("gradeNo"));
				loginVO.setGradeName(rs.getString("gradeName"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("로그인 DB처리중 오류");
			
		}finally {
			DBInfo.close(con, pstmt, rs);
		}
		
		return loginVO;
	}
	
	
	//회원 목록 메소드 
	
	public List<MemberVO>list() throws Exception {
		List<MemberVO>list=null;

		try {
			con=DBInfo.getConnection();
			pstmt=con.prepareStatement(DBSQL.MEMBER_LIST);
			pstmt.setLong(1, 1);
			pstmt.setLong(2, 10);
			rs=pstmt.executeQuery();
			
			if(rs!=null) {
				while(rs.next()) {
					if(list==null)list=new ArrayList<>();
					MemberVO vo = new MemberVO();
					vo.setId(rs.getString("id"));
					vo.setName(rs.getString("name"));
					vo.setGender(rs.getString("gender"));
					vo.setBirth(rs.getString("birth"));
					vo.setTel(rs.getString("tel"));
					vo.setEmail(rs.getString("email"));
					vo.setStatus(rs.getString("status"));
					vo.setGradeNo(rs.getInt("gradeNo"));
					vo.setGradeName(rs.getString("gradeName"));
					
					list.add(vo);
				
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("회원 목록 실행 중 DB처리 오류");
		}finally {
			DBInfo.close(con, pstmt, rs);
		}
		return list;
		
	
	}
	
	//회원등급 수정 메소드
	
	public int gradeModify (MemberVO vo) throws Exception {
		System.out.println("MemberDAO.gradeModify().vo" + vo);
		int result = 0;
		
		try {
			con=DBInfo.getConnection();
			//실행쿼리 확인
			System.out.println("실행쿼리확인:" + DBSQL.MEMBER_GRADE_MODIFY);
			pstmt = con.prepareStatement(DBSQL.MEMBER_GRADE_MODIFY);
			//sql 쿼리에 포함되어 있는 ? 의미에 맞는 데이터를 순서대로 셋팅 
			pstmt.setInt(1, vo.getGradeNo());
			pstmt.setString(2, vo.getId());
			//실행-insert,update,delete 쿼리 실행 <-> select -> ResultSet = executeQuery
			result=pstmt.executeUpdate();
			//result가 1이면 수정 발생, 0이면 수정되지 않음 (조건에 맞는 데이터가 X)
			System.out.println("MemberDAO.result : " + result);
			if(result==1) {
				System.out.println("회원 등급 수정 완료");
			}else {
				throw new Exception("조건에 맞는 id가 없습니다.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("회원등급변경 DB처리중 오류");
			
		}finally {
			DBInfo.close(con, pstmt);
		}
		
		return result;
	}
	
	public MemberVO view(String id) throws Exception {
		MemberVO vo = null;
		
		try {
			con=DBInfo.getConnection();
			pstmt=con.prepareStatement(DBSQL.MEMBER_VIEW);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			
			if(rs!=null && rs.next()) {
				vo = new MemberVO();
				
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setBirth(rs.getString("birth"));
				vo.setTel(rs.getString("tel"));
				vo.setEmail(rs.getString("email"));
				vo.setRegDate(rs.getString("regDate"));
				vo.setStatus(rs.getString("status"));
				vo.setGradeNo(rs.getInt("gradeNo"));
				vo.setGradeName(rs.getString("gradeName"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("내 정보 보기 DB처리중 오류");
		}finally{
			DBInfo.close(con, pstmt,rs);
		}
		return vo;
	}


	
}
