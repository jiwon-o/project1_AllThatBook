package allthatbook.mvc.model.service;

import java.sql.SQLException;

import allthatbook.mvc.model.dao.CartDAO;
import allthatbook.mvc.model.dao.CartDAOImpl;
import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.Cart;

public class CartServiceImpl implements CartService {
    CartDAO cartDAO = new CartDAOImpl();
	
	/**
	 * ��ٱ��Ͽ� å�� ��´�.
	 * */
	@Override
	public void insertBook(Book book, Cart cart) throws SQLException {
		int result = cartDAO.insertBook(book, cart);
        if (result == 0) throw new SQLException("��ٱ��� ��� ����");
	}

	
	/**
	 * ��ٱ��Ͽ� �ִ� å�� �뿩�Ѵ�.
	 * */
	@Override
	public void rentalCartBook(Cart cart) throws SQLException {
		

	}

}
