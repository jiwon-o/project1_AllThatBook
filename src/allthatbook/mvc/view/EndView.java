package allthatbook.mvc.view;

import java.util.List;
import java.util.Scanner;

import allthatbook.mvc.controller.BookController;
import allthatbook.mvc.controller.CartController;
import allthatbook.mvc.controller.RentalController;
import allthatbook.mvc.controller.UpdateAdminController;
import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.Cart;
import allthatbook.mvc.model.dto.CartDetail;
import allthatbook.mvc.model.dto.Rental;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.model.service.BookService;
import allthatbook.mvc.model.service.BookServiceImpl;

public class EndView {

	public static void printBookList(User user, List<Book> bookList) {
		System.out.println("----------------------------------------------    총 도서 수: " + bookList.size() + "개    ------------------------------------------");
		System.out.println("\n");

		for(Book book : bookList) {
			System.out.println(book);
		}
		if(user.getUserId().equals("admin")) {
			BookMenuView.bookDeleteOrUpdateListMenu(user);
		}
	}
	
	public static void printBookList(List<Book> bookList) {
		System.out.println("----------------------------------------------    총 도서 수: " + bookList.size() + "개   -------------------------------------------");
		System.out.println("\n");

		for(Book book : bookList) {
			System.out.println(book);
		}

	}


	
	public static void printSelectByNo(User user, Book book) {
		System.out.println(book + "\n");

		if(user.getUserId().equals("admin")) {
			BookMenuView.bookDeleteOrUpdateMenu(user, book);
		}

	}
	

	public static void printMessage(String message) {
		System.out.println(message);
	}
	
	/**
	 * User 전체 출력
	 */
	public static void printUserList(List<User> userList){
		System.out.println("---------------------------------  전체 User "+userList.size()+  " ----------------------------------------------------------");
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
		System.out.println("------------------------------------------------------  장바구니내용  ----------------------------------------------------------------------");
		System.out.println("\n");
		List<CartDetail> list = cart.getCartDetailList();
		System.out.println("도서 개수 : "+  list.size());
		System.out.println("\n");
		for(CartDetail cartDetail : list) {
			
			int bookNo = cartDetail.getBookNo(); //책번호
			//책번호로 책정보를 출력하는 메소드
			Book book = BookController.bookSelectByBookNo2(bookNo);
			System.out.println(book);

		}
	}
	
}












