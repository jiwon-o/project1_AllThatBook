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
	 * bookNo에 해당하는 정보 검색
	 * */
	Book bookSelectByBookNo(int bookNo)throws SQLException;
	
	/**
	 * 도서명에 해당하는 정보 검색
	 */
	List<Book> bookSelectByBookName(String keyword) throws SQLException;
	
	/**
	 * 저자명에 해당하는 정보 검색
	 */
	List<Book> bookSelectByWriter(String bookWriter) throws SQLException;
	
	/**
	 * 출판사에 해당하는 정보 검색
	 */
	List<Book> bookSelectByPublisher(String bookPublisher) throws SQLException;
	
	/**
	 * 대출 여부에 따른 정보 검색
	 */
	List<Book> bookSelectByState(int state) throws SQLException;
	
	/**
	 * 카테고리 이름에 해당하는 정보 검색
	 * Join으로
	 */
	List<Book> bookSelectByCategory(String category) throws SQLException;
	
	/**
	 * 책 등록
	 */
	int bookInsert(Book book) throws SQLException;
	
	/**
	 * 책 수정
	 */
	int bookUpdate(Book updatebook) throws SQLException;
	
	/**
	 * 책 삭제
	 */

	int bookDelete(int bookNo) throws SQLException;


	List<Book> bookReserveSelect() throws SQLException;

}
