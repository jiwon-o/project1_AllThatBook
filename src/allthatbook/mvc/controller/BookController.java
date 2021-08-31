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
	public static Book bookSelectByBookNo(User user, int bookNo) {
		Book book = null;
		try {
			book = bookService.bookSelectByBookNo(bookNo);
			EndView.printSelectByNo(user, book);
		}catch (SQLException e) {

			FailView.errorMessage(e.getMessage());
	
			

			boolean flag = true;
			while(flag) {
				System.out.println("*** �ٽ� �Ͻðڽ��ϱ�? ( yes or no )*** ");
				String choice = sc.nextLine();
				if(choice.toUpperCase().equals("yes".toUpperCase())) {
					BookMenuView.selectBookByNo(user);
					break;
				}else if(choice.toUpperCase().equals("no".toUpperCase())) {
					break;
				}else {
					System.out.println("*** ( yes or no )�� �Է����ּ���. ***");
				}
			}

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
	 * ������ �ش��ϴ� å ��ȸ
	 */
	public static List<Book> bookSelectByBookName(User user, String keyword) {
		List<Book> bookList = null;
		try {
			bookList = bookService.bookSelectByBookName(keyword);
			EndView.printBookList(user, bookList);
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());

			while(true) {
				System.out.println("*** �ٽ� �Ͻðڽ��ϱ�? ( yes or no ) ***");
				String choice = sc.nextLine();
				if(choice.toUpperCase().equals("yes".toUpperCase())) {
					BookMenuView.selectBookByName(user);
					break;
				}else if(choice.toUpperCase().equals("no".toUpperCase())) {
					break;
				}else {
					System.out.println("*** ( yes or no )�� �Է����ּ���. ***");
				}
			}

		}
		return bookList;
	}

	/**
	 * ���ڸ� �ش��ϴ� å ��ȸ
	 */
	public static List<Book> bookSelectByWriter(User user, String writer) {
		List<Book> bookList = null;
		try {
			bookList = bookService.bookSelectByWriter(writer);
			EndView.printBookList(user, bookList);
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());

			while(true) {
				System.out.println("*** �ٽ� �Ͻðڽ��ϱ�? ( yes or no ) ***");
				String choice = sc.nextLine();
				if(choice.toUpperCase().equals("yes".toUpperCase())) {
					BookMenuView.selectBookByWriter(user);
					break;
				}else if(choice.toUpperCase().equals("no".toUpperCase())) {
					break;
				}else {
					System.out.println("*** ( yes or no )�� �Է����ּ���. ***");
				}
			}
		}
		return bookList;
		
	}

	/**
	 * ���ǻ翡 �ش��ϴ� å ��ȸ
	 */
	public static List<Book> bookSelectByPublisher(User user, String publisher) {
		List<Book> bookList = null;
		try {
			bookList = bookService.bookSelectByPublisher(publisher);
			EndView.printBookList(user, bookList);
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			while(true) {
				System.out.println("*** �ٽ� �Ͻðڽ��ϱ�? ( yes or no ) ***");
				String choice = sc.nextLine();
				if(choice.toUpperCase().equals("yes".toUpperCase())) {
					BookMenuView.selectBookByPublisher(user);
					break;
				}else if(choice.toUpperCase().equals("no".toUpperCase())) {
					break;
				}else {
					System.out.println("*** ( yes or no )�� �Է����ּ���. ***");
				}
			}
		}
		return bookList;
		
	}

	/**
	 * �����о߿� �ش��ϴ� å ��ȸ
	 */
	public static List<Book> bookSelectByCategory(User user, String category) {
		List<Book> bookList = null;
		try {
			bookList = bookService.bookSelectByCategory(category);
			EndView.printBookList(user, bookList);
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			while(true) {
				System.out.println("*** �ٽ� �Ͻðڽ��ϱ�? ( yes or no ) ***");
				String choice = sc.nextLine();
				if(choice.toUpperCase().equals("yes".toUpperCase())) {
					BookMenuView.selectBookByCategory(user);
					break;
				}else if(choice.toUpperCase().equals("no".toUpperCase())) {
					break;
				}else {
					System.out.println("*** ( yes or no )�� �Է����ּ���. ***");
				}
			}
			
		}
		return bookList;
		
	}
	
	/**
	 * �뿩 ���ο� ���� ���� �˻�
	 */
	public static List<Book> bookSelectByState(User user, int state) {
		List<Book> bookList = null;
		try {
			bookList = bookService.bookSelectByState(state);
			EndView.printBookList(user, bookList);
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			while(true) {
				System.out.println("*** �ٽ� �Ͻðڽ��ϱ�? ( yes or no ) ***");
				String choice = sc.nextLine();
				if(choice.toUpperCase().equals("yes".toUpperCase())) {
					BookMenuView.selectBookByState(user);
					break;
				}else if(choice.toUpperCase().equals("no".toUpperCase())) {
					break;
				}else {
					System.out.println("*** ( yes or no )�� �Է����ּ���. ***");
				}
			}
			
		}
		return bookList;
	}
	
	/**
	 * ������-��������-1.������� 
	 * @param book
	 * @return int result
	 */
	public static void bookInsert(Book book) {
		try {
			bookService.bookInsert(book);
			EndView.printMessage("*** ��� �����߽��ϴ�. ***");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * ������-��������-��������
	 * @param bookNo
	 */
	public static void bookDelete(int bookNo) {
		try {
			bookService.bookDelete(bookNo);
			EndView.printMessage("*** ���������� �Ǿ����ϴ�. ***");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}	

}





