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
	Book bookSelectByBookNo(String bookNo)throws SQLException;
	
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
	List<Book> bookSelectByCatName(String bookCatName) throws SQLException;
	
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
	int bookDelete(Book book) throws SQLException;
}
