package allthatbook.mvc.model.dao;

import java.sql.SQLException;

import allthatbook.mvc.model.dto.User;

public interface UserDAO {
	/**
	 * ȸ������
	 */
	int SignUp(User user) throws SQLException;
	
	/**
	 * �α����ϱ�
	 * */
	User login(String userId, String userPwd) throws SQLException;
}
