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
	 * 회원관리 메뉴
	 */
	public static void userAdminMenu() {
		while(true) {
			System.out.println("---관리자 회원 관리---");
			System.out.println("1. 전체회원 조회 | 2. 회원번호로 조회 | 3. 회원ID로 조회 | 4.회원정보수정 | 5.회원정보삭제| 9. 나가기");
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
			case 4 : //회원정보수정 
				userNo = InputUserNo();
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
			}
		}
		
	}

	/**
	 * 도서관리 메뉴
	 */
	public static void bookAdminMenu(User user) {
		while(true) {
			System.out.println("---관리자 도서 관리---");
			System.out.println("1. 새 도서등록 | 2. 도서정보수정 | 3. 도서삭제 | 4. 도서조회 | 5. 대출한도서 조회 | 6. 예약한도서 조회 | 9. 나가기");
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
					Book updatebook = updateBook();
					result = UpdateAdminController.bookUpdate(bookNo, updatebook);
					if(result==1)System.out.println(bookNo+"번 해당 책이 수정되었습니다.");
					break;
				case 3 : 
					bookNo = InputBookNo();
					result = BookController.bookDelete(bookNo);	
					if(result==1)System.out.println(bookNo+"번호가 삭제되었습니다.");
					break;
				case 4 : //도서조회 --- 장바구니 말고 수정, 삭제로 연결되게 
					MenuView.printSelectMenu(user.getUserId());
					break;
				case 5 : //대출한도서 : 상태가 1인도서 조회 
					BookController.bookRentalSelect();
					break;
				case 6 : //예약도서 조회 : 상태가 2인도서 조회
					BookController.bookReserveSelect();
					break;
				case 9 :
					return;
			}
		}
	}	

	/**
	 * 계속하시겠습니까?
	 */
	
	/**
	 * UserNo 입력받기 
	 */
	public static int InputUserNo() {
		System.out.println("userNo 입력 > ");
		int userNo = Integer.parseInt(sc.nextLine());
		return userNo;
	}
	
	
	/**
	 * UserId 입력받기 
	 */
	public static String InputUserId() {
		System.out.print("userId 입력 > ");
		String userId = sc.nextLine();
		return userId;
	}

	/**
	 *	bookInsert에 필요한 book정보 넣기 
	 */
	public static Book InputBook() {
		Book book = new Book();
		System.out.println("bookNo은 자동배정됩니다.");
		
		System.out.print("bookName 입력 > ");
		book.setBookName(sc.nextLine());
		System.out.print("bookWriter 입력 > ");
		book.setBookWriter(sc.nextLine());
		System.out.print("bookPublisher 입력 > ");
		book.setBookPublisher(sc.nextLine());
		System.out.println("출간일은 현재 날짜가 들어갑니다.");

		System.out.print("bookField 입력 > ");
		book.setBookField(sc.nextLine());
		System.out.println("bookState 기본 대출가능0으로 들어갑니다.");

		return book;
	}
	
	/**
	 * bookNo입력받기 
	 */
	public static int InputBookNo() {
		System.out.print("bookNo 입력 > ");
		int bookNo = Integer.parseInt(sc.nextLine());
		return bookNo;
	}
	

	/**
	 * User 수정할 데이터 입력받기 
	 */
	private static User updateUser() {
		User updateUser = new User();
		System.out.println("수정할 userId 입력(수정을 원하지 않을 시 enter을 눌러주세요) > ");
		updateUser.setUserId(sc.nextLine());
		System.out.println("수정할 userPwd 입력(수정을 원하지 않을 시 enter을 눌러주세요) > ");
		updateUser.setUserPwd(sc.nextLine());
		System.out.println("수정할 userName 입력(수정을 원하지 않을 시 enter을 눌러주세요) > ");
		updateUser.setUserName(sc.nextLine());
		System.out.println("수정할 userPhone 입력(수정을 원하지 않을 시 enter을 눌러주세요) > ");
		updateUser.setUserPhone(sc.nextLine());

		return updateUser;
	}
	
	/**
	 * book수정할 데이터 입력받기 
	 */
	public static Book updateBook() {
		Book updateBook = new Book();		
		System.out.print("수정할 bookName 입력(수정을 원하지 않을 시 enter을 눌러주세요) > ");
		updateBook.setBookName(sc.nextLine());
		System.out.print("수정할 bookWriter 입력(수정을 원하지 않을 시 enter을 눌러주세요) > ");
		updateBook.setBookWriter(sc.nextLine());
		System.out.print("수정할 bookPublisher 입력(수정을 원하지 않을 시 enter을 눌러주세요) > ");
		updateBook.setBookPublisher(sc.nextLine());
		System.out.print("수정할 bookField 입력 >(수정을 원하지 않을 시 enter을 눌러주세요) ");
		updateBook.setBookField(sc.nextLine());
		
		return updateBook;
	}
	

}
