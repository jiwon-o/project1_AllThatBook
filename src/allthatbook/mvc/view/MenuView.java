package allthatbook.mvc.view;


import java.util.Scanner;

import allthatbook.mvc.controller.BookController;
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
				//MenuView.register(); // ����
				break;
			case 2 :
				MenuView.login();// �α���
				break;

			case 9 : 
				System.exit(0);
			}
		}

	}
	
	
	public static void printMenu() {
		System.out.println("=== Heejung Shopping Mall ===");
		System.out.println("1. ȸ������   |   2. �α���   |  9. ����");
	}
	
	
	public static void printUserMenu(String userId) {
		while(true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet()); //Set��ü
			System.out.println("-----" +userId+ " �α��� �� -----");
			System.out.println(" 1.�α׾ƿ� |  2.��ǰ����  |  3.�ֹ��ϱ�  | 4. �ֹ���������  |  5.��ٱ��ϴ��  |  6.��ٱ��Ϻ��� ");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				case 1 :
					logout(userId);// 
					return;
					//break;
				case 2 :
					BookController.bookSelect();//��ü ��ǰ��ȸ
					break;
				case 3 :
					
					break;
				case 4 :
					break;
				case 5 :
					
					break;
				case 6 : 
					
					break;
				}
		}
		
	}
	
	public static void printSubMenu() {
		System.out.println("1. ����   |  2.Ż��   | 9. ������");
	}
	
	public static void printAdminMenu() {
		System.out.println("-- ������ �޴� --");
		System.out.println("1. ID�� �˻�   |  2.�̸����� �˻�  | 3.��ü �˻�  |  9. ������");
		
	}
	
	/**
	 * �α��� �޴�
	 * */
	public static void login() {
		 
	}
	
	/**
	 * �α׾ƿ�
	 * */
	public static void logout(String userId) {
		
	}
}



