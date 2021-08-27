package allthatbook.mvc.view;

import java.util.List;

import allthatbook.mvc.model.dto.Book;

public class EndView {
	
	public static void printBookList(List<Book> list) {
		
	}
	
	public static void printSelectByNo(Book book) {
		System.out.println(book + "\n");
	}
	
	public static void printMessage(String message) {
		System.out.println(message);
	}
	
}












