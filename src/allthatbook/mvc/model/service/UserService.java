package allthatbook.mvc.model.service;

import java.sql.SQLException;

import allthatbook.mvc.exception.NotFoundException;
import allthatbook.mvc.exception.PwdCheckException;
import allthatbook.mvc.model.dto.User;

public interface UserService {
	
	/**
	 * 로그인하기
	 * */
	User login(String userId, String userPwd) throws NotFoundException, SQLException;
	
	/**
	 * 회원가입
	 */
	void register(User user, String pwdCheck) throws SQLException, PwdCheckException;
	
}
