package allthatbook.mvc.model.service;

import java.sql.SQLException;

import allthatbook.mvc.model.dao.CartDAO;
import allthatbook.mvc.model.dao.CartDAOImpl;
import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.Cart;

public class CartServiceImpl implements CartService {
    CartDAO cartDAO = new CartDAOImpl();
	
	/**
	 * 장바구니에 책을 담는다.
	 * */
	@Override
	public void insertBook(Book book, Cart cart) throws SQLException {
		int result = cartDAO.insertBook(book, cart);
        if (result == 0) throw new SQLException("장바구니 담기 실패");
	}

	
	/**
	 * 장바구니에 있는 책을 대여한다.
	 * */
	@Override
	public void rentalCartBook(Cart cart) throws SQLException {
		

	}

}
