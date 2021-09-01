package allthatbook.mvc.view;

import java.util.Scanner;

import allthatbook.mvc.controller.CartController;
import allthatbook.mvc.controller.RentalController;
import allthatbook.mvc.controller.ReservationContoller;
import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.Cart;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.session.Session;
import allthatbook.mvc.session.SessionSet;

public class CartMenuView {
	private static Scanner sc = new Scanner(System.in);
	
	
	/**
	 * ��ٱ��� ���(å����, ���ǻ�.. ������ �˻����� ��)
	 */
	public static void putCart(String userId) {
		try {
			System.out.print("������ȣ : ");
			int bookNo = Integer.parseInt(sc.nextLine());
			
			CartController.putCart(userId, bookNo);
			
		}catch (NumberFormatException e) {
			FailView.errorMessage("*** ������ȣ�� ���ڸ� �Է����ּ���. ***");
		}
		

		
		
	}
	
	/**
	 * ��ٱ��� ���(å��ȣ�� �˻����� ��)
	 */
	public static void putCart(User user, Book book) {
		try {
			System.out.println("'" +book.getBookNo()+"'�� ������ ��ٱ��Ͽ� �����ðڽ��ϱ�? (�� / �ƴϿ�)");
			String checkCart = sc.nextLine();
			if("��".equals(checkCart)) {
				CartController.putCart(user.getUserId(), book.getBookNo());
			}else if("�ƴϿ�".equals(checkCart)) {
				System.out.println("*** ��ٱ��� ��⸦ ����߽��ϴ�. ***\n");
				CartMenuView.printCartMenu(user, book);
			}else {
				System.out.println("*** ( �� / �ƴϿ� ) �� �ϳ��� �Է����ּ���. ***\n");
				CartMenuView.printCartMenu(user, book);
			}
			
			
		}catch (Exception e) {
			//e.printStackTrace();
		}
		
	}
    
    

	
	/**
	 * ��ٱ��� �޴�(å��ȣ�� �˻����� ��)
	 */
	public static void printCartMenu(User user, Book book) {
		while(true) {
			try {
				System.out.println("\n*** �˻� ��� �޴� ***");
				System.out.println("-----------------------------------------------------------------------------------------------------------------");
				System.out.println("       1.   �����ϱ�      2.   �����ϱ�      3.   ��ٱ��� ���      4.   ��ٱ��� ����      5.   ���ư���       ");
				System.out.println("-----------------------------------------------------------------------------------------------------------------");
				System.out.print("�޴� �Է� : ");

				switch(Integer.parseInt(sc.nextLine())) {
				case 1:
					System.out.println("*** �����ϱ� �޴��� �����߽��ϴ�. ***\n");
					System.out.println(book.getBookNo() + ". �� " + book.getBookName() + " �� ������ �����Ͻðڽ��ϱ�? ( �� / �ƴϿ� )");
					String checkRental = sc.nextLine();
					if("��".equals(checkRental)) {
						RentalController.insertRental(user, book);
					}else if("�ƴϿ�".equals(checkRental)) {
						System.out.println("*** ������ ����߽��ϴ�. ***\n");
						CartMenuView.printCartMenu(user, book);
					}else {
						System.out.println("*** ( �� / �ƴϿ� ) �� �ϳ��� �Է����ּ���. ***\n");
						CartMenuView.printCartMenu(user, book);
					}
					
					return;
				case 2:
					System.out.println(book.getBookNo() + ". �� " + book.getBookName() + " �� ������ �����Ͻðڽ��ϱ�? ( �� / �ƴϿ� )");
					String checkReserve = sc.nextLine();
					if("��".equals(checkReserve)) {
						ReservationContoller.insertReservation(user, book);
					}else if("�ƴϿ�".equals(checkReserve)) {
						System.out.println("*** ������ ����߽��ϴ�. ***\n");
						CartMenuView.printCartMenu(user, book);
					}else {
						System.out.println("*** ( �� / �ƴϿ� ) �� �ϳ��� �Է����ּ���. ***\n");
						CartMenuView.printCartMenu(user, book);
					}
					
					return;
					
				case 3:
					System.out.println("*** ��ٱ��� ��� �޴��� �����߽��ϴ�. ***\n");
					CartMenuView.putCart(user, book);
					break;
				case 4:
					CartMenuView.viewCart(user);
					
					break;
				case 5:
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
	 * ��ٱ��� �޴�(å����, ���ǻ�.. ������ �˻����� ��)
	 * @param user
	 */
	public static void printCartMenu(User user) {
		while(true) {
			try {
				System.out.println("\n*** �˻� ��� �޴� ***");
				System.out.println("-----------------------------------------------------------------------------------------------------------------");
				System.out.println("       1.   �����ϱ�      2.   �����ϱ�      3.   ��ٱ��� ���      4.   ��ٱ��� ����      5.   ���ư���       ");
				System.out.println("-----------------------------------------------------------------------------------------------------------------");
				System.out.print("�޴� �Է� : ");
				switch(Integer.parseInt(sc.nextLine())) {
				case 1:
					System.out.println("*** �����ϱ� �޴��� �����߽��ϴ�. ***\n");
					System.out.print("������ȣ �Է� : ");  
					int rentalBookNo = Integer.parseInt(sc.nextLine());
					RentalController.insertRental(user, rentalBookNo);
					break;
				case 2:
					System.out.println("*** �����ϱ� �޴��� �����߽��ϴ�. ***\n");
					System.out.print("������ȣ �Է� : ");
					int reserveBookNo = Integer.parseInt(sc.nextLine());
					ReservationContoller.insertReservation(user, reserveBookNo);
					break;

				case 3:
					System.out.println("*** ��ٱ��� ��� �޴��� �����߽��ϴ�. ***\n");
					CartMenuView.putCart(user.getUserId());
					break;
				case 4:
					System.out.println("*** ��ٱ��� ��� ȭ������ �̵��մϴ�. ***\n");
					CartMenuView.viewCart(user);
					
					break;
				case 5:
					return;
				default :
					System.out.println("*** �޴��� �ִ� ��ȣ�� �Է����ּ���. ***");
				}
				
			}catch (NumberFormatException e) {
				System.out.println("*** �޴��� '����'�� �Է����ּ���. ***");
			}
			
		}
	}
	
	/**
     * ��ٱ��� ����
     * */
	public static void viewCart(User user) {
		Cart cart = CartController.viewCart(user);
		printRentalMenu(user, cart);
	}
	
	
	public static void printRentalMenu(User user, Cart cart) {
		while(true) {
			try {
				System.out.println("\n*** ��ٱ��� ��� �޴� ***");
				System.out.println("-----------------------------------------------------------------------------------------------------------------");
				System.out.println("       1.   ��ü ��� �����ϱ�      2.   ��ٱ��� ��� ����      3.   ��ٱ��� ����      4.   ���ư���       ");
				System.out.println("-----------------------------------------------------------------------------------------------------------------");
				System.out.print("�޴� �Է� :  ");
				switch(Integer.parseInt(sc.nextLine())) {
				case 1:
					System.out.println("*** ��ٱ����� ��������� �뿩�մϴ�. ***\n");
					CartController.rentalCartBook(user.getUserId(), cart);
					CartMenuView.viewCart(user);
					return;
				case 2:
					System.out.println("*** ��ٱ��� ����� �����մϴ�. ***\n");
					System.out.print("������ȣ �Է� : ");
					int bookNo = Integer.parseInt(sc.nextLine());
					CartController.removeCartDetail(user.getUserId(), bookNo);
					CartMenuView.viewCart(user);
					return;
				case 3:
					System.out.println("*** ��ٱ��� ����� ��� �����մϴ�. ***\n");
					UserMenuView.clearCart(user);
					CartMenuView.viewCart(user);
					return;
					
				case 4:
					return;
				default :
					System.out.println("*** �޴��� �ִ� ��ȣ�� �Է����ּ���. ***");
				}
			}catch (NumberFormatException e) {
				System.out.println("*** �޴��� '����'�� �Է����ּ���. ***");
			}
			
		}
	}
	
}
