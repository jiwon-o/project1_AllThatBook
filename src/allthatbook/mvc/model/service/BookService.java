package allthatbook.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import allthatbook.mvc.exception.NotFoundException;
import allthatbook.mvc.model.dto.Book;

public interface BookService {
	/**
	 * ��ü å �˻�
	 * */
	List<Book> bookSelect() throws NotFoundException, SQLException;
	
	
	/**
	 * bookNo�� �ش��ϴ� ���� �˻�
	 * */
	Book bookSelectByBookNo(int bookNo)throws SQLException;
	
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
	void bookInsert(Book book) throws SQLException;
	
	/**
	 * å ����
	 */
	void bookUpdate(Book book) throws SQLException;
	
	/**
	 * å ����
	 */
	void bookDelete(Book book) throws SQLException;
}
