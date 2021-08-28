package allthatbook.mvc.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import allthatbook.mvc.exception.NotFoundException;
import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.service.BookService;
import allthatbook.mvc.model.service.BookServiceImpl;
import allthatbook.mvc.session.Session;
import allthatbook.mvc.session.SessionSet;
import allthatbook.mvc.view.EndView;
import allthatbook.mvc.view.FailView;



public class CartController {
	private static BookService bookService = new BookServiceImpl();
		
	/**
	 * 장바구니 담기
	 */
	public static void putCart(String userId, int bookNo) {
		try {
			//책번호에 해당하는 책 검색
			Book book = bookService.bookSelectByBookNo(bookNo);
			
			if(book.getBookState() != 0) {
				throw new SQLException("대여 중인 책은 장바구니에 담을 수 없습니다.");
			}
			
			//id에 해당하는 세션찾기
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(userId);
			
			//세션에서 장바구니 찾기
			Set<Book> cart = (Set<Book>)session.getAttribute("cart"); // 책 저장
			
			//장바구니가 없으면 장바구니 생성
			if(cart == null) {
				cart = new HashSet<>();
				session.setAttribute("cart", cart);
			}
			
			//장바구니에서 상품찾기
			
			if(cart.contains(book)) {
				throw new SQLException("이미 장바구니에 담은 책은 중복해서 담을 수 없습니다.");
			}
			cart.add(book);
			EndView.printMessage("장바구니에 담았습니다");
			
			
		}catch(Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 장바구니 보기
	 */
	public static void viewCart(String id) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);
		
		Set<Book> cart = (Set<Book>) session.getAttribute("cart");
		if(cart == null) { // 장바구니가 없는 고객
			FailView.errorMessage("장바구니가 비었습니다");
		}else {
			EndView.printViewCart(id , cart);
		}
	}
}
