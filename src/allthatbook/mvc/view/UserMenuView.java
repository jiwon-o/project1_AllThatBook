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
				System.out.println("--------------------------------------------------------------");
				System.out.println("      1.   �α���      2.   ȸ������      9.   ����           ");
				System.out.println("--------------------------------------------------------------");
				
				System.out.print("�޴� �Է� : ");
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					UserMenuView.login(); // �α���
					break;
				case 2:
					UserMenuView.register(); // ȸ������
					break;
				case 9:

					System.out.println("*** ���� �����Ͻðڽ��ϱ�? ( �� / �ƴϿ� ) ***");
					String checkLogout = sc.nextLine();
					if("��".equals(checkLogout)) {
						
						System.out.println("\n--------------------------------------------------------------------------------------\n");
						System.out.println("                    �����մϴ�. ������ �� 'AllThatBook' �� �Բ����ּ���.              ");
						System.out.println("\n--------------------------------------------------------------------------------------");
						System.exit(0);
						
					}else if("�ƴϿ�".equals(checkLogout)) {
						System.out.println("*** '�ƴϿ�'�� �Է��ϼ̽��ϴ�. *** ");
						break;
					}else {
						System.out.println("*** ( �� / �ƴϿ� ) �� �ϳ��� �Է����ּ���. ***\n");
						break;
					}
					
				default:
					System.out.println("*** �޴��� �ִ� ��ȣ�� �Է����ּ���. ***\n");

				}
			}catch (NumberFormatException e) {
				System.out.println("*** �޴��� '����'�� �Է����ּ���. ***\n");
			}
			
		}
	}
	
	/**
	 * �α��� �޴�
	 */
	public static void login() {
		System.out.println("*** �α��� ȭ������ �̵��մϴ�. ***");
		System.out.print("\nID : ");
		String userId = sc.nextLine();
		System.out.print("Password : ");
		String userPwd = sc.nextLine();

		UserController.login(userId, userPwd);
		
	}

	/**
	 * ȸ������ �޴�
	 */
	
	public static void register() {
		System.out.println("*** ȸ������ ȭ������ �̵��մϴ�. ***");
		System.out.print("\n���̵� : ");
		String userId = sc.nextLine();
		System.out.print("��й�ȣ : ");
		String userPwd = sc.nextLine();
		System.out.print("��й�ȣ Ȯ�� : ");
		String pwdCheck = sc.nextLine();
		System.out.print("�̸� : ");
		String userName = sc.nextLine();
		System.out.print("�޴���ȭ : ");
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

				System.out.println("\n");
				System.out.println(ss.getSet()); // Set��ü
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("      1.  ��ü���      2.  �����˻�(�뿩, ����)      3.  �����ݳ�      4.   ȸ��������ȸ      5.   ȸ����������      9.   �α׾ƿ�      ");
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.print("�޴� �Է� : ");

				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					System.out.println("*** ��ü ���� ����� ��µ˴ϴ�. ***");
					BookController.bookSelect();
					break;
				case 2 :
					System.out.println("*** ���� �˻� ȭ������ �̵��մϴ�. ***");
					BookMenuView.printSelectMenu(user);
					break;
				case 3:
					System.out.println("*** �����ݳ� �޴��� �����߽��ϴ�. ***\n");
					System.out.print("������ȣ �Է�: ");
					int bookNo = Integer.parseInt(sc.nextLine());
					RentalController.returnBook(user, bookNo);
					break;
				case 4:
					System.out.println("*** ȸ������ ȭ������ �̵��մϴ�. ***");
					UserMenuView.selectUserInfo(user);
					break;
				case 5:  
					System.out.println("*** ȸ���������� ȭ������ �̵��մϴ�. ***");
					UserMenuView.updateTemp(user);
					if(ss.getSet().size()==0) return;
					break;
				case 9:
					System.out.println("*** �α׾ƿ� �޴��� �����߽��ϴ�. ***\n");
					System.out.println("*** ���� �α׾ƿ��Ͻðڽ��ϱ�? ( �� / �ƴϿ� ) ***");
					String checkLogout = sc.nextLine();
					if("��".equals(checkLogout)) {
						logout(user.getUserId());
						System.out.println("*** �α׾ƿ��Ǿ����ϴ�. ***\n");
					}else if("�ƴϿ�".equals(checkLogout)) {
						System.out.println("*** '�ƴϿ�'�� �Է��ϼ̽��ϴ�. *** ");
						break;
					}else {
						System.out.println("*** ( �� / �ƴϿ� ) �� �ϳ��� �Է����ּ���. ***\n");
						break;
					}
					
					return;
				default:
					System.out.println("*** �޴��� �ִ� ��ȣ�� �Է����ּ���. ***");
				}
			}catch (NumberFormatException e) {
				System.out.println("*** �޴��� '����'�� �Է����ּ���. ***");

			}
		}
		
	}
	
	/**
	 * ��ٱ��� ���� ��� ����
	 */
	public static void clearCart(User user) {
		
		System.out.println("��ٱ��� ����� ���� �����Ͻðڽ��ϱ�? ( �� / �ƴϿ� ) ");
		String checkClearCart = sc.nextLine();
		
		if("��".equals(checkClearCart)) {
			CartController.clearCart(user.getUserId());
			System.out.println("*** ��ٱ��� ������ ��� �����Ǿ����ϴ�. ***");
		}else if("�ƴϿ�".equals(checkClearCart)) {
			System.out.println("*** '�ƴϿ�'�� �Է��ϼ̽��ϴ�. *** ");
		}else {
			System.out.println("*** ( �� / �ƴϿ� ) �� �ϳ��� �Է����ּ���. ó������ ���ư��ϴ�. ***\n");
		}
		
	}

	/**
	 * ȸ���������� ȭ������ �������� ������
	 */
	public static void updateTemp(User user) {
		while (true) {
			System.out.println("\nȸ������ ����/Ż�� ���� ��й�ȣ�� �Է��� �ּ���. (������ ����Ϸ��� \"exid\" �Է�) ");
			
			String userPwd = sc.nextLine();
			if (userPwd.equals("exid")) {
				System.out.println("*** �ٽ� �޴��� ���ư��ϴ�. ***");
				break;
			} else if (!(user.getUserPwd().equals(userPwd))) {
				System.out.println("*** ��й�ȣ�� ��ġ�����ʽ��ϴ�. ***");
				continue;
			}

			System.out.println("\n*** ȸ������ ����/Ż�� ***");
			System.out.println("---------------------------------------------------------------");
			System.out.println("        1.   ����       2.   ȸ��Ż��       9.   ������        ");
			System.out.println("---------------------------------------------------------------");
			System.out.print("�޴� �Է� : ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				System.out.println("*** ȸ���������� ȭ������ �̵��մϴ�.*** ");
				UserMenuView.update(user); // ����
				return;
			case 2:
				UserMenuView.delete(user);
				return;
			case 9:
				return;
			default:
				System.out.println("*** �޴��� �ִ� ��ȣ�� �Է����ּ���. ***");
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
			System.out.println("\n*** ȸ���������� ȭ�� ***");
			System.out.println("--------------------------------------------------------------------------------------------------");
			System.out.println("      1.   ��й�ȣ ����      2.   �̸� ����      3.   ��ȭ��ȣ ����      9.   ���� �Ϸ��ϱ�      ");
			System.out.println("--------------------------------------------------------------------------------------------------");
			System.out.print("�޴� �Է� : ");
			menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				System.out.println("*** ��й�ȣ�� �����մϴ�. ***\n");
				System.out.print("������ ��й�ȣ : ");
				userPwd = sc.nextLine();
				System.out.print("������ ��й�ȣ Ȯ�� : ");
				String cheuserPwd = sc.nextLine();
				if (!userPwd.equals(cheuserPwd)) {
					System.out.println("*** ��й�ȣ�� ��ġ���� �ʽ��ϴ�. ***");
					userPwd = user.getUserPwd();
				}else {
					System.out.println("*** ��й�ȣ�� ����Ǿ����ϴ�. ***");
				}
				break;
			case 2:
				System.out.println("*** �̸��� �����մϴ�. ***\n");
				System.out.print("������ �̸� : ");
				userName = sc.nextLine();
				
				System.out.println("*** �̸��� ����Ǿ����ϴ�. ***");
				break;
			case 3:
				System.out.println("*** ��ȭ��ȣ�� �����մϴ�. ***\n");
				System.out.print("������ ��ȭ��ȣ : ");
				userPhone = sc.nextLine();
				
				System.out.println("*** ��ȭ��ȣ�� ����Ǿ����ϴ�. ***");
				break;
			case 9:
				user.setUserPwd(userPwd);
				user.setUserName(userName);
				user.setUserPhone(userPhone);

				UserController.updateUserInfo(user);
				return;
			default:
				System.out.println("*** �޴��� �ִ� ��ȣ�� �Է����ּ���. ***");
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
					System.out.println("���̵� Ʋ�Ƚ��ϴ�. ");
					continue;
				}
				System.out.print("Ż���� ���̵��� ��й�ȣ : ");
				String userPwd = sc.nextLine();
				System.out.print("Ż���� ���̵��� ��й�ȣ ��Ȯ�� : ");
				String checkUserPwd = sc.nextLine();
				if( !(user.getUserPwd().equals(userPwd) && user.getUserPwd().equals(checkUserPwd)) ) {	// !(��&&��)
					System.out.println("��й�ȣ�� ��ġ�����ʽ��ϴ�. ");
					continue;
				}
				
				String checkString = "Ż���ϰڽ��ϴ�";				//���߿� ���� ������ ���ؼ� ������
				System.out.println("\n");
				System.out.println("���� ȸ��Ż�� �Ͻðڽ��ϱ�?");
				System.out.println("����Ϸ��� (�ƴϿ�)�� �Է����ּ���");
				System.out.println("Ż���Ͻ÷��� ��ȣ���� ����("+checkString+")�� �Է����ּ���. ");
				String checkText = sc.nextLine();
				if(checkText.equals("�ƴϿ�")) break;
				if(!checkText.equals(checkString)) {
					System.out.println("�Էµȹ��ڰ� �߸��Ǿ����ϴ� �ٽ��ѹ� Ȯ�����ּ���. ");
					continue;
				}
				
				user.setUserId(userId);
				user.setUserPwd(userPwd);
				UserController.deleteUserInfo(user);
				return;
			}
		case "�ƴϿ�" :
			System.out.println("�޴��� ���ư��ϴ�. ");
		break;
		default :
			System.out.println("( �� or �ƴϿ� )�߿��� �Է��� �ּ���. ");
		}
	}

	

	/**
	 * ȸ�� ������ ���� Ȯ��
	 */
	public static void selectUserInfo(User user) {
		while(true) {
			System.out.println("\nȸ��������ȸ�� ���� ��й�ȣ�� �Է����ּ���. (�˻��� ����Ϸ��� \"exid\" �Է�) ");
			String userPwd = sc.nextLine();
			if (userPwd.equals("exid")) {
				System.out.println("*** �ٽ� �޴��� ���ư��ϴ�. ***");
				break;
			} else if (!(user.getUserPwd().equals(userPwd))) {
				System.out.println("*** ��й�ȣ�� ��ġ�����ʽ��ϴ�. ***");
				continue;
			}
			UserController.selectByUserId(user.getUserId());
			UserController.selectRentalByUserNo(user.getUserNo());
			UserController.selectReservationByUserNo(user.getUserNo());
			UserMenuView.reservationDeleteMenu(user);
			break;
		}
	}

	//���� �����ϱ�
	public static void reservationDeleteMenu(User user) {
		System.out.println("*** ȸ������ �޴� ***");
		System.out.println("----------------------------------------------------------------");
		System.out.println("        1.    ���� �����ϱ�        2.    ���θ޴��� ����        ");
		System.out.println("----------------------------------------------------------------");
		System.out.print("�޴� �Է� : ");
		int menu = Integer.parseInt(sc.nextLine());
		
		switch (menu) {
		case 1:
			System.out.println("*** ���� �����ϱ� �޴��� �����߽��ϴ�. ***\n");
			System.out.print("������ȣ �Է� : ");
			int bookNo = Integer.parseInt(sc.nextLine());
			ReservationContoller.deleteReservation(user, bookNo);
			break;
		case 2:
			System.out.println("���� ���� ȭ���� ���� �Ǿ����ϴ�. ");
			break;
		default:
			System.out.println("�޴��� �ִ� '��ȣ'�� �Է����ּ���");
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
