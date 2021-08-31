package allthatbook.mvc.controller;

import java.sql.SQLException;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.Rental;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.model.service.RentalService;
import allthatbook.mvc.model.service.RentalServiceImpl;
import allthatbook.mvc.view.EndView;
import allthatbook.mvc.view.FailView;

public class RentalController {
    
	private static RentalService rentalService = new RentalServiceImpl();
	
	public static void insertRental(User user, Book book) {
		try {
			Rental rental = new Rental( book.getBookNo(), user.getUserNo() );
			rentalService.insertRental(rental);

			EndView.printMessage("*** '" + book.getBookNo() + "'번 도서를 대출했습니다. ***");
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	public static void insertRental(User user, int bookNo) {
		try {
			Rental rental = new Rental( bookNo, user.getUserNo() );
			rentalService.insertRental(rental);
			EndView.printMessage("*** '" + bookNo + "'번 도서를 대출했습니다. ***");
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	//public static void returnBook(User user, Book book) {
	public static void returnBook(User user, int bookNo) {
		//int bookNo = book.getBookNo;
		try {
			Rental rental = new Rental(bookNo, user.getUserNo());
			rentalService.returnBook(rental);
			EndView.printMessage("*** '"+bookNo + "'번 반납 성공하였습니다. ***");
		}catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
}
