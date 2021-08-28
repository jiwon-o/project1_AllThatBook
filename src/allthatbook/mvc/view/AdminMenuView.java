package allthatbook.mvc.view;

import java.util.Scanner;

import allthatbook.mvc.controller.UserController;

public class AdminMenuView {
	private static Scanner sc = new Scanner(System.in);
	/**
	 * ȸ������ �޴�
	 */
	public static void userAdminMenu() {
		System.out.println("---������ ȸ�� ����---");
		System.out.println("1. ��üȸ�� ��ȸ | 2. ȸ����ȣ�� ��ȸ | 3. ȸ��ID�� ��ȸ | 4.ȸ���������� | 5.ȸ����������| 9. ������");
		int menu=Integer.parseInt(sc.nextLine());
		switch(menu) {
		case 1 :
			UserController.userSelect();
			break;
		case 2 : 
			int userNo=InputUserNo();
			UserController.selectByUserNo(userNo);
			break;
		case 3 :
			String userId=InputUserId();
			UserController.selectByUserId(userId);
			break;
		case 4 : 
			userNo=InputUserNo();
			UserController.updateAdminUserInfo(userNo);
			//ȸ����ȣ�� �޾� �ش� ȸ����ȣ ���� ����
			
			break;
		case 5 : 
			userNo=InputUserNo();
			//ȸ����ȣ�� �޾� �ش�ȸ������ ���� 
			break;
		case 9 :  			
			return;
		}
	}
	/**
	 * �������� �޴�
	 */
	public static void bookAdminMenu() {
		System.out.println("---������ ���� ����---");
		System.out.println("1. ������� | 2. ������������ | 3. �������� | 4. ������ȸ | 5. �����ѵ��� ��ȸ | 6. �����ѵ��� ��ȸ | 9. ������");
		int menu=Integer.parseInt(sc.nextLine());
		switch(menu) {
		case 1 :
				
			break;
		case 2 :

			break;
		case 3 : 
			break;
		case 4 : 
			//BookController
			break;
		case 5 : 
			break;
		case 6 : 
			break;
		case 9 :
			return;
		}
	}
	
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
		System.out.println("userId �Է� > ");
		String userId = sc.nextLine();
		return userId;
	}
	
	
}
