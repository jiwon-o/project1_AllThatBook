package allthatbook.mvc.view;

import java.util.Scanner;

import allthatbook.mvc.controller.CartController;

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

}
