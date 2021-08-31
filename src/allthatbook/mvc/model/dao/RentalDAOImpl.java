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
	 * 대여하기 1) 책상태 확인하기 (0이면 대여가능) 2) Rental 테이블에 insert 3) 책상태 변경하기 (1로 수정)
	 */

	@Override
	public int rentalInsert(Rental rental) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO RENTAL(대여번호, 책번호, 회원번호, 반납예정일자, 대여일자, 반납일자)"
				+ " VALUES(RENTAL_SEQ_NO.NEXTVAL, ?, ?, sysdate+14, sysdate, null)";
		int result = 0;
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false); // 자동커밋해지

			ps = con.prepareStatement(sql);
			ps.setInt(1, rental.getBookNo());
			ps.setInt(2, rental.getUserNo());
			result = ps.executeUpdate();
			if (result == 0) { //
				con.rollback();
				// throw new SQLException("대여 실패");
			} else {
				// 책 꺼내오고 책 상태에 따라 진행

				int re = getBookState(con, rental);

				if (re == 0) {
					// 대여진행 --> 책상태 1로 변경
					changeBookState(con, rental, 1);
				} else if (re == 1) {
					result = 0;
					con.rollback();
					throw new SQLException(rental.getBookNo() + "는대출중인도서");
				} else {
					// 상태가 2인 경우
					// 예약 테이블에선 삭제 시켜줘야한다.
					// 2 --> 1
					if (checkReservation(con, rental) == rental.getUserNo()) {// 예약된 회원번호와 대여하려는 회원의 번호가 같으면 대출진행
						changeBookState(con, rental, 1);
						Reservation reservation = new Reservation(rental.getBookNo(), rental.getUserNo());
						if (reservationDAO.deleteReservation(con, reservation) == 0) {
							con.rollback();
							throw new SQLException("예약된 도서입니다.");
						}
					} else {
						con.rollback();
						throw new SQLException("예약된 도서입니다.");
					}
				}
			} // else끝
		} finally {
			con.commit();
			DbUtil.close(con, ps, null);
		}
		return result;
	}// 메소드끝

	public int rentalInsert(Cart cart, Rental rental) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO RENTAL(대여번호, 책번호, 회원번호, 반납예정일자, 대여일자, 반납일자)"
				+ " VALUES(RENTAL_SEQ_NO.NEXTVAL, ?, ?, sysdate+14, sysdate, null)";
		int result = 0;
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false); // 자동커밋해지

			ps = con.prepareStatement(sql);
			ps.setInt(1, rental.getBookNo());
			ps.setInt(2, rental.getUserNo());
			result = ps.executeUpdate();
			if (result == 0) { //
				con.rollback();
			} else {
				// 책 꺼내오고 책 상태에 따라 진행

				int re = getBookState(con, rental);

				if (re == 0) {
					// 대여진행 --> 책상태 1로 변경
					changeBookState(con, rental, 1);
					CartDetail cartDetail = new CartDetail(cart.getCartId(), rental.getBookNo());
					cartDAO.deleteCartDetail(con, cart, cartDetail);
				} else if (re == 1) {
					result = 0;
					con.rollback();
					throw new SQLException(rental.getBookNo() + "는대출중인도서");
				} else {
					// 상태가 2인 경우
					// 예약 테이블에선 삭제 시켜줘야한다.
					// 2 --> 1
					if (checkReservation(con, rental) == rental.getUserNo()) {// 예약된 회원번호와 대여하려는 회원의 번호가 같으면 대출진행
						changeBookState(con, rental, 1);
						CartDetail cartDetail = new CartDetail(cart.getCartId(), rental.getBookNo());
						cartDAO.deleteCartDetail(con, cart, cartDetail);
						Reservation reservation = new Reservation(rental.getBookNo(), rental.getUserNo());
						if (reservationDAO.deleteReservation(con, reservation) == 0) {
							con.rollback();
							throw new SQLException("책번호 " +rental.getBookNo() + "는 예약된 도서입니다.");
						}
					} else {
						con.rollback();
						throw new SQLException("책번호 " +rental.getBookNo() + "는 예약된 도서입니다.");
					}
				} // else끝
			}
		} finally {
			con.commit();
			DbUtil.close(con, ps, null);
		}
		return result;
	}

	/**
	 * 책상태(bookState) 가져오기
	 */
	public int getBookState(Connection con, Rental rental) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select 상태 from books where 책번호=?";

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
	}// 메소드 끝

	/**
	 * 대출한 도서 책상태 1로 바꿔주기
	 */
	public int changeBookState(Connection con, Rental rental, int state) throws SQLException {
		PreparedStatement ps = null;
		String sql = "update books set 상태 = ? where 책번호 = ?";
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
	}// 메소드 끝

	/**
	 * 도서를 반납하는 메소드 rental을 인수로 받는다. (컨트롤러쪽에서 user랑 book or bookNo) 해당 rental의
	 * userNo, bookNo, bookstate(1)를 가지는 rental이 테이블에 있는지 확인한다 없으면 예외발생(대여인 도서가
	 * 아닙니다.) 있으면 반납진행 반납일자, 반납여부, 연체여부 변경, bookstate변경
	 */
	@Override
	public int returnBook(Rental rental) throws SQLException {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "select 대여번호, 책번호, 회원번호, "
				+ "반납예정일자, 대여일자, 반납일자, "
				+ "반납여부, 연체여부 from rental where 책번호 = ? and 회원번호 = ? and 반납여부 = 0";
		ResultSet rs = null;
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false); // 자동커밋해지
			ps = con.prepareStatement(sql);
			ps.setInt(1, rental.getBookNo());
			ps.setInt(2, rental.getUserNo());
			rs = ps.executeQuery();
			if (rs.next()) {
				// 대여중인 도서가 존재하므로 반납진행
				rental = new Rental(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4));
				int ur = updateRental(con, rental);
				if (ur == 1) {
					// 해당하는 책번호 상태 변경, 만약 예약자가 존재하면 상태 2로, 존재하지 않으면 0으로
					int chkRez = checkReservation(con, rental); // 1이면 예약존재
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
				throw new SQLException("대여중인 도서가 아닙니다.");
			}
		} finally {
			con.commit();
			DbUtil.close(con, ps, rs);
		}
		return result;
	}

	/**
	 * 반납하는 과정에서 선택된 rental의 반납일자, 반납여부, 연체여부를 바꿔주는 메소드
	 */
	public int updateRental(Connection con, Rental rental) throws SQLException {
		PreparedStatement ps = null;
		int result = 0;
		String sql = "update rental set 반납일자 = sysdate, 반납여부 = 1, 연체여부 = ? where 회원번호 = ? and 책번호 = ? and 반납여부 = 0";
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
	 * 책번호에 해당하는 예약자가 존재하는지 확인하고 예약자의 회원번호 리턴
	 */
	public int checkReservation(Connection con, Rental rental) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select 예약번호, 책번호, 회원번호, to_char(예약일자, 'yy/mm/dd')예약일자 from reservation where 책번호 = ?";
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, rental.getBookNo());
			rs = ps.executeQuery();
			if (rs.next())
				result = rs.getInt("회원번호");
		} finally {
			DbUtil.close(null, ps, rs);
		}
		return result;
	}
}// 클래스 끝
