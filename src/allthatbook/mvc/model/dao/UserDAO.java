package allthatbook.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import allthatbook.mvc.model.dto.User;

public interface UserDAO {
	
	/**
	 * 로그인하기
	 * */
	User login(String userId, String userPwd) throws SQLException;
	
	/**
	 * 회원가입
	 */
	int register(User user) throws SQLException;
	
	/**
	 * 회원수정하기
	 */
	int updateUserInfo(User user) throws SQLException;


	/**
	 * 회원탈퇴하기 
	 */
	
	int revoke(User user) throws SQLException;

	List<User> allSelect() throws SQLException;
	
}
