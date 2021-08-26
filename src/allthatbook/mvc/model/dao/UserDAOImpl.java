package allthatbook.mvc.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.util.DbUtil;


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
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("select * from users where 회원ID=? and 패스워드=?");
			ps.setString(1, userId);
			ps.setString(2, userPwd);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			}
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return user;
	}

	


}
