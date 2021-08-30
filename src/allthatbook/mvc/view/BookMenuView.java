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
				Book book =  BookMenuView.selectBookByNo(user); //������ȣ�� �˻�
				CartMenuView.printCartMenu(user, book);
				break;
			case 2 :
				BookMenuView.selectBookByName(user); //���������� �˻�
				break;
			case 3 :
				BookMenuView.selectBookByWriter(user); //���ڸ����� �˻�
				break;
			case 4 :
				BookMenuView.selectBookByPublisher(user); //���ǻ�� �˻�
				break;
			case 5 :
				BookMenuView.selectBookByCategory(user); //�о߷� �˻�
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
	public static Book selectBookByNo(User user) {
		Book book = null;
		while(true) {
			try {
				System.out.print("å��ȣ �Է� > ");
				int no = Integer.parseInt(sc.nextLine());
				
				book = BookController.bookSelectByBookNo(user, no);
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
	public static void selectBookByName(User user) {
		try {
			System.out.print("�ܾ� �˻� > ");
			String keyword = sc.nextLine();
			BookController.bookSelectByBookName(user, keyword);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���ڸ����� �˻��ϱ�
	 */
	public static void selectBookByWriter(User user) {
		try {
			System.out.print("���� �˻� > ");
			String writer = sc.nextLine();
			BookController.bookSelectByWriter(user, writer);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���ǻ�� �˻��ϱ�
	 */
	public static void selectBookByPublisher(User user) {
		try {
			System.out.print("���ǻ� �˻� > ");
			String publisher = sc.nextLine();
			BookController.bookSelectByPublisher(user, publisher);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * �����о߷� �˻��ϱ�
	 */
	public static void selectBookByCategory(User user) {
		try {
			System.out.print("�����о� �˻� > ");
			String category = sc.nextLine();
			BookController.bookSelectByCategory(user, category);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//////////////////////////////������////////////////////////
	/**
	 * ������ ������ȸ(������ȣ) -> ���� or ���� 
	 */
	public static void bookDeleteOrUpdateMenu(User user, Book book) {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		while(flag) {
			int result=0;
			System.out.println("1. ���õ��� ���� | 2. ���õ��� ���� | 3. ���ư��� | 9. ���θ޴��� ����");
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
				case 1 :
					Book updatebook = AdminMenuView.updateBook();
					result = UpdateAdminController.bookUpdate(book.getBookNo(), updatebook);
					if(result==1)System.out.println(book.getBookNo()+"�� �ش� å�� �����Ǿ����ϴ�.");
					break;
				case 2 :
					result = BookController.bookDelete(book.getBookNo());	
					if(result==1)System.out.println(book.getBookNo()+"��ȣ�� �����Ǿ����ϴ�.");
					break;
				case 3:
					flag = false;
					//BookMenuView.printSelectMenu(userId);
					
					break;
				case 9:
					return;
			}
		}
	}
	/**
	 * ������ ������ȸ(������ ���ڸ� ���ǻ� �о�) -> ���� or ����
	 */
	public static void bookDeleteOrUpdateListMenu(User user) {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		while(flag) {
			System.out.println("1. ���õ��� ���� | 2. ���õ��� ���� | 3. ���ư��� | 9. ���θ޴��� ����");
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
					result = BookController.bookDelete(bookNo);	
					if(result==1)System.out.println(bookNo+"��ȣ�� �����Ǿ����ϴ�.");
					break;
				case 3:
					flag = false;
					//BookMenuView.printSelectMenu(userId);
					
					break;
				case 9:
					return;
			}
			
		}
	}
}
