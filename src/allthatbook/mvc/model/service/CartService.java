package allthatbook.mvc.model.service;

import java.sql.SQLException;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.Cart;

public interface CartService {
    
	/**
	 * ��ٱ��Ͽ� å �߰��ϱ�
	 * */
	public void insertBook(Book book, Cart cart) throws SQLException;
	
	
	
	/**
	 * ��ٱ��� å �뿩�ϱ�
	 * */
	public void rentalCartBook(Cart cart) throws SQLException;
	
}
