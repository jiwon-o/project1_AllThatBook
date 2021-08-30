package allthatbook.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import allthatbook.mvc.model.dao.CartDAO;
import allthatbook.mvc.model.dao.CartDAOImpl;
import allthatbook.mvc.model.dao.RentalDAO;
import allthatbook.mvc.model.dao.RentalDAOImpl;
import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.Cart;
import allthatbook.mvc.model.dto.CartDetail;
import allthatbook.mvc.model.dto.Rental;
import allthatbook.mvc.view.FailView;

public class CartServiceImpl implements CartService {
    CartDAO cartDAO = new CartDAOImpl();
	RentalDAO rentalDAO = new RentalDAOImpl();
	
	/**
	 * ��ٱ��Ͽ� å�� ��´�.
	 * */
	@Override
	public void insertBook(int bookNo, Cart cart) throws SQLException {
		int result = cartDAO.insertBook(bookNo, cart);
        if (result == 0) throw new SQLException("��ٱ��� ��� ����");
	}

	
	/**
	 * ��ٱ��Ͽ� �ִ� å�� �뿩�Ѵ�.
	 * */
	@Override
	public int rentalCartBook(Cart cart, CartDetail cartDetail) throws SQLException { //0�̸� �뿩���� //1�̸� �뿩����
		Rental rental = new Rental(cartDetail.getBookNo(), cart.getUserNo());
		int result = rentalDAO.rentalInsert(rental);
		if (result ==0) return 0;
		else {
			cartDAO.deleteCartDetail(cart, cartDetail);
			return 1;
		}
	}


    /**
     * ��ٱ��Ͽ� �ִ� å �����Ѵ�.
     * */
	@Override
	public int deleteCartBook(Cart cart, CartDetail cartDetail) throws SQLException {
		int result = cartDAO.deleteCartDetail(cart, cartDetail);
		return result;
	}		
	
	
	/**
	 * ��ٱ��� ���̺� ��ٱ��� �����Ѵ�.
	 * */
	@Override
	public void createCart(int userNo) throws SQLException {
		int result = cartDAO.cartInsert(userNo);
		if (result ==0 ) throw new SQLException("��ٱ��� ���� ����");
	}

	
	
	/**
	 * ��ٱ��ϰ� ��ٱ��� ���̺� �ִ��� ������ üũ�Ѵ�.
	 * */
	@Override
	public int checkCart(int userNo) throws SQLException {
	    int result = cartDAO.checkCart(userNo);
	    return result;
	}

	/**
	 * ���̺��� ȸ����ȣ�� �ش��ϴ� ��ٱ��� �ҷ��ͼ� ��ٱ��� ��ü �����ϱ�
	 * */
	@Override
	public Cart getCartFromTable(int userNo) throws SQLException {
		Cart cart = cartDAO.createCartFromTable(userNo);
		return cart;
	}
}
