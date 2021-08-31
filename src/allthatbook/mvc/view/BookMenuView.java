package allthatbook.mvc.view;

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
			System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("      1.   도서번호로 검색      2.   도서명으로 검색      3.   저자명으로 검색      4.   출판사로 검색      5.   도서분야로 검색      6.   대출여부로 검색      7.   돌아가기     ");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.print("메뉴 입력 : ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				System.out.println("*** 도서번호로 검색합니다. ***\n");
				Book book = BookMenuView.selectBookByNo(user); // 도서번호로 검색
				if (book != null)
					CartMenuView.printCartMenu(user, book);
				break;
			case 2:
				System.out.println("*** 도서명으로 검색합니다. ***\n");
				List<Book> bookListByName = BookMenuView.selectBookByName(user); // 도서명으로 검색
				if (bookListByName != null)
					CartMenuView.printCartMenu(user);
				break;
			case 3:
				System.out.println("*** 저자명으로 검색합니다. ***\n");
				List<Book> bookListByWriter = BookMenuView.selectBookByWriter(user); // 저자명으로 검색
				if (bookListByWriter != null)
					CartMenuView.printCartMenu(user);
				break;
			case 4:
				System.out.println("*** 출판사로 검색합니다. ***\n");
				List<Book> bookListByPublisher = BookMenuView.selectBookByPublisher(user); // 출판사로 검색
				if (bookListByPublisher != null)
					CartMenuView.printCartMenu(user);
				break;
			case 5:
				System.out.println("*** 도서분야로 검색합니다. ***\n");
				List<Book> bookListByCateory = BookMenuView.selectBookByCategory(user); // 분야로 검색
				if (bookListByCateory != null)
					CartMenuView.printCartMenu(user);
				break;
			case 6:
				System.out.println("*** 대출여부로 검색합니다. ***\n");
				List<Book> bookListByState = BookMenuView.selectBookByState(user); // 대여 여부로 검색(0: 대여가능, 1: 대여중, 2: 예약중)
				if (bookListByState != null)
					CartMenuView.printCartMenu(user);
				break;
			case 7:
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
				
				System.out.print("도서번호 입력 : ");
				int no = Integer.parseInt(sc.nextLine());
				book = BookController.bookSelectByBookNo(user, no);
				if (book == null) {

					System.out.println("\n다시 하시겠습니까? ( 네 / 아니오 )");

					String choice = sc.nextLine();
					switch (choice) {
					case "네":
						continue;
					case "아니오":
						System.out.println("*** '아니오'를 입력하셨습니다. 처음으로 돌아갑니다. ***");
						break;
					default:
						System.out.println("*** ( 네 / 아니오 ) 중 하나만 입력해주세요. 처음으로 돌아갑니다. ***\n");
					}
				}
			} catch (NumberFormatException e) {
				// e.printStackTrace();
				System.out.println("*** '숫자'만 입력해주세요. ***");

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
			System.out.print("도서명 입력 : ");
			String keyword = sc.nextLine();
			bookList = BookController.bookSelectByBookName(user, keyword);
			if (bookList == null) {

				System.out.println("\n다시 하시겠습니까? ( 네 / 아니오 )");

				String choice = sc.nextLine();
				switch (choice) {
				case "네":
					continue;
				case "아니오":
					System.out.println("*** '아니오'를 입력하셨습니다. 처음으로 돌아갑니다. ***");
					break;
				default:
					System.out.println("*** ( 네 / 아니오 ) 중 하나만 입력해주세요. 처음으로 돌아갑니다. ***\n");
				}
			}
			break;
		}
		return bookList;
	}

	/**
	 * 저자명으로 검색하기
	 */
	public static List<Book> selectBookByWriter(User user) {
		List<Book> bookList = null;
		while (true) {
				System.out.print("저자를 입력해주세요 : ");
				String writer = sc.nextLine();
				bookList = BookController.bookSelectByWriter(user, writer);
				if (bookList == null) {

					System.out.println("\n다시 하시겠습니까? ( 네 / 아니오 )");

					String choice = sc.nextLine();
					switch (choice) {
					case "네":
						continue;
					case "아니오":
						System.out.println("*** '아니오'를 입력하셨습니다. 처음으로 돌아갑니다. ***");
						break;
					default:
						System.out.println("*** ( 네 / 아니오 ) 중 하나만 입력해주세요. 처음으로 돌아갑니다. ***\n");
					}
				}
				break;
		}

		return bookList;
	}

	/**
	 * 출판사로 검색하기
	 */
	public static List<Book> selectBookByPublisher(User user) {
		List<Book> bookList = null;
		while (true) {
			try {
				System.out.print("출판사를 입력해주세요 : ");
				String publisher = sc.nextLine();

				bookList = BookController.bookSelectByPublisher(user, publisher);

				if (bookList == null) {

					System.out.println("\n다시 하시겠습니까? ( 네 / 아니오 )");

					String choice = sc.nextLine();
					switch (choice) {
					case "네":
						continue;
					case "아니오":
						System.out.println("*** '아니오'를 입력하셨습니다. 처음으로 돌아갑니다. ***");
						break;
					default:
						System.out.println("*** ( 네 / 아니오 ) 중 하나만 입력해주세요. 처음으로 돌아갑니다. ***\n");
					}
				}
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return bookList;
	}

	/**
	 * 도서분야로 검색하기
	 */
	public static List<Book> selectBookByCategory(User user) {
		List<Book> bookList = null;
		while (true) {
			try {
				System.out.print("찾으시는 분야를 입력해주세요 : ");
				String category = sc.nextLine();

				bookList = BookController.bookSelectByCategory(user, category);

				if (bookList == null) {

					System.out.println("\n다시 하시겠습니까? ( 네 / 아니오 )");

					String choice = sc.nextLine();
					switch (choice) {
					case "네":
						continue;
					case "아니오":
						System.out.println("*** '아니오'를 입력하셨습니다. 처음으로 돌아갑니다. ***");
						break;
					default:
						System.out.println("*** ( 네 / 아니오 ) 중 하나만 입력해주세요. 처음으로 돌아갑니다. ***\n");
					}
				}
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return bookList;
	}

	/**
	 * 대여 상태로 검색하기(0이면 대여가능, 1이면 대여 중, 2이면 예약 중)
	 */
	public static List<Book> selectBookByState(User user) {
		List<Book> bookList = null;
		while (true) {
			try {

				System.out.print("대출여부 검색(대출가능: 0, 대출중: 1, 예약중: 2) : ");
				int state = Integer.parseInt(sc.nextLine());
				bookList = BookController.bookSelectByState(user, state);
				break;
			} catch (NumberFormatException e) {
				FailView.errorMessage("*** 메뉴에 있는 '숫자'만 입력해주세요. ***");
			}
		}
		return bookList;
	}
	
	////////////////////////////// 관리자////////////////////////
	/**
	 * 관리자 도서조회(도서번호) -> 삭제 or 수정 
	 */
	public static void bookDeleteOrUpdateMenu(User user, Book book) {

		while(true) {
			int result=0;

			System.out.println("---------------------------------------------------------------------------");
			System.out.println("1. 선택도서 수정    2. 선택도서 삭제    3. 돌아가기    9. 메인메뉴로 가기  ");
			System.out.println("---------------------------------------------------------------------------");
			int menu = Integer.parseInt(sc.nextLine());

			switch (menu) {
			case 1:
				Book updatebook = AdminMenuView.updateBook();
				result = UpdateAdminController.bookUpdate(book.getBookNo(), updatebook);
				if (result == 1)
					System.out.println("*** '"+book.getBookNo() + "' 번 해당 책이 수정되었습니다. ***");
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
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("1. 선택도서 수정    2. 선택도서 삭제    3. 돌아가기    9. 메인메뉴로 가기 ");
			System.out.println("--------------------------------------------------------------------------");
			int menu = Integer.parseInt(sc.nextLine());
			int bookNo = 0;
			int result = 0;
			switch (menu) {
			case 1:
				bookNo = AdminMenuView.InputBookNo();
				Book updatebook = AdminMenuView.updateBook();
				result = UpdateAdminController.bookUpdate(bookNo, updatebook);
				if (result == 1)
					System.out.println("*** '"+bookNo + "'번 해당 책이 수정되었습니다. ***");
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
