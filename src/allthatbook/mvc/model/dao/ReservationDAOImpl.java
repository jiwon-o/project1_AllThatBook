package allthatbook.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.SQLException;

import allthatbook.mvc.model.dto.Reservation;
import allthatbook.mvc.util.DbUtil;

public class ReservationDAOImpl implements ReservationDAO {

	
	
	/**
	 * �����ϱ�
	 * ������ �Ѹ� �����ϹǷ� �켱 �������̺� å��ȣ�� �ش��ϴ� ������ �ִ��� Ȯ��
	 * ���� ������ �������� �������� �����ϹǷ� book_state�� 1������ Ȯ��
	 * Ȯ���� ���� ������ �������̺� �־��ش�.
	 * */	
	@Override
	public int insertReservation(Reservation reservation) throws SQLException {
//		Connection con = null;
//		PreparedStatement ps = null;
//		String sql = "insert into reservation values(reservation_seq_no.nextval, ?, ?, sysdate )";
//		int result = 0;
//		
//		try {
//			con = DbUtil.getConnection();
//			ps = con.prepareStatement(sql);
//			ps.setInt(1, reservation.getBookNo());
//			ps.setInt(2, reservation.getUserNo());
//			result = ps.executeUpdate();
//		}
//		
		return 0;
	}

	 /**
	 * 1. ���⿡�� ���������� ������ å�� �����ϸ� �������̺��� ���� --> ���⿡�� connection �μ��� �޾Ƽ�
	 * 2. �������̺��� �ٷ� ����	  
	 * */
	
	@Override
	public int deleteReservation(Reservation reservation) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReservation(Connection con, Reservation reservation) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
