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
		System.out.println("----- 총 도서 수: " + bookList.size() + "개 ----------");
		for(Book book : bookList) {
			System.out.println(book);
		}
		
		printCartMenu(userId);

	}
	
	public static void printBookList(List<Book> bookList) {
		System.out.println("----- 총 도서 수: " + bookList.size() + "개 ----------");
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
	public static void printViewCart(String id, Cart cart) {
		System.out.println("---장바구니내용---");
		List<CartDetail> list = cart.getCartDetailList();
		for(CartDetail cartDetail : list) {
			int bookNo = cartDetail.getBookNo(); //책번호
			BookController.bookSelectByBookNo(id, bookNo);
		}
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1.대여하기  |  2.목록 삭제하기  |  3.장바구니 비우기  |  4.돌아가기");
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
	 * 장바구니 메뉴
	 */
	public static void printCartMenu(String userId) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1.대여하기  |  2.장바구니 담기  |  3.장바구니 보기  |  4.돌아가기  |  9.메인메뉴로 가기");
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












