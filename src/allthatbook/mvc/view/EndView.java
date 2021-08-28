package allthatbook.mvc.view;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.User;


public class EndView {

	public static void printBookList(String userId, List<Book> bookList) {
		System.out.println("----- �� ���� ��: " + bookList.size() + "�� ----------");
		for(Book book : bookList) {
			System.out.println(book);
		}
		
		printCartMenu(userId);
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
	public static void printViewCart(String id, Set<Book> cart) {
		System.out.println("---��ٱ��ϳ���---");
		
		for(Book book : cart) {
			int bookNo = book.getBookNo(); //å��ȣ
			String bookName = book.getBookName(); //å�̸�
			String bookWriter = book.getBookWriter(); //����
			String bookPublisher = book.getBookPublisher(); //���ǻ�
			String bookField = book.getBookField(); //å �о�
			int bookState = book.getBookState();
			
			System.out.println(bookNo + " | " + bookName + " | " + bookWriter + " | " + bookPublisher + " | " + bookField + " | " + bookState);
		}
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1.�뿩�ϱ�  |  2.��� �����ϱ�  |  3.��ٱ��� ����  |  4.���ư���");
			switch(Integer.parseInt(sc.nextLine())) {
			case 1:
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
		System.out.println("1.�뿩�ϱ�  |  9.������");
		switch(Integer.parseInt(sc.nextLine())) {
		case 1:
			//rental = new Rental()....
			//�ִµ� ���� �߰��� ���� �ִ¼� ����ٸ�
			break;
		case 9:
			break;
		}
	 }
	}
}












