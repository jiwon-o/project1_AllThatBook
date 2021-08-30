package allthatbook.mvc.view;

import java.util.Scanner;

import allthatbook.mvc.controller.BookController;
import allthatbook.mvc.model.dto.Book;

public class BookMenuView {
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * �˻� �޴�
	 */
	public static void printSelectMenu(String userId) {
		while(true) {
			System.out.println("1.������ȣ�� �˻�  |  2.���������� �˻�  |  3.���ڸ����� �˻�  |  4.���ǻ�� �˻�  |  5.�����о߷� �˻�  |  6.�뿩���η� �˻�  |  9.���ư���");
			System.out.print("��ȣ �Է� > ");
			int menu =Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				Book book =  BookMenuView.selectBookByNo(userId); //������ȣ�� �˻�
				//EndView.printCartMenu(userId, book);
				
				break;
			case 2 :
				BookMenuView.selectBookByName(userId); //���������� �˻�
				break;
			case 3 :
				BookMenuView.selectBookByWriter(userId); //���ڸ����� �˻�
				break;
			case 4 :
				BookMenuView.selectBookByPublisher(userId); //���ǻ�� �˻�
				break;
			case 5 :
				BookMenuView.selectBookByCategory(userId); //�о߷� �˻�
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
}
