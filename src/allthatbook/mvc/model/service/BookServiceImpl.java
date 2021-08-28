package allthatbook.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import allthatbook.mvc.exception.NotFoundException;
import allthatbook.mvc.model.dao.BookDAO;
import allthatbook.mvc.model.dao.BookDAOImpl;
import allthatbook.mvc.model.dto.Book;


public class BookServiceImpl implements BookService {
	BookDAO bookDao = new BookDAOImpl();
    /**
     * ��ü å ��ȸ
     * */
	public List<Book> bookSelect() throws NotFoundException , SQLException{
		List<Book> list = bookDao.bookSelect();
		if(list.size()==0)throw new NotFoundException("���� ��ǰ�� �����ϴ�.");
		return list;
	}
	
	/**
	 * å ��ȣ�� �ش��ϴ� ��ǰ�˻�
	 * */
	@Override
	public Book bookSelectByBookNo(int bookNo) throws SQLException {
		Book book = bookDao.bookSelectByBookNo(bookNo);
		if(book == null) throw new SQLException(bookNo + "�� �ش��ϴ� å�� ���� �����ϴ�.");
		return book;
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
		int result = bookDao.bookInsert(book);
		return result;
	}

	@Override
	public void bookUpdate(Book book) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int bookDelete(int bookNo) throws SQLException {
		int result = bookDao.bookDelete(bookNo);
		return result;
	}

	@Override
	public List<Book> bookRentalSelect() throws NotFoundException, SQLException {
		List<Book> list = bookDao.bookRentalSelect();
		if(list.size()==0)throw new NotFoundException("��� ���� ���Ⱑ���մϴ�.");
		return list;
	}

	@Override
	public List<Book> bookReserveSelect() throws NotFoundException, SQLException {
		List<Book> list = bookDao.bookReserveSelect();
		if(list.size()==0)throw new NotFoundException("��� ���� ���డ���մϴ�.");
		return list;
	}
}
