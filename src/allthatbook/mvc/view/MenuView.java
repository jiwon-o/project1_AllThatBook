package allthatbook.mvc.view;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import allthatbook.mvc.controller.BookController;
import allthatbook.mvc.controller.UserController;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.session.Session;
import allthatbook.mvc.session.SessionSet;

public class MenuView {
	private static Scanner sc = new Scanner(System.in);
	
	public static void menu() {
		while(true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet());
			
			MenuView.printMenu();
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				MenuView.login(); //로그인
				
				break;
			case 2 :
				MenuView.register(); //회원가입
				break;

			case 9 : 
				System.exit(0);
			}
		}

	}
	
	
	

	public static void printMenu() {
		System.out.println("=== AllThatBook Library ===");
		System.out.println("1. 로그인   |   2. 회원가입   |  9. 종료");
	}
	
	
	public static void printUserMenu(String userId) {
		while(true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet()); //Set객체
			System.out.println("-----" +userId+ " 로그인 중 -----");
			System.out.println(" 1.전체목록  |  2.도서검색  | 3.도서대여  |  4.도서반납  |  5.책신청  |  6.장바구니담기  |  7.회원정보  |  9.로그아웃 ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
			case 1 :
				BookController.bookSelect();//전체 상품조회
				break;
			case 2 :
				selectBookByNo();
				break;
			case 3 :
				
				break;
			case 4 :
				break;
			case 5 :
				
				break;
			case 6 : 
				MenuView.putCart(userId);
				break;
			case 9 :
				logout(userId);
				return;
			}
		}
		
	}
	
	public static void printSubMenu() {
		System.out.println("1. 수정   |  2.탈퇴   | 9. 나가기");
	}
	
	public static void printAdminMenu(String userId) {
		System.out.println("-- 관리자 메뉴 --");
		System.out.println("1. 회원관리   |  2. 도서관리  | 3. 대출관리 |  9. 나가기");
		int menu=Integer.parseInt(sc.nextLine());
		switch(menu) {
		case 1 :
			AdminMenuView.userAdminMenu();
			break;
		case 2 :
			AdminMenuView.bookAdminMenu();
			break;
		case 3 :
			break;
		case 9 :
			logout(userId);
			return;
		}
	}
	
	/**
	 * 책번호로 검색하기
	 */
	public static void selectBookByNo() {
		try {
			System.out.println("책번호 입력 > ");
			int no = Integer.parseInt(sc.nextLine());
			
			BookController.bookSelectByBookNo(no);
		}catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("숫자만 입력해주세요.");
			System.out.println("다시 하시겠습니까? (yes or no)");
			String choice = sc.nextLine();
			if(choice.equals("yes")) {
				selectBookByNo();
			}
		}
	}
	
	/**
	 * 책번호로 검색하기
	 */
	public static void selectBookByNo() {
		try {
			System.out.println("책번호 입력 > ");
			int no = Integer.parseInt(sc.nextLine());
			
			BookController.bookSelectByBookNo(no);
		}catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("숫자만 입력해주세요.");
			System.out.println("다시 하시겠습니까? (yes or no)");
			String choice = sc.nextLine();
			if(choice.equals("yes")) {
				selectBookByNo();
			}
		}
	}
	
	/**
     * 장바구니 담기
     * */
    public static void putCart(String userId) {
		System.out.println("----장바구니 담기----");
		System.out.print("책번호 : ");
		int bookNo = Integer.parseInt(sc.nextLine());
		
		CartController.putCart(userId, bookNo);
	
		
	}
    
	/**
	 * 로그인 메뉴
	 * */
	public static void login() {
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		 
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();
		 
		UserController.login(userId, userPwd);
	}
	
	/**
	 * 회원가입 메뉴
	 */
	private static void register() {
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		 
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();
		
		System.out.print("비밀번호 확인 : ");
		String pwdCheck = sc.nextLine();
		
		System.out.print("이름 : ");
		String userName = sc.nextLine();
		
		System.out.print("전화번호 : ");
		String userPhone = sc.nextLine();
		
		User user = new User(0, userId, userPwd, userName, userPhone, null);
		UserController.register(user, pwdCheck);
		
	}

	/**
	 * 로그아웃
	 * */
	public static void logout(String userId) {
		Session session = new Session(userId);
		
		SessionSet ss = SessionSet.getInstance();
		ss.remove(session);	
	}
	
}



