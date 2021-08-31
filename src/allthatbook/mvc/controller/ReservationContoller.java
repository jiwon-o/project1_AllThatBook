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
	
		Reservation reservation = new Reservation(bookNo, user.getUserNo());
		try {
		    reservationService.insertReservation(reservation);
		    EndView.printMessage("'" + bookNo + "'번 도서를 예약했습니다");
		    //EndView.printMessage(book.getBookNo() + "번 도서 대출 성공");
		}catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	

	public static void insertReservation(User user, Book book) {
		
		Reservation reservation = new Reservation(book.getBookNo(), user.getUserNo());
		try {
		    reservationService.insertReservation(reservation);
		    EndView.printMessage("'" + book.getBookNo() + "'번 도서를 예약했습니다");
		    //EndView.printMessage(book.getBookNo() + "번 도서 대출 성공");
		}catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	public static void deleteReservation(int userNo, int bookNo){
		try {
		    reservationService.deleteReservation(userNo, bookNo);
		    EndView.printMessage(bookNo+"번 도서 예약이 삭제되었습니다");

		}catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

}
