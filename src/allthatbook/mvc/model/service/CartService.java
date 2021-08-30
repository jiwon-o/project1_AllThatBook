package allthatbook.mvc.model.service;

import java.sql.SQLException;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.Cart;
import allthatbook.mvc.model.dto.CartDetail;

public interface CartService {
    
	/**
	 * 장바구니에 책 추가하기
	 * */
	public void insertBook(int bookNo, Cart cart) throws SQLException;
	
	
	
	/**
	 * 장바구니 책 대여하기
	 * */
	public int rentalCartBook(Cart cart, CartDetail cartDetail) throws SQLException;
	
	
	/**
	 * 장바구니에서 책 삭제하기
	 * */
	public int deleteCartBook(Cart cart, CartDetail cartDetail) throws SQLException;
	
	/**
	 * 장바구니 비우기
	 * */
	public void clearCartBook(Cart cart) throws SQLException;
	
	/**
	 * 장바구니 생성하기
	 * */
	public void createCart(int userNo) throws SQLException;
	
	
	/**
	 * 장바구니 테이블에 장바구니 존재하는지 체크
	 * */
	public int checkCart(int userId) throws SQLException;
	
	
	/**
	 * 테이블에서 회원번호에 해당하는 장바구니 불러와서 장바구니 객체 리턴하기
	 * */
	public Cart getCartFromTable(int userId) throws SQLException;
	
	
	/**
	 * userId를 입력으로 받았을때 해당하는 userNo를 리턴해주는 메소드
	 * */
	//public int getUserNoByUserId(int userNo) throws SQLException;
	
}
