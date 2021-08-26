package allthatbook.mvc.model.service;

import java.sql.SQLException;

import allthatbook.mvc.exception.NotFoundException;
import allthatbook.mvc.model.dto.User;

public interface UserService {
	/**
	 * ȸ������
	 */
	void SignUp(User user) throws SQLException;
	
	/**
	 * �α����ϱ�
	 * */
	User login(String userId, String userPwd) throws NotFoundException, SQLException;
}
