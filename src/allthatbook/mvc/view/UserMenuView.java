package allthatbook.mvc.view;

import java.util.Scanner;

import allthatbook.mvc.controller.BookController;
import allthatbook.mvc.controller.CartController;
import allthatbook.mvc.controller.UserController;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.session.Session;
import allthatbook.mvc.session.SessionSet;

public class UserMenuView {
	private static Scanner sc = new Scanner(System.in);
	
	public static void menu() {
		while (true) {
			try {
				SessionSet ss = SessionSet.getInstance();
				System.out.println(ss.getSet());

				System.out.println("=== AllThatBook Library ===");
				System.out.println("1. 로그인   |   2. 회원가입   |  9. 로그아웃");
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					UserMenuView.login(); // 로그인
					break;
				case 2:
					UserMenuView.register(); // 회원가입
					break;
				case 9:
					System.exit(0);
				default:
					System.out.println("메뉴에 있는 번호를 입력해주세요");
				}
			}catch (NumberFormatException e) {
				System.out.println("메뉴는 숫자만 입력해주세요.");
			}
			
		}
	}
	
	/**
	 * 로그인 메뉴
	 */
	public static void login() {
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();

		UserController.login(userId, userPwd);
	}

	/**
	 * 회원가입 메뉴
	 */
	public static void register() {
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();
		System.out.print("비밀번호 확인 : ");
		String pwdCheck = sc.nextLine();
		System.out.print("이름 : ");
		String userName = sc.nextLine();
		System.out.print("전화번호 : ");
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
				System.out.println(ss.getSet()); // Set객체
				System.out.println("-----" + user.getUserId() + " 로그인 중 -----");
				System.out.println(
						" 1.전체목록  | 2.도서검색(대여, 예약) | 3.도서반납  | 4.책신청 |  5.장바구니담기 | 6.장바구니보기 |  7.회원정보  |  8.회원정보수정  |  9.로그아웃 | |100.장바구니 비우기");
				System.out.print("번호 입력 > ");
				int menu = Integer.parseInt(sc.nextLine());
				switch (menu) {
				case 1:
					BookController.bookSelect();// 전체 상품조회
					break;
				case 2 :
					BookMenuView.printSelectMenu(user);
					break;
				case 3:

					break;
				case 4:

					break;
				case 5:         
					CartMenuView.putCart(user.getUserId());
					break;
				case 6:
					CartMenuView.viewCart(user.getUserId());
					break;
				case 7:
					UserMenuView.selectUserInfo(user);
					break;
				case 8:
					UserMenuView.updateTemp(user);
					if(ss.getSet().size()==0) return;
					break;
				case 9:
					logout(user.getUserId());
					return;
					
				case 100:
					CartController.clearCart(user.getUserId());
					
				default:
					System.out.println("메뉴에 있는 번호를 입력해주세요");
				}
			}catch (NumberFormatException e) {
				System.out.println("메뉴는 숫자만 입력해주세요.");

			}
		}
		
	}
	
	/**
	 * 회원정보수정 화면으로 가기위한 페이지
	 */
	public static void updateTemp(User user) {
		while (true) {
			System.out.println("정보수정/탈퇴를 위해 비밀번호를 입력해 주세요. (수정을 취소하려면 \"exid\" 입력해주세요)");
			String userPwd = sc.nextLine();
			if (userPwd.equals("exid")) {
				System.out.println("다시 메뉴로 돌아갑니다.");
				break;
			} else if (!(user.getUserPwd().equals(userPwd))) {
				System.out.println("비밀번호를 잘못입력 하셨습니다.");
				continue;
			}

			System.out.println(" 1. 수정   |  2.회원탈퇴  | 9. 나가기 ");
			System.out.print("번호 선택 > ");
			int menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				UserMenuView.update(user); // 수정
				return;
			case 2:
				UserMenuView.delete(user);
				return;
			case 9:
				System.out.println("수정화면이 종료 되었습니다.");
				return;
			default:
				System.out.println("메뉴에 있는 번호를 입력해주세요");
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
			System.out.println("1. 비밀번호 변경  2. 이름 변경  3.  전화번호 변경  0. 변경 완료하기 (0번 누를때까지 반복)");
			System.out.print("번호 선택 > ");
			menu = Integer.parseInt(sc.nextLine());
			switch (menu) {
			case 1:
				System.out.print("변경할 비밀번호 : ");
				userPwd = sc.nextLine();
				System.out.print("변경할 비밀번호 확인: ");
				String cheuserPwd = sc.nextLine();
				if (!userPwd.equals(cheuserPwd)) {
					System.out.println("비밀번호가 일치하지 않습니다.");
					userPwd = user.getUserPwd();
				}
				break;
			case 2:
				System.out.print("변경할 이름 : ");
				userName = sc.nextLine();
				break;
			case 3:
				System.out.print("변경할 전화번호 : ");
				userPhone = sc.nextLine();
				break;
			case 0:
				user.setUserPwd(userPwd);
				user.setUserName(userName);
				user.setUserPhone(userPhone);

				UserController.updateUserInfo(user);
				return;
			default:
				System.out.println("메뉴에 있는 번호를 입력해주세요");
			}
		}
	}

	/**
	 * 회원탈퇴
	 */
	public static void delete(User user) {
		System.out.println("***회원 탈퇴를 하시겠습니까? (네/아니오)***");
		String checkDeleteID = sc.nextLine();
		switch(checkDeleteID) {
		case "네" :
			while(true) {
				System.out.print("탈퇴하실 아이디 : ");
				String userId = sc.nextLine();
				if( !(user.getUserId().equals(userId)) ) {
					System.out.println("***아이디가 틀렸습니다.***");
					continue;
				}
				System.out.print("탈퇴하실 비밀번호 : ");
				String userPwd = sc.nextLine();
				System.out.print("탈퇴하실 비밀번호 확인 : ");
				String checkUserPwd = sc.nextLine();
				if( !(user.getUserPwd().equals(userPwd) && user.getUserPwd().equals(checkUserPwd)) ) {	// !(참&&참)
					System.out.println("***비밀번호가 틀렸습니다.***");
					continue;
				}
				
				String checkString = "탈퇴하겠습니다";				//나중에 문구 수정을 위해서 빼놓음
				System.out.println("***정말 회원 탈퇴를 하시겠습니까?***");
				System.out.println("***탈퇴하시려면 괄호안의 문자("+checkString+")를 적어주세요***");
				if(!sc.nextLine().equals(checkString)) {
					System.out.println("***입력문자가 잘못 되었습니다.***");
					continue;
				}
				
				user.setUserId(userId);
				user.setUserPwd(userPwd);
				UserController.deleteUserInfo(user);
				return;
			}
		case "아니오" :
			System.out.println("***메뉴로 돌아갑니다.***");
		break;
		default :
			System.out.println("***네/아니오 중에서 입력해 주세요***");
		}
	}
	

	/**
	 * 회원 본인의 정보 확인
	 */
	public static void selectUserInfo(User user) {
		while(true) {
			System.out.println("정보조회를 위해 비밀번호를 입력해 주세요. (검색을 취소하려면 \"exid\" 입력해주세요)");
			String userPwd = sc.nextLine();
			if (userPwd.equals("exid")) {
				System.out.println("다시 메뉴로 돌아갑니다.");
				break;
			} else if (!(user.getUserPwd().equals(userPwd))) {
				System.out.println("비밀번호를 잘못입력 하셨습니다.");
				continue;
			}
			UserController.selectByUserId(user.getUserId());
			break;
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
