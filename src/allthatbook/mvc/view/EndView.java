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
import allthatbook.mvc.model.dto.Reservation;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.model.service.BookService;
import allthatbook.mvc.model.service.BookServiceImpl;

public class EndView {

	public static void printBookList(User user, List<Book> bookList) {
		System.out.println("\n------------------------------------------------------------------ *** 검색 목록 (" + bookList.size() + "개 ***) ------------------------------------------------------------------\n");
	
		for(Book book : bookList) {
			System.out.println(book);
		}
		System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
	}
	
	public static void printBookList(List<Book> bookList) {
		System.out.println("\n------------------------------------------------------------------ *** 검색 목록 (" + bookList.size() + "개 ***) ------------------------------------------------------------------\n");

		for(Book book : bookList) {
			System.out.println(book);
		}
		System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}


	
	public static void printSelectByNo(User user, Book book) {
		System.out.println("\n-------------------------------------------------------------------- *** 검색 목록 *** --------------------------------------------------------------------\n");
		System.out.println(book + "\n");
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
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
		
	}

	/**
	 * 장바구니 출력
	 */

	public static void printViewCart(User user, Cart cart) {
		List<CartDetail> list = cart.getCartDetailList();
		System.out.println("\n------------------------------------------------------------- *** 장바구니 목록 (" + list.size() + "개 ***) -------------------------------------------------------------\n");
		for(CartDetail cartDetail : list) {
			
			int bookNo = cartDetail.getBookNo(); //책번호
			//책번호로 책정보를 출력하는 메소드
			Book book = BookController.bookSelectByBookNo2(bookNo);
			System.out.println(book);

		}
		System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------------\n");
	}
	
	public static void printReservation(List<Reservation> list) {
		System.out.println("------------------------------------------------------ *** 예약목록 (" + list.size() + "개) *** ------------------------------------------------------\n");
		for (Reservation reservation : list) {
			System.out.println(reservation);
		}
		System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------\n");
	}
	
	public static void printRental(List<Rental> list) {
		System.out.println("------------------------------------------------------ *** 대출목록 (" + list.size() + "개) *** ------------------------------------------------------\n");
		for (Rental rental : list) {
			System.out.println(rental);
		}
		System.out.println("\n");
	}
	
}












