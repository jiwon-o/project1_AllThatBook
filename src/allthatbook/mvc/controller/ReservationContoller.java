package allthatbook.mvc.controller;

import java.sql.SQLException;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.Reservation;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.model.service.RentalServiceImpl;
import allthatbook.mvc.model.service.ReservationService;
import allthatbook.mvc.model.service.ReservationServiceImpl;
import allthatbook.mvc.view.EndView;
import allthatbook.mvc.view.FailView;

public class ReservationContoller {
   
	private static ReservationService reservationService = new ReservationServiceImpl();
	
	public static void insertReservation(User user, int bookNo) {
	//public static void insertReservation(User user, Book book) {
		//Reservation reservation = new Reservation(book.getBookNo(), user.getUserNo());
		Reservation reservation = new Reservation(bookNo, user.getUserNo());
		try {
		    reservationService.insertReservation(reservation);
		    EndView.printMessage(bookNo + "�� ���� ���� ����");
		    //EndView.printMessage(book.getBookNo() + "�� ���� �뿩 ����");
		}catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
}
