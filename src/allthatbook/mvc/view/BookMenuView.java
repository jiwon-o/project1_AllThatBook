package allthatbook.mvc.view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import allthatbook.mvc.controller.BookController;
import allthatbook.mvc.controller.UpdateAdminController;
import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.User;

public class BookMenuView {
	private static Scanner sc = new Scanner(System.in);

	/**
	 * �˻� �޴�
	 */
	public static void printSelectMenu(User user) {
		while (true) {
			System.out.println("\n");
			System.out.println(
					"1. ������ȣ�� �˻�    2. ���������� �˻�    3. ���ڸ����� �˻�    4. ���ǻ�� �˻�     5. �����о߷� �˻�     6. �뿩���η� �˻�    9. ���ư���    ");
			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------------");
			System.out.print("���Ͻô� ������ ��ȣ�� �Է����ּ��� :  ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				Book book = BookMenuView.selectBookByNo(user); // ������ȣ�� �˻�
				if (book != null)
					CartMenuView.printCartMenu(user, book);
				break;
			case 2:
				List<Book> bookListByName = BookMenuView.selectBookByName(user); // ���������� �˻�
				if (bookListByName != null)
					CartMenuView.printCartMenu(user);
				break;
			case 3:
				List<Book> bookListByWriter = BookMenuView.selectBookByWriter(user); // ���ڸ����� �˻�
				if (bookListByWriter != null)
					CartMenuView.printCartMenu(user);
				break;
			case 4:
				List<Book> bookListByPublisher = BookMenuView.selectBookByPublisher(user); // ���ǻ�� �˻�
				if (bookListByPublisher != null)
					CartMenuView.printCartMenu(user);
				break;
			case 5:
				List<Book> bookListByCateory = BookMenuView.selectBookByCategory(user); // �о߷� �˻�
				if (bookListByCateory != null)
					CartMenuView.printCartMenu(user);
				break;
			case 6:
				List<Book> bookListByState = BookMenuView.selectBookByState(user); // �뿩 ���η� �˻�(0: �뿩����, 1: �뿩��, 2: ������)
				if (bookListByState != null)
					CartMenuView.printCartMenu(user);
				break;
			case 9:
				return;
			}
		}
	}

	/**
	 * ������ȣ�� �˻��ϱ�
	 */
	public static Book selectBookByNo(User user) {
		Book book = null;
		while (true) {
			try {
				System.out.print("������ȣ�� �Է����ּ��� : ");
				int no = Integer.parseInt(sc.nextLine());
				System.out.println("\n");
				book = BookController.bookSelectByBookNo(user, no);
				if (book == null) {

					System.out.println("�ٽ� �Ͻðڽ��ϱ�? ( �� or �ƴϿ� )");
					String choice = sc.nextLine();
					switch (choice) {
					case "��":
						continue;
					case "�ƴϿ�":
						System.out.println("ó������ ���ư��ϴ�.");
						break;
					default:
						System.out.println("�� or �ƴϿ� �� �ϳ��� �Է����ּ���... ó������ ���ư��ϴ�.");
					}

				}
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println("'����'�� �Է����ּ���. ");

			}
			return book;
		}
	}

