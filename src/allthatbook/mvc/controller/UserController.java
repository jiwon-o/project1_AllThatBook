package allthatbook.mvc.controller;

import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.model.service.UserService;
import allthatbook.mvc.view.FailView;
import allthatbook.mvc.view.MenuView;

public class UserController {
	static UserService userService = new UserService();
	/**
	 * �α���
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
}
