package allthatbook.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import allthatbook.mvc.model.dto.Reservation;
import allthatbook.mvc.util.DbUtil;

public class ReservationDAOImpl implements ReservationDAO {

	/**
	 * �����ϱ�
	 * ���� ������ å���� Ȯ��
	 * ������ �ߺ��ؼ� ������ �� �����Ƿ� �ߺ�üũ
	 * ������ �Ѹ� �����ϹǷ� �켱 �������̺� å��ȣ�� �ش��ϴ� ������ �ִ��� Ȯ��
	 * ���� ������ �������� �������� �����ϹǷ� book_state�� 1������ Ȯ��
	 * Ȯ���� ���� ������ �������̺� �־��ش�.
	 * */	
	public int insertReservation(Reservation reservation) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "insert into reservation values(reservation_seq_no.nextval, ?, ?, sysdate)";
		int result = 0;

		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, reservation.getBookNo());
			ps.setInt(2, reservation.getUserNo());
			// 1. �ش� å��ȣ�� ȸ����ȣ�� �ش��ϴ� ������ ���� ���̺� �����ϴ��� Ȯ��
			int chk = chkDuplicate(con, reservation);
			if (chk == 1)
				throw new SQLException("*** �������� �̹� �����߽��ϴ�. ***");
			
			// 2. bookstate�� 1���� Ȯ��
			chk = getBookState(con, reservation);
			if (chk != 1) throw new SQLException("*** �������� ������ ������ �� �ֽ��ϴ�. ���Ⱑ���ϰų� �������� ������ ������ �� �����ϴ�. ***");
			
			//3. �������� ����� �������� Ȯ��
			chk = getUserState(con, reservation);
			if (chk == reservation.getUserNo()) throw new SQLException("*** ������ ������ ������ ������ �� �����ϴ�. ***");
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		return result;
	}

	/**
	 * 1. ���⿡�� ���������� ������ å�� �����ϸ� �������̺��� ���� --> ���⿡�� connection �μ��� �޾Ƽ� 2. �������̺���
	 * �ٷ� ����
	 */
	@Override
	public int deleteReservation(int userNo, int bookNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "delete from reservation where ȸ����ȣ = ? and å��ȣ = ?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, userNo);
			ps.setInt(2, bookNo);
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(con, ps);
		}
		return result;
	}

	@Override
	public int deleteReservation(Connection con, Reservation reservation) throws SQLException {
		PreparedStatement ps = null;
		int result = 0;
		String sql = "delete from reservation where ȸ����ȣ = ? and å��ȣ = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, reservation.getUserNo());
			ps.setInt(2, reservation.getBookNo());
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(null, ps);
		}
		return result;
	}

	/**
	 * �ߺ��� å�� �����ϴ��� üũ���ִ� �޼ҵ�
	 */
	public int chkDuplicate(Connection con, Reservation reservation) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select �����ȣ, å��ȣ, ȸ����ȣ, to_char(��������, 'yy/mm/dd')�������� from reservation"
				+ " where å��ȣ = ? and ȸ����ȣ = ? ";
		int result = 0;
		int bookNo = reservation.getBookNo();
		int userNo = reservation.getUserNo();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, bookNo);
			ps.setInt(2, userNo);
			rs = ps.executeQuery();
			if (rs.next())
				result = 1;
		} finally {
			DbUtil.close(null, ps, rs);
		}
		return result;
	}
	
	
	public int getBookState(Connection con, Reservation reservation) throws SQLException{
		  PreparedStatement ps=null;
		  ResultSet rs = null;
		  String sql="select ���� from books where å��ȣ=?";
		  int result = 0;
		 try {
			 ps = con.prepareStatement(sql);
			 ps.setInt(1, reservation.getBookNo());
			 rs = ps.executeQuery();
			 if (rs.next()) result = rs.getInt(1);
		  }	   
      finally {
  	    DbUtil.close(null, ps , rs);
      }
		
		return result;
	}//�޼ҵ� ��

	
	public int getUserState(Connection con, Reservation reservation) throws SQLException{
		  PreparedStatement ps=null;
		  ResultSet rs = null;
		  String sql = "select * from rental where ȸ����ȣ = ? and å��ȣ = ? and �ݳ����� = 0";
		  int result = 0;
		 try {
			 ps = con.prepareStatement(sql);
			 ps.setInt(1, reservation.getUserNo());
			 ps.setInt(2, reservation.getBookNo());
			 rs = ps.executeQuery();
			 if (rs.next()) { 
				 result = rs.getInt("ȸ����ȣ");
				 }
		  }	   
    finally {
	    DbUtil.close(null, ps , rs);
    }
		
		return result;
	}//�޼ҵ� ��

}
