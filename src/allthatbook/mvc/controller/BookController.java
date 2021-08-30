package allthatbook.mvc.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.service.BookService;
import allthatbook.mvc.model.service.BookServiceImpl;
import allthatbook.mvc.view.BookMenuView;
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
	public static Book bookSelectByBookNo(String userId, int bookNo) {
		Book book = null;
		try {
			book = bookService.bookSelectByBookNo(bookNo);
			EndView.printSelectByNo(userId, book);
		}catch (SQLException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
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
	public static void bookSelectByBookName(String userId, String keyword) {
		Scanner sc = new Scanner(System.in);
		try {
			List<Book> bookList = bookService.bookSelectByBookName(keyword);
			EndView.printBookList(userId, bookList);
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			System.out.println("�ٽ� �Ͻðڽ��ϱ�? (yes or no)");
			String choice = sc.nextLine();
			if(choice.equals("yes")) {
				BookMenuView.selectBookByName(userId);
			}
		}
		
	}

	/**
	 * ���ڸ� �ش��ϴ� å ��ȸ
	 */
	public static void bookSelectByWriter(String userId, String writer) {
		Scanner sc = new Scanner(System.in);
		try {
			List<Book> bookList = bookService.bookSelectByWriter(writer);
			EndView.printBookList(userId, bookList);
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			System.out.println("�ٽ� �Ͻðڽ��ϱ�? (yes or no)");
			String choice = sc.nextLine();
			if(choice.equals("yes")) {
				BookMenuView.selectBookByWriter(userId);
			}
		}
		
	}

	/**
	 * ���ǻ翡 �ش��ϴ� å ��ȸ
	 */
	public static void bookSelectByPublisher(String userId, String publisher) {
		Scanner sc = new Scanner(System.in);
		try {
			List<Book> bookList = bookService.bookSelectByPublisher(publisher);
			EndView.printBookList(userId, bookList);
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			System.out.println("�ٽ� �Ͻðڽ��ϱ�? (yes or no)");
			String choice = sc.nextLine();
			if(choice.equals("yes")) {
				BookMenuView.selectBookByPublisher(userId);
			}
		}
		
	}

	/**
	 * �����о߿� �ش��ϴ� å ��ȸ
	 */
	public static void bookSelectByCategory(String userId, String category) {
		Scanner sc = new Scanner(System.in);
		try {
			List<Book> bookList = bookService.bookSelectByCategory(category);
			EndView.printBookList(userId, bookList);
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			System.out.println("�ٽ� �Ͻðڽ��ϱ�? (yes or no)");
			String choice = sc.nextLine();
			if(choice.equals("yes")) {
				BookMenuView.selectBookByCategory(userId);
			}
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





