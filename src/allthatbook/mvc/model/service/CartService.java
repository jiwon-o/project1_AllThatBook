package allthatbook.mvc.model.service;

import java.sql.SQLException;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.Cart;

public interface CartService {
    
	/**
	 * 장바구니에 책 추가하기
	 * */
	public void insertBook(Book book, Cart cart) throws SQLException;
	
	
	
	/**
	 * 장바구니 책 대여하기
	 * */
	public void rentalCartBook(Cart cart) throws SQLException;
	
}
