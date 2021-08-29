package allthatbook.mvc.model.dao;

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
	int reservationInsert(Reservation reservation) throws SQLException;
	
}
