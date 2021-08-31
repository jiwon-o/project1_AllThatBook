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
	 * ������ȣ
	 * @param user
	 * @param book
	 */
	public static void insertRental(User user, Book book) {
		try {
			Rental rental = new Rental( book.getBookNo(), user.getUserNo() );
			rentalService.insertRental(rental);

			EndView.printMessage("*** '" + book.getBookNo() + "'�� ������ �����߽��ϴ�. ***");
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	public static void insertRental(User user, int bookNo) {
		Scanner sc = new Scanner(System.in);
		try {
			Rental rental = new Rental( bookNo, user.getUserNo() );
			System.out.println("'" + bookNo + "'�� ������ �����Ͻðڽ��ϱ�? ( �� / �ƴϿ� )");
			String checkRental = sc.nextLine();
			if("��".equals(checkRental)) {
				rentalService.insertRental(rental);
				EndView.printMessage("*** '" + bookNo + "'�� ������ �����߽��ϴ�. ***");
			}else if("�ƴϿ�".equals(checkRental)) {
				System.out.println("*** ������ ����߽��ϴ�. ***\n");
				return;
			}else {
				System.out.println("*** ( �� / �ƴϿ� ) �� �ϳ��� �Է����ּ���. ***\n");
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

			EndView.printMessage("*** '"+bookNo + "'�� ������ �ݳ��Ͽ����ϴ�. ***");

		}catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
}
