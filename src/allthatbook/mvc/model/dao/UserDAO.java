package allthatbook.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.util.DbUtil;

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
	

	int deleteUserInfo(User user) throws SQLException;

	
	List<User> allSelect() throws SQLException ;

	/**
	 * userNo�� ��ȸ
	 */
	User selectByUserNo(int userNo) throws SQLException;

	/**
	 * userId�� ��ȸ
	 */
	User selectByUserId(String userId) throws SQLException;
}
