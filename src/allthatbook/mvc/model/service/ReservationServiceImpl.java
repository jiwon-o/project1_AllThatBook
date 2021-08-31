package allthatbook.mvc.model.service;

import java.sql.SQLException;

import allthatbook.mvc.model.dao.ReservationDAO;
import allthatbook.mvc.model.dto.Reservation;

public class ReservationServiceImpl implements ReservationService{

	@Override
	public void insertReservation(Reservation reservation) throws SQLException {
		int result = reservationDAO.insertReservation(reservation);
		if (result == 0) throw new SQLException(" 예약실패 ");
		
	}

}
