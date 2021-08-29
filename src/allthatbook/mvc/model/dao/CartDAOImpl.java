package allthatbook.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	public int insertBook(int bookNo, Cart cart) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "insert into cart_detail values(?, ?)";
		int result = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			int cartId = cart.getCartId();
			ps.setInt(1, cartId);
			ps.setInt(2, bookNo);
			result = ps.executeUpdate();
			CartDetail cartDetail = new CartDetail(cartId, bookNo);
			cart.addCartDetail(cartDetail);
		}finally {
			DbUtil.close(con, ps);
		}
		return result;
	}
	
	
	
	
	/**
	 * 장바구니가 추가하는 메소드
	 * */
	@Override
	public int cartInsert(int userNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO CART(장바구니번호, 회원번호) VALUES(CART_SEQ_NO.NEXTVAL, ?)";
		int result = 0;
		int chk = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, userNo);
		}finally {
			DbUtil.close(con, ps, null);
		}
		return result;
	}
	
	/**
	장바구니 테이블에 회원에 해당하는 장바구니가 있는지 회원번호로 확인
	장바구니 존재하면 1
    장바구니 존재하지 않으면 0
	*/
	@Override
	public int checkCart(int userNo) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select 회원번호 from cart where 회원번호 = ?";
		int result = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, userNo);
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
	public List<CartDetail> selectCartDetail(Cart cart) {
		return cart.getCartDetailList();
	}
	
	
	
	/**
	 * 장바구니에서 대출된 책들을
	 * 대여상세테이블에서 삭제하는 메소드
	 * */
	@Override
	public int deleteCartDetail(Cart cart, CartDetail cartDetail) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "delete from cart_detail where 장바구니번호 = ? and 책번호 = ?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, cartDetail.getCartId());
			ps.setInt(2,  cartDetail.getBookNo());
			result = ps.executeUpdate();
			cart.removeCartDetail(cartDetail);
		}
	    finally {
	    	DbUtil.close(null, ps);
	    }
		return result;
	}



	/**
	 * 테이블에서 회원번호에 해당하는 장바구니 불러와서 장바구니 객체 리턴하기
	 * */
	@Override
	public Cart createCartFromTable(int userNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cart cart = null;
		String sql = "select * from cart where 회원번호 = ?";
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, userNo);
			rs = ps.executeQuery();
			int cartNo = rs.getInt(1);
			cart = new Cart(cartNo, userNo);
			cart.setCartDetailList(getCartDetailFromTable(cartNo));
		}
	    finally {
	    	DbUtil.close(con, ps, rs);
	    }
		return cart;
	}
	
	
	/**
	 * 카트번호에 해당하는 cart_detail_list들 return하기
	 * */
	public List<CartDetail> getCartDetailFromTable(int cartNo) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from cart_detail where 장바구니번호 = ?";
		List<CartDetail> list = new ArrayList<CartDetail>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, cartNo);
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				CartDetail cartDetail = new CartDetail(rs.getInt(1), rs.getInt(2));
				list.add(cartDetail);
			}
		}finally {
			DbUtil.close(con, ps, rs);
		}
		return list;
	}
	
	
}
