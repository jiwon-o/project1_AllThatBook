package allthatbook.mvc.view;

import java.util.Scanner;

import allthatbook.mvc.controller.CartController;
import allthatbook.mvc.controller.RentalController;
import allthatbook.mvc.controller.ReservationContoller;
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
		try {
			System.out.print("도서번호 : ");
			int bookNo = Integer.parseInt(sc.nextLine());
			
			CartController.putCart(userId, bookNo);
			
		}catch (NumberFormatException e) {
			FailView.errorMessage("*** 도서번호는 숫자를 입력해주세요. ***");
		}
		

		
		
	}
	
	/**
	 * 장바구니 담기(책번호로 검색했을 때)
	 */
	public static void putCart(User user, Book book) {
		try {
			System.out.println("'" +book.getBookNo()+"'번 도서를 장바구니에 담으시겠습니까? (네 / 아니오)");
			String checkCart = sc.nextLine();
			if("네".equals(checkCart)) {
				CartController.putCart(user.getUserId(), book.getBookNo());
			}else if("아니오".equals(checkCart)) {
				System.out.println("*** 장바구니 담기를 취소했습니다. ***\n");
				CartMenuView.printCartMenu(user, book);
			}else {
				System.out.println("*** ( 네 / 아니오 ) 중 하나만 입력해주세요. ***\n");
				CartMenuView.printCartMenu(user, book);
			}
			
			
		}catch (Exception e) {
			//e.printStackTrace();
		}
		
	}
    
    

	
	/**
	 * 장바구니 메뉴(책번호로 검색했을 때)
	 */
	public static void printCartMenu(User user, Book book) {
		while(true) {
			try {
				System.out.println("\n*** 검색 목록 메뉴 ***");
				System.out.println("-----------------------------------------------------------------------------------------------------------------");
				System.out.println("       1.   대출하기      2.   예약하기      3.   장바구니 담기      4.   장바구니 보기      5.   돌아가기       ");
				System.out.println("-----------------------------------------------------------------------------------------------------------------");
				System.out.print("메뉴 입력 : ");

				switch(Integer.parseInt(sc.nextLine())) {
				case 1:
					System.out.println("*** 대출하기 메뉴를 선택했습니다. ***\n");
					System.out.println(book.getBookNo() + ". 『 " + book.getBookName() + " 』 도서를 대출하시겠습니까? ( 네 / 아니오 )");
					String checkRental = sc.nextLine();
					if("네".equals(checkRental)) {
						RentalController.insertRental(user, book);
					}else if("아니오".equals(checkRental)) {
						System.out.println("*** 대출을 취소했습니다. ***\n");
						CartMenuView.printCartMenu(user, book);
					}else {
						System.out.println("*** ( 네 / 아니오 ) 중 하나만 입력해주세요. ***\n");
						CartMenuView.printCartMenu(user, book);
					}
					
					return;
				case 2:
					System.out.println(book.getBookNo() + ". 『 " + book.getBookName() + " 』 도서를 예약하시겠습니까? ( 네 / 아니오 )");
					String checkReserve = sc.nextLine();
					if("네".equals(checkReserve)) {
						ReservationContoller.insertReservation(user, book);
					}else if("아니오".equals(checkReserve)) {
						System.out.println("*** 예약을 취소했습니다. ***\n");
						CartMenuView.printCartMenu(user, book);
					}else {
						System.out.println("*** ( 네 / 아니오 ) 중 하나만 입력해주세요. ***\n");
						CartMenuView.printCartMenu(user, book);
					}
					
					return;
					
				case 3:
					System.out.println("*** 장바구니 담기 메뉴를 선택했습니다. ***\n");
					CartMenuView.putCart(user, book);
					break;
				case 4:
					CartMenuView.viewCart(user);
					
					break;
				case 5:
					return;
				default:
					System.out.println("*** 메뉴에 있는 번호를 입력해주세요. ***");
				}
			}catch (NumberFormatException e) {
				System.out.println("*** 메뉴는 '숫자'만 입력해주세요. ***");
			}
			
		}
	}
	
	/**
	 * 장바구니 메뉴(책제목, 출판사.. 등으로 검색했을 때)
	 * @param user
	 */
	public static void printCartMenu(User user) {
		while(true) {
			try {
				System.out.println("\n*** 검색 목록 메뉴 ***");
				System.out.println("-----------------------------------------------------------------------------------------------------------------");
				System.out.println("       1.   대출하기      2.   예약하기      3.   장바구니 담기      4.   장바구니 보기      5.   돌아가기       ");
				System.out.println("-----------------------------------------------------------------------------------------------------------------");
				System.out.print("메뉴 입력 : ");
				switch(Integer.parseInt(sc.nextLine())) {
				case 1:
					System.out.println("*** 대출하기 메뉴를 선택했습니다. ***\n");
					System.out.print("도서번호 입력 : ");  
					int rentalBookNo = Integer.parseInt(sc.nextLine());
					RentalController.insertRental(user, rentalBookNo);
					break;
				case 2:
					System.out.println("*** 예약하기 메뉴를 선택했습니다. ***\n");
					System.out.print("도서번호 입력 : ");
					int reserveBookNo = Integer.parseInt(sc.nextLine());
					ReservationContoller.insertReservation(user, reserveBookNo);
					break;

				case 3:
					System.out.println("*** 장바구니 담기 메뉴를 선택했습니다. ***\n");
					CartMenuView.putCart(user.getUserId());
					break;
				case 4:
					System.out.println("*** 장바구니 목록 화면으로 이동합니다. ***\n");
					CartMenuView.viewCart(user);
					
					break;
				case 5:
					return;
				default :
					System.out.println("*** 메뉴에 있는 번호를 입력해주세요. ***");
				}
				
			}catch (NumberFormatException e) {
				System.out.println("*** 메뉴는 '숫자'만 입력해주세요. ***");
			}
			
		}
	}
	
	/**
     * 장바구니 보기
     * */
	public static void viewCart(User user) {
		Cart cart = CartController.viewCart(user);
		printRentalMenu(user, cart);
	}
	
	
	public static void printRentalMenu(User user, Cart cart) {
		while(true) {
			try {
				System.out.println("\n*** 장바구니 목록 메뉴 ***");
				System.out.println("-----------------------------------------------------------------------------------------------------------------");
				System.out.println("       1.   전체 목록 대출하기      2.   장바구니 목록 삭제      3.   장바구니 비우기      4.   돌아가기       ");
				System.out.println("-----------------------------------------------------------------------------------------------------------------");
				System.out.print("메뉴 입력 :  ");
				switch(Integer.parseInt(sc.nextLine())) {
				case 1:
					System.out.println("*** 장바구니의 도서목록을 대여합니다. ***\n");
					CartController.rentalCartBook(user.getUserId(), cart);
					CartMenuView.viewCart(user);
					return;
				case 2:
					System.out.println("*** 장바구니 목록을 삭제합니다. ***\n");
					System.out.print("도서번호 입력 : ");
					int bookNo = Integer.parseInt(sc.nextLine());
					CartController.removeCartDetail(user.getUserId(), bookNo);
					CartMenuView.viewCart(user);
					return;
				case 3:
					System.out.println("*** 장바구니 목록을 모두 삭제합니다. ***\n");
					UserMenuView.clearCart(user);
					CartMenuView.viewCart(user);
					return;
					
				case 4:
					return;
				default :
					System.out.println("*** 메뉴에 있는 번호를 입력해주세요. ***");
				}
			}catch (NumberFormatException e) {
				System.out.println("*** 메뉴는 '숫자'만 입력해주세요. ***");
			}
			
		}
	}
	
}
