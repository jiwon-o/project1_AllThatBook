package allthatbook.mvc.view;

import java.util.List;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.User;

public class EndView {
	
	public static void printBookList(List<Book> list) {
		
	}
	
	public static void printMessage(String message) {
		System.out.println(message);
	}
	
	/**
	 * User 전체 출력
	 */
	public static void printUserList(List<User> userList){
		System.out.println("-----전체 User "+userList.size()+"명------");
		for(User user : userList) {
			System.out.println(user);
		}
		System.out.println();
	}
	/**
	 * UserId로 조회해서 출력
	 */
	public static void printSelectByUserId(User user) {
		System.out.println(user);
		System.out.println();
	}
}












