package allthatbook.mvc.view;

import java.util.Scanner;

import allthatbook.mvc.controller.BookController;
import allthatbook.mvc.controller.CartController;
import allthatbook.mvc.controller.RentalController;
import allthatbook.mvc.controller.ReservationContoller;
import allthatbook.mvc.controller.UserController;
import allthatbook.mvc.model.dto.Cart;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.session.Session;
import allthatbook.mvc.session.SessionSet;

public class UserMenuView {
	private static Scanner sc = new Scanner(System.in);
	
	public static void menu() {
		while (true) {
			try {
				SessionSet ss = SessionSet.getInstance();
				System.out.println(ss.getSet());
				System.out.println("=========================================================");
				System.out.println("                                                         ");
				System.out.println("                      AllThatBook                        ");
				System.out.println("                                                         ");
				System.out.println("=========================================================");
				System.out.println("---------------------------------------------------------");
				System.out.println("    1.   �α���       2.    ȸ������        9.     ����        ");
				System.out.println("---------------------------------------------------------");
				//System.out.println("���Ͻô� ������ ��ȣ�� �Է����ּ���. ");

				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					UserMenuView.login(); // �α���
					break;
				case 2:
					UserMenuView.register(); // ȸ������
					break;
				case 9:
					System.out.println("       �����մϴ� ������ �� 'AllThatBook' �� �Բ����ּ���.      ");
					System.exit(0);
				default:
					System.out.println();
					System.out.println(" �޴��� �ִ� ��ȣ�� �Է����ּ���. ");
					System.out.println();
				}
			}catch (NumberFormatException e) {
				System.out.println(" �޴��� '����'�� �Է����ּ���. ");
			}
			
		}
	}
	
	/**
	 * �α��� �޴�
	 */
	public static void login() {
		System.out.print(" ID : ");
		String userId = sc.nextLine();
		System.out.print(" PASSWORD : ");
		String userPwd = sc.nextLine();

		System.out.println("\n");
		UserController.login(userId, userPwd);
	}

	/**
	 * ȸ������ �޴�
	 */
	
	public static void register() {
		System.out.print(" ID : ");
		String userId = sc.nextLine();
		System.out.print(" PASSWORD : ");
		String userPwd = sc.nextLine();
		System.out.print(" PASSWORD CHECK: ");
		String pwdCheck = sc.nextLine();
		System.out.print(" NAME : ");
		String userName = sc.nextLine();
		System.out.print(" PHONE : ");
		String userPhone = sc.nextLine();

		User user = new User(0, userId, userPwd, userName, userPhone, null);
		UserController.register(user, pwdCheck);
	}
	

	/**
	 * ���� �Ŵ�â
	 */
	public static void printUserMenu(User user) {
		while (true) {
			try {
				SessionSet ss = SessionSet.getInstance();
				System.out.println(ss.getSet()); // Set��ü
				System.out.println(" " + user.getUserId() + "���� �α��� ���Դϴ�.");
				System.out.println("\n");
				System.out.println(

						"1. ��ü���    2. �����˻� (�뿩, ����)   3. �����ݳ�   4. å��û   5. ��ٱ��ϴ��    6. ��ٱ��Ϻ���    7. ȸ������     8. ȸ����������    9. �α׾ƿ�    100. ��ٱ��� ����");
				System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.print("���Ͻô� ������ ��ȣ�� �Է����ּ��� :  ");
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					BookController.bookSelect();// ��ü ��ǰ��ȸ
					break;
				case 2 :
					BookMenuView.printSelectMenu(user);
					break;
				case 3:

					break;
				case 4:

					break;
				case 5:         
					CartMenuView.putCart(user.getUserId());
					break;
				case 6:
					CartMenuView.viewCart(user.getUserId());
					//CartMenuView.printCartMenu(user, null);
					break;
				case 7:
					UserMenuView.selectUserInfo(user);
					break;
				case 8:
					UserMenuView.updateTemp(user);
					if(ss.getSet().size()==0) return;
					break;
				case 9:
					logout(user.getUserId());
					return;
				case 100:
					UserMenuView.clearCart(user);
					CartController.clearCart(user.getUserId());
				    break;
				case 101:
					System.out.print("������ å ��ȣ: ");
					int bookNo = sc.nextInt();
					ReservationContoller.insertReservation(user, bookNo);
					break;
				case 102:
					System.out.print("�ݳ��� å ��ȣ: ");
					RentalController.returnBook(user, sc.nextInt());
					break;
					

				default:
					System.out.println("\n");
					System.out.println(" �޴��� �ִ� ��ȣ�� �Է����ּ���");
				}
			}catch (NumberFormatException e) {
				System.out.println("\n");
				System.out.println(" �޴��� ���ڸ� �Է����ּ���.");

			}
		}
		
	}
	
	/**
	 * ��ٱ��� ���� ��� ����
	 */
	public static void clearCart(User user) {
		
		System.out.println("���� �����Ͻðڽ��ϱ�? (��/�ƴϿ�)");
		String checkClearCart = sc.nextLine();
		
		if("��".equals(checkClearCart)) {
			CartController.clearCart(user.getUserId());
			System.out.println("��ٱ��� ������ ��� �����Ǿ����ϴ�.");
		}
		
	}

	/**
	 * ȸ���������� ȭ������ �������� ������
	 */
	public static void updateTemp(User user) {
		while (true) {
			System.out.println("��������/Ż�� ���� ��й�ȣ�� �Է��� �ּ���. (������ ����Ϸ��� \"exid\" �Է����ּ���)");
			String userPwd = sc.nextLine();
			if (userPwd.equals("exid")) {
				System.out.println("�ٽ� �޴��� ���ư��ϴ�.");
				break;
			} else if (!(user.getUserPwd().equals(userPwd))) {
				System.out.println("��й�ȣ�� �߸��Է� �ϼ̽��ϴ�.");
				continue;
			}

			System.out.println("      1.  ����          2.ȸ��Ż��         9.   ������       ");
			System.out.println("--------------------------------------------------------");
			System.out.print("��ȣ�� �������ּ��� : ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				UserMenuView.update(user); // ����
				return;
			case 2:
				UserMenuView.delete(user);
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
			System.out.println("\n");
			System.out.println("1. ��й�ȣ ����    2. �̸� ����    3. ��ȭ��ȣ ����    0. ���� �Ϸ��ϱ� (0�� ���������� �ݺ�)");
			System.out.println("---------------------------------------------------------------------------");
			System.out.print("��ȣ�� �Է����ּ��� :  ");
			menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				System.out.print("������ ��й�ȣ : ");
				userPwd = sc.nextLine();
				System.out.print("������ ��й�ȣ ��Ȯ�� : ");
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
		System.out.println("ȸ��Ż�� �����Ͻðڽ��ϱ�? ( �� or �ƴϿ� ) ");
		String checkDeleteID = sc.nextLine();
		switch(checkDeleteID) {
		case "��" :
			while(true) {
				System.out.print("Ż���� ���̵� : ");
				String userId = sc.nextLine();
				if( !(user.getUserId().equals(userId)) ) {
					System.out.println("���̵� Ʋ�Ƚ��ϴ�.");
					continue;
				}
				System.out.print("Ż���� ���̵��� ��й�ȣ : ");
				String userPwd = sc.nextLine();
				System.out.print("Ż���� ���̵��� ��й�ȣ ��Ȯ�� : ");
				String checkUserPwd = sc.nextLine();
				if( !(user.getUserPwd().equals(userPwd) && user.getUserPwd().equals(checkUserPwd)) ) {	// !(��&&��)
					System.out.println("��й�ȣ�� ��ġ�����ʽ��ϴ�.");
					continue;
				}
				
				String checkString = "Ż���ϰڽ��ϴ�";				//���߿� ���� ������ ���ؼ� ������
				System.out.println("\n");
				System.out.println("���� ȸ��Ż�� �Ͻðڽ��ϱ�? ");
				System.out.println("Ż���Ͻ÷��� ��ȣ���� ����("+checkString+")�� �����ּ���.");
				if(!sc.nextLine().equals(checkString)) {
					System.out.println("�Էµȹ��ڰ� �߸��Ǿ����ϴ� �ٽ��ѹ� Ȯ�����ּ���.");
					continue;
				}
				
				user.setUserId(userId);
				user.setUserPwd(userPwd);
				UserController.deleteUserInfo(user);
				return;
			}
		case "�ƴϿ�" :
			System.out.println("�޴��� ���ư��ϴ�.");
		break;
		default :
			System.out.println("( �� or �ƴϿ� )�߿��� �Է��� �ּ���.");
		}
	}
	

	/**
	 * ȸ�� ������ ���� Ȯ��
	 */
	public static void selectUserInfo(User user) {
		while(true) {
			System.out.println("ȸ��������ȸ�� ���� ��й�ȣ�� �Է����ּ���. (�˻��� ����Ϸ��� \"exid\" �Է����ּ���)");
			String userPwd = sc.nextLine();
			if (userPwd.equals("exid")) {
				System.out.println("�ٽ� �޴��� ���ư��ϴ�.");
				break;
			} else if (!(user.getUserPwd().equals(userPwd))) {
				System.out.println("��й�ȣ�� ��ġ�����ʽ��ϴ�.");
				continue;
			}
			UserController.selectByUserId(user.getUserId());
			UserController.selectRentalByUserNo(user.getUserNo());
			UserController.selectReservationByUserNo(user.getUserNo());
			break;
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
