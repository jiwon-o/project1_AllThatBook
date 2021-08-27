package allthatbook.mvc.controller;

import java.sql.SQLException;

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
	 * */
	public static void login(String userId, String userPwd) {
		try {
			User user = userService.login(userId, userPwd);
			MenuView.printUserMenu(userId);
			//MenuView.menu();
		}catch (Exception e) {
			//e.printStackTrace();
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
		}catch (SQLException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}catch (PwdCheckException e) { //비밀번호가 일치하지 않을 때
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 회원정보 수정
	 */
	public static void updateUserInfo(User user) {
		try {
			userService.updateUserInfo(user);
			MenuView.printSubMenu();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 회원탈퇴 
	 */
	public static void revoke(User user) {
		try {
			userService.revoke(user);
				MenuView.printSubMenu();
		}catch (SQLException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
}
