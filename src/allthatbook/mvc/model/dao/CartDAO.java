package allthatbook.mvc.model.dao;

import java.sql.SQLException;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.Cart;

public interface CartDAO {
	/**
	책을 장바구니에 추가할때,
	만약 장바구니가 존재하지 않는다면
	장바구니 테이블에 장바구니 추가
    */
	int cartInsert(Cart cart) throws SQLException;
	
	
	int insertBook(Book book, Cart cart) throws SQLException;
}
