package allthatbook.mvc.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.service.BookService;
import allthatbook.mvc.model.service.BookServiceImpl;
import allthatbook.mvc.view.BookMenuView;
import allthatbook.mvc.view.EndView;
import allthatbook.mvc.view.FailView;

public class BookController {
	static BookService bookService = new BookServiceImpl();
	/**
	 * 전체 책 조회
	 * */
	public static void bookSelect() {
		try {
			List<Book> list = bookService.bookSelect();
			EndView.printBookList(list);
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	

	/**
	 * 도서번호에 해당하는 책 조회
	 */
	public static Book bookSelectByBookNo(String userId, int bookNo) {
		Book book = null;
		try {
			book = bookService.bookSelectByBookNo(bookNo);
			EndView.printSelectByNo(userId, book);
		}catch (SQLException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		return book;
	}
	
	
	public static Book bookSelectByBookNo2(int bookNo) {
		Book book = null;
		try {
			book = bookService.bookSelectByBookNo(bookNo);
			return book;
		}catch (SQLException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		return book;
	}

	/**
	 * 도서명에 해당하는 책 조회
	 */
	public static void bookSelectByBookName(String userId, String keyword) {
		Scanner sc = new Scanner(System.in);
		try {
			List<Book> bookList = bookService.bookSelectByBookName(keyword);
			EndView.printBookList(userId, bookList);
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			System.out.println("다시 하시겠습니까? (yes or no)");
			String choice = sc.nextLine();
			if(choice.equals("yes")) {
				BookMenuView.selectBookByName(userId);
			}
		}
		
	}

	/**
	 * 저자명에 해당하는 책 조회
	 */
	public static void bookSelectByWriter(String userId, String writer) {
		Scanner sc = new Scanner(System.in);
		try {
			List<Book> bookList = bookService.bookSelectByWriter(writer);
			EndView.printBookList(userId, bookList);
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			System.out.println("다시 하시겠습니까? (yes or no)");
			String choice = sc.nextLine();
			if(choice.equals("yes")) {
				BookMenuView.selectBookByWriter(userId);
			}
		}
		
	}

	/**
	 * 출판사에 해당하는 책 조회
	 */
	public static void bookSelectByPublisher(String userId, String publisher) {
		Scanner sc = new Scanner(System.in);
		try {
			List<Book> bookList = bookService.bookSelectByPublisher(publisher);
			EndView.printBookList(userId, bookList);
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			System.out.println("다시 하시겠습니까? (yes or no)");
			String choice = sc.nextLine();
			if(choice.equals("yes")) {
				BookMenuView.selectBookByPublisher(userId);
			}
		}
		
	}

	/**
	 * 도서분야에 해당하는 책 조회
	 */
	public static void bookSelectByCategory(String userId, String category) {
		Scanner sc = new Scanner(System.in);
		try {
			List<Book> bookList = bookService.bookSelectByCategory(category);
			EndView.printBookList(userId, bookList);
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			System.out.println("다시 하시겠습니까? (yes or no)");
			String choice = sc.nextLine();
			if(choice.equals("yes")) {
				BookMenuView.selectBookByCategory(userId);
			}
		}
		
	}
	
	/**
	 * 관리자-도서관리-1.도서등록 
	 * @param book
	 * @return int result
	 */
	public static int bookInsert(Book book) {
		int result=0;
		try {
			result = bookService.bookInsert(book);
		} catch (SQLException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		return result;
	}

	/**
	 * 관리자-도서관리-도서삭제
	 * @param bookNo
	 * @return int result
	 */
	public static int bookDelete(int bookNo) {
		int result=0;
		try {
			result = bookService.bookDelete(bookNo);
		} catch (SQLException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 관리자-도서관리-대출중도서조회
	 * */
	public static void bookRentalSelect() {
		try {
			List<Book> list = bookService.bookRentalSelect();
			EndView.printBookList(list);
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}


	/**
	 * 관리자-도서관리-예약된 도서 조회
	 */
	public static void bookReserveSelect() {
		try {
			List<Book> list = bookService.bookReserveSelect();
			EndView.printBookList(list);
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}

}





