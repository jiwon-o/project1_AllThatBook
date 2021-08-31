package allthatbook.mvc.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import allthatbook.mvc.model.dao.ReservationDAO;
import allthatbook.mvc.model.dto.Reservation;
import allthatbook.mvc.util.DbUtil;

public class ReservationServiceImpl implements ReservationService{

	@Override
	public void insertReservation(Reservation reservation) throws SQLException {
		int result = reservationDAO.insertReservation(reservation);
		if (result == 0) throw new SQLException(" 예약실패 ");
		
	}
	
	public void deleteReservation(int userNo, int bookNo) throws SQLException {
		int result = reservationDAO.deleteReservation(userNo, bookNo);
		if (result == 0) throw new SQLException(" 예약 삭제 실패 ");
	}

}
