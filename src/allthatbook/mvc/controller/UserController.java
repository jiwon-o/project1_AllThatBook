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
	 * �α���
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
	 * ȸ������
	 */
	public static void register(User user, String pwdCheck) {
		try {
			userService.register(user, pwdCheck);
			EndView.printMessage("ȸ�������� �Ϸ�Ǿ����ϴ�.");
		} catch (SQLException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		} catch (PwdCheckException e) { // ��й�ȣ�� ��ġ���� ���� ��
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * ȸ������ ���� ó��
	 */
	public static void updateUserInfo(User user) {
		try {
			int result = userService.updateUserInfo(user);

			if (result == 0) {
				FailView.errorMessage("������ �����Ͽ����ϴ�.");
				MenuView.updateTemp(user);
			} else if (result == 1) {	// ���� ���� ������ > ȭ��: ù��° ���θ޴�
				EndView.printMessage("������ �����Ǿ����ϴ�. �ٽ� �α��� ���ּ���");
				MenuView.logout(user.getUserId()); //���� �Ǿ �ٽ� �α��� ��Ű��
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ȸ��Ż��
	 */
	
	
	public static void revoke(User user) {
		/*
		try {
			int result = userService.revoke(user);

			if (result == 0) {
				// Ż�� ���� ���������� > ȭ��: �ٽ� ����, Ż��, ������
				MenuView.updateTemp(user);
			} else if (result == 1) {
				// Ż�� ���� ������ > ȭ��: �α��� ȸ������ ���� (ó���޴�)
				MenuView.printMenu();
			} else if (result == 2) {
				// ���̵� �н����尡 ��ġ���� ������ > ȭ��: �ٽ� ����, Ż��, ������
				MenuView.updateTemp(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
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
