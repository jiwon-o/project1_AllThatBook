package allthatbook.mvc.model.service;

import java.sql.SQLException;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.Cart;
import allthatbook.mvc.model.dto.CartDetail;

public interface CartService {
    
	/**
	 * ��ٱ��Ͽ� å �߰��ϱ�
	 * */
	public void insertBook(int bookNo, Cart cart) throws SQLException;
	
	
	
	/**
	 * ��ٱ��� å �뿩�ϱ�
	 * */
	public int rentalCartBook(Cart cart, CartDetail cartDetail) throws SQLException;
	
	
	/**
	 * ��ٱ��Ͽ��� å �����ϱ�
	 * */
	public int deleteCartBook(Cart cart, CartDetail cartDetail) throws SQLException;
	
	/**
	 * ��ٱ��� ����
	 * */
	public void clearCartBook(Cart cart) throws SQLException;
	
	/**
	 * ��ٱ��� �����ϱ�
	 * */
	public void createCart(int userNo) throws SQLException;
	
	
	/**
	 * ��ٱ��� ���̺� ��ٱ��� �����ϴ��� üũ
	 * */
	public int checkCart(int userId) throws SQLException;
	
	
	/**
	 * ���̺��� ȸ����ȣ�� �ش��ϴ� ��ٱ��� �ҷ��ͼ� ��ٱ��� ��ü �����ϱ�
	 * */
	public Cart getCartFromTable(int userId) throws SQLException;
	
	
	/**
	 * userId�� �Է����� �޾����� �ش��ϴ� userNo�� �������ִ� �޼ҵ�
	 * */
	//public int getUserNoByUserId(int userNo) throws SQLException;
	
}
