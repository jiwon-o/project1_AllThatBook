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
	��ٱ��Ͽ� å�� �߰��ϴ� �޼ҵ�
	cart��ü�� �ִ� detail_list�� �߰�
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
	 * ��ٱ����� å�� �����ϴ� �޼ҵ�
	 * 1. ��ٱ����� å���� ���� �����Ѵ�.
	 * 2. ����� ��ٱ��ϻ󼼿� �ִ� CartDetail���� �����Ѵ�.
	 * */
//	public int rentalCartBook(Cart cart) {
//		Connection con = null;
//		PreparedStatement ps = null;
//		String sql = "insert in"
//		
//		return 0;
//	}
	
	
	/**
	 * ��ٱ��ϰ� �������� �ʴ´ٸ� �߰��ϴ� �޼ҵ�
	 * 
	 * */
	@Override
	public int cartInsert(Cart cart) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO CART(��ٱ��Ϲ�ȣ, ȸ����ȣ) VALUES(CART_SEQ_NO, ?)";
		int result = 0;
		int chk = 0;
		try {
			con = DbUtil.getConnection();
			con.setAutoCommit(false); //�ڵ�Ŀ������
			chk = checkCart(con, cart);
			ps = con.prepareStatement(sql);
			ps.setInt(1, cart.getUserNo());
			if (chk ==1) { //chk�� 1�̸� �ش� ȸ����ȣ�� �ش��ϴ� ��ٱ��� ����
				con.rollback();
			}
		}finally {
			con.commit();
			DbUtil.close(con, ps, null);
		}
		return result;
	}
	
	/**
	��ٱ��Ͽ� ȸ���� �ش��ϴ� ��ٱ��ϰ� �ִ��� ȸ����ȣ�� Ȯ��
	��ٱ��� �����ϸ� 1
    ��ٱ��� �������� ������ 0 --> ��ٱ��� ����
	*/
	public int checkCart(Connection con, Cart cart) throws SQLException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select ȸ����ȣ from cart where ȸ����ȣ = ?";
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
	 * �뿩������ ��������
	 * */
	public List<CartDetail> selectCartDetail(Cart cart) throws SQLException {
		return null;
	}
}
