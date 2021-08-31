package allthatbook.mvc.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import allthatbook.mvc.model.dto.Cart;
import allthatbook.mvc.model.dto.CartDetail;
import allthatbook.mvc.model.dto.User;
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
	 * ��ٱ��� ���
	 */
	public static void putCart(String userId, int bookNo) {

		try {
			int userNo = userService.selectByUserId(userId).getUserNo(); // userId�� �ش��ϴ� userNo
			// id�� �ش��ϴ� ����ã��
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(userId);

			// ���ǿ��� ��ٱ��� ã��
			Cart cart = (Cart) session.getAttribute("cart"); // å ����

			// Session�� ��ٱ��ϰ� ������ ��ٱ��� ����
			// ���̺� ��ٱ��ϰ� ������ ���̺� ��ٱ��� ����
			if (cart == null) {
				int chk = cartService.checkCart(userNo); // 0�̸� ��ٱ������̺� ȸ����ȣ�� �ش��ϴ� ��ٱ��� ����x
				if (chk == 0)
					cartService.createCart(userNo);
				cart = cartService.getCartFromTable(userNo); // ��ٱ��� ���̺� ��ٱ��ϰ� �����Ѵٸ� �ҷ��´�.
				session.setAttribute("cart", cart);
			}

			// ��ٱ��Ͽ��� ��ǰã��
			if (cart.chkCartDuplicate(bookNo)) { // true�� �ߺ��Ǵ�å ����

				throw new SQLException("*** �̹� ��ٱ��Ͽ� ���� ������ �ߺ��ؼ� ���� �� �����ϴ�. ***");
			}
			cartService.insertBook(bookNo, cart);
			EndView.printMessage("*** '" + bookNo + "'�� ������ ��ٱ��Ͽ� ��ҽ��ϴ�. ***");

		}catch(Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	
	/**
	 * ��ٱ��� ����
	 */

	public static void clearCart(String userId) {

		try {
			// id�� �ش��ϴ� ����ã��
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(userId);
			Cart cart = (Cart) session.getAttribute("cart"); // å ����

			cartService.clearCartBook(cart);

		} catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * ��ٱ��Ͽ��� å �ϳ� �����ϱ�
	 */

	public static void removeCartDetail(String userId, int bookNo) {

		try {
			// id�� �ش��ϴ� ����ã��
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(userId);
			Cart cart = (Cart) session.getAttribute("cart"); // å ����
			int result = 0;
			List<CartDetail> cartDetailList = cart.getCartDetailList();

			for (CartDetail cartDetail : cartDetailList) {
				if (cartDetail.getBookNo() == bookNo) {
					cartService.deleteCartBook(cart, cartDetail);
					System.out.println("*** '" + cartDetail.getBookNo() + "'�� ������ ��ٱ��Ͽ��� �����Ͽ����ϴ�. ***");

					return;
				}
			}
			System.out.println("*** �������� ***");
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	/**
	 * ��ٱ��� ����
	 */
	public static Cart viewCart(User user) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(user.getUserId());

		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) { // ��ٱ��ϰ� ���� ��
			FailView.errorMessage("*** ��ٱ��ϰ� ������ϴ�. ***");
		} else {
			EndView.printViewCart(user, cart);
		}
		
		return cart;
	}

	/**
	 * ��ٱ��Ͽ� ��� å ��ü ����
	 * */
	public static void rentalCartBook(String userId, Cart cart) {
		List<CartDetail> list = cart.getCartDetailList();
		List<CartDetail> tempList = new ArrayList<CartDetail>();
		for (CartDetail cartDetail : list) {
			try {
				cartService.rentalCartBook(cart, cartDetail);

				EndView.printMessage("*** '" + cartDetail.getBookNo() + "'�� ������ �����Ͽ����ϴ�. ***");

				tempList.add(cartDetail);
			} catch (SQLException e) {
				//e.printStackTrace();
				FailView.errorMessage(e.getMessage());
			}
		}
		for (CartDetail cartDetail : tempList) {
			list.remove(cartDetail);
		}
	}
}
