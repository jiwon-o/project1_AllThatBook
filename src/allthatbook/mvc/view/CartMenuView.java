package allthatbook.mvc.view;

import java.util.List;
import java.util.Scanner;

import allthatbook.mvc.controller.CartController;
import allthatbook.mvc.controller.RentalController;
import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.Cart;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.session.Session;
import allthatbook.mvc.session.SessionSet;

public class CartMenuView {
	private static Scanner sc = new Scanner(System.in);
	
	
	/**
	 * 장바구니 담기(책제목, 출판사.. 등으로 검색했을 때)
	 */
	public static void putCart(String userId) {
		System.out.println("\n");
		System.out.println("----------------------------- 장바구니 담기 -------------------------------");
		System.out.print("책번호 : ");
		int bookNo = Integer.parseInt(sc.nextLine());

		CartController.putCart(userId, bookNo);
	}
	
	/**
	 * 장바구니 담기(책번호로 검색했을 때)
	 */
	public static void putCart(String userId, Book book) {
		System.out.println("----장바구니 담기----");
		
		CartController.putCart(userId, book.getBookNo());
	}
    
    /**
     * 장바구니 보기
     * */
	public static void viewCart(String userId) {
		CartController.viewCart(userId);
	}

	
	/**
	 * 장바구니 메뉴(책번호로 검색했을 때)
	 */
	public static void printCartMenu(User user, Book book) {
		while(true) {

			System.out.println("1. 대여하기    2. 장바구니 담기   3. 장바구니 보기    4. 돌아가기    9. 메인메뉴로 가기  ");
			System.out.println("------------------------------------------------------------------------");
			System.out.print("번호 입력 > ");

			switch(Integer.parseInt(sc.nextLine())) {
			case 1:
				System.out.println(book.getBookNo() + ". < " + book.getBookName() + " > 책을 대여하시겠습니까? ( 네 or 아니오 )");
				String checkRental = sc.nextLine();
				if("네".equals(checkRental)) {
					RentalController.insertRental(user, book);
				}else if("아니오".equals(checkRental)) {
					System.out.println("대출되지 않았습니다.");
					return;
				}else {
					System.out.println("( 네 or 아니오 ) 중 하나만 입력해주세요.");
					CartMenuView.printCartMenu(user, book);
				}
				
				return;
			case 2:
				CartMenuView.putCart(user.getUserId(), book);
				break;
			case 3:		/////////////////////////
				CartMenuView.viewCart(user.getUserId());
				
				break;
			case 4:
				return;
			}
		}
	}
	
	/**
	 * 장바구니 메뉴(책제목, 출판사.. 등으로 검색했을 때)
	 * @param user
	 */
	public static void printCartMenu(User user) {
		while(true) {
			System.out.println("1.대여하기  |  2.장바구니 담기  |  3.장바구니 보기  |  4.돌아가기  ");
			System.out.print("번호 입력 > ");
			switch(Integer.parseInt(sc.nextLine())) {
			case 1:
				System.out.print("대여 하실책 번호 입력 > ");
				int bookNo = Integer.parseInt(sc.nextLine());
				RentalController.insertRental(user, bookNo);
				break;
			case 2:
				CartMenuView.putCart(user.getUserId());
				break;
			case 3:		/////////////////////////
				CartMenuView.viewCart(user.getUserId());
				SessionSet ss = SessionSet.getInstance();
				Session session = ss.get(user.getUserId());

				Cart cart = (Cart) session.getAttribute("cart");
				CartController.rentalCartBook( user.getUserId(), cart);
				//세션에 있는 cart객체 안에 있는 cartDetailList 안에 번호에 맞는 삭제
				break;
			case 4:
				return;
			}
		}
	}
	
	public static void printRentalMenu(User user) {
		while(true) {
			System.out.println("1.전체 목록 대여하기  |  4.돌아가기  ");
			System.out.print("번호 입력 > ");
			switch(Integer.parseInt(sc.nextLine())) {
			case 1:
				System.out.print("대여 하실책 번호 입력 > ");
				int bookNo = Integer.parseInt(sc.nextLine());
				RentalController.insertRental(user, bookNo);
				break;
			case 2:
				CartMenuView.putCart(user.getUserId());
				break;
			case 3:		/////////////////////////
				CartMenuView.viewCart(user.getUserId());
				SessionSet ss = SessionSet.getInstance();
				Session session = ss.get(user.getUserId());

				Cart cart = (Cart) session.getAttribute("cart");
				CartController.rentalCartBook( user.getUserId(), cart);
				//세션에 있는 cart객체 안에 있는 cartDetailList 안에 번호에 맞는 삭제
				break;
			case 4:
				return;
			}
		}
	}
	
}
