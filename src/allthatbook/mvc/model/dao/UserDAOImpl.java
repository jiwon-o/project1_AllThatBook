package allthatbook.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.util.DbUtil;

public class UserDAOImpl implements UserDAO {

	/**
	 * �α���
	 */
	@Override
	public User login(String userId, String userPwd) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("select * from users where ȸ��ID=? and �н�����=?");
			ps.setString(1, userId);
			ps.setString(2, userPwd);

			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return user;
	}

	/**
	 * ȸ������
	 */
	@Override
	public int register(User user) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(
					"insert into users(ȸ����ȣ, ȸ��ID, �н�����, ȸ���̸�, ����ó, �������) values (user_seq_no.nextval, ?, ?, ?, ?, sysdate)");
			ps.setString(1, user.getUserId());
			ps.setString(2, user.getUserPwd());
			ps.setString(3, user.getUserName());
			ps.setString(4, user.getUserPhone());

			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		return result;
	}

	/**
	 * ȸ������ �����ϱ�
	 */
	@Override
	public int updateUserInfo(User user) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("update users set �н����� = ?,����ó = ? where ȸ��ID =?");
			ps.setString(1, user.getUserPwd());
			ps.setString(2, user.getUserPhone());
			ps.setString(3, user.getUserId());

			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		return result;
	}

	/**
	 * ȸ��Ż��
	 */
	@Override
	public int deleteUserInfo(User user) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement("delete from users where ȸ��ID =? and �н����� =?");
			ps.setString(1, user.getUserId());
			ps.setString(2, user.getUserPwd());

			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		return result;
	}

	/**
	 * ��üȸ�� ��ȸ
	 */
	public List<User> allSelect() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<>();
		String sql = "select*from users";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
				userList.add(user);
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return userList;
	}

	/**
	 * userNo�� ��ȸ
	 */
	public User selectByUserNo(int userNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = new User();
		String sql = "select*from users where ȸ����ȣ=?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, userNo);
			rs = ps.executeQuery();

			if (rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return user;
	}

	/**
	 * userId�� ��ȸ
	 */
	public User selectByUserId(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = new User();
		String sql = "select*from users where ȸ��ID=?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();

			if (rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
			}
		} finally {
			DbUtil.close(con, ps, rs);
		}
		return user;
	}
	
}
