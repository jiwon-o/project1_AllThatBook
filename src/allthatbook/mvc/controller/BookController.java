package allthatbook.mvc.controller;

import java.sql.SQLException;
import java.util.List;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.service.BookService;
import allthatbook.mvc.model.service.BookServiceImpl;
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
	public static void bookSelectByBookNo(int bookNo) {
		try {
			Book book = bookService.bookSelectByBookNo(bookNo);
			EndView.printSelectByNo(book);
		}catch (SQLException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
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





