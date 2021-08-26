package allthatbook.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import allthatbook.mvc.model.dto.Book;

public interface BookDAO {
	/**
	 * ��ü å �˻�
	 * */
	List<Book> bookSelect() throws SQLException;
	
	/**
	 * ISBN�� �ش��ϴ� ���� �˻�
	 * */
	Book bookSelectByBookNo(String bookNo)throws SQLException;
}
