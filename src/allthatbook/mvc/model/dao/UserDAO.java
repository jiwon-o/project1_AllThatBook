package allthatbook.mvc.model.dao;

import java.sql.SQLException;

import allthatbook.mvc.model.dto.User;

public interface UserDAO {
	/**
	 * 회원가입
	 */
	int SignUp(User user) throws SQLException;
	
	/**
	 * 로그인하기
	 * */
	User login(String userId, String userPwd) throws SQLException;
}
