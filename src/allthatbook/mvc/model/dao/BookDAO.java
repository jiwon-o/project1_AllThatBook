package allthatbook.mvc.model.dao;

import java.awt.print.Book;
import java.sql.SQLException;
import java.util.List;

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
