package allthatbook.mvc.view;

import java.util.Scanner;
import java.util.Set;

import allthatbook.mvc.controller.BookController;
import allthatbook.mvc.controller.CartController;
import allthatbook.mvc.controller.UserController;
import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.session.Session;
import allthatbook.mvc.session.SessionSet;

public class MenuView {
	private static Scanner sc = new Scanner(System.in);

	public static void menu() {
		while (true) {

			try {
				SessionSet ss = SessionSet.getInstance();
				System.out.println(ss.getSet());

				System.out.println("=== AllThatBook Library ===");
				System.out.println("1. �α���   |   2. ȸ������   |  9. ����");
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					MenuView.login(); // �α���
					break;
				case 2:
					MenuView.register(); // ȸ������
					break;

				case 9:
					System.exit(0);
				default:
					System.out.println("�޴��� �ִ� ��ȣ�� �Է����ּ���");
				}
			}catch (NumberFormatException e) {
				System.out.println("�޴��� ���ڸ� �����մϴ�.");
			}
		}
		
		
	}

	public static void printUserMenu(User user) {
		
		while (true) {

			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet()); // Set��ü
			System.out.println("-----" + user.getUserId() + " �α��� �� -----");
			System.out.println(
					" 1.��ü���  | 2.�����˻�(�뿩, ����) | 3.�����ݳ�  | 4.å��û |  5.��ٱ��ϴ�� | 6.��ٱ��Ϻ��� |  7.ȸ������  |  8.ȸ����������  |  9.�α׾ƿ� | ");
			System.out.print("��ȣ �Է� > ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				BookController.bookSelect();// ��ü ��ǰ��ȸ
				break;

			case 2 :
				printSelectMenu(user.getUserId());

				break;
			case 3:

				break;
			case 4:

				break;
			case 5:         
				MenuView.putCart(user.getUserId());
				break;
			case 6:
				MenuView.viewCart(user.getUserId());
				break;
			case 7:
				
				break;


			}
		}

		
	}
	/**
	 * �˻� �޴�
	 */
	public static void printSelectMenu(String userId) {
		
		while(true) {
			try {
				System.out.println("1.������ȣ�� �˻�  |  2.���������� �˻�  |  3.���ڸ����� �˻�  |  4.���ǻ�� �˻�  |  5.�����о߷� �˻�  |  6.�뿩���η� �˻�  |  9.���θ޴��� ����");
				
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
					//printUserMenu(user);
					return;
				default:
					System.out.println("�޴��� �ִ� ��ȣ�� �Է����ּ���");
				}
			}catch (NumberFormatException e) {
				System.out.println("�޴��� ���ڸ� �����մϴ�.");

			}
		}
		
	}
	

	/**
	 * �����ڸ޴�
	 */

	public static void printAdminMenu(User user) {
		System.out.println("-- ������ �޴� --");
		System.out.println("1. ȸ������   |  2. ��������  | 3. ������� |  9. ������");
		int menu = Integer.parseInt(sc.nextLine());

		try {
			switch (menu) {
			case 1:
				AdminMenuView.userAdminMenu();
				break;
			case 2:
				AdminMenuView.bookAdminMenu();
				break;
			case 3:
				break;
			case 9:
				logout(user.getUserId());
				return;
			default:
				System.out.println("�޴��� �ִ� ��ȣ�� �Է����ּ���");
			}
		}catch (NumberFormatException e) {
			System.out.println("�޴��� ���ڸ� �����մϴ�.");

		}
		
	}

	
