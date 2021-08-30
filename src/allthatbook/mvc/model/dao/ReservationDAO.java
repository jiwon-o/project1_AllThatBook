package allthatbook.mvc.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import allthatbook.mvc.model.dto.Rental;
import allthatbook.mvc.model.dto.Reservation;

public interface ReservationDAO {
    
	
	/**
	 * 예약하기
	 * 예약은 한명만 가능하므로 우선 예약테이블에 책번호에 해당하는 예약이 있는지 확인
	 * 또한 예약은 대출중인 도서에만 가능하므로 book_state가 1인지도 확인
	 * 확인이 끝난 도서는 예약테이블에 넣어준다.
	 * */
	int insertReservation(Reservation reservation) throws SQLException;
	
	/**
	 * 예약삭제 (오버로딩)
	 * 1. 대출에서 대출대기중인 상태의 책을 대출하면 예약테이블에서 삭제 --> 대출에서 connection 인수로 받아서
	 * 2. 예약테이블에서 바로 삭제 
	 * */
	int deleteReservation(Reservation reservation) throws SQLException; 
	
	int deleteReservation(Connection con, Reservation reservation) throws SQLException; 
	
	
}
