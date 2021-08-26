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
     * ��ü å ��ȸ
     * */
	public List<Book> bookSelect() throws NotFoundException , SQLException{
		List<Book> list=bookDao.bookSelect();
		if(list.size()==0)throw new NotFoundException("���� ��ǰ�� �����ϴ�.");
		return list;
	}
	
	/**
	 * å ��ȣ�� �ش��ϴ� ��ǰ�˻�
	 * */
	public Book goodsSelectBygoodsId(String bookNo) throws  SQLException{
		Book book=bookDao.bookSelectByBookNo(bookNo);
		if(book==null)throw new SQLException(bookNo + " ���� ��ǰ�� �����ϴ�.");
		return book;
	}
}
