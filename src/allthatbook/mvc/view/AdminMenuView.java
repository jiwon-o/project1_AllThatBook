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
	 * 관리자메뉴
	 */
	public static void printAdminMenu(User user) {
		SessionSet ss = SessionSet.getInstance();
		while (true) {
			try {
				System.out.println("\n");
				System.out.println(ss.getSet()); // Set객체
				System.out.println("-------------------------------------------------------------------");
				System.out.println("        1.   회원관리      2.   도서관리      9.   로그아웃        ");
				System.out.println("-------------------------------------------------------------------");
				System.out.print("메뉴 입력 : ");
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					System.out.println("*** 회원관리 화면으로 이동합니다. ***");
					AdminMenuView.userAdminMenu();
					break;
				case 2:
					System.out.println("*** 도서관리 화면으로 이동합니다. ***");
					AdminMenuView.bookAdminMenu(user);
					break;
				case 3:
					break;
				case 9:
					System.out.println("*** 로그아웃 메뉴를 선택했습니다. ***\n");
					System.out.println("*** 정말 로그아웃하시겠습니까? ( 네 / 아니오 ) ***");
					String checkLogout = sc.nextLine();
					if("네".equals(checkLogout)) {
						UserMenuView.logout(user.getUserId());
						System.out.println("*** 로그아웃되었습니다. ***\n");
					}else if("아니오".equals(checkLogout)) {
						System.out.println("*** '아니오'를 입력하셨습니다. *** ");
						break;
					}else {
						System.out.println("*** ( 네 / 아니오 ) 중 하나만 입력해주세요. ***\n");
						break;
					}
					
					return;
				default:
					System.out.println("*** 메뉴에 있는 번호를 입력해주세요. ***");
				}
			} catch (NumberFormatException e) {
				FailView.errorMessage("*** 메뉴는 '숫자'만 입력가능합니다. ***");
			}

		}
	}

	/**
	 * 회원관리 메뉴
	 * 
	 * @throws Exception
	 */
	public static void userAdminMenu() {
		int result = 0;
		int userNo;
		User user;
		while (true) {
			try {
				System.out.println("\n*** 회원관리 화면 ***");
				System.out.println(
						"-----------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println(
						"        1.   전체회원 조회      2.   회원번호로 조회      3.   회원ID로 조회      4.   회원정보수정      5.   회원정보삭제      9.   돌아가기        ");
				System.out.println(
						"-----------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.print("메뉴 입력 : ");
				int menu = Integer.parseInt(sc.nextLine());

				switch (menu) {
				case 1:
					System.out.println("*** 전체회원을 조회합니다. ***");
					UserController.userSelect();
					break;
				case 2:
					System.out.println("*** 회원번호로 조회합니다. ***");
					userNo = InputUserNo();
					UserController.selectByUserNo(userNo);
					break;
				case 3:
					System.out.println("*** 회원ID로 조회합니다. ***");
					String userId = InputUserId();
					UserController.selectByUserId(userId);
					break;
				case 4: // 회원정보수정
					System.out.println("*** 회원정보수정 화면으로 이동합니다. ***");
					userNo = InputUserNo();
					user = UserController.selectByUserNo(userNo);
					if (user == null) {
						break;
					}
					User updateuser = updateUser();
					result = UpdateAdminController.userUpdate(userNo, updateuser);
					if (result == 1)
						System.out.println("*** '" + userNo + "'번 회원의 정보가 수정되었습니다. ");

					break;
				case 5:
					System.out.println("*** 회원정보삭제 화면으로 이동합니다. ***");
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
					System.out.println("*** 메뉴에 있는 '번호'만 입력해주세요. ***");
				}
			} catch (NumberFormatException e) {
				FailView.errorMessage("*** 메뉴는 '숫자'만 입력가능합니다. ***");
			}
		}

	}

	/**
	 * 도서관리 메뉴
	 */
	public static void bookAdminMenu(User user) {
		while (true) {
			try {
				System.out.println("\n*** 도서관리 화면 ***");
				System.out.println(
						"-----------------------------------------------------------------------------------------------------");
				System.out.println("      1.  새 도서등록      2.  도서조회      3.  도서정보수정      4.  도서삭제      9.  돌아가기      ");
				System.out.println(
						"-----------------------------------------------------------------------------------------------------");
				System.out.print("메뉴 입력 : ");
				int menu = Integer.parseInt(sc.nextLine());
				int bookNo = 0;
				int result = 0;
				Book book = null;
				switch (menu) {
				case 1:
					System.out.println("*** 새로운 도서를 등록합니다. ***");
					book = InputBook();
					BookController.bookInsert(book);

					break;
				case 2:
					System.out.println("*** 도서정보를 조회합니다. ***\n");
					printSelectMenu(user);
					
					break;
				case 3:
					System.out.println("*** 도서정보를 수정합니다. ***");
					bookNo = InputBookNo();
					book = BookController.bookSelectByBookNo(user, bookNo);
					if (book == null) {
						break;
					}
					Book updatebook = updateBook();
					result = UpdateAdminController.bookUpdate(bookNo, updatebook);
					if (result == 1)
						System.out.println("\n*** '" + bookNo + "'번 도서를 수정했습니다.");
					

					break;
				case 4:
					System.out.println("*** 도서정보를 삭제합니다. ***\n");
					bookNo = InputBookNo();
					System.out.println("'" + bookNo + "'번 도서를 정말로 삭제하시겠습니까? ( 네 / 아니오 )");
					String checkDeleteBook = sc.nextLine();
					if ("네".equals(checkDeleteBook)) {
						BookController.bookDelete(bookNo);

					} else if ("아니오".equals(checkDeleteBook)) {
						System.out.println("*** 삭제를 취소했습니다. ***\n");
						AdminMenuView.bookAdminMenu(user);
					} else {
						System.out.println("*** ( 네 / 아니오 ) 중 하나만 입력해주세요. ***\n");
						AdminMenuView.bookAdminMenu(user);
					}
					break;
				case 9:
					return;
				default:
					System.out.println("*** 메뉴에 있는 '번호'만 입력해주세요. ***");
				}

			} catch (NumberFormatException e) {
				FailView.errorMessage("*** 메뉴는 '숫자'만 입력가능합니다. ***");
			}
		}
	}

	/**
	 * 관리자 도서 검색 메뉴
	 */
	public static void printSelectMenu(User user) {
		while (true) {
			try {
				System.out.println("\n*** 도서조회 메뉴 ***");
				System.out.println(
						"----------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println(
						"   1.  도서번호로 검색   2.  도서명으로 검색   3.  저자명으로 검색   4.  출판사로 검색   5.  도서분야로 검색   6.  대여여부로 검색   9.  돌아가기   ");
				System.out.println(
						"----------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.print("메뉴 입력 : ");
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					System.out.println("*** 도서번호로 검색합니다. ***");
					BookMenuView.selectBookByNo(user); // 도서번호로 검색
					return;
				case 2:
					System.out.println("*** 도서명으로 검색합니다. ***");
					BookMenuView.selectBookByName(user); // 도서명으로 검색
					return;
				case 3:
					System.out.println("*** 저자명으로 검색합니다. ***");
					BookMenuView.selectBookByWriter(user); // 저자명으로 검색
					return;
				case 4:
					System.out.println("*** 출판사로 검색합니다. ***");
					BookMenuView.selectBookByPublisher(user); // 출판사로 검색
					return;
				case 5:
					System.out.println("*** 도서분야로 검색합니다. ***");
					BookMenuView.selectBookByCategory(user); // 분야로 검색
					return;
				case 6:
					System.out.println("*** 대여여부로 검색합니다. ***");
					BookMenuView.selectBookByState(user); // 대여 여부로 검색(0: 대여가능, 1: 대여중, 2: 예약중)
					return;
				case 9:
					return;
				default:
					System.out.println("*** 메뉴에 있는 '번호'만 입력해주세요. ***");
				
				}
			} catch (NumberFormatException e) {
				FailView.errorMessage("*** 메뉴는 '숫자'만 입력가능합니다. ***");
			
			}
		}
	}

	/**
	 * UserNo 입력받기
	 */

	public static int InputUserNo() {
		int userNo = 0;
		try {
			System.out.print("\n회원번호 입력 : ");
			userNo = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {

		} finally {
			return userNo;
		}
	}

	/**
	 * UserId 입력받기
	 */
	public static String InputUserId() {
		System.out.print("\n회원ID 입력 : ");
		String userId = sc.nextLine();
		return userId;
	}

	/**
	 * bookInsert에 필요한 book정보 넣기
	 */
	public static Book InputBook() {
		User user = new User();
		Book book = null;
		while (true) {
//	        System.out.println("도서번호는 자동배정됩니다.");

			System.out.print("\n도서이름 입력 : ");
			String bookName = sc.nextLine();
			if (bookName.equals("")) {
				System.out.println("*** 도서이름의 입력은 필수입니다. ***");
				continue;
			}
			System.out.print("저자명 입력 : ");
			String bookWriter = sc.nextLine();
			System.out.print("출판사 입력 : ");
			String bookPublisher = sc.nextLine();
			System.out.print("도서분야 입력 : ");
			String bookField = sc.nextLine();

			if (bookName.equals("") || bookWriter.equals("") || bookPublisher.equals("") || bookField.equals("")) {
				System.out.println("*** 도서이름, 저자명, 출판사, 도서분야는 입력 필수 ***");
				continue;
			}
			System.out.println("『 " + bookName + " 』 도서를 정말로 등록하시겠습니까? ( 네 / 아니오 )");
			String checkInputBook = sc.nextLine();
			if ("네".equals(checkInputBook)) {
				book = new Book(0, bookName, bookWriter, bookPublisher, bookField, 0);

			} else if ("아니오".equals(checkInputBook)) {
				System.out.println("*** 등록을 취소했습니다. ***\n");
				AdminMenuView.bookAdminMenu(user);
			} else {
				System.out.println("*** ( 네 / 아니오 ) 중 하나만 입력해주세요. ***\n");
				AdminMenuView.bookAdminMenu(user);
			}

			break;
		}

		return book;
	}

	/**
	 * bookNo입력받기
	 */
	public static int InputBookNo() {

		int bookNo = 0;
		try {
			System.out.print("도서번호 입력  : ");
			bookNo = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {

		} finally {
			return bookNo;
		}
	}

	/**
	 * User 수정할 데이터 입력받기
	 */
	private static User updateUser() {
		User updateUser = new User();
		System.out.println("\n");
		System.out.println("수정할 회원ID를 입력해주세요. (수정을 원하지 않을 시 ENTER 입력)");
		updateUser.setUserId(sc.nextLine());
		System.out.println("수정할 이름을 입력해주세요. (수정을 원하지 않을 시 ENTER 입력)");
		updateUser.setUserName(sc.nextLine());
		System.out.println("수정할 전화번호를 입력해주세요. (수정을 원하지 않을 시 ENTER 입력)");
		updateUser.setUserPhone(sc.nextLine());

		return updateUser;
	}

	/**
	 * book수정할 데이터 입력받기
	 */
	public static Book updateBook() {
		Book updateBook = new Book();
		System.out.print("\n수정할 도서명을 입력해주세요. (수정을 원하지 않을 시 ENTER 입력) : ");
		updateBook.setBookName(sc.nextLine());
		System.out.print("수정할 저자명을 입력해주세요. (수정을 원하지 않을 시 ENTER 입력) : ");
		updateBook.setBookWriter(sc.nextLine());
		System.out.print("수정할 출판사를 입력해주세요. (수정을 원하지 않을 시 ENTER 입력) : ");
		updateBook.setBookPublisher(sc.nextLine());
		System.out.print("수정할 도서분야를 입력해주세요. (수정을 원하지 않을 시 ENTER 입력) : ");
		updateBook.setBookField(sc.nextLine());

		return updateBook;
	}

}
