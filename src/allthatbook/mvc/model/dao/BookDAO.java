package allthatbook.mvc.model.dao;

import allthatbook.mvc.model.dto.Book;
import java.sql.SQLException;
import java.util.List;


public interface BookDAO {
	
	/**
	 * ��ü å �˻�
	 * */
	List<Book> bookSelect() throws SQLException;
	
	
	/**
	 * bookNo�� �ش��ϴ� ���� �˻�
	 * */
	Book bookSelectByBookNo(int bookNo)throws SQLException;
	
	/**
	 * ������ �ش��ϴ� ���� �˻�
	 */
	List<Book> bookSelectByBookName(String keyword) throws SQLException;
	
	/**
	 * ���ڸ� �ش��ϴ� ���� �˻�
	 */
	List<Book> bookSelectByWriter(String bookWriter) throws SQLException;
	
	/**
	 * ���ǻ翡 �ش��ϴ� ���� �˻�
	 */
	List<Book> bookSelectByPublisher(String bookPublisher) throws SQLException;
	
	/**
	 * ī�װ� �̸��� �ش��ϴ� ���� �˻�
	 * Join����
	 */
	List<Book> bookSelectByCategory(String category) throws SQLException;
	
	/**
	 * å ���
	 */
	int bookInsert(Book book) throws SQLException;
	
	/**
	 * å ����
	 */
	int bookUpdate(Book book) throws SQLException;
	
	/**
	 * å ����
	 */

	int bookDelete(int bookNo) throws SQLException;
	
	/**
	 * ������ ���� ��ȸ
	 */
	List<Book> bookRentalSelect()throws SQLException;
	
	/**
	 * ����� ���� ��ȸ
	 */
	List<Book> bookReserveSelect()throws SQLException;

}
