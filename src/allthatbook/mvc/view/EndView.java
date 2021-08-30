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
	public static void printBookList(String userId, List<Book> bookList) {
		System.out.println("----- �� ���� ��: " + bookList.size() + "�� ----------");
		for(Book book : bookList) {
			System.out.println(book);
		}
		if(userId.equals("admin")) {
			bookDeleteOrUpdateListMenu(userId);
		}else printCartMenu(userId);

	}
	
	public static void printBookList(List<Book> bookList) {
		System.out.println("----- �� ���� ��: " + bookList.size() + "�� ----------");
		for(Book book : bookList) {
			System.out.println(book);
		}

	}


	
	public static void printSelectByNo(String userId, Book book) {
		System.out.println(book + "\n");
		if(userId.equals("admin")) {
			bookDeleteOrUpdateMenu(userId, book);
		}else printCartMenu(userId);
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
		System.out.println(list.size());
		for(CartDetail cartDetail : list) {
			
			int bookNo = cartDetail.getBookNo(); //å��ȣ
			//å��ȣ�� å������ ����ϴ� �޼ҵ�
			Book book = BookController.bookSelectByBookNo2(bookNo);
			System.out.println(book);
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
	
	/**
	 * ������ ������ȸ(������ȣ) -> ���� or ���� 
	 */
	public static void bookDeleteOrUpdateMenu(String userId, Book book) {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		while(flag) {
			int result=0;
			System.out.println("1. ���õ��� ���� | 2. ���õ��� ���� | 3. ���ư��� | 9. ���θ޴��� ����");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
				case 1 :
					Book updatebook = AdminMenuView.updateBook();
					result = UpdateAdminController.bookUpdate(book.getBookNo(), updatebook);
					if(result==1)System.out.println(book.getBookNo()+"�� �ش� å�� �����Ǿ����ϴ�.");
					break;
				case 2 :
					result = BookController.bookDelete(book.getBookNo());	
					if(result==1)System.out.println(book.getBookNo()+"��ȣ�� �����Ǿ����ϴ�.");
					break;
				case 3:
					flag = false;
					MenuView.printSelectMenu(userId);
					
					break;
				case 9:
					return;
			}
		}
	}
	/**
	 * ������ ������ȸ(������ ���ڸ� ���ǻ� �о�) -> ���� or ����
	 */
	public static void bookDeleteOrUpdateListMenu(String userId) {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		while(flag) {
			System.out.println("1. ���õ��� ���� | 2. ���õ��� ���� | 3. ���ư��� | 9. ���θ޴��� ����");
			int menu = Integer.parseInt(sc.nextLine());
			int bookNo=0;
			int result=0;
			switch(menu) {
				case 1:
					bookNo = AdminMenuView.InputBookNo();
					Book updatebook = AdminMenuView.updateBook();
					result = UpdateAdminController.bookUpdate(bookNo, updatebook);
					if(result==1)System.out.println(bookNo+"�� �ش� å�� �����Ǿ����ϴ�.");
					break;
				case 2:
					bookNo = AdminMenuView.InputBookNo();
					result = BookController.bookDelete(bookNo);	
					if(result==1)System.out.println(bookNo+"��ȣ�� �����Ǿ����ϴ�.");
					break;
				case 3:
					flag = false;
					MenuView.printSelectMenu(userId);
					
					break;
				case 9:
					return;
			}
			
		}
	}
}












