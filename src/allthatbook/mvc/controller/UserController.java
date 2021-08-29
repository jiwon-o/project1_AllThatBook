package allthatbook.mvc.controller;

import java.sql.SQLException;
import java.util.List;

import allthatbook.mvc.exception.PwdCheckException;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.model.service.UserService;
import allthatbook.mvc.model.service.UserServiceImpl;
import allthatbook.mvc.view.EndView;
import allthatbook.mvc.view.FailView;
import allthatbook.mvc.view.MenuView;

public class UserController {
	static UserService userService = new UserServiceImpl();

	/**
	 * 로그인
	 */
	public static void login(String userId, String userPwd) {
		try {
			User user = userService.login(userId, userPwd);
			if (user.getUserId().equals("admin")) {
				MenuView.printAdminMenu(user);
			} else {
				MenuView.printUserMenu(user);
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
			EndView.printMessage("회원가입이 완료되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		} catch (PwdCheckException e) { // 비밀번호가 일치하지 않을 때
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 회원정보 수정 처리
	 */
	public static void updateUserInfo(User user) {
		try {
			userService.updateUserInfo(user);
			EndView.printMessage("수정이 성공되었습니다. 다시 로그인 해주세요");
			MenuView.logout(user.getUserId()); //수정 되어서 다시 로그인 시키기
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
			EndView.printMessage("회원탈퇴가 성공되었습니다. 로그인 화면으로 이동합니다.");
			MenuView.logout(user.getUserId()); //수정 되어서 다시 로그인 시키기
		} catch (SQLException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		} 
	}
	
	/**
	 * 회원삭제
	 */
	public static void deleteAdminUserInfo(int userNo) {
		try {
			userService.deleteUserInfo(userNo);
			EndView.printMessage("회원삭제가 되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
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
	public static void selectByUserNo(int userNo) {
		try {
			User user = userService.selectByUserNo(userNo);
			EndView.printSelectByUserId(user);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * userId로 조회
	 */
	public static void selectByUserId(String userId) {
		try {
			User user = userService.selectByUserId(userId);
			EndView.printSelectByUserId(user);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 회원번호를 받아 해당 회원번호 정보 수정
	 */
	public static void updateAdminUserInfo(int userNo) {
		try {
			
			
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}
	

}
