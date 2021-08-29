package allthatbook.mvc.model.dao;

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
	int reservationInsert(Reservation reservation) throws SQLException;
	
}
