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

}





