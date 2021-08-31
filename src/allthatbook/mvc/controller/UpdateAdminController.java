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
		try { 
			User user = userSelect(userNo);
			updateUser.setUserNo(userNo);
			updateUser.setRegDate(user.getRegDate());
			if(updateUser.getUserId().isEmpty())updateUser.setUserId(user.getUserId());
			if(updateUser.getUserPwd().isEmpty())updateUser.setUserPwd(user.getUserPwd());
			if(updateUser.getUserName().isEmpty())updateUser.setUserName(user.getUserName());
			if(updateUser.getUserPhone().isEmpty())updateUser.setUserPhone(user.getUserPhone());
			result=userService.userUpdate(updateUser);
		} catch (SQLException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
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

	public static int bookUpdate(int bookNo, Book updateBook) {
		int result=0;
		try {
			Book book = bookSelect(bookNo);
			updateBook.setBookNo(bookNo);
			updateBook.setBookState(book.getBookState());
			if(updateBook.getBookName().isEmpty())updateBook.setBookName(book.getBookName()); 
			if(updateBook.getBookWriter().isEmpty())updateBook.setBookWriter(book.getBookWriter()); 
			if(updateBook.getBookPublisher().isEmpty())updateBook.setBookPublisher(book.getBookPublisher()); 
			if(updateBook.getBookField().isEmpty())updateBook.setBookField(book.getBookField()); 
			result = bookService.bookUpdate(updateBook);
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
