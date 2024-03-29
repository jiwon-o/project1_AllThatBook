package allthatbook.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import allthatbook.mvc.model.dto.Reservation;
import allthatbook.mvc.util.DbUtil;

public class ReservationDAOImpl implements ReservationDAO {

	/**
	 * 예약하기
	 * 내가 대출한 책인지 확인
	 * 그전에 중복해서 예약할 수 없으므로 중복체크
	 * 예약은 한명만 가능하므로 우선 예약테이블에 책번호에 해당하는 예약이 있는지 확인
	 * 또한 예약은 대출중인 도서에만 가능하므로 book_state가 1인지도 확인
	 * 확인이 끝난 도서는 예약테이블에 넣어준다.
	 * */	
	public int insertReservation(Reservation reservation) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "insert into reservation values(reservation_seq_no.nextval, ?, ?, sysdate)";
		int result = 0;

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, reservation.getBookNo());
			ps.setInt(2, reservation.getUserNo());
			// 1. 해당 책번호와 회원번호에 해당하는 예약이 예약 테이블에 존재하는지 확인
			int chk = chkDuplicate(con, reservation);
			if (chk == 1)
				throw new SQLException("*** 누군가가 이미 예약했습니다. ***");
			
			// 2. bookstate가 1인지 확인
			chk = getBookState(con, reservation);
			if (chk != 1) throw new SQLException("*** 대출중인 도서만 예약할 수 있습니다. 대출가능하거나 예약중인 도서는 예약할 수 없습니다. ***");
			
			//3. 대출중인 사람이 본인인지 확인
			chk = getUserState(con, reservation);
			if (chk == reservation.getUserNo()) throw new SQLException("*** 본인이 대출한 도서는 예약할 수 없습니다. ***");
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		return result;
	}

	/**
	 * 1. 대출에서 대출대기중인 상태의 책을 대출하면 예약테이블에서 삭제 --> 대출에서 connection 인수로 받아서 2. 예약테이블에서
	 * 바로 삭제
	 */
	@Override
	public int deleteReservation(int userNo, int bookNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "delete from reservation where 회원번호 = ? and 책번호 = ?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, userNo);
			ps.setInt(2, bookNo);
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		return result;
	}

	@Override
	public int deleteReservation(Connection con, Reservation reservation) throws SQLException {
		PreparedStatement ps = null;
		int result = 0;
		String sql = "delete from reservation where 회원번호 = ? and 책번호 = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, reservation.getUserNo());
			ps.setInt(2, reservation.getBookNo());
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(null, ps);
		}
		return result;
	}

	/**
	 * 중복된 책을 예약하는지 체크해주는 메소드
	 */
	public int chkDuplicate(Connection con, Reservation reservation) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select 예약번호, 책번호, 회원번호, to_char(예약일자, 'yy/mm/dd')예약일자 from reservation"
				+ " where 책번호 = ? and 회원번호 = ? ";
		int result = 0;
		int bookNo = reservation.getBookNo();
		int userNo = reservation.getUserNo();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, bookNo);
			ps.setInt(2, userNo);
			rs = ps.executeQuery();
			if (rs.next())
				result = 1;
		} finally {
			DbUtil.close(null, ps, rs);
		}
		return result;
	}
	
	
	public int getBookState(Connection con, Reservation reservation) throws SQLException{
		  PreparedStatement ps=null;
		  ResultSet rs = null;
		  String sql="select 상태 from books where 책번호=?";
		  int result = 0;
		 try {
			 ps = con.prepareStatement(sql);
			 ps.setInt(1, reservation.getBookNo());
			 rs = ps.executeQuery();
			 if (rs.next()) result = rs.getInt(1);
		  }	   
      finally {
  	    DbUtil.close(null, ps , rs);
      }
		
		return result;
	}//메소드 끝

	
	public int getUserState(Connection con, Reservation reservation) throws SQLException{
		  PreparedStatement ps=null;
		  ResultSet rs = null;
		  String sql = "select * from rental where 회원번호 = ? and 책번호 = ? and 반납여부 = 0";
		  int result = 0;
		 try {
			 ps = con.prepareStatement(sql);
			 ps.setInt(1, reservation.getUserNo());
			 ps.setInt(2, reservation.getBookNo());
			 rs = ps.executeQuery();
			 if (rs.next()) { 
				 result = rs.getInt("회원번호");
				 }
		  }	   
    finally {
	    DbUtil.close(null, ps , rs);
    }
		
		return result;
	}//메소드 끝

}
