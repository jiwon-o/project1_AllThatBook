package allthatbook.mvc.view;

import java.util.List;
import java.util.Scanner;
import allthatbook.mvc.controller.BookController;
import allthatbook.mvc.controller.UpdateAdminController;
import allthatbook.mvc.controller.UserController;
import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.session.SessionSet;

public class AdminMenuView {
	private static Scanner sc = new Scanner(System.in);

	/**
	 * �����ڸ޴�
	 */
	public static void printAdminMenu(User user) {
		SessionSet ss = SessionSet.getInstance();
		while (true) {
			try {
				System.out.println("\n");
				System.out.println(ss.getSet()); // Set��ü
				System.out.println("-------------------------------------------------------------------");
				System.out.println("        1.   ȸ������      2.   ��������      9.   �α׾ƿ�        ");
				System.out.println("-------------------------------------------------------------------");
				System.out.print("�޴� �Է� : ");
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					System.out.println("*** ȸ������ ȭ������ �̵��մϴ�. ***");
					AdminMenuView.userAdminMenu();
					break;
				case 2:
					System.out.println("*** �������� ȭ������ �̵��մϴ�. ***");
					AdminMenuView.bookAdminMenu(user);
					break;
				case 3:
					break;
				case 9:
					System.out.println("*** �α׾ƿ� �޴��� �����߽��ϴ�. ***\n");
					System.out.println("*** ���� �α׾ƿ��Ͻðڽ��ϱ�? ( �� / �ƴϿ� ) ***");
					String checkLogout = sc.nextLine();
					if("��".equals(checkLogout)) {
						UserMenuView.logout(user.getUserId());
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
			} catch (NumberFormatException e) {
				FailView.errorMessage("*** �޴��� '����'�� �Է°����մϴ�. ***");
			}

		}
	}

	/**
	 * ȸ������ �޴�
	 * 
	 * @throws Exception
	 */
	public static void userAdminMenu() {
		int result = 0;
		int userNo;
		User user;
		while (true) {
			try {
				System.out.println("\n*** ȸ������ ȭ�� ***");
				System.out.println(
						"-----------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println(
						"        1.   ��üȸ�� ��ȸ      2.   ȸ����ȣ�� ��ȸ      3.   ȸ��ID�� ��ȸ      4.   ȸ����������      5.   ȸ����������      9.   ���ư���        ");
				System.out.println(
						"-----------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.print("�޴� �Է� : ");
				int menu = Integer.parseInt(sc.nextLine());

				switch (menu) {
				case 1:
					System.out.println("*** ��üȸ���� ��ȸ�մϴ�. ***");
					UserController.userSelect();
					break;
				case 2:
					System.out.println("*** ȸ����ȣ�� ��ȸ�մϴ�. ***");
					userNo = InputUserNo();
					UserController.selectByUserNo(userNo);
					break;
				case 3:
					System.out.println("*** ȸ��ID�� ��ȸ�մϴ�. ***");
					String userId = InputUserId();
					UserController.selectByUserId(userId);
					break;
				case 4: // ȸ����������
					System.out.println("*** ȸ���������� ȭ������ �̵��մϴ�. ***");
					userNo = InputUserNo();
					user = UserController.selectByUserNo(userNo);
					if (user == null) {
						break;
					}
					User updateuser = updateUser();
					result = UpdateAdminController.userUpdate(userNo, updateuser);
					if (result == 1)
						System.out.println("*** '" + userNo + "'�� ȸ���� ������ �����Ǿ����ϴ�. ");

					break;
				case 5:
					System.out.println("*** ȸ���������� ȭ������ �̵��մϴ�. ***");
					userNo = InputUserNo();
					user = UserController.selectByUserNo(userNo);
					System.out.println("\n");
					if (user == null) {
						break;
					}
					UserController.deleteAdminUserInfo(userNo);
					break;
				case 9:

					return;
				default:
					System.out.println("*** �޴��� �ִ� '��ȣ'�� �Է����ּ���. ***");
				}
			} catch (NumberFormatException e) {
				FailView.errorMessage("*** �޴��� '����'�� �Է°����մϴ�. ***");
			}
		}

	}

	/**
	 * �������� �޴�
	 */
	public static void bookAdminMenu(User user) {
		while (true) {
			try {
				System.out.println("\n*** �������� ȭ�� ***");
				System.out.println(
						"-----------------------------------------------------------------------------------------------------");
				System.out.println("      1.  �� �������      2.  ������ȸ      3.  ������������      4.  ��������      9.  ���ư���      ");
				System.out.println(
						"-----------------------------------------------------------------------------------------------------");
				System.out.print("�޴� �Է� : ");
				int menu = Integer.parseInt(sc.nextLine());
				int bookNo = 0;
				int result = 0;
				Book book = null;
				switch (menu) {
				case 1:
					System.out.println("*** ���ο� ������ ����մϴ�. ***");
					book = InputBook();
					BookController.bookInsert(book);

					break;
				case 2:
					System.out.println("*** ���������� ��ȸ�մϴ�. ***\n");
					printSelectMenu(user);
					
					break;
				case 3:
					System.out.println("*** ���������� �����մϴ�. ***");
					bookNo = InputBookNo();
					book = BookController.bookSelectByBookNo(user, bookNo);
					if (book == null) {
						break;
					}
					Book updatebook = updateBook();
					result = UpdateAdminController.bookUpdate(bookNo, updatebook);
					if (result == 1)
						System.out.println("\n*** '" + bookNo + "'�� ������ �����߽��ϴ�.");
					

					break;
				case 4:
					System.out.println("*** ���������� �����մϴ�. ***\n");
					bookNo = InputBookNo();
					System.out.println("'" + bookNo + "'�� ������ ������ �����Ͻðڽ��ϱ�? ( �� / �ƴϿ� )");
					String checkDeleteBook = sc.nextLine();
					if ("��".equals(checkDeleteBook)) {
						BookController.bookDelete(bookNo);

					} else if ("�ƴϿ�".equals(checkDeleteBook)) {
						System.out.println("*** ������ ����߽��ϴ�. ***\n");
						AdminMenuView.bookAdminMenu(user);
					} else {
						System.out.println("*** ( �� / �ƴϿ� ) �� �ϳ��� �Է����ּ���. ***\n");
						AdminMenuView.bookAdminMenu(user);
					}
					break;
				case 9:
					return;
				default:
					System.out.println("*** �޴��� �ִ� '��ȣ'�� �Է����ּ���. ***");
				}

			} catch (NumberFormatException e) {
				FailView.errorMessage("*** �޴��� '����'�� �Է°����մϴ�. ***");
			}
		}
	}

	/**
	 * ������ ���� �˻� �޴�
	 */
	public static void printSelectMenu(User user) {
		while (true) {
			try {
				System.out.println("\n*** ������ȸ �޴� ***");
				System.out.println(
						"----------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println(
						"   1.  ������ȣ�� �˻�   2.  ���������� �˻�   3.  ���ڸ����� �˻�   4.  ���ǻ�� �˻�   5.  �����о߷� �˻�   6.  �뿩���η� �˻�   9.  ���ư���   ");
				System.out.println(
						"----------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.print("�޴� �Է� : ");
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					System.out.println("*** ������ȣ�� �˻��մϴ�. ***");
					BookMenuView.selectBookByNo(user); // ������ȣ�� �˻�
					return;
				case 2:
					System.out.println("*** ���������� �˻��մϴ�. ***");
					BookMenuView.selectBookByName(user); // ���������� �˻�
					return;
				case 3:
					System.out.println("*** ���ڸ����� �˻��մϴ�. ***");
					BookMenuView.selectBookByWriter(user); // ���ڸ����� �˻�
					return;
				case 4:
					System.out.println("*** ���ǻ�� �˻��մϴ�. ***");
					BookMenuView.selectBookByPublisher(user); // ���ǻ�� �˻�
					return;
				case 5:
					System.out.println("*** �����о߷� �˻��մϴ�. ***");
					BookMenuView.selectBookByCategory(user); // �о߷� �˻�
					return;
				case 6:
					System.out.println("*** �뿩���η� �˻��մϴ�. ***");
					BookMenuView.selectBookByState(user); // �뿩 ���η� �˻�(0: �뿩����, 1: �뿩��, 2: ������)
					return;
				case 9:
					return;
				default:
					System.out.println("*** �޴��� �ִ� '��ȣ'�� �Է����ּ���. ***");
				
				}
			} catch (NumberFormatException e) {
				FailView.errorMessage("*** �޴��� '����'�� �Է°����մϴ�. ***");
			
			}
		}
	}

	/**
	 * UserNo �Է¹ޱ�
	 */

	public static int InputUserNo() {
		int userNo = 0;
		try {
			System.out.print("\nȸ����ȣ �Է� : ");
			userNo = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {

		} finally {
			return userNo;
		}
	}

	/**
	 * UserId �Է¹ޱ�
	 */
	public static String InputUserId() {
		System.out.print("\nȸ��ID �Է� : ");
		String userId = sc.nextLine();
		return userId;
	}

	/**
	 * bookInsert�� �ʿ��� book���� �ֱ�
	 */
	public static Book InputBook() {
		User user = new User();
		Book book = null;
		while (true) {
//	        System.out.println("������ȣ�� �ڵ������˴ϴ�.");

			System.out.print("\n�����̸� �Է� : ");
			String bookName = sc.nextLine();
			if (bookName.equals("")) {
				System.out.println("*** �����̸��� �Է��� �ʼ��Դϴ�. ***");
				continue;
			}
			System.out.print("���ڸ� �Է� : ");
			String bookWriter = sc.nextLine();
			System.out.print("���ǻ� �Է� : ");
			String bookPublisher = sc.nextLine();
			System.out.print("�����о� �Է� : ");
			String bookField = sc.nextLine();

			if (bookName.equals("") || bookWriter.equals("") || bookPublisher.equals("") || bookField.equals("")) {
				System.out.println("*** �����̸�, ���ڸ�, ���ǻ�, �����оߴ� �Է� �ʼ� ***");
				continue;
			}
			System.out.println("�� " + bookName + " �� ������ ������ ����Ͻðڽ��ϱ�? ( �� / �ƴϿ� )");
			String checkInputBook = sc.nextLine();
			if ("��".equals(checkInputBook)) {
				book = new Book(0, bookName, bookWriter, bookPublisher, bookField, 0);

			} else if ("�ƴϿ�".equals(checkInputBook)) {
				System.out.println("*** ����� ����߽��ϴ�. ***\n");
				AdminMenuView.bookAdminMenu(user);
			} else {
				System.out.println("*** ( �� / �ƴϿ� ) �� �ϳ��� �Է����ּ���. ***\n");
				AdminMenuView.bookAdminMenu(user);
			}

			break;
		}

		return book;
	}

	/**
	 * bookNo�Է¹ޱ�
	 */
	public static int InputBookNo() {

		int bookNo = 0;
		try {
			System.out.print("������ȣ �Է�  : ");
			bookNo = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {

		} finally {
			return bookNo;
		}
	}

	/**
	 * User ������ ������ �Է¹ޱ�
	 */
	private static User updateUser() {
		User updateUser = new User();
		System.out.println("\n");
		System.out.println("������ ȸ��ID�� �Է����ּ���. (������ ������ ���� �� ENTER �Է�)");
		updateUser.setUserId(sc.nextLine());
		System.out.println("������ �̸��� �Է����ּ���. (������ ������ ���� �� ENTER �Է�)");
		updateUser.setUserName(sc.nextLine());
		System.out.println("������ ��ȭ��ȣ�� �Է����ּ���. (������ ������ ���� �� ENTER �Է�)");
		updateUser.setUserPhone(sc.nextLine());

		return updateUser;
	}

	/**
	 * book������ ������ �Է¹ޱ�
	 */
	public static Book updateBook() {
		Book updateBook = new Book();
		System.out.print("\n������ �������� �Է����ּ���. (������ ������ ���� �� ENTER �Է�) : ");
		updateBook.setBookName(sc.nextLine());
		System.out.print("������ ���ڸ��� �Է����ּ���. (������ ������ ���� �� ENTER �Է�) : ");
		updateBook.setBookWriter(sc.nextLine());
		System.out.print("������ ���ǻ縦 �Է����ּ���. (������ ������ ���� �� ENTER �Է�) : ");
		updateBook.setBookPublisher(sc.nextLine());
		System.out.print("������ �����о߸� �Է����ּ���. (������ ������ ���� �� ENTER �Է�) : ");
		updateBook.setBookField(sc.nextLine());

		return updateBook;
	}

}
