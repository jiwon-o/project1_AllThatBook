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
	 * ��ٱ��� ���
	 */
	public static void putCart(String userId, int bookNo) {
		try {
			//å��ȣ�� �ش��ϴ� å �˻�
			Book book = bookService.bookSelectByBookNo(bookNo);
			
			if(book.getBookState() != 0) {
				throw new SQLException("�뿩 ���� å�� ��ٱ��Ͽ� ���� �� �����ϴ�.");
			}
			
			//id�� �ش��ϴ� ����ã��
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(userId);
			
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
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * ��ٱ��� ����
	 */
	public static void viewCart(String id) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);
		
		Set<Book> cart = (Set<Book>) session.getAttribute("cart");
		if(cart == null) { // ��ٱ��ϰ� ���� ��
			FailView.errorMessage("��ٱ��ϰ� ������ϴ�");
		}else {
			EndView.printViewCart(id , cart);
		}
	}
}
