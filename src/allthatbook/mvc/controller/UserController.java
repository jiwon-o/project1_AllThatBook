package allthatbook.mvc.controller;

import java.sql.SQLException;
import java.util.List;

import allthatbook.mvc.exception.NotFoundException;
import allthatbook.mvc.exception.PwdCheckException;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.model.service.UserServiceImpl;
import allthatbook.mvc.view.EndView;
import allthatbook.mvc.view.FailView;
import allthatbook.mvc.view.MenuView;

public class UserController {
	static UserServiceImpl userService = new UserServiceImpl();

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
			// MenuView.menu();
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
			int result = userService.updateUserInfo(user);

			if (result == 0) {
				FailView.errorMessage("수정이 실패하였습니다.");
				MenuView.updateTemp(user);
			} else if (result == 1) {	// 수정 성공 했을때 > 화면: 첫번째 메인메뉴
				EndView.printMessage("수정이 성공되었습니다. 다시 로그인 해주세요");
				MenuView.logout(user.getUserId()); //수정 되어서 다시 로그인 시키기
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 회원탈퇴
	 */
	
	
	public static void revoke(User user) {
		/*
		try {
			int result = userService.revoke(user);

			if (result == 0) {
				// 탈퇴 쿼리 실패했을때 > 화면: 다시 수정, 탈퇴, 나가기
				MenuView.updateTemp(user);
			} else if (result == 1) {
				// 탈퇴 성공 했을때 > 화면: 로그인 회원가입 종료 (처음메뉴)
				MenuView.printMenu();
			} else if (result == 2) {
				// 아이디나 패스워드가 일치하지 않을때 > 화면: 다시 수정, 탈퇴, 나가기
				MenuView.updateTemp(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
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

}
