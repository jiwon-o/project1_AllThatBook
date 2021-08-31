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
		System.out.println("----------------------------------------------    �� ���� ��: " + bookList.size() + "��    ------------------------------------------");
		System.out.println("\n");

		for(Book book : bookList) {
			System.out.println(book);
		}
		if(user.getUserId().equals("admin")) {
			BookMenuView.bookDeleteOrUpdateListMenu(user);
		}
	}
	
	public static void printBookList(List<Book> bookList) {
		System.out.println("----------------------------------------------    �� ���� ��: " + bookList.size() + "��   -------------------------------------------");
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
	 * User ��ü ���
	 */
	public static void printUserList(List<User> userList){
		System.out.println("---------------------------------  ��ü User "+userList.size()+  " ----------------------------------------------------------");
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
		System.out.println("------------------------------------------------------  ��ٱ��ϳ���  ----------------------------------------------------------------------");
		System.out.println("\n");
		List<CartDetail> list = cart.getCartDetailList();
		System.out.println("���� ���� : "+  list.size());
		System.out.println("\n");
		for(CartDetail cartDetail : list) {
			
			int bookNo = cartDetail.getBookNo(); //å��ȣ
			//å��ȣ�� å������ ����ϴ� �޼ҵ�
			Book book = BookController.bookSelectByBookNo2(bookNo);
			System.out.println(book);

		}
	}
	
}












