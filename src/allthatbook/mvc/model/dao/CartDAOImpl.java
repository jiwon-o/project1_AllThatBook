package allthatbook.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.Cart;
import allthatbook.mvc.model.dto.CartDetail;
import allthatbook.mvc.util.DbUtil;

public class CartDAOImpl implements CartDAO {
	
	
	
	/**
	장바구니에 책을 추가하는 메소드
	cart객체에 있는 detail_list에 추가
	CART_DETAIL
    */
	@Override
	public int insertBook(Book book, Cart cart) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "insert into cart_detail values(?, ?)";
		int result = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, cart.getCartId());
			ps.setInt(2, book.getBookNo());
			result = ps.executeUpdate();
		}finally {
			DbUtil.close(con, ps);
		}
		return result;
	}
	
	
	
	/**
	 * 장바구니의 책을 대출하는 메소드
	 * 1. 장바구니의 책들을 전부 대출한다.
	 * 2. 대출된 장바구니상세에 있는 CartDetail들을 삭제한다.
	 * */
//	public int rentalCartBook(Cart cart) {
//		Connection con = null;
//		PreparedStatement ps = null;
//		String sql = "insert in"
//		
//		return 0;
//	}
	
	
	/**
	 * 장바구니가 존재하지 않는다면 추가하는 메소드
	 * 
	 * */
	@Override
	public int cartInsert(Cart cart) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO CART(장바구니번호, 회원번호) VALUES(CART_SEQ_NO, ?)";
		int result = 0;
		int chk = 0;
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false); //자동커밋해지
			chk = checkCart(con, cart);
			ps = con.prepareStatement(sql);
			ps.setInt(1, cart.getUserNo());
			if (chk ==1) { //chk가 1이면 해당 회원번호에 해당하는 장바구니 존재
				con.rollback();
			}
		}finally {
			con.commit();
			DbUtil.close(con, ps, null);
		}
		return result;
	}
	
	/**
	장바구니에 회원에 해당하는 장바구니가 있는지 회원번호로 확인
	장바구니 존재하면 1
    장바구니 존재하지 않으면 0 --> 장바구니 생성
	*/
	public int checkCart(Connection con, Cart cart) throws SQLException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select 회원번호 from cart where 회원번호 = ?";
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, cart.getUserNo());
			rs = ps.executeQuery();
			if (rs.next()) result =  1;
		}
	    finally {
	    	DbUtil.close(null, ps, rs);
	    }
		return result;
	}
    
	
	/**
	 * 대여상세정보 가져오기
	 * */
	public List<CartDetail> selectCartDetail(Cart cart) throws SQLException {
		return null;
	}
}
