package allthatbook.mvc.view;


import java.util.Scanner;

import allthatbook.mvc.controller.BookController;
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
				//MenuView.register(); // 가입
				break;
			case 2 :
				MenuView.login();// 로그인
				break;

			case 9 : 
				System.exit(0);
			}
		}

	}
	
	
	public static void printMenu() {
		System.out.println("=== Heejung Shopping Mall ===");
		System.out.println("1. 회원가입   |   2. 로그인   |  9. 종료");
	}
	
	
	public static void printUserMenu(String userId) {
		while(true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet()); //Set객체
			System.out.println("-----" +userId+ " 로그인 중 -----");
			System.out.println(" 1.로그아웃 |  2.상품보기  |  3.주문하기  | 4. 주문내역보기  |  5.장바구니담기  |  6.장바구니보기 ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 :
					logout(userId);// 
					return;
					//break;
				case 2 :
					BookController.bookSelect();//전체 상품조회
					break;
				case 3 :
					
					break;
				case 4 :
					break;
				case 5 :
					
					break;
				case 6 : 
					
					break;
				}
		}
		
	}
	
	public static void printSubMenu() {
		System.out.println("1. 수정   |  2.탈퇴   | 9. 나가기");
	}
	
	public static void printAdminMenu() {
		System.out.println("-- 관리자 메뉴 --");
		System.out.println("1. ID로 검색   |  2.이름으로 검색  | 3.전체 검색  |  9. 나가기");
		
	}
	
	/**
	 * 로그인 메뉴
	 * */
	public static void login() {
		 
	}
	
	/**
	 * 로그아웃
	 * */
	public static void logout(String userId) {
		
	}
}



