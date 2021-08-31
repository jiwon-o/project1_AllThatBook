package allthatbook.mvc.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import allthatbook.mvc.model.dto.Rental;

public interface RentalDAO {
	  /**
	   * �뿩�ϱ�
	   *  1) å���� Ȯ���ϱ� (0�̸� �뿩����)
	   *  2) Rental ���̺� insert
	   *  3) å���� �����ϱ� (1�� ����)
	   * */
	   int rentalInsert(Rental rental) throws SQLException;
	   
	   /**
	    * �ݳ��ϱ�
	    * */
	   int returnBook(Rental rental) throws SQLException; 
}
