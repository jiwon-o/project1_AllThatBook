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
	��ٱ��Ͽ� å�� �߰��ϴ� �޼ҵ�
	cart��ü�� �ִ� detail_list�� �߰�
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
	 * ��ٱ��ϰ� �߰��ϴ� �޼ҵ�
	 * */
	@Override
	public int cartInsert(int userNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO CART(��ٱ��Ϲ�ȣ, ȸ����ȣ) VALUES(CART_SEQ_NO.NEXTVAL, ?)";
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
	��ٱ��� ���̺� ȸ���� �ش��ϴ� ��ٱ��ϰ� �ִ��� ȸ����ȣ�� Ȯ��
	��ٱ��� �����ϸ� 1
    ��ٱ��� �������� ������ 0
	*/
	@Override
	public int checkCart(int userNo) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select ȸ����ȣ from cart where ȸ����ȣ = ?";
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
	 * �뿩������ ��������
	 * */
	public List<CartDetail> selectCartDetail(Cart cart) {
		return cart.getCartDetailList();
	}
	
	
	
	/**
	 * ��ٱ��Ͽ��� ����� å����
	 * �뿩�����̺��� �����ϴ� �޼ҵ�
	 * */
	@Override
	public int deleteCartDetail(Cart cart, CartDetail cartDetail) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "delete from cart_detail where ��ٱ��Ϲ�ȣ = ? and å��ȣ = ?";
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
	 * ���̺��� ȸ����ȣ�� �ش��ϴ� ��ٱ��� �ҷ��ͼ� ��ٱ��� ��ü �����ϱ�
	 * */
	@Override
	public Cart createCartFromTable(int userNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cart cart = null;
		String sql = "select * from cart where ȸ����ȣ = ?";
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
	 * īƮ��ȣ�� �ش��ϴ� cart_detail_list�� return�ϱ�
	 * */
	public List<CartDetail> getCartDetailFromTable(int cartNo) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from cart_detail where ��ٱ��Ϲ�ȣ = ?";
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
