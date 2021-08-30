package allthatbook.mvc.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import allthatbook.mvc.model.dto.Rental;
import allthatbook.mvc.model.dto.Reservation;

public interface ReservationDAO {
    
	
	/**
	 * �����ϱ�
	 * ������ �Ѹ� �����ϹǷ� �켱 �������̺� å��ȣ�� �ش��ϴ� ������ �ִ��� Ȯ��
	 * ���� ������ �������� �������� �����ϹǷ� book_state�� 1������ Ȯ��
	 * Ȯ���� ���� ������ �������̺� �־��ش�.
	 * */
	int insertReservation(Reservation reservation) throws SQLException;
	
	/**
	 * ������� (�����ε�)
	 * 1. ���⿡�� ���������� ������ å�� �����ϸ� �������̺��� ���� --> ���⿡�� connection �μ��� �޾Ƽ�
	 * 2. �������̺��� �ٷ� ���� 
	 * */
	int deleteReservation(Reservation reservation) throws SQLException; 
	
	int deleteReservation(Connection con, Reservation reservation) throws SQLException; 
	
	
}
