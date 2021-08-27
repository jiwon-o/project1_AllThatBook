package allthatbook.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import allthatbook.mvc.exception.NotFoundException;
import allthatbook.mvc.exception.PwdCheckException;
import allthatbook.mvc.model.dao.UserDAOImpl;
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
	

	
	
	
	/**
	 * ȸ������
	 */
	void updateUserInfo(User user) throws SQLException;
	
	
	/**
	 * ȸ��Ż��
	 */
	void revoke(User user)throws SQLException;
	
}
