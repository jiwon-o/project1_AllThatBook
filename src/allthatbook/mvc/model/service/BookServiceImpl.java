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
		if(list.size()==0)throw new NotFoundException("*** 현재 도서가 없습니다. ***");
		return list;
	}
	
	/**
	 * 도서번호에 해당하는 도서 검색
	 * */
	@Override
	public Book bookSelectByBookNo(int bookNo) throws SQLException {
		Book book = bookDao.bookSelectByBookNo(bookNo);
		if(book == null) {

			throw new SQLException("*** 해당 도서번호에 해당하는 도서는 현재 없습니다. ***");

		}else {
			System.out.println("*** '" + bookNo + "'번 책을 검색합니다. ***");

		}
		
		return book;
	}

	/**
	 * 도서명에 해당하는 도서 검색
	 */
	@Override
	public List<Book> bookSelectByBookName(String keyword) throws SQLException {
		List<Book> bookList = bookDao.bookSelectByBookName(keyword);
		if(bookList == null || bookList.isEmpty()) {
			throw new SQLException("*** 입력하신 단어(" + keyword + ")에 해당하는 도서를 찾을 수 없습니다. ***");
		}else {
			System.out.println("*** 제목에 '" + keyword + "'이 포함된 도서를 검색합니다. ***");
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
			throw new SQLException("*** 입력하신 저자명(" + writer + ")에 해당하는 도서를 찾을 수 없습니다. ***");
		}
		return bookList;
	}
	
	

	@Override
	public List<Book> bookSelectByPublisher(String publisher) throws SQLException {
		List<Book> bookList = bookDao.bookSelectByPublisher(publisher);
		if(bookList == null || bookList.isEmpty()) {
			throw new SQLException("*** 입력하신 출판사(" + publisher + ")에 해당하는 도서를 찾을 수 없습니다. ***");
		}
		return bookList;
	}

	@Override
	public List<Book> bookSelectByCategory(String category) throws SQLException {
		List<Book> bookList = bookDao.bookSelectByCategory(category);
		if(bookList == null || bookList.isEmpty()) {

			throw new SQLException("*** 입력하신 도서분야(" + category + ")에 해당하는 도서를 찾을 수 없습니다. ***");

		}
		return bookList;
	}

	/**
	 * 대출 여부에 따른 도서 검색
	 */
	@Override
	public List<Book> bookSelectByState(int state) throws SQLException {
		List<Book> bookList = bookDao.bookSelectByState(state);
		if(0<=state && state <=2) {
			if(bookList == null || bookList.isEmpty()) {
				throw new SQLException("*** 입력하신 대출여부(" + state + ")에 해당하는 도서를 찾을 수 없습니다. ***");
			}
		}else {
			throw new SQLException("*** 대출가능(0) or 대출중(1) or 예약중(2) 중 하나를 입력해주세요. 처음으로 돌아갑니다. ***");
		}
		
		return bookList;
	}
	
	@Override
	public void bookInsert(Book book) throws SQLException {
		int result = bookDao.bookInsert(book);
		if(result==0)throw new SQLException("*** 책등록 실패했습니다. ***");
	}

	@Override
	public int bookUpdate(Book updatebook) throws SQLException {
		int result = bookDao.bookUpdate(updatebook);
		if(result==0)throw new SQLException("*** 책수정 실패했습니다. ***");
		return result;
	}

	@Override
	public void bookDelete(int bookNo) throws SQLException {
		int result = bookDao.bookDelete(bookNo);
		if(result==0)throw new SQLException("*** 도서삭제가 되지 않았습니다. ***");
	}

}
