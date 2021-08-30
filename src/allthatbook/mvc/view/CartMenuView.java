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

	/**
	 * ��ٱ��� �޴�
	 */

	public static void printCartMenu(User user, Book book) {

		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1.�뿩�ϱ�  |  2.��ٱ��� ���  |  3.��ٱ��� ����  |  4.���ư���  |  9.���θ޴��� ����");
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
