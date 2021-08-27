package allthatbook.mvc.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import allthatbook.mvc.exception.NotFoundException;
import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.service.BookService;
import allthatbook.mvc.model.service.BookServiceImpl;
import allthatbook.mvc.view.FailView;

public class CartController {
	private static BookService bookService = new BookServiceImpl();
		
	public static void putCart(String id, int bookNo) {
		try {
			//책번호에 해당하는 책 검색
			Book book = bookService.bookSelectByBookNo(bookNo);
			
		}catch(Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
}
