package allthatbook.mvc.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.service.BookService;
import allthatbook.mvc.model.service.BookServiceImpl;
import allthatbook.mvc.view.EndView;
import allthatbook.mvc.view.FailView;
import allthatbook.mvc.view.MenuView;

public class BookController {
	static BookService bookService = new BookServiceImpl();
	/**
	 * 전체 책 조회
	 * */
	public static void bookSelect() {
		try {
			List<Book> list = bookService.bookSelect();
			//EndView.printBookList(list);
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	

	/**
	 * 도서번호에 해당하는 책 조회
	 */
	public static void bookSelectByBookNo(String userId, int bookNo) {
		try {
			Book book = bookService.bookSelectByBookNo(bookNo);
			EndView.printSelectByNo(userId, book);
		}catch (SQLException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
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
				MenuView.selectBookByName(userId);
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
				MenuView.selectBookByWriter(userId);
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
				MenuView.selectBookByPublisher(userId);
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
				MenuView.selectBookByCategory(userId);
			}
		}
		
	}
}





