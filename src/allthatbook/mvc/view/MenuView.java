package allthatbook.mvc.view;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import allthatbook.mvc.controller.BookController;
import allthatbook.mvc.controller.UserController;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.session.Session;
import allthatbook.mvc.session.SessionSet;

public class MenuView {
	private static Scanner sc = new Scanner(System.in);
	
	public static void menu() {
		while(true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet());
			
			MenuView.printMenu();
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				MenuView.login(); //�α���
				
				break;
			case 2 :
				MenuView.register(); //ȸ������
				break;

			case 9 : 
				System.exit(0);
			}
		}

	}
	
	
	

	public static void printMenu() {
		System.out.println("=== AllThatBook Library ===");
		System.out.println("1. �α���   |   2. ȸ������   |  9. ����");
	}
	
	
	public static void printUserMenu(String userId) {
		while(true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet()); //Set��ü
			System.out.println("-----" +userId+ " �α��� �� -----");
			System.out.println(" 1.��ü���  |  2.�����˻�  | 3.�����뿩  |  4.�����ݳ�  |  5.å��û  |  6.��ٱ��ϴ��  |  7.ȸ������  |  9.�α׾ƿ� ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
			case 1 :
				BookController.bookSelect();//��ü ��ǰ��ȸ
				break;
			case 2 :
				selectBookByNo();
				break;
			case 3 :
				
				break;
			case 4 :
				break;
			case 5 :
				
				break;
			case 6 : 
				MenuView.putCart(userId);
				break;
			case 9 :
				logout(userId);
				return;
			}
		}
		
	}
	
	public static void printSubMenu() {
		System.out.println("1. ����   |  2.Ż��   | 9. ������");
	}
	
	public static void printAdminMenu(String userId) {
		System.out.println("-- ������ �޴� --");
		System.out.println("1. ȸ������   |  2. ��������  | 3. ������� |  9. ������");
		int menu=Integer.parseInt(sc.nextLine());
		switch(menu) {
		case 1 :
			AdminMenuView.userAdminMenu();
			break;
		case 2 :
			AdminMenuView.bookAdminMenu();
			break;
		case 3 :
			break;
		case 9 :
			logout(userId);
			return;
		}
	}
	
	/**
	 * å��ȣ�� �˻��ϱ�
	 */
	public static void selectBookByNo() {
		try {
			System.out.println("å��ȣ �Է� > ");
			int no = Integer.parseInt(sc.nextLine());
			
			BookController.bookSelectByBookNo(no);
		}catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("���ڸ� �Է����ּ���.");
			System.out.println("�ٽ� �Ͻðڽ��ϱ�? (yes or no)");
			String choice = sc.nextLine();
			if(choice.equals("yes")) {
				selectBookByNo();
			}
		}
	}
	
	/**
	 * å��ȣ�� �˻��ϱ�
	 */
	public static void selectBookByNo() {
		try {
			System.out.println("å��ȣ �Է� > ");
			int no = Integer.parseInt(sc.nextLine());
			
			BookController.bookSelectByBookNo(no);
		}catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("���ڸ� �Է����ּ���.");
			System.out.println("�ٽ� �Ͻðڽ��ϱ�? (yes or no)");
			String choice = sc.nextLine();
			if(choice.equals("yes")) {
				selectBookByNo();
			}
		}
	}
	
	/**
     * ��ٱ��� ���
     * */
    public static void putCart(String userId) {
		System.out.println("----��ٱ��� ���----");
		System.out.print("å��ȣ : ");
		int bookNo = Integer.parseInt(sc.nextLine());
		
		CartController.putCart(userId, bookNo);
	
		
	}
    
	/**
	 * �α��� �޴�
	 * */
	public static void login() {
		System.out.print("���̵� : ");
		String userId = sc.nextLine();
		 
		System.out.print("��й�ȣ : ");
		String userPwd = sc.nextLine();
		 
		UserController.login(userId, userPwd);
	}
	
	/**
	 * ȸ������ �޴�
	 */
	private static void register() {
		System.out.print("���̵� : ");
		String userId = sc.nextLine();
		 
		System.out.print("��й�ȣ : ");
		String userPwd = sc.nextLine();
		
		System.out.print("��й�ȣ Ȯ�� : ");
		String pwdCheck = sc.nextLine();
		
		System.out.print("�̸� : ");
		String userName = sc.nextLine();
		
		System.out.print("��ȭ��ȣ : ");
		String userPhone = sc.nextLine();
		
		User user = new User(0, userId, userPwd, userName, userPhone, null);
		UserController.register(user, pwdCheck);
		
	}

	/**
	 * �α׾ƿ�
	 * */
	public static void logout(String userId) {
		Session session = new Session(userId);
		
		SessionSet ss = SessionSet.getInstance();
		ss.remove(session);	
	}
	
}



