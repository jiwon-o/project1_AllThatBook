package allthatbook.mvc.view;

import java.util.List;
import java.util.Scanner;
import allthatbook.mvc.controller.BookController;
import allthatbook.mvc.controller.UpdateAdminController;
import allthatbook.mvc.controller.UserController;
import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.User;


public class AdminMenuView {
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * �����ڸ޴�
	 */
	public static void printAdminMenu(User user) {
		while(true) {
			try {

				System.out.println("                         ������ �޴�                         ");
				System.out.println("\n");
				System.out.println("    1. ȸ������           2. ��������           9. �α׾ƿ�     ");
				System.out.println("---------------------------------------------------------------------");
				
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					AdminMenuView.userAdminMenu();
					break;
				case 2:
					AdminMenuView.bookAdminMenu(user);
					break;
				case 3:
					break;
				case 9:
					UserMenuView.logout(user.getUserId());
					return;
				default:
					System.out.println("�޴��� �ִ� '��ȣ'�� �Է����ּ���. ");
				}
			} catch (NumberFormatException e) {
				FailView.errorMessage("�޴��� '����'�� �Է°����մϴ�. ");
			}

		}
	}
	
	/**
	 * ȸ������ �޴�
	 * @throws Exception 
	 */
	public static void userAdminMenu() {
		while(true) {
			try {	
				System.out.println("                               ������ ȸ�� ����                                       ");
				System.out.println("1. ��üȸ�� ��ȸ  2. ȸ����ȣ�� ��ȸ  3. ȸ��ID�� ��ȸ   4.ȸ����������   5.ȸ����������    9. ���ư���  ");
				System.out.println("------------------------------------------------------------------------------------------------");

				int menu=Integer.parseInt(sc.nextLine());
				int result=0;
				int userNo;
				User user;
				switch(menu) {
				case 1 :
					UserController.userSelect();
					break;
				case 2 : 
					userNo=InputUserNo();
					UserController.selectByUserNo(userNo);
					break;
				case 3 :
					String userId=InputUserId();
					UserController.selectByUserId(userId);
					break;
				case 4 : //ȸ���������� 
					userNo = InputUserNo();
					user = UserController.selectByUserNo(userNo);
					if(user==null) {
						break;
					}
					User updateuser = updateUser();
					result = UpdateAdminController.userUpdate(userNo, updateuser);
					if(result==1)System.out.println(userNo+"�� ȸ���� �����Ǿ����ϴ�. ");
					break;
				case 5 : 
					userNo = InputUserNo();
					user = UserController.selectByUserNo(userNo);
					if(user==null) {
						break;
					}
					UserController.deleteAdminUserInfo(userNo);
					break;
				case 9 :  			
					return;
				default : 
					System.out.println("�޴��� �ִ� '��ȣ'�� �Է����ּ���. ");
				}
			} catch (NumberFormatException e) {
				FailView.errorMessage("�޴��� '����'�� �Է°����մϴ�. ");
			}
		}
		
	}

	/**
	 * �������� �޴�
	 */
	public static void bookAdminMenu(User user) {
		while(true) {
			try {
				System.out.println("                                        ������ ���� ����                                            ");
				System.out.println("1. �� �������          2. ������������          3. ��������           4. ������ȸ            9. ������    ");
				System.out.println("------------------------------------------------------------------------------------------------------------");

				int menu=Integer.parseInt(sc.nextLine());
				int bookNo=0;
				int result=0;
				Book book=null;
				switch(menu) {
					case 1 :
						book=InputBook();
						BookController.bookInsert(book);


						break;
					case 2 : //������������
						bookNo = InputBookNo();
						book = BookController.bookSelectByBookNo(user, bookNo);
						if(book==null) {
							break;
						}
						Book updatebook = updateBook();
						result = UpdateAdminController.bookUpdate(bookNo, updatebook);
						if(result==1)System.out.println(bookNo+"�� �ش� ������ �����Ǿ����ϴ�. ");
						break;
					case 3 : 
						bookNo = InputBookNo();
						BookController.bookDelete(bookNo);	
						break;
					case 4 : 
						printSelectMenu(user);
						break;
					case 9 :
						return;
					default:
						System.out.println("�޴��� �ִ� '��ȣ'�� �Է����ּ���. ");
				}
				
			} catch (NumberFormatException e) {
				FailView.errorMessage("�޴��� '����'�� �Է°����մϴ�. ");
			}
		}
	}	
	
	/**
	 * ������ ���� �˻� �޴�
	 */
	public static void printSelectMenu(User user) {
		while(true) {
			System.out.println("\n");
			System.out.println("1. ������ȣ�� �˻�    2. ���������� �˻�    3. ���ڸ����� �˻�    4. ���ǻ�� �˻�     5. �����о߷� �˻�     6. �뿩���η� �˻�    9. ���ư���    ");
			System.out.println("-----------------------------------------------------------------------------------------------------------------------");
			System.out.print("���Ͻô� ������ ��ȣ�� �Է����ּ��� :  ");
			int menu =Integer.parseInt(sc.nextLine());
			switch(menu) {
			case 1 :
				Book book =  BookMenuView.selectBookByNo(user); //������ȣ�� �˻�
				return;
			case 2 :
				List<Book> bookListByName = BookMenuView.selectBookByName(user); //���������� �˻�
				return;
			case 3 :
				List<Book> bookListByWriter = BookMenuView.selectBookByWriter(user); //���ڸ����� �˻�
				return;
			case 4 :
				List<Book> bookListByPublisher = BookMenuView.selectBookByPublisher(user); //���ǻ�� �˻�
				return;
			case 5 :
				List<Book> bookListByCateory = BookMenuView.selectBookByCategory(user); //�о߷� �˻�
				return;
			case 6 : 
				List<Book> bookListByState = BookMenuView.selectBookByState(user); //�뿩 ���η� �˻�(0: �뿩����, 1: �뿩��, 2: ������)
				return;
			case 9 :
				return;
			}
		}
	}

	/**
	 * UserNo �Է¹ޱ� 
	 */

	public static int InputUserNo(){
		int userNo=0;
		try {
			System.out.print("userNo �Է� :  ");
			userNo = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			
		}finally {
			return userNo;
		}
	}

	/**
	 * UserId �Է¹ޱ� 
	 */
	public static String InputUserId() {
		System.out.print("UserId �Է� : ");
		String userId = sc.nextLine();
		return userId;
	}

	/**
	 *	bookInsert�� �ʿ��� book���� �ֱ� 
	 */	
	public static Book InputBook() {
		Book book = null;
		while(true) {
	        System.out.println("������ȣ�� �ڵ������˴ϴ�.");
	        
	        System.out.print("�����̸� �Է� : ");
	        String bookName = sc.nextLine();
	        if(bookName.equals("")) {
	        	System.out.println("�����̸��� �Է��� �ʼ��Դϴ�. ");
	        	continue;
	        }
	        System.out.print("���ڸ� �Է� : ");
	        String bookWriter = sc.nextLine();
	        System.out.print("���ǻ� �Է� : ");
	        String bookPublisher = sc.nextLine();
	        System.out.print("�����о� �Է� : ");
	        String bookField = sc.nextLine();
	        System.out.println("�����뿩�� �⺻ ���Ⱑ�� '0' ���� ���ϴ�.");
	        if(bookName.equals("")||bookWriter.equals("")||bookPublisher.equals("")||bookField.equals("")) {
	              System.out.println("<�����̸�, ���ڸ�, ���ǻ�, �����оߴ� �Է� �ʼ�>");
	              continue;
	           }
	        book = new Book(0, bookName, bookWriter, bookPublisher, bookField, 0);
	        break;
		}


		return book;
	}

	
	/**
	 * bookNo�Է¹ޱ� 
	 */
	public static int InputBookNo() {

		int bookNo=0;
		try {
			System.out.print("������ȣ �Է�  : ");
			bookNo = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			
		}finally {
			return bookNo;
		}	
	}
	
	/**
	 * User ������ ������ �Է¹ޱ� 
	 */
	private static User updateUser() {
		User updateUser = new User();
		System.out.println("������ UserID �Է� (������ ������ ���� �� ENTER �� �����ּ���) : ");
		updateUser.setUserId(sc.nextLine());
		System.out.println("������ UserPASSWORD �Է� (������ ������ ���� �� ENTER �� �����ּ���) : ");
		updateUser.setUserPwd(sc.nextLine());
		System.out.println("������ UserNAME �Է� (������ ������ ���� �� ENTER �� �����ּ���) : ");
		updateUser.setUserName(sc.nextLine());
		System.out.println("������ UserPHONE �Է� (������ ������ ���� �� ENTER �� �����ּ���) : ");
		updateUser.setUserPhone(sc.nextLine()); 

		return updateUser;
	}
	
	/**
	 * book������ ������ �Է¹ޱ� 
	 */
	public static Book updateBook() {
		Book updateBook = new Book();		
		System.out.print("������ �����̸� �Է� (������ ������ ���� �� ENTER �� �����ּ���) : ");
		updateBook.setBookName(sc.nextLine());
		System.out.print("������ ���ڸ� �Է� (������ ������ ���� �� ENTER �� �����ּ���) : ");
		updateBook.setBookWriter(sc.nextLine());
		System.out.print("������ ���ǻ� �Է� (������ ������ ���� �� ENTER �� �����ּ���) : ");
		updateBook.setBookPublisher(sc.nextLine());
		System.out.print("������ �����о� �Է� (������ ������ ���� �� ENTER �� �����ּ���) : ");
		updateBook.setBookField(sc.nextLine());
		
		return updateBook;
	}
	

}
