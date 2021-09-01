package allthatbook.mvc.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import allthatbook.mvc.exception.PwdCheckException;
import allthatbook.mvc.model.dto.Rental;
import allthatbook.mvc.model.dto.Reservation;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.model.service.UserService;
import allthatbook.mvc.model.service.UserServiceImpl;
import allthatbook.mvc.view.AdminMenuView;
import allthatbook.mvc.view.EndView;
import allthatbook.mvc.view.FailView;
import allthatbook.mvc.view.UserMenuView;

public class UserController {
	static UserService userService = new UserServiceImpl();

	/**
	 * 로그인
	 */
	public static void login(String userId, String userPwd) {
		try {
			User user = userService.login(userId, userPwd);
			if (user.getUserId().equals("admin")) {
				AdminMenuView.printAdminMenu(user);
			} else {
				UserMenuView.printUserMenu(user);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 회원가입
	 */
	public static void register(User user, String pwdCheck) {
		try {
			userService.register(user, pwdCheck);
			EndView.printMessage("*** 회원가입이 완료되었습니다. ***\n");
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		} catch (PwdCheckException e) { // 비밀번호가 일치하지 않을 때
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 회원정보 수정 처리
	 */
	public static void updateUserInfo(User user) {
		try {
			userService.updateUserInfo(user);
			EndView.printMessage("*** 수정이 완료되었습니다. 다시 로그인 해주세요. ***\n");
			UserMenuView.logout(user.getUserId()); //수정 되어서 다시 로그인 시키기
		} catch (SQLException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		} 
	}

	/**
	 * 회원탈퇴
	 */
	public static void deleteUserInfo(User user) {
		try {
			userService.deleteUserInfo(user);
			EndView.printMessage("*** 회원탈퇴가 완료되었습니다 로그인 화면으로 이동합니다. ***");
			UserMenuView.logout(user.getUserId()); //수정 되어서 다시 로그인 시키기
		} catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		} 
	}

	/**
	 * 회원삭제
	 */
	public static void deleteAdminUserInfo(int userNo) {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("\n'"+userNo+"'번 회원의 정보를 정말로 삭제하시겠습니까? ( 네 / 아니오 )");
			String checkDeleteUser = sc.nextLine();
			if("네".equals(checkDeleteUser)) {
				userService.deleteUserInfo(userNo);
				EndView.printMessage("*** 회원정보를 삭제하였습니다. ***");
			}else if("아니오".equals(checkDeleteUser)) {
				System.out.println("*** 회원삭제를 취소했습니다. ***\n");
				return;
			}else {
				System.out.println("*** ( 네 / 아니오 ) 중 하나만 입력해주세요. ***\n");
				return;
			}
			
			
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		} 
	}
	/**
	 * 전체 회원 조회
	 */
	public static void userSelect() {
		try {
			List<User> userList = userService.allSelect();
			EndView.printUserList(userList);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * userNo로 조회
	 */
	public static User selectByUserNo(int userNo) {
		User user=null;
		try {
			user = userService.selectByUserNo(userNo);
			EndView.printSelectByUserId(user);
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
			
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		return user;
	}

	/**
	 * userId로 조회
	 */
	public static void selectByUserId(String userId) {
		try {
			User user = userService.selectByUserId(userId);
			EndView.printSelectByUserId(user);
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 회원번호를 받아 해당 회원번호 정보 수정
	 */
	public static void updateAdminUserInfo(int userNo) {
		try {

			//바꿀 
			User user = userService.selectByUserNo(userNo);

			
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	/**
	 * 회원번호를 받아 대여목록을 출력
	 * */
	public static void selectRentalByUserNo(int userNo){
		try {
		    List<Rental> rentalList = userService.selectRentalByUserNo(userNo);
		    EndView.printRental(rentalList);
		}catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 회원번호를 받아 예약목록을 출력
	 * */
	public static void selectReservationByUserNo(int userNo){
		try {
		    List<Reservation> reservationList = userService.selectReservationByUserNo(userNo);
		    EndView.printReservation(reservationList);
		}catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
}
