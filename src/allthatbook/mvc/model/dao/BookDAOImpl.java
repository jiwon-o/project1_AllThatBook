package allthatbook.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;
import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.util.DbUtil;


public class BookDAOImpl implements BookDAO {

	@Override
	public List<Book> bookSelect() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book bookSelectByBookNo(String bookNo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> bookSelectByWriter(String bookWriter) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> bookSelectByPublisher(String bookPublisher) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> bookSelectByCatName(String bookCatName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int bookInsert(Book book) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int bookUpdate(Book book) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int bookDelete(Book book) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
