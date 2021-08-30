package allthatbook.mvc.view;

import java.util.Scanner;

import allthatbook.mvc.controller.CartController;

import allthatbook.mvc.model.dto.Book;

import allthatbook.mvc.controller.RentalController;
import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.User;

public class CartMenuView {
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * 장바구니 담기
	 */
	public static void putCart(String userId) {
		System.out.println("----장바구니 담기----");
		System.out.print("책번호 : ");
		int bookNo = Integer.parseInt(sc.nextLine());

		CartController.putCart(userId, bookNo);

	}
    
    /**
     * 장바구니 보기
     * */
	public static void viewCart(String userId) {
		CartController.viewCart(userId);
	}

	/**
	 * 장바구니 메뉴
	 */

	public static void printCartMenu(User user, Book book) {

		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1.대여하기  |  2.장바구니 담기  |  3.장바구니 보기  |  4.돌아가기  |  9.메인메뉴로 가기");
			switch(Integer.parseInt(sc.nextLine())) {
			case 1:
				RentalController.insertRental(user, book);
				break;
			case 2:
				CartMenuView.putCart(user.getUserId());
				break;
			case 3:
				CartMenuView.viewCart(user.getUserId());
				break;
			case 4:
				BookMenuView.printSelectMenu(user);
				break;
			case 9:
				//MenuView.printUserMenu(userId);
				return;
			}
		}
	}
}
