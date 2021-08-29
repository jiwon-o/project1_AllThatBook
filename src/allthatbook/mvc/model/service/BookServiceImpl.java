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
	 * 도서번호에 해당하는 도서 검색
	 * */
	@Override
	public Book bookSelectByBookNo(int bookNo) throws SQLException {
		Book book = bookDao.bookSelectByBookNo(bookNo);
		if(book == null) throw new SQLException("도서번호 " + bookNo + "에 해당하는 책은 현재 없습니다.");
		return book;
	}

	/**
	 * 도서명에 해당하는 도서 검색
	 */
	@Override
	public List<Book> bookSelectByBookName(String keyword) throws SQLException {
		List<Book> bookList = bookDao.bookSelectByBookName(keyword);
		if(bookList == null || bookList.isEmpty()) {
			throw new SQLException("입력하신 단어(" + keyword + ")에 해당하는 도서를 찾을 수 없습니다.");
		}
		return bookList;
	}
	
	/**
	 * 저자명에 해당하는 도서 검색
	 */
	@Override
	public List<Book> bookSelectByWriter(String writer) throws SQLException {
		List<Book> bookList = bookDao.bookSelectByWriter(writer);
		if(bookList == null || bookList.isEmpty()) {
			throw new SQLException("입력하신 저자명(" + writer + ")에 해당하는 도서를 찾을 수 없습니다.");
		}
		return bookList;
	}

	@Override
	public List<Book> bookSelectByPublisher(String publisher) throws SQLException {
		List<Book> bookList = bookDao.bookSelectByPublisher(publisher);
		if(bookList == null || bookList.isEmpty()) {
			throw new SQLException("입력하신 출판사(" + publisher + ")에 해당하는 도서를 찾을 수 없습니다.");
		}
		return bookList;
	}

	@Override
	public List<Book> bookSelectByCategory(String category) throws SQLException {
		List<Book> bookList = bookDao.bookSelectByCategory(category);
		if(bookList == null || bookList.isEmpty()) {
			throw new SQLException("입력하신 도서분야(" + category + ")에 해당하는 도서를 찾을 수 없습니다.");
		}
		return bookList;
	}

	@Override
	public int bookInsert(Book book) throws SQLException {
		int result = bookDao.bookInsert(book);
		return result;
	}

	@Override
	public int bookUpdate(Book updatebook) throws SQLException {
		int result = bookDao.bookUpdate(updatebook);
		return result;
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
