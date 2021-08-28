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
     * 전체 책 조회
     * */
	public List<Book> bookSelect() throws NotFoundException , SQLException{
		List<Book> list = bookDao.bookSelect();
		if(list.size()==0)throw new NotFoundException("현재 상품이 없습니다.");
		return list;
	}
	
	/**
	 * 책 번호에 해당하는 상품검색
	 * */
	@Override
	public Book bookSelectByBookNo(int bookNo) throws SQLException {
		Book book = bookDao.bookSelectByBookNo(bookNo);
		if(book == null) throw new SQLException(bookNo + "에 해당하는 책은 현재 없습니다.");
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
		if(list.size()==0)throw new NotFoundException("모든 도서 대출가능합니다.");
		return list;
	}

	@Override
	public List<Book> bookReserveSelect() throws NotFoundException, SQLException {
		List<Book> list = bookDao.bookReserveSelect();
		if(list.size()==0)throw new NotFoundException("모든 도서 예약가능합니다.");
		return list;
	}
}
