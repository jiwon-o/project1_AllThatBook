package allthatbook.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import allthatbook.mvc.exception.NotFoundException;
import allthatbook.mvc.exception.PwdCheckException;
import allthatbook.mvc.model.dto.Rental;
import allthatbook.mvc.model.dto.Reservation;
import allthatbook.mvc.model.dto.User;

public interface UserService {
	
	/**
	 * �α����ϱ�
	 * */
	User login(String userId, String userPwd) throws NotFoundException, SQLException;
	
	
	/**
	 * ȸ������
	 */
	void register(User user, String pwdCheck) throws SQLException, PwdCheckException;

	/**
	 * ��üȸ�� ��ȸ
	 */
	List<User> allSelect()  throws NotFoundException, SQLException;

	User selectByUserId(String userId) throws NotFoundException, SQLException;

	User selectByUserNo(int userNo) throws NotFoundException, SQLException;
	
	
	/**
	 * ȸ������
	 */
	void updateUserInfo(User user) throws SQLException;
	
	
	/**
	 * ȸ��Ż��
	 */
	void deleteUserInfo(User user)throws SQLException;

	void deleteUserInfo(int userNo)throws SQLException;
	
	
	/**
	 * ȸ������
	 */
	void deleteAdminUserInfo(User user)throws SQLException;

	/**
	 * ȸ�� ���� ���� 
	 * @return
	 */
	 int userUpdate(User updateUser) throws SQLException;
	
	 
	/**
	 * �뿩���� ��� 
	 * */ 
	List<Rental> selectRentalByUserNo(int userNo) throws SQLException;
	 
	 /**
	  * �������� ���
	  * */
	List<Reservation> selectReservationByUserNo(int userNo) throws SQLException;
}
