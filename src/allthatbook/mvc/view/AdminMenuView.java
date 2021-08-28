package allthatbook.mvc.view;

import java.util.Scanner;

import allthatbook.mvc.controller.BookController;
import allthatbook.mvc.controller.UserController;
import allthatbook.mvc.model.dto.Book;

public class AdminMenuView {
	private static Scanner sc = new Scanner(System.in);
	/**
	 * 회원관리 메뉴
	 */
	public static void userAdminMenu() {
		System.out.println("---관리자 회원 관리---");
		System.out.println("1. 전체회원 조회 | 2. 회원번호로 조회 | 3. 회원ID로 조회 | 4.회원정보수정 | 5.회원정보삭제| 9. 나가기");
		int menu=Integer.parseInt(sc.nextLine());
		switch(menu) {
		case 1 :
			UserController.userSelect();
			break;
		case 2 : 
			int userNo=InputUserNo();
			UserController.selectByUserNo(userNo);
			break;
		case 3 :
			String userId=InputUserId();
			UserController.selectByUserId(userId);
			break;
		case 4 : //회원번호를 받아 해당 회원번호 정보 수정
			//userNo=InputUserNo();
			//UserController.updateAdminUserInfo(userNo);
			//사용자메소드랑 연결 
			break;
		case 5 : 
			userNo=InputUserNo();
			UserController.updateAdminUserInfo(userNo);
			//사용자메소드랑 연결...?
			break;
		case 9 :  			
			return;
		}
	}
	/**
	 * 도서관리 메뉴
	 */
	public static void bookAdminMenu() {
		System.out.println("---관리자 도서 관리---");
		System.out.println("1. 새도서등록 | 2. 도서정보수정 | 3. 도서삭제 | 4. 도서조회 | 5. 대출한도서 조회 | 6. 예약한도서 조회 | 9. 나가기");
		int menu=Integer.parseInt(sc.nextLine());
		//while문 사용할지...?
		int bookNo=0;
		int result=0;
		switch(menu) {
			case 1 :
				Book book=InputBook();
				result = BookController.bookInsert(book);
				if(result==1) System.out.println(book.getBookNo()+"등록되었습니다.");
				break;
			case 2 :
				bookNo = InputBookNo();
				
				break;
			case 3 : 
				bookNo = InputBookNo();
				result = BookController.bookDelete(bookNo);	
				if(result==1)System.out.println(bookNo+"번호가 삭제되었습니다.");
				break;
			case 4 : 
				//사용자 메소드 사용...?
				//
				break;
			case 5 : //대출한도서 : 상태가 1인도서 조회 
				BookController.bookRentalSelect();
				break;
			case 6 : //예약도서 조회 : 상태가 2인도서 조회
				BookController.bookReserveSelect();
				break;
			case 9 :
				return;
		}
	}
	
	public static void bookUpdateAdminMenu() {
		System.out.println("무엇을 수정하시겠습니까? 1.도서명 2.저자명 3.출판사명 4.분야");
	}
	
	/**
	 * 계속하시겠습니까?
	 */
	
	/**
	 * UserNo 입력받기 
	 */
	public static int InputUserNo() {
		System.out.println("userNo 입력 > ");
		int userNo = Integer.parseInt(sc.nextLine());
		return userNo;
	}
	
	
	/**
	 * UserId 입력받기 
	 */
	public static String InputUserId() {
		System.out.print("userId 입력 > ");
		String userId = sc.nextLine();
		return userId;
	}
	
	/**
	 *	bookInsert에 필요한 book정보 넣기 
	 */
	public static Book InputBook() {
		Book book = new Book();
		System.out.println("bookNo은 자동배정됩니다.");
		
		System.out.print("bookName 입력 > ");
		book.setBookName(sc.nextLine());
		System.out.print("bookWriter 입력 > ");
		book.setBookWriter(sc.nextLine());
		System.out.print("bookPublisher 입력 > ");
		book.setBookPublisher(sc.nextLine());
		System.out.println("출간일은 현재 날짜가 들어갑니다.");

		System.out.print("bookField 입력 > ");
		book.setBookField(sc.nextLine());
		System.out.println("bookState 기본 대출가능0으로 들어갑니다.");

		return book;
	}
	
	/**
	 * bookNo입력받기 
	 */
	public static int InputBookNo() {
		System.out.print("bookNo 입력 > ");
		int bookNo = Integer.parseInt(sc.nextLine());
		return bookNo;
	}
	
}
