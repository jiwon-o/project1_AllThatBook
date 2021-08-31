package allthatbook.mvc.view;

import java.util.Scanner;

import allthatbook.mvc.controller.BookController;
import allthatbook.mvc.controller.UpdateAdminController;
import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.User;

public class BookMenuView {
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * �˻� �޴�
	 */
	public static void printSelectMenu(User user) {
		while(true) {
			System.out.println("1.������ȣ�� �˻�  |  2.���������� �˻�  |  3.���ڸ����� �˻�  |  4.���ǻ�� �˻�  |  5.�����о߷� �˻�  |  6.�뿩���η� �˻�  |  9.���ư���");
			System.out.print("��ȣ �Է� > ");
			int menu =Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				Book book =  BookMenuView.selectBookByNo(user.getUserId()); //������ȣ�� �˻�
				break;
			case 2 :
				BookMenuView.selectBookByName(user.getUserId()); //���������� �˻�
				break;
			case 3 :
				BookMenuView.selectBookByWriter(user.getUserId()); //���ڸ����� �˻�
				break;
			case 4 :
				BookMenuView.selectBookByPublisher(user.getUserId()); //���ǻ�� �˻�
				break;
			case 5 :
				BookMenuView.selectBookByCategory(user.getUserId()); //�о߷� �˻�
				break;
			case 6 : 
				//�뿩���η� �˻�
				break;
			case 9 :
				//printUserMenu(user);
				return;
			}
		}
	}
	
	/**
	 * ������ȣ�� �˻��ϱ�
	 */
	public static Book selectBookByNo(String userId) {
		Book book = null;
		while(true) {
			try {
				System.out.print("å��ȣ �Է� > ");
				int no = Integer.parseInt(sc.nextLine());
				
				book = BookController.bookSelectByBookNo(userId, no);
			}catch (NumberFormatException e) {
				//e.printStackTrace();
				System.out.println("���ڸ� �Է����ּ���.");
			}
			
		return book;
		}
	}
	
	/**
	 * ���������� �˻��ϱ�
	 */
	public static void selectBookByName(String userId) {
		try {
			System.out.print("�ܾ� �˻� > ");
			String keyword = sc.nextLine();
			BookController.bookSelectByBookName(userId, keyword);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���ڸ����� �˻��ϱ�
	 */
	public static void selectBookByWriter(String userId) {
		try {
			System.out.print("���� �˻� > ");
			String writer = sc.nextLine();
			BookController.bookSelectByWriter(userId, writer);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���ǻ�� �˻��ϱ�
	 */
	public static void selectBookByPublisher(String userId) {
		try {
			System.out.print("���ǻ� �˻� > ");
			String publisher = sc.nextLine();
			BookController.bookSelectByPublisher(userId, publisher);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * �����о߷� �˻��ϱ�
	 */
	public static void selectBookByCategory(String userId) {
		try {
			System.out.print("�����о� �˻� > ");
			String category = sc.nextLine();
			BookController.bookSelectByCategory(userId, category);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//////////////////////////////������////////////////////////
	/**
	 * ������ ������ȸ(������ȣ) -> ���� or ���� 
	 */
	public static void bookDeleteOrUpdateMenu(String userId, Book book) {
		Scanner sc = new Scanner(System.in);

		while(true) {
			int result=0;
			System.out.println("1. ���õ��� ���� | 2. ���õ��� ���� | 3. ���ư���");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
				case 1 :
					Book updatebook = AdminMenuView.updateBook();
					result = UpdateAdminController.bookUpdate(book.getBookNo(), updatebook);
					if(result==1)System.out.println(book.getBookNo()+"�� �ش� å�� �����Ǿ����ϴ�.");
					break;
				case 2 :
					BookController.bookDelete(book.getBookNo());	
					break;
				case 3:
					return;
			}
		}
	}
	/**
	 * ������ ������ȸ(������ ���ڸ� ���ǻ� �о�) -> ���� or ����
	 */
	public static void bookDeleteOrUpdateListMenu(String userId) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("1. ���õ��� ���� | 2. ���õ��� ���� | 3. ���ư���");
			int menu = Integer.parseInt(sc.nextLine());
			int bookNo=0;
			int result=0;
			switch(menu) {
				case 1:
					bookNo = AdminMenuView.InputBookNo();
					Book updatebook = AdminMenuView.updateBook();
					result = UpdateAdminController.bookUpdate(bookNo, updatebook);
					if(result==1)System.out.println(bookNo+"�� �ش� å�� �����Ǿ����ϴ�.");
					break;
				case 2:
					bookNo = AdminMenuView.InputBookNo();
					BookController.bookDelete(bookNo);	
					break;
				case 3:
					return;
			}
			
		}
	}
}
