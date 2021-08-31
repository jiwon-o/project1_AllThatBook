package allthatbook.mvc.controller;

import java.sql.SQLException;
import java.util.Scanner;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.Rental;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.model.service.RentalService;
import allthatbook.mvc.model.service.RentalServiceImpl;
import allthatbook.mvc.view.CartMenuView;
import allthatbook.mvc.view.EndView;
import allthatbook.mvc.view.FailView;

public class RentalController {
    
	private static RentalService rentalService = new RentalServiceImpl();
	
	/**
	 * 도서번호
	 * @param user
	 * @param book
	 */
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
		Scanner sc = new Scanner(System.in);
		try {
			Rental rental = new Rental( bookNo, user.getUserNo() );
			System.out.println("'" + bookNo + "'번 도서를 대출하시겠습니까? ( 네 / 아니오 )");
			String checkRental = sc.nextLine();
			if("네".equals(checkRental)) {
				rentalService.insertRental(rental);
				EndView.printMessage("*** '" + bookNo + "'번 도서를 대출했습니다. ***");
			}else if("아니오".equals(checkRental)) {
				System.out.println("*** 대출을 취소했습니다. ***\n");
				return;
			}else {
				System.out.println("*** ( 네 / 아니오 ) 중 하나만 입력해주세요. ***\n");
				return;
			}
			
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

			EndView.printMessage("*** '"+bookNo + "'번 도서를 반납하였습니다. ***");

		}catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
}
