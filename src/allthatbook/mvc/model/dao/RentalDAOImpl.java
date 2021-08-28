package allthatbook.mvc.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import allthatbook.mvc.model.dto.Rental;
import allthatbook.mvc.util.DbUtil;

public class RentalDAOImpl implements RentalDAO {
	  /**
	   * 대여하기
	   *  1) 책상태 확인하기 (0이면 대여가능)
	   *  2) Rental 테이블에 insert
	   *  3) 책상태 변경하기 (1로 수정)
	   * */

	@Override
	public int rentalInsert(Rental rental) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null;
		 String sql="INSERT INTO RENTAL(대여번호, 책번호, 회원번호, 반납예정일자, 대여일자, 반납일자)" + 
		  		" VALUES(RENTAL_SEQ_NO.NEXTVAL, ?, ?, ?, ?, null)";
		 int result=0;
		 try {
			 con = DbUtil.getConnection();
			 con.setAutoCommit(false); //자동커밋해지
			   
			 ps = con.prepareStatement(sql);
			 ps.setInt(1, rental.getBookNo());
			 ps.setInt(2, rental.getUserNo());
			 ps.setDate(3, rental.getExreturnDate());
			 ps.setDate(4, rental.getRentDate());  
			 result = ps.executeUpdate();
			   if(result==0) {
				   con.rollback();
				   throw new SQLException("대여 실패");
			   }
			   else {
				   //책 꺼내오고 책 상태에 따라 진행 
				   
				   int re = getBookState(con, rental);
				   
				   if (re == 0) {
					   //대여진행 --> 책상태 1로 변경
					   changeBookState(con, rental);
				   }else if(re == 1) {
					   con.rollback();
					   throw new SQLException("대출중인도서");
				   }else {
					   //상태가 2인 경우 예약된 회원번호와 대여하려는 회원의 번호가 같으면 대출진행
				   }
				}//else끝   
				   
			con.commit();
	     }finally {
	    	con.commit();
	      	DbUtil.close(con, ps , null);
	      }
			
		return result;
	}//메소드끝



	/**
	 * 책상태(bookState) 가져오기
	 * */
	public int getBookState(Connection con, Rental rental) throws SQLException{
		  PreparedStatement ps=null;
		  ResultSet rs = null;
		  String sql="select 상태 from books where 책번호=?";
		  int result = 0;
		 try {
			 ps = con.prepareStatement(sql);
			 ps.setInt(1, rental.getBookNo());
			 rs = ps.executeQuery();
			 result = rs.getInt(1);
		  }	   
        finally {
    	    DbUtil.close(null, ps , rs);
        }
		
		return result;
	}//메소드 끝
	
	
	/**
	 * 대출한 도서 책상태 1로 바꿔주기
	 * */
	public int changeBookState(Connection con, Rental rental) throws SQLException{
		  PreparedStatement ps=null;
		  String sql="update books set 상태 = 1 where 책번호 = ?";
		  int result = 0;
		 try {
			 ps = con.prepareStatement(sql);
			 ps.setInt(1, rental.getBookNo());
			 result = ps.executeUpdate();
		  }	   
      finally {
  	    DbUtil.close(null, ps);
      }
		
		return result;
	}//메소드 끝

}//클래스 끝
