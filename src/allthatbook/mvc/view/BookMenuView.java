package allthatbook.mvc.view;

import java.util.Scanner;

import allthatbook.mvc.controller.BookController;
import allthatbook.mvc.model.dto.Book;

public class BookMenuView {
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * 검색 메뉴
	 */
	public static void printSelectMenu(String userId) {
		while(true) {
			System.out.println("1.도서번호로 검색  |  2.도서명으로 검색  |  3.저자명으로 검색  |  4.출판사로 검색  |  5.도서분야로 검색  |  6.대여여부로 검색  |  9.돌아가기");
			System.out.print("번호 입력 > ");
			int menu =Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				Book book =  BookMenuView.selectBookByNo(userId); //도서번호로 검색
				//EndView.printCartMenu(userId, book);
				
				break;
			case 2 :
				BookMenuView.selectBookByName(userId); //도서명으로 검색
				break;
			case 3 :
				BookMenuView.selectBookByWriter(userId); //저자명으로 검색
				break;
			case 4 :
				BookMenuView.selectBookByPublisher(userId); //출판사로 검색
				break;
			case 5 :
				BookMenuView.selectBookByCategory(userId); //분야로 검색
				break;
			case 6 : 
				//대여여부로 검색
				break;
			case 9 :
				//printUserMenu(user);
				return;
			}
		}
	}
	
	/**
	 * 도서번호로 검색하기
	 */
	public static Book selectBookByNo(String userId) {
		Book book = null;
		while(true) {
			try {
				System.out.print("책번호 입력 > ");
				int no = Integer.parseInt(sc.nextLine());
				
				book = BookController.bookSelectByBookNo(userId, no);
			}catch (NumberFormatException e) {
				//e.printStackTrace();
				System.out.println("숫자만 입력해주세요.");
			}
			
		return book;
		}
	}
	
	/**
	 * 도서명으로 검색하기
	 */
	public static void selectBookByName(String userId) {
		try {
			System.out.print("단어 검색 > ");
			String keyword = sc.nextLine();
			BookController.bookSelectByBookName(userId, keyword);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 저자명으로 검색하기
	 */
	public static void selectBookByWriter(String userId) {
		try {
			System.out.print("저자 검색 > ");
			String writer = sc.nextLine();
			BookController.bookSelectByWriter(userId, writer);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 출판사로 검색하기
	 */
	public static void selectBookByPublisher(String userId) {
		try {
			System.out.print("출판사 검색 > ");
			String publisher = sc.nextLine();
			BookController.bookSelectByPublisher(userId, publisher);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * 도서분야로 검색하기
	 */
	public static void selectBookByCategory(String userId) {
		try {
			System.out.print("도서분야 검색 > ");
			String category = sc.nextLine();
			BookController.bookSelectByCategory(userId, category);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
