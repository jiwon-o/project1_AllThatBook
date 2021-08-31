package allthatbook.mvc.view;

import java.util.Scanner;

import allthatbook.mvc.controller.BookController;
import allthatbook.mvc.controller.CartController;
import allthatbook.mvc.controller.RentalController;
import allthatbook.mvc.controller.ReservationContoller;
import allthatbook.mvc.controller.UserController;
import allthatbook.mvc.model.dto.Cart;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.session.Session;
import allthatbook.mvc.session.SessionSet;

public class UserMenuView {
	private static Scanner sc = new Scanner(System.in);
	
	public static void menu() {
		while (true) {
			try {
				System.out.println("--------------------------------------------------------------");
				System.out.println("      1.   로그인      2.   회원가입      9.   종료           ");
				System.out.println("--------------------------------------------------------------");
				
				System.out.print("메뉴 입력 : ");
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					UserMenuView.login(); // 로그인
					break;
				case 2:
					UserMenuView.register(); // 회원가입
					break;
				case 9:

					System.out.println("*** 정말 종료하시겠습니까? ( 네 / 아니오 ) ***");
					String checkLogout = sc.nextLine();
					if("네".equals(checkLogout)) {
						
						System.out.println("\n--------------------------------------------------------------------------------------\n");
						System.out.println("                    감사합니다. 다음에 또 'AllThatBook' 과 함께해주세요.              ");
						System.out.println("\n--------------------------------------------------------------------------------------");
						System.exit(0);
						
					}else if("아니오".equals(checkLogout)) {
						System.out.println("*** '아니오'를 입력하셨습니다. *** ");
						break;
					}else {
						System.out.println("*** ( 네 / 아니오 ) 중 하나만 입력해주세요. ***\n");
						break;
					}
					
				default:
					System.out.println("*** 메뉴에 있는 번호를 입력해주세요. ***\n");

				}
			}catch (NumberFormatException e) {
				System.out.println("*** 메뉴는 '숫자'만 입력해주세요. ***\n");
			}
			
		}
	}
	
	/**
	 * 로그인 메뉴
	 */
	public static void login() {
		System.out.println("*** 로그인 화면으로 이동합니다. ***");
		System.out.print("\nID : ");
		String userId = sc.nextLine();
		System.out.print("Password : ");
		String userPwd = sc.nextLine();

		UserController.login(userId, userPwd);
		
	}

	/**
	 * 회원가입 메뉴
	 */
	
	public static void register() {
		System.out.println("*** 회원가입 화면으로 이동합니다. ***");
		System.out.print("\n아이디 : ");
		String userId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();
		System.out.print("비밀번호 확인 : ");
		String pwdCheck = sc.nextLine();
		System.out.print("이름 : ");
		String userName = sc.nextLine();
		System.out.print("휴대전화 : ");
		String userPhone = sc.nextLine();

		User user = new User(0, userId, userPwd, userName, userPhone, null);
		UserController.register(user, pwdCheck);
	}
	

	/**
	 * 유저 매뉴창
	 */
	public static void printUserMenu(User user) {
		while (true) {
			try {
				SessionSet ss = SessionSet.getInstance();

				System.out.println("\n");
				System.out.println(ss.getSet()); // Set객체
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("      1.  전체목록      2.  도서검색(대여, 예약)      3.  도서반납      4.   회원정보조회      5.   회원정보수정      9.   로그아웃      ");
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.print("메뉴 입력 : ");

				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					System.out.println("*** 전체 도서 목록이 출력됩니다. ***");
					BookController.bookSelect();
					break;
				case 2 :
					System.out.println("*** 도서 검색 화면으로 이동합니다. ***");
					BookMenuView.printSelectMenu(user);
					break;
				case 3:
					System.out.println("*** 도서반납 메뉴를 선택했습니다. ***\n");
					System.out.print("도서번호 입력: ");
					int bookNo = Integer.parseInt(sc.nextLine());
					RentalController.returnBook(user, bookNo);
					break;
				case 4:
					System.out.println("*** 회원정보 화면으로 이동합니다. ***");
					UserMenuView.selectUserInfo(user);
					break;
				case 5:  
					System.out.println("*** 회원정보수정 화면으로 이동합니다. ***");
					UserMenuView.updateTemp(user);
					if(ss.getSet().size()==0) return;
					break;
				case 9:
					System.out.println("*** 로그아웃 메뉴를 선택했습니다. ***\n");
					System.out.println("*** 정말 로그아웃하시겠습니까? ( 네 / 아니오 ) ***");
					String checkLogout = sc.nextLine();
					if("네".equals(checkLogout)) {
						logout(user.getUserId());
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
			}catch (NumberFormatException e) {
				System.out.println("*** 메뉴는 '숫자'만 입력해주세요. ***");

			}
		}
		
	}
	
	/**
	 * 장바구니 내역 모두 삭제
	 */
	public static void clearCart(User user) {
		
		System.out.println("장바구니 목록을 정말 삭제하시겠습니까? ( 네 / 아니오 ) ");
		String checkClearCart = sc.nextLine();
		
		if("네".equals(checkClearCart)) {
			CartController.clearCart(user.getUserId());
			System.out.println("*** 장바구니 내역이 모두 삭제되었습니다. ***");
		}else if("아니오".equals(checkClearCart)) {
			System.out.println("*** '아니오'를 입력하셨습니다. *** ");
		}else {
			System.out.println("*** ( 네 / 아니오 ) 중 하나만 입력해주세요. 처음으로 돌아갑니다. ***\n");
		}
		
	}

	/**
	 * 회원정보수정 화면으로 가기위한 페이지
	 */
	public static void updateTemp(User user) {
		while (true) {
			System.out.println("\n회원정보 수정/탈퇴를 위해 비밀번호를 입력해 주세요. (수정을 취소하려면 \"exid\" 입력) ");
			
			String userPwd = sc.nextLine();
			if (userPwd.equals("exid")) {
				System.out.println("*** 다시 메뉴로 돌아갑니다. ***");
				break;
			} else if (!(user.getUserPwd().equals(userPwd))) {
				System.out.println("*** 비밀번호가 일치하지않습니다. ***");
				continue;
			}

			System.out.println("\n*** 회원정보 수정/탈퇴 ***");
			System.out.println("---------------------------------------------------------------");
			System.out.println("        1.   수정       2.   회원탈퇴       9.   나가기        ");
			System.out.println("---------------------------------------------------------------");
			System.out.print("메뉴 입력 : ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				System.out.println("*** 회원정보수정 화면으로 이동합니다.*** ");
				UserMenuView.update(user); // 수정
				return;
			case 2:
				UserMenuView.delete(user);
				return;
			case 9:
				return;
			default:
				System.out.println("*** 메뉴에 있는 번호를 입력해주세요. ***");
			}
		}
	}

	/**
	 * 회원정보 수정
	 */
	public static void update(User user) {
		int menu = 0;
		String userPwd = user.getUserPwd(); // 바꾸기전 비밀번호 기억하기
		String userName = user.getUserName(); // 바꾸기전 이름 기억하기
		String userPhone = user.getUserPhone(); // 바꾸기전 전화번호 기억하기

		while (true) {
			System.out.println("\n*** 회원정보수정 화면 ***");
			System.out.println("--------------------------------------------------------------------------------------------------");
			System.out.println("      1.   비밀번호 변경      2.   이름 변경      3.   전화번호 변경      9.   변경 완료하기      ");
			System.out.println("--------------------------------------------------------------------------------------------------");
			System.out.print("메뉴 입력 : ");
			menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				System.out.println("*** 비밀번호를 변경합니다. ***\n");
				System.out.print("변경할 비밀번호 : ");
				userPwd = sc.nextLine();
				System.out.print("변경할 비밀번호 확인 : ");
				String cheuserPwd = sc.nextLine();
				if (!userPwd.equals(cheuserPwd)) {
					System.out.println("*** 비밀번호가 일치하지 않습니다. ***");
					userPwd = user.getUserPwd();
				}else {
					System.out.println("*** 비밀번호가 변경되었습니다. ***");
				}
				break;
			case 2:
				System.out.println("*** 이름을 변경합니다. ***\n");
				System.out.print("변경할 이름 : ");
				userName = sc.nextLine();
				
				System.out.println("*** 이름이 변경되었습니다. ***");
				break;
			case 3:
				System.out.println("*** 전화번호를 변경합니다. ***\n");
				System.out.print("변경할 전화번호 : ");
				userPhone = sc.nextLine();
				
				System.out.println("*** 전화번호가 변경되었습니다. ***");
				break;
			case 9:
				user.setUserPwd(userPwd);
				user.setUserName(userName);
				user.setUserPhone(userPhone);

				UserController.updateUserInfo(user);
				return;
			default:
				System.out.println("*** 메뉴에 있는 번호를 입력해주세요. ***");
			}
		}
	}

	/**
	 * 회원탈퇴
	 */
	public static void delete(User user) {

		System.out.println("회원탈퇴를 진행하시겠습니까? ( 네 or 아니오 ) ");
		String checkDeleteID = sc.nextLine();
		switch(checkDeleteID) {
		case "네" :
			while(true) {
				System.out.print("탈퇴할 아이디 : ");
				String userId = sc.nextLine();
				if( !(user.getUserId().equals(userId)) ) {
					System.out.println("아이디가 틀렸습니다. ");
					continue;
				}
				System.out.print("탈퇴할 아이디의 비밀번호 : ");
				String userPwd = sc.nextLine();
				System.out.print("탈퇴할 아이디의 비밀번호 재확인 : ");
				String checkUserPwd = sc.nextLine();
				if( !(user.getUserPwd().equals(userPwd) && user.getUserPwd().equals(checkUserPwd)) ) {	// !(참&&참)
					System.out.println("비밀번호가 일치하지않습니다. ");
					continue;
				}
				
				String checkString = "탈퇴하겠습니다";				//나중에 문구 수정을 위해서 빼놓음
				System.out.println("\n");
				System.out.println("정말 회원탈퇴를 하시겠습니까?");
				System.out.println("취소하려면 (아니오)를 입력해주세요");
				System.out.println("탈퇴하시려면 괄호안의 문자("+checkString+")를 입력해주세요. ");
				String checkText = sc.nextLine();
				if(checkText.equals("아니오")) break;
				if(!checkText.equals(checkString)) {
					System.out.println("입력된문자가 잘못되었습니다 다시한번 확인해주세요. ");
					continue;
				}
				
				user.setUserId(userId);
				user.setUserPwd(userPwd);
				UserController.deleteUserInfo(user);
				return;
			}
		case "아니오" :
			System.out.println("메뉴로 돌아갑니다. ");
		break;
		default :
			System.out.println("( 네 or 아니오 )중에서 입력해 주세요. ");
		}
	}

	

	/**
	 * 회원 본인의 정보 확인
	 */
	public static void selectUserInfo(User user) {
		while(true) {
			System.out.println("\n회원정보조회를 위해 비밀번호를 입력해주세요. (검색을 취소하려면 \"exid\" 입력) ");
			String userPwd = sc.nextLine();
			if (userPwd.equals("exid")) {
				System.out.println("*** 다시 메뉴로 돌아갑니다. ***");
				break;
			} else if (!(user.getUserPwd().equals(userPwd))) {
				System.out.println("*** 비밀번호가 일치하지않습니다. ***");
				continue;
			}
			UserController.selectByUserId(user.getUserId());
			UserController.selectRentalByUserNo(user.getUserNo());
			UserController.selectReservationByUserNo(user.getUserNo());
			UserMenuView.reservationDeleteMenu(user);
			break;
		}
	}

	//예약 삭제하기
	public static void reservationDeleteMenu(User user) {
		System.out.println("*** 회원정보 메뉴 ***");
		System.out.println("----------------------------------------------------------------");
		System.out.println("        1.    예약 삭제하기        2.    메인메뉴로 가기        ");
		System.out.println("----------------------------------------------------------------");
		System.out.print("메뉴 입력 : ");
		int menu = Integer.parseInt(sc.nextLine());
		
		switch (menu) {
		case 1:
			System.out.println("*** 예약 삭제하기 메뉴를 선택했습니다. ***\n");
			System.out.print("도서번호 입력 : ");
			int bookNo = Integer.parseInt(sc.nextLine());
			ReservationContoller.deleteReservation(user, bookNo);
			break;
		case 2:
			System.out.println("예약 삭제 화면이 종료 되었습니다. ");
			break;
		default:
			System.out.println("메뉴에 있는 '번호'를 입력해주세요");
		}
	}
	
	/**
	 * 로그아웃
	 */
	public static void logout(String userId) {
		Session session = new Session(userId);

		SessionSet ss = SessionSet.getInstance();
		ss.remove(session);
	}
}
