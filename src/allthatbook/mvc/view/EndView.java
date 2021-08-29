package allthatbook.mvc.view;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.User;


public class EndView {

	public static void printBookList(User user, List<Book> bookList) {
		System.out.println("----- 총 도서 수: " + bookList.size() + "개 ----------");
		for(Book book : bookList) {
			System.out.println(book);
		}
		
		printCartMenu(user);
	}
	
	public static void printBookList(List<Book> bookList) {
		System.out.println("----- 총 도서 수: " + bookList.size() + "개 ----------");
		for(Book book : bookList) {
			System.out.println(book);
		}

	}
	
	public static void printSelectByNo(User user, Book book) {
		System.out.println(book + "\n");
		
		printCartMenu(user);
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
	public static void printViewCart(User user, Set<Book> cart) {
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
		while(true) {
			System.out.println("1.대여하기  |  2.목록 삭제하기  |  3.장바구니 비우기  |  4.돌아가기");
			switch(Integer.parseInt(sc.nextLine())) {
			case 1:
				break;
			case 2:
				MenuView.deleteCartList(user);
				break;
			case 3:
				MenuView.removeCart(user);
				return;
			case 4:
				return;
			default:
				System.out.println("메뉴에 있는 번호를 입력해주세요");
			}
		}
	}
	
	/**
	 * 장바구니 메뉴
	 */
	public static void printCartMenu(User user) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1.대여하기  |  2.장바구니 담기  |  3.장바구니 보기  |  4.돌아가기");
			switch(Integer.parseInt(sc.nextLine())) {
			case 1:
				break;
			case 2:
				MenuView.putCart(user);
				break;
			case 3:
				MenuView.viewCart(user);
				break;
			case 4:
				MenuView.printSelectMenu(user);
				break;
			default:
				System.out.println("메뉴에 있는 번호를 입력해주세요");
			}
		}

	}
}












