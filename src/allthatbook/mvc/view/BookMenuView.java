package allthatbook.mvc.view;

import java.util.Scanner;

import allthatbook.mvc.controller.BookController;
import allthatbook.mvc.controller.UpdateAdminController;
import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.User;

public class BookMenuView {
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * 검색 메뉴
	 */
	public static void printSelectMenu(User user) {
		while(true) {
			System.out.println("1.도서번호로 검색  |  2.도서명으로 검색  |  3.저자명으로 검색  |  4.출판사로 검색  |  5.도서분야로 검색  |  6.대여여부로 검색  |  9.돌아가기");
			System.out.print("번호 입력 > ");
			int menu =Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				Book book =  BookMenuView.selectBookByNo(user.getUserId()); //도서번호로 검색
				break;
			case 2 :
				BookMenuView.selectBookByName(user.getUserId()); //도서명으로 검색
				break;
			case 3 :
				BookMenuView.selectBookByWriter(user.getUserId()); //저자명으로 검색
				break;
			case 4 :
				BookMenuView.selectBookByPublisher(user.getUserId()); //출판사로 검색
				break;
			case 5 :
				BookMenuView.selectBookByCategory(user.getUserId()); //분야로 검색
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
	
	//////////////////////////////관리자////////////////////////
	/**
	 * 관리자 도서조회(도서번호) -> 삭제 or 수정 
	 */
	public static void bookDeleteOrUpdateMenu(String userId, Book book) {
		Scanner sc = new Scanner(System.in);

		while(true) {
			int result=0;
			System.out.println("1. 선택도서 수정 | 2. 선택도서 삭제 | 3. 돌아가기");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
				case 1 :
					Book updatebook = AdminMenuView.updateBook();
					result = UpdateAdminController.bookUpdate(book.getBookNo(), updatebook);
					if(result==1)System.out.println(book.getBookNo()+"번 해당 책이 수정되었습니다.");
					break;
				case 2 :
					BookController.bookDelete(book.getBookNo());	
					break;
				case 3:
					return;
			}
		}
	}
	/**
	 * 관리자 도서조회(도서명 저자명 출판사 분야) -> 삭제 or 수정
	 */
	public static void bookDeleteOrUpdateListMenu(String userId) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1. 선택도서 수정 | 2. 선택도서 삭제 | 3. 돌아가기");
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
					BookController.bookDelete(bookNo);	
					break;
				case 3:
					return;
			}
			
		}
	}
}
