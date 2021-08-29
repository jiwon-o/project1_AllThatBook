package allthatbook.mvc.controller;

import java.sql.SQLException;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.model.service.BookService;
import allthatbook.mvc.model.service.BookServiceImpl;
import allthatbook.mvc.model.service.UserService;
import allthatbook.mvc.model.service.UserServiceImpl;
import allthatbook.mvc.view.FailView;

public class UpdateAdminController {
	static UserService userService = new UserServiceImpl();
	static BookService bookService = new BookServiceImpl();
	
	public static int userUpdate(int userNo, User updateUser) {
		int result=0;
//		try { 여기하는중
//			User user = userSelect(userNo);
//			//updateUser.set
//		} catch (SQLException e) {
//			e.printStackTrace();
//			FailView.errorMessage(e.getMessage());
//		}
		return result;
	}
	
	private static User userSelect(int userNo) {
		User user = null;
		try {
			user = userService.selectByUserNo(userNo);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public static int bookUpdate(int bookNo, Book updatebook) {
		int result=0;
		try {
			Book book = bookSelect(bookNo);
			updatebook.setBookNo(bookNo);
			updatebook.setPubDate(book.getPubDate());
			updatebook.setBookState(book.getBookState());
			if(updatebook.getBookName().isEmpty())updatebook.setBookName(book.getBookName()); 
			if(updatebook.getBookWriter().isEmpty())updatebook.setBookWriter(book.getBookWriter()); 
			if(updatebook.getBookPublisher().isEmpty())updatebook.setBookPublisher(book.getBookPublisher()); 
			if(updatebook.getBookField().isEmpty())updatebook.setBookField(book.getBookField()); 
			result = bookService.bookUpdate(updatebook);
		} catch (SQLException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		return result;			
	}
	
	/**
	 * 도서수정시 바꾸고 싶지 않은 부분을 위해 기존 책 정보 가져오기 
	 * @param bookNo
	 * @return book
	 */
	public static Book bookSelect(int bookNo) {
		Book book = null;
		try {
			book = bookService.bookSelectByBookNo(bookNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}



}
