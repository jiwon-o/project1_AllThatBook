package allthatbook.mvc.controller;

import java.sql.SQLException;
import java.util.List;

import allthatbook.mvc.model.dto.Cart;
import allthatbook.mvc.model.dto.CartDetail;
import allthatbook.mvc.model.service.BookService;
import allthatbook.mvc.model.service.BookServiceImpl;
import allthatbook.mvc.model.service.CartService;
import allthatbook.mvc.model.service.CartServiceImpl;
import allthatbook.mvc.model.service.UserService;
import allthatbook.mvc.model.service.UserServiceImpl;
import allthatbook.mvc.session.Session;
import allthatbook.mvc.session.SessionSet;
import allthatbook.mvc.view.EndView;
import allthatbook.mvc.view.FailView;

public class CartController {
	private static BookService bookService = new BookServiceImpl();
	private static CartService cartService = new CartServiceImpl();
	private static UserService userService = new UserServiceImpl();

	/**
	 * 장바구니 담기
	 */
	public static void putCart(String userId, int bookNo) {

		try {
			int userNo = userService.selectByUserId(userId).getUserNo(); // userId에 해당하는 userNo
			// id에 해당하는 세션찾기
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(userId);

			// 세션에서 장바구니 찾기
			Cart cart = (Cart) session.getAttribute("cart"); // 책 저장

			// Session에 장바구니가 없으면 장바구니 생성
			// 테이블에 장바구니가 없으면 테이블에 장바구니 생성
			if (cart == null) {
				int chk = cartService.checkCart(userNo); // 0이면 장바구니테이블에 회원번호에 해당하는 장바구니 존재x
				if (chk == 0)
					cartService.createCart(userNo);
				cart = cartService.getCartFromTable(userNo); // 장바구니 테이블에 장바구니가 존재한다면 불러온다.
				session.setAttribute("cart", cart);
			}

			// 장바구니에서 상품찾기
			if (cart.chkCartDuplicate(bookNo)) { // true면 중복되는책 존재
				throw new SQLException("이미 장바구니에 담은 책은 중복해서 담을 수 없습니다.");
			}

			cartService.insertBook(bookNo, cart);
			EndView.printMessage("장바구니에 담았습니다");
			System.out.println("\n");
		}catch(Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 장바구니 비우기
	 */

	public static void clearCart(String userId) {

		try {
			// id에 해당하는 세션찾기
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(userId);
			Cart cart = (Cart) session.getAttribute("cart"); // 책 저장

			cartService.clearCartBook(cart);

		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 장바구니에서 책 하나 제거하기
	 */

	public static void removeCartDetail(String userId, int bookNo) {

		try {
			// id에 해당하는 세션찾기
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(userId);
			Cart cart = (Cart) session.getAttribute("cart"); // 책 저장
			int result = 0;
			List<CartDetail> cartDetailList = cart.getCartDetailList();

			for (CartDetail cartDetail : cartDetailList) {
				if (cartDetail.getBookNo() == bookNo) {
					cartService.deleteCartBook(cart, cartDetail);

					System.out.println("책번호 : " + cartDetail.getBookNo() + "장바구니에서 삭제 완료");

					return;
				}
			}
			System.out.println("삭제실패");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * 장바구니 보기
	 */
	public static void viewCart(String userId) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(userId);

		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) { // 장바구니가 없는 고객
			FailView.errorMessage("장바구니가 비었습니다");
		} else {
			EndView.printViewCart(userId, cart);
		}
	}

	/**
	 * 장바구니에 담긴 책 전체 대여
	 * */
	public static void rentalCartBook(String userId, Cart cart) {
		List<CartDetail> list = cart.getCartDetailList();
		for (CartDetail cartDetail : list) {
			try {
				cartService.rentalCartBook(cart, cartDetail);
				EndView.printMessage(cartDetail.getBookNo() + "번 대여 성공했습니다.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
