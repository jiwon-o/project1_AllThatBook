package allthatbook.mvc.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.Cart;
import allthatbook.mvc.model.dto.CartDetail;

public interface CartDAO {
	/**
	å�� ��ٱ��Ͽ� �߰��Ҷ�,
	���� ��ٱ��ϰ� �������� �ʴ´ٸ�
	��ٱ��� ���̺� ��ٱ��� �߰�
    */
	int cartInsert(int userNo) throws SQLException;
	
	
	int insertBook(int bookNo, Cart cart) throws SQLException;
	
	int deleteCartDetail(Cart cart, CartDetail cartDetail) throws SQLException;
	int deleteCartDetail(Connection con, Cart cart, CartDetail cartDetail) throws SQLException;
	
	int checkCart(int userNo) throws SQLException;
	
	Cart createCartFromTable(int userNo) throws SQLException;
}
