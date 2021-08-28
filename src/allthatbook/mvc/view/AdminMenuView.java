package allthatbook.mvc.view;

import java.util.Scanner;

import allthatbook.mvc.controller.UserController;

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
		case 4 : 
			userNo=InputUserNo();
			UserController.updateAdminUserInfo(userNo);
			//회원번호를 받아 해당 회원번호 정보 수정
			
			break;
		case 5 : 
			userNo=InputUserNo();
			//회원번호를 받아 해당회원정보 수정 
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
		System.out.println("1. 도서등록 | 2. 도서정보수정 | 3. 도서삭제 | 4. 도서조회 | 5. 대출한도서 조회 | 6. 예약한도서 조회 | 9. 나가기");
		int menu=Integer.parseInt(sc.nextLine());
		switch(menu) {
		case 1 :
				
			break;
		case 2 :

			break;
		case 3 : 
			break;
		case 4 : 
			//BookController
			break;
		case 5 : 
			break;
		case 6 : 
			break;
		case 9 :
			return;
		}
	}
	
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
		System.out.println("userId 입력 > ");
		String userId = sc.nextLine();
		return userId;
	}
	
	
}
