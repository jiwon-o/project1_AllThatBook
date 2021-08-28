package allthatbook.mvc.view;

import java.util.Scanner;

import allthatbook.mvc.controller.BookController;
import allthatbook.mvc.controller.CartController;
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

			System.out.println(" 1.��ü���  |  2.�����˻�  | 3.�����뿩  |  4.�����ݳ�  |  5.å��û  |  6.��ٱ��ϴ��  |  7.ȸ������  |  8.ȸ����������  |  9.�α׾ƿ� |  10.��ٱ��Ϻ���");


			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
			case 1 :
				BookController.bookSelect();//��ü ��ǰ��ȸ
				break;
			case 2 :
				printSelectMenu(userId);
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
			case 7 : 
				
				
			case 8 :
				MenuView.updateTemp();
				break;
			case 9 :
				logout(userId);
				return;
			case 10 :
				viewCart(userId);
				
			}
		}
		
	}
	/**
	 * �˻� �޴�
	 */
	public static void printSelectMenu(String userId) {
		while(true) {
			System.out.println("1.������ȣ�� �˻�  |  2.���������� �˻�  |  3.���ڸ����� �˻�  |  4.���ǻ�� �˻�  |  5.�����о߷� �˻�  |  6.�뿩���η� �˻�  |  9.���ư���");
			
			int menu =Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				selectBookByNo(userId); //������ȣ�� �˻�
				break;
			case 2 :
				selectBookByName(userId); //���������� �˻�
				break;
			case 3 :
				selectBookByWriter(userId); //���ڸ����� �˻�
				break;
			case 4 :
				selectBookByPublisher(userId); //���ǻ�� �˻�
				break;
			case 5 :
				selectBookByCategory(userId); //�о߷� �˻�
				break;
			case 6 : 
				
				break;
			case 9 :
				printUserMenu(userId);
				break;
			}
		}
	}
	

	/**
	 * ȸ������ �޴�
	 */
	public static void printSubMenu() {
		System.out.println("1. ����  |  2.ȸ��Ż��  |  9. ������");
	}
	

	/**
	 * �����ڸ޴�
	 */
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
	
//////////////////////   �˻�   /////////////////////
	/**
	 * ������ȣ�� �˻��ϱ�
	 */
	public static void selectBookByNo(String userId) {
		try {
			System.out.println("å��ȣ �Է� > ");
			int no = Integer.parseInt(sc.nextLine());
			
			BookController.bookSelectByBookNo(userId, no);
		}catch (NumberFormatException e) {
			//e.printStackTrace();
			System.out.println("���ڸ� �Է����ּ���.");
			System.out.println("�ٽ� �Ͻðڽ��ϱ�? (yes or no)");
			String choice = sc.nextLine();
			if(choice.equals("yes")) {
				selectBookByNo(userId);
			}
		}
	}
	
	/**
	 * ���������� �˻��ϱ�
	 */
	public static void selectBookByName(String userId) {
		try {
			System.out.println("�ܾ� �˻� > ");
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
			System.out.println("���� �˻� > ");
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
			System.out.println("���ǻ� �˻� > ");
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
			System.out.println("�����о� �˻� > ");
			String category = sc.nextLine();
			
			BookController.bookSelectByCategory(userId, category);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
///////////////////////   ��ٱ���   //////////////////////
	
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
     * ��ٱ��� ����
     * */
	public static void viewCart(String userId) {
		CartController.viewCart(userId);
		
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
	 * ȸ���������� ȭ������ �������� ������
	 * */
	public static void updateTemp() {
		
		MenuView.printSubMenu();
		int menu = Integer.parseInt(sc.nextLine());
		switch(menu) {
		case 1 :
			MenuView.update(); //����
			break;
		case 2 :
			MenuView.delete();
			break;
		case 9 : 
			MenuView.printMenu();
			break; 
		}
		
		//System.out.print(����);
		String update = sc.nextLine(); 
	}
	
	/**
	 * ȸ������ ����
	 * */
	public static void update() {
		System.out.print("���̵� : ");
		String userId = sc.nextLine();
		 
		System.out.print("������ ��й�ȣ : ");
		String userPwd = sc.nextLine();
		
		System.out.print("������ ��ȭ��ȣ : ");
		String userPhone = sc.nextLine();
		
		
		User user = new User();
		
		user.setUserId(userId); 
		user.setUserPwd(userPwd);
		user.setUserPhone(userPhone);
		 
		UserController.updateUserInfo(user);
	}
	

	/**
	 * ȸ��Ż�� ȭ�����ΰ������� �ӽ�������
	 * */
	public static void deleteTemp() {
		
		MenuView.printSubMenu();
		int menu = Integer.parseInt(sc.nextLine());
		switch(menu) {
		case 1 :
			MenuView.update(); 
			break;
		case 2 :
			MenuView.delete(); //����
			break;
		case 9 : 
			MenuView.printMenu();
		}
		//System.out.print("2. Ż�� ");
		String delete = sc.nextLine();
	}
	
	/**
	 * ȸ��Ż��
	 * */
	public static void delete() {
		System.out.print("���̵� : ");
		String userId = sc.nextLine();
		 
		System.out.print("��й�ȣ : ");
		String userPwd = sc.nextLine();
		
		User user = new User();
		
		user.setUserId(userId); 
		user.setUserPwd(userPwd);
		
		UserController.revoke(user);
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



