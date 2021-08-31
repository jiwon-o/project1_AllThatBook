package allthatbook.mvc.model.service;

import java.sql.SQLException;

import allthatbook.mvc.model.dao.ReservationDAO;
import allthatbook.mvc.model.dao.ReservationDAOImpl;
import allthatbook.mvc.model.dto.Reservation;

public interface ReservationService {
    ReservationDAO reservationDAO = new ReservationDAOImpl();
	/**
	 * 예약하기
	 * */
	public void insertReservation(Reservation reservation) throws SQLException;
}
