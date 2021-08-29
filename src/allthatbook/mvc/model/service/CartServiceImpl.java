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
	 * 장바구니에 책을 담는다.
	 * */
	@Override
	public void insertBook(int bookNo, Cart cart) throws SQLException {
		int result = cartDAO.insertBook(bookNo, cart);
        if (result == 0) throw new SQLException("장바구니 담기 실패");
	}

	
	/**
	 * 장바구니에 있는 책을 대여한다.
	 * */
	@Override
	public int rentalCartBook(Cart cart, CartDetail cartDetail) throws SQLException {
		Rental rental = new Rental(cartDetail.getBookNo(), cart.getUserNo());
		int result = rentalDAO.rentalInsert(rental);
		if (result ==0) return 0;
		else {
			cartDAO.deleteCartDetail(cart, cartDetail);
			return 1;
			}
	}


    /**
     * 장바구니에 있는 책 삭제한다.
     * */
	@Override
	public int deleteCartBook(Cart cart, CartDetail cartDetail) throws SQLException {
		int result = cartDAO.deleteCartDetail(cart, cartDetail);
		if (result==0) return 1;
		else {
			throw new SQLException("장바구니에서 삭제실패");
		}
	}		
	
	
	/**
	 * 장바구니 테이블에 장바구니 생성한다.
	 * */
	@Override
	public void createCart(int userNo) throws SQLException {
		int result = cartDAO.cartInsert(userNo);
		if (result ==0 ) throw new SQLException("장바구니 생성 실패");
	}

	
	
	/**
	 * 장바구니가 장바구니 테이블에 있는지 없는지 체크한다.
	 * */
	@Override
	public int checkCart(int userNo) throws SQLException {
	    int result = cartDAO.checkCart(userNo);
	    return result;
	}

	/**
	 * 테이블에서 회원번호에 해당하는 장바구니 불러와서 장바구니 객체 리턴하기
	 * */
	@Override
	public Cart getCartFromTable(int userNo) throws SQLException {
		Cart cart = cartDAO.createCartFromTable(userNo);
		return cart;
	}
}
