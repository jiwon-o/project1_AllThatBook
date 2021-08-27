package allthatbook.mvc.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.util.DbUtil;


public class UserDAOImpl implements UserDAO {
	
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

	/**
	 * 회원가입
	 */
	@Override
	public int register(User user) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		//insert into board (board_no, subject, writer, content, board_date) values (board_seq.nextval, ?, ?, ?, sysdate)
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("insert into users(회원번호, 회원ID, 패스워드, 회원이름, 연락처, 등록일자) values (user_seq_no.nextval, ?, ?, ?, ?, sysdate)");
			ps.setString(1, user.getUserId());
			ps.setString(2, user.getUserPwd());
			ps.setString(3, user.getUserName());
			ps.setString(4, user.getUserPhone());
			
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps);
		}
		return result;
	}

	@Override
	public int updateUserInfo(User user) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("update users set 패스워드 = ?,연락처 = ? where 회원ID =?");
			ps.setString(1, user.getUserPwd());
			ps.setString(2, user.getUserPhone());
			ps.setString(3, user.getUserId());
			
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps);
		}
		return result;
	}

	
	
	

}
