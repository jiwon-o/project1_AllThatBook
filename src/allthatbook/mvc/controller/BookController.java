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
	static Scanner sc = new Scanner(System.in);
	
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
			if(book != null) {
				System.out.println("*** '" + bookNo + "'번 책을 검색합니다. ***");
			}
			EndView.printSelectByNo(user, book);
		}catch (SQLException e) {

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
	public static List<Book> bookSelectByBookName(User user, String keyword) {
		List<Book> bookList = null;
		try {
			bookList = bookService.bookSelectByBookName(keyword);
			EndView.printBookList(user, bookList);
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());

		}
		return bookList;
	}

	/**
	 * 저자명에 해당하는 책 조회
	 */
	public static List<Book> bookSelectByWriter(User user, String writer) {
		List<Book> bookList = null;
		try {
			bookList = bookService.bookSelectByWriter(writer);
			EndView.printBookList(user, bookList);
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());

		}
		return bookList;
		
	}

	/**
	 * 출판사에 해당하는 책 조회
	 */
	public static List<Book> bookSelectByPublisher(User user, String publisher) {
		List<Book> bookList = null;
		try {
			bookList = bookService.bookSelectByPublisher(publisher);
			EndView.printBookList(user, bookList);
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());

		}
		return bookList;
		
	}

	/**
	 * 도서분야에 해당하는 책 조회
	 */
	public static List<Book> bookSelectByCategory(User user, String category) {
		List<Book> bookList = null;
		try {
			bookList = bookService.bookSelectByCategory(category);
			EndView.printBookList(user, bookList);
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			
		}
		return bookList;
		
	}
	
	/**
	 * 대여 여부에 따른 도서 검색
	 */
	public static List<Book> bookSelectByState(User user, int state) {
		List<Book> bookList = null;
		try {
			bookList = bookService.bookSelectByState(state);
			EndView.printBookList(user, bookList);
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			
		}
		return bookList;
	}
	
	/**
	 * 관리자-도서관리-1.도서등록 
	 * @param book
	 * @return int result
	 */
	public static void bookInsert(Book book) {
		try {
			bookService.bookInsert(book);
			EndView.printMessage("*** 『 " + book.getBookName() + " 』 도서를 등록했습니다. ***");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 관리자-도서관리-도서삭제
	 * @param bookNo
	 */
	public static void bookDelete(int bookNo) {
		try {
			bookService.bookDelete(bookNo);
			EndView.printMessage("***'" + bookNo + "'번 도서가 삭제되었습니다. ***");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}	

}





