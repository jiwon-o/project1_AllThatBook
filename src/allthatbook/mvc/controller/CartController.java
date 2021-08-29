package allthatbook.mvc.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import allthatbook.mvc.exception.NotFoundException;
import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.model.service.BookService;
import allthatbook.mvc.model.service.BookServiceImpl;
import allthatbook.mvc.session.Session;
import allthatbook.mvc.session.SessionSet;
import allthatbook.mvc.view.EndView;
import allthatbook.mvc.view.FailView;



public class CartController {
	private static BookService bookService = new BookServiceImpl();
		
	/**
	 * ��ٱ��� ���
	 */
	public static void putCart(User user, int bookNo) {
		try {
			//å��ȣ�� �ش��ϴ� å �˻�
			Book book = bookService.bookSelectByBookNo(bookNo);
			
			if(book.getBookState() != 0) {
				throw new SQLException("�뿩 ���� å�� ��ٱ��Ͽ� ���� �� �����ϴ�.");
			}
			
			//id�� �ش��ϴ� ����ã��
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(user.getUserId());
			
			//���ǿ��� ��ٱ��� ã��
			Set<Book> cart = (Set<Book>)session.getAttribute("cart"); // å ����
			
			//��ٱ��ϰ� ������ ��ٱ��� ����
			if(cart == null) {
				cart = new HashSet<>();
				session.setAttribute("cart", cart);
			}
			
			//��ٱ��Ͽ��� ��ǰã��
			
			if(cart.contains(book)) {
				throw new SQLException("�̹� ��ٱ��Ͽ� ���� å�� �ߺ��ؼ� ���� �� �����ϴ�.");
			}
			cart.add(book);
			EndView.printMessage("��ٱ��Ͽ� ��ҽ��ϴ�");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ٱ��� ����
	 */
	public static void viewCart(User user) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(user.getUserId());
		
		Set<Book> cart = (Set<Book>) session.getAttribute("cart");
		if(cart == null) { // ��ٱ��ϰ� ���� ��
			FailView.errorMessage("��ٱ��ϰ� ������ϴ�");
		}else {
			EndView.printViewCart(user , cart);
		}
	}
	
	/**
	 * ��ٱ��� ����
	 */
	public static void removeCart(User user) {
		try {
			//id�� �ش��ϴ� ����ã��
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(user.getUserId());
			
			session.removeAttribute("cart"); // ��ٱ��� ��� ��ü ����
			
		}catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
		
		
	}

	/**
	 * ��ٱ��� ��� ����
	 */
	public static void deleteCartList(User user) {
		//id�� �ش��ϴ� ����ã��
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(user.getUserId());
		
		Set<Book> cart = (Set<Book>) session.getAttribute("cart");
		
		for(String value : cart) {
			System.out.println(" -- " + value);
		}
	}
}
