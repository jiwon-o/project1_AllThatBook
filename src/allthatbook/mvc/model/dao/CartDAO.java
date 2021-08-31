package allthatbook.mvc.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.Cart;
import allthatbook.mvc.model.dto.CartDetail;

public interface CartDAO {
	/**
	책을 장바구니에 추가할때,
	만약 장바구니가 존재하지 않는다면
	장바구니 테이블에 장바구니 추가
    */
	int cartInsert(int userNo) throws SQLException;
	
	
	int insertBook(int bookNo, Cart cart) throws SQLException;
	
	int deleteCartDetail(Cart cart, CartDetail cartDetail) throws SQLException;
	int deleteCartDetail(Connection con, Cart cart, CartDetail cartDetail) throws SQLException;
	
	int checkCart(int userNo) throws SQLException;
	
	Cart createCartFromTable(int userNo) throws SQLException;
}
