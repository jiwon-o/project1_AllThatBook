package allthatbook.mvc.model.dao;

import java.sql.SQLException;

import allthatbook.mvc.model.dto.User;

public interface UserDAO {
	
	/**
	 * �α����ϱ�
	 * */
	User login(String userId, String userPwd) throws SQLException;
	
	/**
	 * ȸ������
	 */
	int register(User user) throws SQLException;
	
	/**
	 * ȸ�������ϱ�
	 */
	int updateUserInfo(User user) throws SQLException;
	
	/**
	 * ȸ��Ż���ϱ� 
	 */
	
	
	
}
