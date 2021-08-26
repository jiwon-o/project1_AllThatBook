package allthatbook.mvc.controller;

import java.util.List;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.service.BookService;
import allthatbook.mvc.view.EndView;
import allthatbook.mvc.view.FailView;

public class BookController {
	static BookService bookService = new BookService();
	/**
	 * 전체 책 조회
	 * */
	public static void bookSelect() {
		System.out.println("김성은이 추가");
		System.out.println("오지원이 추가");
		try {
			List<Book> list = bookService.bookSelect();
			EndView.printBookList(list);
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
}
