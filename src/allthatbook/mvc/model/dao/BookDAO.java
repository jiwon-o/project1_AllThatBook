package allthatbook.mvc.model.dao;

import allthatbook.mvc.model.dto.Book;
import java.sql.SQLException;
import java.util.List;


public interface BookDAO {
	/**
	 * 전체 책 검색
	 * */
	List<Book> bookSelect() throws SQLException;
	
	/**
	 * ISBN에 해당하는 정보 검색
	 * */
	Book bookSelectByBookNo(String bookNo)throws SQLException;
}
