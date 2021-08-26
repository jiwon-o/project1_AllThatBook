package allthatbook.mvc.model.service;

import java.awt.print.Book;
import java.sql.SQLException;
import java.util.List;

import allthatbook.mvc.exception.NotFoundException;
import allthatbook.mvc.model.dao.BookDAO;
import allthatbook.mvc.model.dao.BookDAOImpl;


public class BookService {
	BookDAO bookDao = new BookDAOImpl();
    /**
     * 전체 책 조회
     * */
	public List<Book> bookSelect() throws NotFoundException , SQLException{
		List<Book> list=bookDao.bookSelect();
		if(list.size()==0)throw new NotFoundException("현재 상품이 없습니다.");
		return list;
	}
	
	/**
	 * 책 번호에 해당하는 상품검색
	 * */
	public Book goodsSelectBygoodsId(String bookNo) throws  SQLException{
		Book book=bookDao.bookSelectByBookNo(bookNo);
		if(book==null)throw new SQLException(bookNo + " 현재 상품이 없습니다.");
		return book;
	}
}
