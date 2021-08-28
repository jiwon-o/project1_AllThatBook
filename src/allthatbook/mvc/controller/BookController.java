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
	 * ��ü å ��ȸ
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
	 * ������ȣ�� �ش��ϴ� å ��ȸ
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
	 * ������-��������-1.������� 
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
	 * ������-��������-��������
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
	 * ������-��������-�����ߵ�����ȸ
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
	 * ������-��������-����� ���� ��ȸ
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





