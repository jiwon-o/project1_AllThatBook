package allthatbook.mvc.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import allthatbook.mvc.model.dto.Cart;
import allthatbook.mvc.model.dto.CartDetail;
import allthatbook.mvc.model.dto.Rental;
import allthatbook.mvc.model.dto.Reservation;
import allthatbook.mvc.util.DbUtil;

public class RentalDAOImpl implements RentalDAO {
	CartDAO cartDAO = new CartDAOImpl();

	ReservationDAO reservationDAO = new ReservationDAOImpl();

	/**
	 * �뿩�ϱ� 1) å���� Ȯ���ϱ� (0�̸� �뿩����) 2) Rental ���̺� insert 3) å���� �����ϱ� (1�� ����)
	 */

	@Override
	public int rentalInsert(Rental rental) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO RENTAL(�뿩��ȣ, å��ȣ, ȸ����ȣ, �ݳ���������, �뿩����, �ݳ�����)"
				+ " VALUES(RENTAL_SEQ_NO.NEXTVAL, ?, ?, sysdate+14, sysdate, null)";
		int result = 0;
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false); // �ڵ�Ŀ������

			ps = con.prepareStatement(sql);
			ps.setInt(1, rental.getBookNo());
			ps.setInt(2, rental.getUserNo());
			result = ps.executeUpdate();
			if (result == 0) { //
				con.rollback();
				// throw new SQLException("�뿩 ����");
			} else {
				// å �������� å ���¿� ���� ����

				int re = getBookState(con, rental);

				if (re == 0) {
					// �뿩���� --> å���� 1�� ����
					changeBookState(con, rental, 1);
				} else if (re == 1) {
					result = 0;
					con.rollback();
					throw new SQLException(rental.getBookNo() + "�´������ε���");
				} else {
					// ���°� 2�� ���
					// ���� ���̺��� ���� ��������Ѵ�.
					// 2 --> 1
					if (checkReservation(con, rental) == rental.getUserNo()) {// ����� ȸ����ȣ�� �뿩�Ϸ��� ȸ���� ��ȣ�� ������ ��������
						changeBookState(con, rental, 1);
						Reservation reservation = new Reservation(rental.getBookNo(), rental.getUserNo());
						if (reservationDAO.deleteReservation(con, reservation) == 0) {
							con.rollback();
							throw new SQLException("����� �����Դϴ�.");
						}
					} else {
						con.rollback();
						throw new SQLException("����� �����Դϴ�.");
					}
				}
			} // else��
		} finally {
			con.commit();
			DbUtil.close(con, ps, null);
		}
		return result;
	}// �޼ҵ峡

	public int rentalInsert(Cart cart, Rental rental) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO RENTAL(�뿩��ȣ, å��ȣ, ȸ����ȣ, �ݳ���������, �뿩����, �ݳ�����)"
				+ " VALUES(RENTAL_SEQ_NO.NEXTVAL, ?, ?, sysdate+14, sysdate, null)";
		int result = 0;
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false); // �ڵ�Ŀ������

			ps = con.prepareStatement(sql);
			ps.setInt(1, rental.getBookNo());
			ps.setInt(2, rental.getUserNo());
			result = ps.executeUpdate();
			if (result == 0) { //
				con.rollback();
			} else {
				// å �������� å ���¿� ���� ����

				int re = getBookState(con, rental);

				if (re == 0) {
					// �뿩���� --> å���� 1�� ����
					changeBookState(con, rental, 1);
					CartDetail cartDetail = new CartDetail(cart.getCartId(), rental.getBookNo());
					cartDAO.deleteCartDetail(con, cart, cartDetail);
				} else if (re == 1) {
					result = 0;
					con.rollback();
					throw new SQLException(rental.getBookNo() + "�´������ε���");
				} else {
					// ���°� 2�� ���
					// ���� ���̺��� ���� ��������Ѵ�.
					// 2 --> 1
					if (checkReservation(con, rental) == rental.getUserNo()) {// ����� ȸ����ȣ�� �뿩�Ϸ��� ȸ���� ��ȣ�� ������ ��������
						changeBookState(con, rental, 1);
						CartDetail cartDetail = new CartDetail(cart.getCartId(), rental.getBookNo());
						cartDAO.deleteCartDetail(con, cart, cartDetail);
						Reservation reservation = new Reservation(rental.getBookNo(), rental.getUserNo());
						if (reservationDAO.deleteReservation(con, reservation) == 0) {
							con.rollback();
							throw new SQLException("å��ȣ " +rental.getBookNo() + "�� ����� �����Դϴ�.");
						}
					} else {
						con.rollback();
						throw new SQLException("å��ȣ " +rental.getBookNo() + "�� ����� �����Դϴ�.");
					}
				} // else��
			}
		} finally {
			con.commit();
			DbUtil.close(con, ps, null);
		}
		return result;
	}

	/**
	 * å����(bookState) ��������
	 */
	public int getBookState(Connection con, Rental rental) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select ���� from books where å��ȣ=?";

		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, rental.getBookNo());
			rs = ps.executeQuery();
			if (rs.next())
				result = rs.getInt(1);
		} finally {
			DbUtil.close(null, ps, rs);
		}
		return result;
	}// �޼ҵ� ��

	/**
	 * ������ ���� å���� 1�� �ٲ��ֱ�
	 */
	public int changeBookState(Connection con, Rental rental, int state) throws SQLException {
		PreparedStatement ps = null;
		String sql = "update books set ���� = ? where å��ȣ = ?";
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, state);
			ps.setInt(2, rental.getBookNo());
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(null, ps);
		}
		return result;
	}// �޼ҵ� ��

	/**
	 * ������ �ݳ��ϴ� �޼ҵ� rental�� �μ��� �޴´�. (��Ʈ�ѷ��ʿ��� user�� book or bookNo) �ش� rental��
	 * userNo, bookNo, bookstate(1)�� ������ rental�� ���̺� �ִ��� Ȯ���Ѵ� ������ ���ܹ߻�(�뿩�� ������
	 * �ƴմϴ�.) ������ �ݳ����� �ݳ�����, �ݳ�����, ��ü���� ����, bookstate����
	 */
	@Override
	public int returnBook(Rental rental) throws SQLException {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "select �뿩��ȣ, å��ȣ, ȸ����ȣ, "
				+ "�ݳ���������, �뿩����, �ݳ�����, "
				+ "�ݳ�����, ��ü���� from rental where å��ȣ = ? and ȸ����ȣ = ? and �ݳ����� = 0";
		ResultSet rs = null;
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false); // �ڵ�Ŀ������
			ps = con.prepareStatement(sql);
			ps.setInt(1, rental.getBookNo());
			ps.setInt(2, rental.getUserNo());
			rs = ps.executeQuery();
			if (rs.next()) {
				// �뿩���� ������ �����ϹǷ� �ݳ�����
				rental = new Rental(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4));
				int ur = updateRental(con, rental);
				if (ur == 1) {
					// �ش��ϴ� å��ȣ ���� ����, ���� �����ڰ� �����ϸ� ���� 2��, �������� ������ 0����
					int chkRez = checkReservation(con, rental); // 1�̸� ��������
					if (chkRez > 0) {
						changeBookState(con, rental, 2);
					} else {
						changeBookState(con, rental, 0);
					}
					result = 1;
				} else {
					con.rollback();
				}
			} else {
				throw new SQLException("�뿩���� ������ �ƴմϴ�.");
			}
		} finally {
			con.commit();
			DbUtil.close(con, ps, rs);
		}
		return result;
	}

	/**
	 * �ݳ��ϴ� �������� ���õ� rental�� �ݳ�����, �ݳ�����, ��ü���θ� �ٲ��ִ� �޼ҵ�
	 */
	public int updateRental(Connection con, Rental rental) throws SQLException {
		PreparedStatement ps = null;
		int result = 0;
		String sql = "update rental set �ݳ����� = sysdate, �ݳ����� = 1, ��ü���� = ? where ȸ����ȣ = ? and å��ȣ = ? and �ݳ����� = 0";
		Date date = new Date(LocalDate.now().getYear() - 1900, LocalDate.now().getMonthValue() - 1,	LocalDate.now().getDayOfMonth());
		try {
			ps = con.prepareStatement(sql);
			if (rental.getExreturnDate().after(date)) {
				ps.setInt(1, 0);
			} else {
				ps.setInt(1, 1);
			}
			ps.setInt(2, rental.getUserNo());
			ps.setInt(3, rental.getBookNo());
			result = ps.executeUpdate();
		} finally {
			DbUtil.close(null, ps);
		}
		return result;
	}

	/**
	 * å��ȣ�� �ش��ϴ� �����ڰ� �����ϴ��� Ȯ���ϰ� �������� ȸ����ȣ ����
	 */
	public int checkReservation(Connection con, Rental rental) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select �����ȣ, å��ȣ, ȸ����ȣ, to_char(��������, 'yy/mm/dd')�������� from reservation where å��ȣ = ?";
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, rental.getBookNo());
			rs = ps.executeQuery();
			if (rs.next())
				result = rs.getInt("ȸ����ȣ");
		} finally {
			DbUtil.close(null, ps, rs);
		}
		return result;
	}
}// Ŭ���� ��
