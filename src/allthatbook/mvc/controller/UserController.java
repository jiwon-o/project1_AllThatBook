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
	static UserServiceImpl userService = new UserServiceImpl();

	/**
	 * �α���
	 * */
	public static void login(String userId, String userPwd) {
		try {
			User user = userService.login(userId, userPwd);
			if(userId.equals("admin")) {
				MenuView.printAdminMenu(userId);
			}else{
				MenuView.printUserMenu(userId);
			}
			//MenuView.menu();
		}catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			
		}
	}
	
	/**
	 * ȸ������
	 */
	public static void register(User user, String pwdCheck) {
		try {
			userService.register(user, pwdCheck);
			EndView.printMessage("ȸ�������� �Ϸ�Ǿ����ϴ�.");
		}catch (SQLException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}catch (PwdCheckException e) { //��й�ȣ�� ��ġ���� ���� ��
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * ȸ������ ����
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
	 * ȸ��Ż�� 
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
	
	/**
	 * ��ü ȸ�� ��ȸ
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
	 * userNo�� ��ȸ
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
	 * userId�� ��ȸ
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
