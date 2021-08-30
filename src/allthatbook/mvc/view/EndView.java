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
		System.out.println("----- 총 도서 수: " + bookList.size() + "개 ----------");
		for(Book book : bookList) {
			System.out.println(book);
		}
		if(userId.equals("admin")) {
			bookDeleteOrUpdateListMenu(userId);
		}else printCartMenu(userId);

	}
	
	public static void printBookList(List<Book> bookList) {
		System.out.println("----- 총 도서 수: " + bookList.size() + "개 ----------");
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
		System.out.println(list.size());
		for(CartDetail cartDetail : list) {
			
			int bookNo = cartDetail.getBookNo(); //책번호
			//책번호로 책정보를 출력하는 메소드
			Book book = BookController.bookSelectByBookNo2(bookNo);
			System.out.println(book);
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
	
	/**
	 * 관리자 도서조회(도서번호) -> 삭제 or 수정 
	 */
	public static void bookDeleteOrUpdateMenu(String userId, Book book) {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		while(flag) {
			int result=0;
			System.out.println("1. 선택도서 수정 | 2. 선택도서 삭제 | 3. 돌아가기 | 9. 메인메뉴로 가기");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
				case 1 :
					Book updatebook = AdminMenuView.updateBook();
					result = UpdateAdminController.bookUpdate(book.getBookNo(), updatebook);
					if(result==1)System.out.println(book.getBookNo()+"번 해당 책이 수정되었습니다.");
					break;
				case 2 :
					result = BookController.bookDelete(book.getBookNo());	
					if(result==1)System.out.println(book.getBookNo()+"번호가 삭제되었습니다.");
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
	 * 관리자 도서조회(도서명 저자명 출판사 분야) -> 삭제 or 수정
	 */
	public static void bookDeleteOrUpdateListMenu(String userId) {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		while(flag) {
			System.out.println("1. 선택도서 수정 | 2. 선택도서 삭제 | 3. 돌아가기 | 9. 메인메뉴로 가기");
			int menu = Integer.parseInt(sc.nextLine());
			int bookNo=0;
			int result=0;
			switch(menu) {
				case 1:
					bookNo = AdminMenuView.InputBookNo();
					Book updatebook = AdminMenuView.updateBook();
					result = UpdateAdminController.bookUpdate(bookNo, updatebook);
					if(result==1)System.out.println(bookNo+"번 해당 책이 수정되었습니다.");
					break;
				case 2:
					bookNo = AdminMenuView.InputBookNo();
					result = BookController.bookDelete(bookNo);	
					if(result==1)System.out.println(bookNo+"번호가 삭제되었습니다.");
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












