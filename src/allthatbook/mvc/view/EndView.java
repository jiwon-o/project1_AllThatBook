package allthatbook.mvc.view;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.User;


public class EndView {
	
	public static void printBookList(List<Book> list) {
		
	}
	
	public static void printSelectByNo(Book book) {
		System.out.println(book + "\n");
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
		System.out.println("1.�ֹ��ϱ�  |  9.������");
		switch(Integer.parseInt(sc.nextLine())) {
		case 1:
			
			break;
		case 9:
			break;
		}
	}
}












