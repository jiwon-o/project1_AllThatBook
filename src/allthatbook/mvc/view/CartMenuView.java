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
	 * ��ٱ��� ���(å����, ���ǻ�.. ������ �˻����� ��)
	 */
	public static void putCart(String userId) {
		System.out.println("\n");
		System.out.println("----------------------------- ��ٱ��� ��� -------------------------------");
		System.out.print("å��ȣ : ");
		int bookNo = Integer.parseInt(sc.nextLine());

		CartController.putCart(userId, bookNo);
	}
	
	/**
	 * ��ٱ��� ���(å��ȣ�� �˻����� ��)
	 */
	public static void putCart(String userId, Book book) {
		System.out.println("----��ٱ��� ���----");
		
		CartController.putCart(userId, book.getBookNo());
	}
    
    /**
     * ��ٱ��� ����
     * */
	public static void viewCart(String userId) {
		CartController.viewCart(userId);
	}

	
	/**
	 * ��ٱ��� �޴�(å��ȣ�� �˻����� ��)
	 */
	public static void printCartMenu(User user, Book book) {
		while(true) {

			System.out.println("1. �뿩�ϱ�    2. ��ٱ��� ���   3. ��ٱ��� ����    4. ���ư���    9. ���θ޴��� ����  ");
			System.out.println("------------------------------------------------------------------------");
			System.out.print("��ȣ �Է� > ");

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
	 * ��ٱ��� �޴�(å����, ���ǻ�.. ������ �˻����� ��)
	 * @param user
	 */
	public static void printCartMenu(User user) {
		while(true) {
			System.out.println("1.�뿩�ϱ�  |  2.��ٱ��� ���  |  3.��ٱ��� ����  |  4.���ư���  ");
			System.out.print("��ȣ �Է� > ");
			switch(Integer.parseInt(sc.nextLine())) {
			case 1:
				System.out.print("�뿩 �Ͻ�å ��ȣ �Է� > ");
				int bookNo = Integer.parseInt(sc.nextLine());
				RentalController.insertRental(user, bookNo);
				break;
			case 2:
				CartMenuView.putCart(user.getUserId());
				break;
			case 3:
				CartMenuView.viewCart(user.getUserId());
				break;
			case 4:
				return;
			}
		}
	}
	
}