	/**
	 * ���������� �˻��ϱ�
	 */
	public static List<Book> selectBookByName(User user) {
		List<Book> bookList = null;
		while (true) {
			try {
				System.out.print("å�� �̸��� �Է����ּ��� : ");
				String keyword = sc.nextLine();
				System.out.println("\n");
				bookList = BookController.bookSelectByBookName(user, keyword);
				if (bookList == null) {
					System.out.println("�ٽ� �Ͻðڽ��ϱ�? ( �� or �ƴϿ� )");
					String choice = sc.nextLine();
					switch (choice) {
					case "��":
						continue;
					case "�ƴϿ�":
						System.out.println("ó������ ���ư��ϴ�.");
						break;
					default:
						System.out.println("�� or �ƴϿ� �� �ϳ��� �Է����ּ���... ó������ ���ư��ϴ�.");					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bookList;
		}

	}

	/**
	 * ���ڸ����� �˻��ϱ�
	 */
	public static List<Book> selectBookByWriter(User user) {
		List<Book> bookList = null;
		while(true) {
			try {
				System.out.print("���ڸ� �Է����ּ��� : ");
				String writer = sc.nextLine();

				System.out.println("\n");
				bookList = BookController.bookSelectByWriter(user, writer);
				if (bookList == null) {

					System.out.println("�ٽ� �Ͻðڽ��ϱ�? ( �� or �ƴϿ� )");
					String choice = sc.nextLine();
					switch (choice) {
					case "��":
						continue;
					case "�ƴϿ�":
						System.out.println("ó������ ���ư��ϴ�.");
						break;
					default:
						System.out.println("�� or �ƴϿ� �� �ϳ��� �Է����ּ���... ó������ ���ư��ϴ�.");					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bookList;
		}
	}

	/**
	 * ���ǻ�� �˻��ϱ�
	 */
	public static List<Book> selectBookByPublisher(User user) {
		List<Book> bookList = null;
		while(true) {
			try {
				System.out.print("���ǻ縦 �Է����ּ��� : ");
				String publisher = sc.nextLine();

				System.out.println("\n");
				bookList = BookController.bookSelectByPublisher(user, publisher);
				
				if (bookList == null) {

					System.out.println("�ٽ� �Ͻðڽ��ϱ�? ( �� or �ƴϿ� )");
					String choice = sc.nextLine();
					switch (choice) {
					case "��":
						continue;
					case "�ƴϿ�":
						System.out.println("ó������ ���ư��ϴ�.");
						break;
					default:
						System.out.println("�� or �ƴϿ� �� �ϳ��� �Է����ּ���... ó������ ���ư��ϴ�.");					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return bookList;
		}
		
	}

	/**
	 * �����о߷� �˻��ϱ�
	 */
	public static List<Book> selectBookByCategory(User user) {
		List<Book> bookList = null;
		while(true) {
			try {
				System.out.print("ã���ô� �о߸� �Է����ּ��� : ");
				String category = sc.nextLine();

				System.out.println("\n");
				bookList = BookController.bookSelectByCategory(user, category);
				
				if (bookList == null) {

					System.out.println("�ٽ� �Ͻðڽ��ϱ�? ( �� or �ƴϿ� )");
					String choice = sc.nextLine();
					switch (choice) {
					case "��":
						continue;
					case "�ƴϿ�":
						System.out.println("ó������ ���ư��ϴ�.");
						break;
					default:
						System.out.println("�� or �ƴϿ� �� �ϳ��� �Է����ּ���... ó������ ���ư��ϴ�.");					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bookList;
		}
		
	}

	/**
	 * �뿩 ���·� �˻��ϱ�(0�̸� �뿩����, 1�̸� �뿩 ��, 2�̸� ���� ��)
	 */
	public static List<Book> selectBookByState(User user) {
		List<Book> bookList = null;
		while(true) {
			try {
				System.out.print("�뿩 ���� �˻� (���Ⱑ��: 0, ���� ��: 1, ���� ��: 2)\n > ");
				int state = Integer.parseInt(sc.nextLine());

				bookList = BookController.bookSelectByState(user, state);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bookList;
		}
		
	}

	////////////////////////////// ������////////////////////////
	/**
	 * ������ ������ȸ(������ȣ) -> ���� or ����
	 */
	public static void bookDeleteOrUpdateMenu(User user, Book book) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			int result = 0;

			System.out.println("1. ���õ��� ����    2. ���õ��� ����    3. ���ư���    9. ���θ޴��� ����  ");
			System.out.println("-------------------------------------------------------------");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				Book updatebook = AdminMenuView.updateBook();
				result = UpdateAdminController.bookUpdate(book.getBookNo(), updatebook);
				if (result == 1)
					System.out.println(book.getBookNo() + "�� �ش� å�� �����Ǿ����ϴ�.");
				break;
			case 2:
				BookController.bookDelete(book.getBookNo());
				break;
			case 3:
				return;
			}
		}
	}

	/**
	 * ������ ������ȸ(������ ���ڸ� ���ǻ� �о�) -> ���� or ����
	 */
	public static void bookDeleteOrUpdateListMenu(User user) {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			System.out.println("1. ���õ��� ����    2. ���õ��� ����    3. ���ư���    9. ���θ޴��� ���� ");
			System.out.println("------------------------------------------------------------");
			int menu = Integer.parseInt(sc.nextLine());
			int bookNo = 0;
			int result = 0;
			switch (menu) {
			case 1:
				bookNo = AdminMenuView.InputBookNo();
				Book updatebook = AdminMenuView.updateBook();
				result = UpdateAdminController.bookUpdate(bookNo, updatebook);
				if (result == 1)
					System.out.println(bookNo + "�� �ش� å�� �����Ǿ����ϴ�.");
				break;
			case 2:
				bookNo = AdminMenuView.InputBookNo();
				BookController.bookDelete(bookNo);
				break;
			case 3:
				return;
			}

		}
	}
}
