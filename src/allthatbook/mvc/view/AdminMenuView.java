package allthatbook.mvc.view;

import java.util.Scanner;

import allthatbook.mvc.controller.BookController;

import allthatbook.mvc.controller.UpdateAdminController;
import allthatbook.mvc.controller.UserController;
import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.User;


public class AdminMenuView {
	private static Scanner sc = new Scanner(System.in);
	/**
	 * ȸ������ �޴�
	 */
	public static void userAdminMenu() {
		while(true) {
			System.out.println("---������ ȸ�� ����---");
			System.out.println("1. ��üȸ�� ��ȸ | 2. ȸ����ȣ�� ��ȸ | 3. ȸ��ID�� ��ȸ | 4.ȸ���������� | 5.ȸ����������| 9. ������");
			int menu=Integer.parseInt(sc.nextLine());
			int result=0;
			int userNo=0;
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
				User updateuser = updateUser();
				result = UpdateAdminController.userUpdate(userNo, updateuser);
				if(result==1)System.out.println(userNo+"�� ȸ���� �����Ǿ����ϴ�.");
				break;
			case 5 : 
				userNo=InputUserNo();
				UserController.deleteAdminUserInfo(userNo);
				break;
			case 9 :  			
				return;
			}
		}
		
	}

	/**
	 * �������� �޴�
	 */
	public static void bookAdminMenu(User user) {
		while(true) {
			System.out.println("---������ ���� ����---");
			System.out.println("1. �� ������� | 2. ������������ | 3. �������� | 4. ������ȸ | 5. �����ѵ��� ��ȸ | 6. �����ѵ��� ��ȸ | 9. ������");
			int menu=Integer.parseInt(sc.nextLine());
			int bookNo=0;
			int result=0;
			Book book=null;
			switch(menu) {
				case 1 :
					book=InputBook();
					result = BookController.bookInsert(book);
					if(result==1) System.out.println(book.getBookNo()+"��ϵǾ����ϴ�.");
					break;
				case 2 : //������������
					bookNo = InputBookNo();
					Book updatebook = updateBook();
					result = UpdateAdminController.bookUpdate(bookNo, updatebook);
					if(result==1)System.out.println(bookNo+"�� �ش� å�� �����Ǿ����ϴ�.");
					break;
				case 3 : 
					bookNo = InputBookNo();
					result = BookController.bookDelete(bookNo);	
					if(result==1)System.out.println(bookNo+"��ȣ�� �����Ǿ����ϴ�.");
					break;
				case 4 : //������ȸ --- ��ٱ��� ���� ����, ������ ����ǰ� 
					MenuView.printSelectMenu(user.getUserId());
					break;
				case 5 : //�����ѵ��� : ���°� 1�ε��� ��ȸ 
					BookController.bookRentalSelect();
					break;
				case 6 : //���൵�� ��ȸ : ���°� 2�ε��� ��ȸ
					BookController.bookReserveSelect();
					break;
				case 9 :
					return;
			}
		}
	}	

	/**
	 * ����Ͻðڽ��ϱ�?
	 */
	
	/**
	 * UserNo �Է¹ޱ� 
	 */
	public static int InputUserNo() {
		System.out.println("userNo �Է� > ");
		int userNo = Integer.parseInt(sc.nextLine());
		return userNo;
	}
	
	
	/**
	 * UserId �Է¹ޱ� 
	 */
	public static String InputUserId() {
		System.out.print("userId �Է� > ");
		String userId = sc.nextLine();
		return userId;
	}

	/**
	 *	bookInsert�� �ʿ��� book���� �ֱ� 
	 */
	public static Book InputBook() {
		Book book = new Book();
		System.out.println("bookNo�� �ڵ������˴ϴ�.");
		
		System.out.print("bookName �Է� > ");
		book.setBookName(sc.nextLine());
		System.out.print("bookWriter �Է� > ");
		book.setBookWriter(sc.nextLine());
		System.out.print("bookPublisher �Է� > ");
		book.setBookPublisher(sc.nextLine());
		System.out.println("�Ⱓ���� ���� ��¥�� ���ϴ�.");

		System.out.print("bookField �Է� > ");
		book.setBookField(sc.nextLine());
		System.out.println("bookState �⺻ ���Ⱑ��0���� ���ϴ�.");

		return book;
	}
	
	/**
	 * bookNo�Է¹ޱ� 
	 */
	public static int InputBookNo() {
		System.out.print("bookNo �Է� > ");
		int bookNo = Integer.parseInt(sc.nextLine());
		return bookNo;
	}
	

	/**
	 * User ������ ������ �Է¹ޱ� 
	 */
	private static User updateUser() {
		User updateUser = new User();
		System.out.println("������ userId �Է�(������ ������ ���� �� enter�� �����ּ���) > ");
		updateUser.setUserId(sc.nextLine());
		System.out.println("������ userPwd �Է�(������ ������ ���� �� enter�� �����ּ���) > ");
		updateUser.setUserPwd(sc.nextLine());
		System.out.println("������ userName �Է�(������ ������ ���� �� enter�� �����ּ���) > ");
		updateUser.setUserName(sc.nextLine());
		System.out.println("������ userPhone �Է�(������ ������ ���� �� enter�� �����ּ���) > ");
		updateUser.setUserPhone(sc.nextLine());

		return updateUser;
	}
	
	/**
	 * book������ ������ �Է¹ޱ� 
	 */
	public static Book updateBook() {
		Book updateBook = new Book();		
		System.out.print("������ bookName �Է�(������ ������ ���� �� enter�� �����ּ���) > ");
		updateBook.setBookName(sc.nextLine());
		System.out.print("������ bookWriter �Է�(������ ������ ���� �� enter�� �����ּ���) > ");
		updateBook.setBookWriter(sc.nextLine());
		System.out.print("������ bookPublisher �Է�(������ ������ ���� �� enter�� �����ּ���) > ");
		updateBook.setBookPublisher(sc.nextLine());
		System.out.print("������ bookField �Է� >(������ ������ ���� �� enter�� �����ּ���) ");
		updateBook.setBookField(sc.nextLine());
		
		return updateBook;
	}
	

}
