package allthatbook.mvc.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.User;
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
	public static Book bookSelectByBookNo(User user, int bookNo) {
		Book book = null;
		try {
			book = bookService.bookSelectByBookNo(bookNo);
			EndView.printSelectByNo(user, book);
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
	public static void bookSelectByBookName(User user, String keyword) {
		Scanner sc = new Scanner(System.in);
		try {
			List<Book> bookList = bookService.bookSelectByBookName(keyword);
			EndView.printBookList(user, bookList);
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			System.out.println("다시 하시겠습니까? (yes or no)");
			String choice = sc.nextLine();
			if(choice.equals("yes")) {
				BookMenuView.selectBookByName(user);
			}
		}
		
	}

	/**
	 * 저자명에 해당하는 책 조회
	 */
	public static void bookSelectByWriter(User user, String writer) {
		Scanner sc = new Scanner(System.in);
		try {
			List<Book> bookList = bookService.bookSelectByWriter(writer);
			EndView.printBookList(user, bookList);
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			System.out.println("다시 하시겠습니까? (yes or no)");
			String choice = sc.nextLine();
			if(choice.equals("yes")) {
				BookMenuView.selectBookByWriter(user);
			}
		}
		
	}

	/**
	 * 출판사에 해당하는 책 조회
	 */
	public static void bookSelectByPublisher(User user, String publisher) {
		Scanner sc = new Scanner(System.in);
		try {
			List<Book> bookList = bookService.bookSelectByPublisher(publisher);
			EndView.printBookList(user, bookList);
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			System.out.println("다시 하시겠습니까? (yes or no)");
			String choice = sc.nextLine();
			if(choice.equals("yes")) {
				BookMenuView.selectBookByPublisher(user);
			}
		}
		
	}

	/**
	 * 도서분야에 해당하는 책 조회
	 */
	public static void bookSelectByCategory(User user, String category) {
		Scanner sc = new Scanner(System.in);
		try {
			List<Book> bookList = bookService.bookSelectByCategory(category);
			EndView.printBookList(user, bookList);
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			System.out.println("다시 하시겠습니까? (yes or no)");
			String choice = sc.nextLine();
			if(choice.equals("yes")) {
				BookMenuView.selectBookByCategory(user);
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





