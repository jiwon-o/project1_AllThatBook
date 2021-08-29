package allthatbook.mvc.view;

import java.util.List;
import java.util.Scanner;

import allthatbook.mvc.controller.BookController;
import allthatbook.mvc.controller.CartController;
import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.Cart;
import allthatbook.mvc.model.dto.CartDetail;
import allthatbook.mvc.model.dto.User;


public class EndView {

	public static void printBookList(String userId, List<Book> bookList) {
		System.out.println("----- �� ���� ��: " + bookList.size() + "�� ----------");
		for(Book book : bookList) {
			System.out.println(book);
		}
		
		printCartMenu(userId);

	}
	
	public static void printBookList(List<Book> bookList) {
		System.out.println("----- �� ���� ��: " + bookList.size() + "�� ----------");
		for(Book book : bookList) {
			System.out.println(book);
		}

	}


	
	public static void printSelectByNo(String userId, Book book) {
		System.out.println(book + "\n");
		printCartMenu(userId);
	}
	
	public static void printMessage(String message) {
		System.out.println(message);
	}
	
	/**
	 * User ��ü ���
	 */
	public static void printUserList(List<User> userList){
		System.out.println("-----��ü User "+userList.size()+"��------");
		for(User user : userList) {
			System.out.println(user);
		}
		System.out.println();
	}
	/**
	 * UserId�� ��ȸ�ؼ� ���
	 */
	public static void printSelectByUserId(User user) {
		System.out.println(user);
		System.out.println();
	}

	/**
	 * ��ٱ��� ���
	 */
	public static void printViewCart(String id, Cart cart) {
		System.out.println("---��ٱ��ϳ���---");
		List<CartDetail> list = cart.getCartDetailList();
		for(CartDetail cartDetail : list) {
			int bookNo = cartDetail.getBookNo(); //å��ȣ
			BookController.bookSelectByBookNo(id, bookNo);
		}
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1.�뿩�ϱ�  |  2.��� �����ϱ�  |  3.��ٱ��� ����  |  4.���ư���");
			switch(Integer.parseInt(sc.nextLine())) {
			case 1:
				CartController.rentalCartBook(id, cart);
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				return;
			}
		}
	}
	
	/**
	 * ��ٱ��� �޴�
	 */
	public static void printCartMenu(String userId) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1.�뿩�ϱ�  |  2.��ٱ��� ���  |  3.��ٱ��� ����  |  4.���ư���  |  9.���θ޴��� ����");
			switch(Integer.parseInt(sc.nextLine())) {
			case 1:
				break;
			case 2:
				MenuView.putCart(userId);
				break;
			case 3:
				MenuView.viewCart(userId);
				break;
			case 4:
				MenuView.printSelectMenu(userId);
				break;
			case 9:
				//MenuView.printUserMenu(userId);
				return;
			}
		}
	}
}












