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
	 * ���� ���ο� ���� ���� �˻�
	 */
	List<Book> bookSelectByState(int state) throws SQLException;
	
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
	int bookUpdate(Book updatebook) throws SQLException;
	
	/**
	 * å ����
	 */

	int bookDelete(int bookNo) throws SQLException;

	/**
	 * ������ ���� ��ȸ
	 * */
	List<Book> bookRentalSelect() throws NotFoundException, SQLException;
	
	/**
	 * ������ ���� ��ȸ
	 * */
	List<Book> bookReserveSelect() throws NotFoundException, SQLException;


	
	
}