//////////////////////   �˻�   /////////////////////


	/**
	 * ������ȣ�� �˻��ϱ�
	 */
	public static void selectBookByNo(String user) {
		while(true) {
			try {
				System.out.println("å��ȣ �Է� > ");
				int no = Integer.parseInt(sc.nextLine());
				
				BookController.bookSelectByBookNo(user, no);
			}catch (NumberFormatException e) {
				//e.printStackTrace();
				System.out.println("���ڸ� �Է����ּ���.");
				
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
			///e.printStackTrace();

			
		}
	}
	
	/**
	 * ���ڸ����� �˻��ϱ�
	 */
	public static void selectBookByWriter(String user) {
		try {
			System.out.println("���� �˻� > ");
			String writer = sc.nextLine();
			
			BookController.bookSelectByWriter(user, writer);
		}catch (Exception e) {
			//e.printStackTrace();

		}
	}
	
	/**
	 * ���ǻ�� �˻��ϱ�
	 */
	public static void selectBookByPublisher(String user) {
		try {
			System.out.println("���ǻ� �˻� > ");
			String publisher = sc.nextLine();
			
			BookController.bookSelectByPublisher(user, publisher);
		}catch (Exception e) {
			//e.printStackTrace();

		}
	}
		
	/**
	 * �����о߷� �˻��ϱ�
	 */
	public static void selectBookByCategory(String user) {
		try {
			System.out.println("�����о� �˻� > ");
			String category = sc.nextLine();
			
			BookController.bookSelectByCategory(user, category);
		}catch (Exception e) {
			//e.printStackTrace();
		}
		
	}
	
	
///////////////////////   ��ٱ���   //////////////////////
	
	/**
	 * ��ٱ��� ���
	 */
	public static void putCart(String userId) {
		try {
			System.out.println("----��ٱ��� ���----");
			System.out.print("å��ȣ : ");
			int bookNo = Integer.parseInt(sc.nextLine());

			CartController.putCart(userId, bookNo);
		}catch (NumberFormatException e) {
			System.out.println("���ڸ� �Է����ּ���.");
		}


	}
    
    /**
     * ��ٱ��� ����
     * */
	public static void viewCart(String userId) {
		CartController.viewCart(userId);
		
	}
	

//	/**
//	 * ��ٱ��� ����
//	 */
//	public static void removeCart(String userId) {
//		System.out.println("��ٱ��� ����� ��� �����Ͻðڽ��ϱ�? (yes / no)");
//		String choice = sc.nextLine();
//		if(choice.equals("yes")) {
//			System.out.println("������ �����Ͻðڽ��ϱ�? (yes / no)");
//			String certain = sc.nextLine();
//			if(certain.equals("yes")) {
//				CartController.removeCart(userId);
//				System.out.println("�����Ǿ����ϴ�.");
//			}
//		}
//	}
//	
//	/**
//	 * ��ٱ��� ��� �����ϱ�
//	 */
//	public static void deleteCartList(String userId) {
//		System.out.println("������ ������ȣ�� �Է��Ͻÿ� > ");
//		int no = Integer.parseInt(sc.nextLine());
//		
//		CartController.deleteCartList(userId);
//	}
	
	/**
	 * �α��� �޴�
	 */
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
	 */
	public static void updateTemp(User user) {
		while (true) {
			System.out.println("��������/Ż�� ���� ��й�ȣ�� �Է��� �ּ���. (������ ����Ϸ��� \"exid\" �����ּ���)");
			String userPwd = sc.nextLine();
			if (userPwd.equals("exid")) {
				System.out.println("�ٽ� �޴��� ���ư��ϴ�.");
				break;
			} else if (!(user.getUserPwd().equals(userPwd))) {
				System.out.println("��й�ȣ�� �߸��Է� �ϼ̽��ϴ�.");
				continue;
			}

			System.out.println(" 1. ����   |  2.ȸ��Ż��  | 9. ������ ");
			System.out.print("��ȣ ���� > ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				MenuView.update(user); // ����
				return;
			case 2:
				MenuView.delete(user);
				return;
			case 9:
				System.out.println("����ȭ���� ���� �Ǿ����ϴ�.");
				return;
			default:
				System.out.println("�޴��� �ִ� ��ȣ�� �Է����ּ���");
			}
		}
	}

	/**
	 * ȸ������ ����
	 */
	public static void update(User user) {
		int menu = 0;
		String userPwd = user.getUserPwd(); // �ٲٱ��� ��й�ȣ ����ϱ�
		String userName = user.getUserName(); // �ٲٱ��� �̸� ����ϱ�
		String userPhone = user.getUserPhone(); // �ٲٱ��� ��ȭ��ȣ ����ϱ�

		while (true) {
			System.out.println("1. ��й�ȣ ����  2. �̸� ����  3.  ��ȭ��ȣ ����  0. ���� �Ϸ��ϱ� (0�� ���������� �ݺ�)");
			System.out.print("��ȣ ���� > ");
			menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				System.out.print("������ ��й�ȣ : ");
				userPwd = sc.nextLine();
				System.out.print("������ ��й�ȣ Ȯ��: ");
				String cheuserPwd = sc.nextLine();
				if (!userPwd.equals(cheuserPwd)) {
					System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					userPwd = user.getUserPwd();
				}
				break;
			case 2:
				System.out.print("������ �̸� : ");
				userName = sc.nextLine();
				break;
			case 3:
				System.out.print("������ ��ȭ��ȣ : ");
				userPhone = sc.nextLine();
				break;
			case 0:
				user.setUserPwd(userPwd);
				user.setUserName(userName);
				user.setUserPhone(userPhone);

				UserController.updateUserInfo(user);
				return;
			default:
				System.out.println("�޴��� �ִ� ��ȣ�� �Է����ּ���");
			}
		}
	}

	/**
	 * ȸ��Ż��
	 */
	public static void delete(User user) {
		System.out.println("***ȸ�� Ż�� �Ͻðڽ��ϱ�? (��/�ƴϿ�)***");
		String checkDeleteID = sc.nextLine();
		switch(checkDeleteID) {
		case "��" :
			while(true) {
				System.out.print("Ż���Ͻ� ���̵� : ");
				String userId = sc.nextLine();
				if( !(user.getUserId().equals(userId)) ) {
					System.out.println("***���̵� Ʋ�Ƚ��ϴ�.***");
					continue;
				}
				System.out.print("Ż���Ͻ� ��й�ȣ : ");
				String userPwd = sc.nextLine();
				System.out.print("Ż���Ͻ� ��й�ȣ Ȯ�� : ");
				String checkUserPwd = sc.nextLine();
				if( !(user.getUserPwd().equals(userPwd) && user.getUserPwd().equals(checkUserPwd)) ) {	// !(��&&��)
					System.out.println("***��й�ȣ�� Ʋ�Ƚ��ϴ�.***");
					continue;
				}
				
				String checkString = "Ż���ϰڽ��ϴ�";				//���߿� ���� ������ ���ؼ� ������
				System.out.println("***���� ȸ�� Ż�� �Ͻðڽ��ϱ�?***");
				System.out.println("***Ż���Ͻ÷��� ��ȣ���� ����("+checkString+")�� �����ּ���***");
				if(!sc.nextLine().equals(checkString)) {
					System.out.println("***�Է¹��ڰ� �߸� �Ǿ����ϴ�.***");
					continue;
				}
				
				user.setUserId(userId);
				user.setUserPwd(userPwd);
				UserController.deleteUserInfo(user);
				return;
			}
		case "�ƴϿ�" :
			System.out.println("***�޴��� ���ư��ϴ�.***");
		break;
		default :
			System.out.println("***��/�ƴϿ� �߿��� �Է��� �ּ���***");
		}
	}

	/**
	 * �α׾ƿ�
	 */
	public static void logout(String userId) {
		Session session = new Session(userId);

		SessionSet ss = SessionSet.getInstance();
		ss.remove(session);
	}


}
