package allthatbook.mvc.model.dao;


import java.sql.SQLException;

import allthatbook.mvc.model.dto.User;


public class UserDAOImpl implements UserDAO {

	/**
	 * 회원가입
	 */
	@Override
	public int SignUp(User user) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 로그인
	 */
	@Override
	public User login(String userId, String userPwd) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	


}
