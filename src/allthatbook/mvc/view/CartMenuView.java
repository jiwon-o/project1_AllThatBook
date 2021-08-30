package allthatbook.mvc.view;

import java.util.Scanner;

import allthatbook.mvc.controller.CartController;

public class CartMenuView {
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * ��ٱ��� ���
	 */
	public static void putCart(String userId) {
		System.out.println("----��ٱ��� ���----");
		System.out.print("å��ȣ : ");
		int bookNo = Integer.parseInt(sc.nextLine());

		CartController.putCart(userId, bookNo);

	}
    
    /**
     * ��ٱ��� ����
     * */
	public static void viewCart(String userId) {
		CartController.viewCart(userId);
	}

}
