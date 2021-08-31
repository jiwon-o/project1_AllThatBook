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
		if(list.size()==0)throw new NotFoundException("*** ���� ������ �����ϴ�. ***");
		return list;
	}
	
	/**
	 * ������ȣ�� �ش��ϴ� ���� �˻�
	 * */
	@Override
	public Book bookSelectByBookNo(int bookNo) throws SQLException {
		Book book = bookDao.bookSelectByBookNo(bookNo);
		if(book == null) {

			throw new SQLException("*** �ش� ������ȣ�� �ش��ϴ� ������ ���� �����ϴ�. ***");

		}else {
			System.out.println("*** '" + bookNo + "'�� å�� �˻��մϴ�. ***");

		}
		
		return book;
	}

	/**
	 * ������ �ش��ϴ� ���� �˻�
	 */
	@Override
	public List<Book> bookSelectByBookName(String keyword) throws SQLException {
		List<Book> bookList = bookDao.bookSelectByBookName(keyword);
		if(bookList == null || bookList.isEmpty()) {
			throw new SQLException("*** �Է��Ͻ� �ܾ�(" + keyword + ")�� �ش��ϴ� ������ ã�� �� �����ϴ�. ***");
		}else {
			System.out.println("*** ���� '" + keyword + "'�� ���Ե� ������ �˻��մϴ�. ***");
		}
		return bookList;
	}
	
	/**
	 * ���ڸ� �ش��ϴ� ���� �˻�
	 */
	@Override
	public List<Book> bookSelectByWriter(String writer) throws SQLException {
		List<Book> bookList = bookDao.bookSelectByWriter(writer);
		if(bookList == null || bookList.isEmpty()) {
			throw new SQLException("*** �Է��Ͻ� ���ڸ�(" + writer + ")�� �ش��ϴ� ������ ã�� �� �����ϴ�. ***");
		}
		return bookList;
	}
	
	

	@Override
	public List<Book> bookSelectByPublisher(String publisher) throws SQLException {
		List<Book> bookList = bookDao.bookSelectByPublisher(publisher);
		if(bookList == null || bookList.isEmpty()) {
			throw new SQLException("*** �Է��Ͻ� ���ǻ�(" + publisher + ")�� �ش��ϴ� ������ ã�� �� �����ϴ�. ***");
		}
		return bookList;
	}

	@Override
	public List<Book> bookSelectByCategory(String category) throws SQLException {
		List<Book> bookList = bookDao.bookSelectByCategory(category);
		if(bookList == null || bookList.isEmpty()) {

			throw new SQLException("*** �Է��Ͻ� �����о�(" + category + ")�� �ش��ϴ� ������ ã�� �� �����ϴ�. ***");

		}
		return bookList;
	}

	/**
	 * ���� ���ο� ���� ���� �˻�
	 */
	@Override
	public List<Book> bookSelectByState(int state) throws SQLException {
		List<Book> bookList = bookDao.bookSelectByState(state);
		if(0<=state && state <=2) {
			if(bookList == null || bookList.isEmpty()) {
				throw new SQLException("*** �Է��Ͻ� ���⿩��(" + state + ")�� �ش��ϴ� ������ ã�� �� �����ϴ�. ***");
			}
		}else {
			throw new SQLException("*** ���Ⱑ��(0) or ������(1) or ������(2) �� �ϳ��� �Է����ּ���. ó������ ���ư��ϴ�. ***");
		}
		
		return bookList;
	}
	
	@Override
	public void bookInsert(Book book) throws SQLException {
		int result = bookDao.bookInsert(book);
		if(result==0)throw new SQLException("*** å��� �����߽��ϴ�. ***");
	}

	@Override
	public int bookUpdate(Book updatebook) throws SQLException {
		int result = bookDao.bookUpdate(updatebook);
		if(result==0)throw new SQLException("*** å���� �����߽��ϴ�. ***");
		return result;
	}

	@Override
	public void bookDelete(int bookNo) throws SQLException {
		int result = bookDao.bookDelete(bookNo);
		if(result==0)throw new SQLException("*** ���������� ���� �ʾҽ��ϴ�. ***");
	}

}
