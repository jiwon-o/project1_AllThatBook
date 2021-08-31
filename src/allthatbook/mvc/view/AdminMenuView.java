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
	 * 관리자메뉴
	 */
	public static void printAdminMenu(User user) {
		while(true) {
			try {

				System.out.println("                   -관리자 메뉴-                     ");
				System.out.println("1. 회원관리     2. 도서관리    3. 대출관리     9. 나가기   ");
				System.out.println("-------------------------------------------------");
				
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
					System.out.println("메뉴에 있는 번호를 입력해주세요");
				}
			} catch (NumberFormatException e) {
				FailView.errorMessage("메뉴는 숫자만 입력가능합니다.");
			}

		}
	}
	
	/**
	 * 회원관리 메뉴
	 * @throws Exception 
	 */
	public static void userAdminMenu() {
		while(true) {
			try {	
				System.out.println("                              관리자 회원 관리                                       ");
				System.out.println("1. 전체회원 조회  2. 회원번호로 조회  3. 회원ID로 조회   4.회원정보수정   5.회원정보삭제   9. 나가기  ");
				System.out.println("---------------------------------------------------------------------------------");

				int menu=Integer.parseInt(sc.nextLine());
				int result=0;
				int userNo;
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
				case 4 : //회원정보수정 
					userNo = InputUserNo();
					User user = UserController.selectByUserNo(userNo);
					if(user==null) {
						break;
					}
					User updateuser = updateUser();
					result = UpdateAdminController.userUpdate(userNo, updateuser);
					if(result==1)System.out.println(userNo+"번 회원이 수정되었습니다.");
					break;
				case 5 : 
					userNo=InputUserNo();
					UserController.deleteAdminUserInfo(userNo);
					break;
				case 9 :  			
					return;
				default : 
					System.out.println("메뉴에 있는 번호만 입력해주세요.");
				}
			} catch (NumberFormatException e) {
				FailView.errorMessage("메뉴는 숫자만 입력가능합니다.");
			}
		}
		
	}

	/**
	 * 도서관리 메뉴
	 */
	public static void bookAdminMenu(User user) {
		while(true) {
			try {
				System.out.println("                                       관리자 도서 관리                                          ");
				System.out.println("1. 새 도서등록   2. 도서정보수정   3. 도서삭제   4. 도서조회   5. 대출한도서 조회   6. 예약한도서 조회   9. 나가기  ");
				System.out.println("---------------------------------------------------------------------------------------------");
				int menu=Integer.parseInt(sc.nextLine());
				int bookNo=0;
				int result=0;
				Book book=null;
				switch(menu) {
					case 1 :
						book=InputBook();
						result = BookController.bookInsert(book);
						if(result==1) System.out.println(book.getBookNo()+"등록되었습니다.");
						break;
					case 2 : //도서정보수정
						bookNo = InputBookNo();
						book = BookController.bookSelectByBookNo(user, bookNo);
						if(book==null) {
							break;
						}
						Book updatebook = updateBook();
						result = UpdateAdminController.bookUpdate(bookNo, updatebook);
						if(result==1)System.out.println(bookNo+"번 해당 책이 수정되었습니다.");
						break;
					case 3 : 
						bookNo = InputBookNo();
						BookController.bookDelete(bookNo);	
						break;
					case 4 : //도서조회 --- 장바구니 말고 수정, 삭제로 연결되게 
						BookMenuView.printSelectMenu(user);
						break;
					case 5 : //대출한도서 : 상태가 1인도서 조회 
						BookController.bookRentalSelect();
						break;
					case 6 : //예약도서 조회 : 상태가 2인도서 조회
						BookController.bookReserveSelect();
						break;
					case 9 :
						return;
					default:
						System.out.println("메뉴에 있는 번호만 입력해주세요.");
				}
				
			} catch (NumberFormatException e) {
				FailView.errorMessage("메뉴는 숫자만 입력가능합니다.");
			}
		}
	}	


	/**
	 * UserNo 입력받기 
	 */

	public static int InputUserNo(){
		int userNo=0;
		try {
			System.out.print("userNo 입력 > ");
			userNo = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			
		}finally {
			return userNo;
		}
	}
	
	/**
	 * UserId 입력받기 
	 */
	public static String InputUserId() {
		System.out.print("userId 입력 : ");
		String userId = sc.nextLine();
		return userId;
	}

	/**
	 *	bookInsert에 필요한 book정보 넣기 
	 */
	public static Book InputBook() {
		Book book = null;
		while(true) {
	        System.out.println("bookNo은 자동배정됩니다.");
	        
	        System.out.print("bookName 입력 : ");
	        String bookName = sc.nextLine();
	        if(bookName.equals("")) {
	        	System.out.println("bookName은 입력 필수");
	        	continue;
	        }
	        System.out.print("bookWriter 입력 : ");
	        String bookWriter = sc.nextLine();
	        System.out.print("bookPublisher 입력 : ");
	        String bookPublisher = sc.nextLine();
	        System.out.println("출간일은 자동배정됩니다.");
	        String pubDate = sc.nextLine();
	        System.out.print("bookField 입력 : ");
	        String bookField = sc.nextLine();
	        System.out.println("bookState 기본 대출가능0으로 들어갑니다.");
	        
	        book = new Book(0, bookName, bookWriter, bookPublisher, pubDate, bookField, 0);
	        break;
		}

		return book;
	}
	
	/**
	 * bookNo입력받기 
	 */
	public static int InputBookNo() {

		int bookNo=0;
		try {
			System.out.print("bookNo 입력 > ");
			bookNo = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			
		}finally {
			return bookNo;
		}	
	}
	
	/**
	 * User 수정할 데이터 입력받기 
	 */
	private static User updateUser() {
		User updateUser = new User();
		System.out.println("수정할 userId 입력(수정을 원하지 않을 시 enter을 눌러주세요) : ");
		updateUser.setUserId(sc.nextLine());
		System.out.println("수정할 userPwd 입력(수정을 원하지 않을 시 enter을 눌러주세요) : ");
		updateUser.setUserPwd(sc.nextLine());
		System.out.println("수정할 userName 입력(수정을 원하지 않을 시 enter을 눌러주세요) : ");
		updateUser.setUserName(sc.nextLine());
		System.out.println("수정할 userPhone 입력(수정을 원하지 않을 시 enter을 눌러주세요) : ");
		updateUser.setUserPhone(sc.nextLine());

		return updateUser;
	}
	
	/**
	 * book수정할 데이터 입력받기 
	 */
	public static Book updateBook() {
		Book updateBook = new Book();		
		System.out.print("수정할 bookName 입력(수정을 원하지 않을 시 enter을 눌러주세요) : ");
		updateBook.setBookName(sc.nextLine());
		System.out.print("수정할 bookWriter 입력(수정을 원하지 않을 시 enter을 눌러주세요) : ");
		updateBook.setBookWriter(sc.nextLine());
		System.out.print("수정할 bookPublisher 입력(수정을 원하지 않을 시 enter을 눌러주세요) : ");
		updateBook.setBookPublisher(sc.nextLine());
		System.out.print("수정할 bookField 입력 >(수정을 원하지 않을 시 enter을 눌러주세요) : ");
		updateBook.setBookField(sc.nextLine());
		
		return updateBook;
	}
	

}
