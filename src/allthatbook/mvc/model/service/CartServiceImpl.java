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
	BookService bookService = new BookServiceImpl();
	
	/**
	 * 장바구니에 책을 담는다.
	 * */
	@Override
	public void insertBook(int bookNo, Cart cart) throws SQLException {
		bookService.bookSelectByBookNo(bookNo);
		int result = cartDAO.insertBook(bookNo, cart);
        if (result == 0) throw new SQLException("*** 장바구니 담기 실패했습니다. ***");

	}

	
	/**
	 * 장바구니에 있는 책을 대여한다.
	 * */
	@Override
	public void rentalCartBook(Cart cart, CartDetail cartDetail) throws SQLException { //0이면 대여실패 //1이면 대여성공
		Rental rental = new Rental(cartDetail.getBookNo(), cart.getUserNo());
		int result = rentalDAO.rentalInsert(cart, rental);
		//if (result != 0)cart.removeCartDetail(cartDetail);
		if (result == 0) throw new SQLException("*** 장바구니 목록에 도서가 존재하지 않습니다. ***");
	}

	/**
	 * 장바구니 전체 비운다.
	 * */
    @Override
    public void clearCartBook(Cart cart) throws SQLException {
    	List<CartDetail> cartDetailList = cart.getCartDetailList();
    	for (CartDetail cartDetail : cartDetailList) {
    		cartDAO.deleteCartDetail(cart, cartDetail);
    	}
    	cartDetailList.clear();
    }
	
    /**
     * 장바구니에 있는 책 하나 삭제한다.
     * */
	@Override
	public int deleteCartBook(Cart cart, CartDetail cartDetail) throws SQLException {
		int result = cartDAO.deleteCartDetail(cart, cartDetail);
		if (result == 1) {cart.removeCartDetail(cartDetail);}

		return result;
	}		
	
	
	/**
	 * 장바구니 테이블에 장바구니 생성한다.
	 * */
	@Override
	public void createCart(int userNo) throws SQLException {
		int result = cartDAO.cartInsert(userNo);
		if (result ==0 ) throw new SQLException("장바구니 생성 실패했습니다. ");
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
