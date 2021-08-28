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
	 * User 전체 출력
	 */
	public static void printUserList(List<User> userList){
		System.out.println("-----전체 User "+userList.size()+"명------");
		for(User user : userList) {
			System.out.println(user);
		}
		System.out.println();
	}
	/**
	 * UserId로 조회해서 출력
	 */
	public static void printSelectByUserId(User user) {
		System.out.println(user);
		System.out.println();
	}

	/**
	 * 장바구니 출력
	 */
	public static void printViewCart(String id, Set<Book> cart) {
		System.out.println("---장바구니내용---");
		
		for(Book book : cart) {
			int bookNo = book.getBookNo(); //책번호
			String bookName = book.getBookName(); //책이름
			String bookWriter = book.getBookWriter(); //저자
			String bookPublisher = book.getBookPublisher(); //출판사
			String bookField = book.getBookField(); //책 분야
			int bookState = book.getBookState();
			
			System.out.println(bookNo + " | " + bookName + " | " + bookWriter + " | " + bookPublisher + " | " + bookField + " | " + bookState);
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("1.주문하기  |  9.나가기");
		switch(Integer.parseInt(sc.nextLine())) {
		case 1:
			
			break;
		case 9:
			break;
		}
	}
}












