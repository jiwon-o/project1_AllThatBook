package allthatbook.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.SQLException;

import allthatbook.mvc.model.dto.Reservation;
import allthatbook.mvc.util.DbUtil;

public class ReservationDAOImpl implements ReservationDAO {

	
	
	/**
	 * 예약하기
	 * 예약은 한명만 가능하므로 우선 예약테이블에 책번호에 해당하는 예약이 있는지 확인
	 * 또한 예약은 대출중인 도서에만 가능하므로 book_state가 1인지도 확인
	 * 확인이 끝난 도서는 예약테이블에 넣어준다.
	 * */	
	@Override
	public int insertReservation(Reservation reservation) throws SQLException {
//		Connection con = null;
//		PreparedStatement ps = null;
//		String sql = "insert into reservation values(reservation_seq_no.nextval, ?, ?, sysdate )";
//		int result = 0;
//		
//		try {
//			con = DbUtil.getConnection();
//			ps = con.prepareStatement(sql);
//			ps.setInt(1, reservation.getBookNo());
//			ps.setInt(2, reservation.getUserNo());
//			result = ps.executeUpdate();
//		}
//		
		return 0;
	}

	 /**
	 * 1. 대출에서 대출대기중인 상태의 책을 대출하면 예약테이블에서 삭제 --> 대출에서 connection 인수로 받아서
	 * 2. 예약테이블에서 바로 삭제	  
	 * */
	
	@Override
	public int deleteReservation(Reservation reservation) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReservation(Connection con, Reservation reservation) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
