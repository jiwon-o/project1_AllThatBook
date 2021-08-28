package allthatbook.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

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
	
	int revoke(User user) throws SQLException;

	List<User> allSelect() throws SQLException;
	
}
