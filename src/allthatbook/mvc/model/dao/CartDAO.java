package allthatbook.mvc.model.dao;

import java.sql.SQLException;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.Cart;

public interface CartDAO {
	/**
	å�� ��ٱ��Ͽ� �߰��Ҷ�,
	���� ��ٱ��ϰ� �������� �ʴ´ٸ�
	��ٱ��� ���̺� ��ٱ��� �߰�
    */
	int cartInsert(Cart cart) throws SQLException;
	
	
	int insertBook(Book book, Cart cart) throws SQLException;
}
