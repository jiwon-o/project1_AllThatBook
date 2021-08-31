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
	 * 검색 메뉴
	 */
	public static void printSelectMenu(User user) {
		while (true) {
			System.out.println("\n");
			System.out.println(
					"1. 도서번호로 검색    2. 도서명으로 검색    3. 저자명으로 검색    4. 출판사로 검색     5. 도서분야로 검색     6. 대여여부로 검색    9. 돌아가기    ");
			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------------");
			System.out.print("원하시는 서비스의 번호를 입력해주세요 :  ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				Book book = BookMenuView.selectBookByNo(user); // 도서번호로 검색
				if (book != null)
					CartMenuView.printCartMenu(user, book);
				break;
			case 2:
				List<Book> bookListByName = BookMenuView.selectBookByName(user); // 도서명으로 검색
				if (bookListByName != null)
					CartMenuView.printCartMenu(user);
				break;
			case 3:
				List<Book> bookListByWriter = BookMenuView.selectBookByWriter(user); // 저자명으로 검색
				if (bookListByWriter != null)
					CartMenuView.printCartMenu(user);
				break;
			case 4:
				List<Book> bookListByPublisher = BookMenuView.selectBookByPublisher(user); // 출판사로 검색
				if (bookListByPublisher != null)
					CartMenuView.printCartMenu(user);
				break;
			case 5:
				List<Book> bookListByCateory = BookMenuView.selectBookByCategory(user); // 분야로 검색
				if (bookListByCateory != null)
					CartMenuView.printCartMenu(user);
				break;
			case 6:
				List<Book> bookListByState = BookMenuView.selectBookByState(user); // 대여 여부로 검색(0: 대여가능, 1: 대여중, 2: 예약중)
				if (bookListByState != null)
					CartMenuView.printCartMenu(user);
				break;
			case 9:
				return;
			}
		}
	}

	/**
	 * 도서번호로 검색하기
	 */
	public static Book selectBookByNo(User user) {
		Book book = null;
		while (true) {
			try {
				System.out.print("도서번호를 입력해주세요 : ");
				int no = Integer.parseInt(sc.nextLine());
				System.out.println("\n");
				book = BookController.bookSelectByBookNo(user, no);
				if (book == null) {

					System.out.println("다시 하시겠습니까? ( 네 or 아니오 )");
					String choice = sc.nextLine();
					switch (choice) {
					case "네":
						continue;
					case "아니오":
						System.out.println("처음으로 돌아갑니다.");
						break;
					default:
						System.out.println("네 or 아니오 중 하나를 입력해주세요... 처음으로 돌아갑니다.");
					}

				}
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println("'숫자'만 입력해주세요. ");

			}
			return book;
		}
	}

	/**
	 * 도서명으로 검색하기
	 */
	public static List<Book> selectBookByName(User user) {
		List<Book> bookList = null;
		while (true) {
			try {
				System.out.print("책의 이름을 입력해주세요 : ");
				String keyword = sc.nextLine();
				System.out.println("\n");
				bookList = BookController.bookSelectByBookName(user, keyword);
				if (bookList == null) {
					System.out.println("다시 하시겠습니까? ( 네 or 아니오 )");
					String choice = sc.nextLine();
					switch (choice) {
					case "네":
						continue;
					case "아니오":
						System.out.println("처음으로 돌아갑니다.");
						break;
					default:
						System.out.println("네 or 아니오 중 하나를 입력해주세요... 처음으로 돌아갑니다.");					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bookList;
		}

	}

	/**
	 * 저자명으로 검색하기
	 */
	public static List<Book> selectBookByWriter(User user) {
		List<Book> bookList = null;
		while(true) {
			try {
				System.out.print("저자를 입력해주세요 : ");
				String writer = sc.nextLine();

				System.out.println("\n");
				bookList = BookController.bookSelectByWriter(user, writer);
				if (bookList == null) {

					System.out.println("다시 하시겠습니까? ( 네 or 아니오 )");
					String choice = sc.nextLine();
					switch (choice) {
					case "네":
						continue;
					case "아니오":
						System.out.println("처음으로 돌아갑니다.");
						break;
					default:
						System.out.println("네 or 아니오 중 하나를 입력해주세요... 처음으로 돌아갑니다.");					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bookList;
		}
	}

	/**
	 * 출판사로 검색하기
	 */
	public static List<Book> selectBookByPublisher(User user) {
		List<Book> bookList = null;
		while(true) {
			try {
				System.out.print("출판사를 입력해주세요 : ");
				String publisher = sc.nextLine();

				System.out.println("\n");
				bookList = BookController.bookSelectByPublisher(user, publisher);
				
				if (bookList == null) {

					System.out.println("다시 하시겠습니까? ( 네 or 아니오 )");
					String choice = sc.nextLine();
					switch (choice) {
					case "네":
						continue;
					case "아니오":
						System.out.println("처음으로 돌아갑니다.");
						break;
					default:
						System.out.println("네 or 아니오 중 하나를 입력해주세요... 처음으로 돌아갑니다.");					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return bookList;
		}
		
	}

	/**
	 * 도서분야로 검색하기
	 */
	public static List<Book> selectBookByCategory(User user) {
		List<Book> bookList = null;
		while(true) {
			try {
				System.out.print("찾으시는 분야를 입력해주세요 : ");
				String category = sc.nextLine();

				System.out.println("\n");
				bookList = BookController.bookSelectByCategory(user, category);
				
				if (bookList == null) {

					System.out.println("다시 하시겠습니까? ( 네 or 아니오 )");
					String choice = sc.nextLine();
					switch (choice) {
					case "네":
						continue;
					case "아니오":
						System.out.println("처음으로 돌아갑니다.");
						break;
					default:
						System.out.println("네 or 아니오 중 하나를 입력해주세요... 처음으로 돌아갑니다.");					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bookList;
		}
		
	}

	/**
	 * 대여 상태로 검색하기(0이면 대여가능, 1이면 대여 중, 2이면 예약 중)
	 */
	public static List<Book> selectBookByState(User user) {
		List<Book> bookList = null;
		while(true) {
			try {
				System.out.print("대여 여부 검색 (대출가능: 0, 대출 중: 1, 예약 중: 2)\n > ");
				int state = Integer.parseInt(sc.nextLine());

				bookList = BookController.bookSelectByState(user, state);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bookList;
		}
		
	}

	////////////////////////////// 관리자////////////////////////
	/**
	 * 관리자 도서조회(도서번호) -> 삭제 or 수정
	 */
	public static void bookDeleteOrUpdateMenu(User user, Book book) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			int result = 0;

			System.out.println("1. 선택도서 수정    2. 선택도서 삭제    3. 돌아가기    9. 메인메뉴로 가기  ");
			System.out.println("-------------------------------------------------------------");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				Book updatebook = AdminMenuView.updateBook();
				result = UpdateAdminController.bookUpdate(book.getBookNo(), updatebook);
				if (result == 1)
					System.out.println(book.getBookNo() + "번 해당 책이 수정되었습니다.");
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
	 * 관리자 도서조회(도서명 저자명 출판사 분야) -> 삭제 or 수정
	 */
	public static void bookDeleteOrUpdateListMenu(User user) {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			System.out.println("1. 선택도서 수정    2. 선택도서 삭제    3. 돌아가기    9. 메인메뉴로 가기 ");
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
					System.out.println(bookNo + "번 해당 책이 수정되었습니다.");
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
