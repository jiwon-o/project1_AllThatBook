package allthatbook.mvc.model.service;

import java.sql.SQLException;

import allthatbook.mvc.exception.NotFoundException;
import allthatbook.mvc.exception.PwdCheckException;
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
	
}
