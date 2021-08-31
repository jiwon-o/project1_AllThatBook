package allthatbook.mvc.view;

import java.util.List;
import java.util.Scanner;

import allthatbook.mvc.controller.CartController;
import allthatbook.mvc.controller.RentalController;
import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.User;

public class CartMenuView {
	private static Scanner sc = new Scanner(System.in);
	
	
	/**
	 * 장바구니 담기(책제목, 출판사.. 등으로 검색했을 때)
	 */
	public static void putCart(String userId) {
		System.out.println("\n");
		System.out.println(">>>>>> 장바구니 담기 >>>>>>");
		System.out.print("도서번호 :  ");
		int bookNo = Integer.parseInt(sc.nextLine());

		CartController.putCart(userId, bookNo);
	}
	
	/**
	 * 장바구니 담기(책번호로 검색했을 때)
	 */
	public static void putCart(String userId, Book book) {
		System.out.println(">>>>>> 장바구니 담기 >>>>>>");
		
		CartController.putCart(userId, book.getBookNo());
	}
    
    /**
     * 장바구니 보기
     * */
	public static void viewCart(String userId) {
		CartController.viewCart(userId);
	}

	
	/**
	 * 장바구니 메뉴(책번호로 검색했을 때)
	 */
	public static void printCartMenu(User user, Book book) {
		while(true) {

			System.out.println("1. 대여하기    2. 장바구니 담기   3. 장바구니 보기    4. 돌아가기    9. 메인메뉴로 가기  ");
			System.out.println("------------------------------------------------------------------------");
			System.out.print("번호 입력 :  ");

			switch(Integer.parseInt(sc.nextLine())) {
			case 1:
				RentalController.insertRental(user, book);
				return;
			case 2:
				CartMenuView.putCart(user.getUserId(), book);
				break;
			case 3:
				CartMenuView.viewCart(user.getUserId());
				
				break;
			case 4:
				return;
			}
		}
	}
	
	/**
	 * 장바구니 메뉴(책제목, 출판사.. 등으로 검색했을 때)
	 * @param user
	 */
	public static void printCartMenu(User user) {
		while(true) {
			System.out.println("1. 대여하기    2. 장바구니 담기   3. 장바구니 보기    4. 돌아가기   ");
			System.out.println("--------------------------------------------------------");
			System.out.print("번호 입력 :  ");
			switch(Integer.parseInt(sc.nextLine())) {
			case 1:
				System.out.print("대여할 도서의 번호입력  : ");  
				int bookNo = Integer.parseInt(sc.nextLine());
				RentalController.insertRental(user, bookNo);
				break;
			case 2:
				CartMenuView.putCart(user.getUserId());
				break;
			case 3:
				System.out.println("\n");
				CartMenuView.viewCart(user.getUserId());
				break;
			case 4:
				return;
			}
		}
	}
	
}
