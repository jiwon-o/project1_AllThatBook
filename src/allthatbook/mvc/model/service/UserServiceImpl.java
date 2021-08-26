package allthatbook.mvc.model.service;

import java.sql.SQLException;

import allthatbook.mvc.exception.NotFoundException;
import allthatbook.mvc.exception.PwdCheckException;
import allthatbook.mvc.model.dao.UserDAO;
import allthatbook.mvc.model.dao.UserDAOImpl;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.session.Session;
import allthatbook.mvc.session.SessionSet;

public class UserServiceImpl implements UserService {
	UserDAO userDao = new UserDAOImpl();
	
	/**
	 * 로그인
	 * */
	public User login(String userId, String userPwd) throws NotFoundException , SQLException{
		User user=userDao.login(userId, userPwd);
		if(user==null) {
			throw new NotFoundException("아이디 또는 비밀번호가 잘못 입력되었습니다. 정확히 입력해주세요.");
		}
		
		//로그인 된 정보 저장하기
		Session session = new Session(userId);
		
		SessionSet sessionSet = SessionSet.getInstance();
		sessionSet.add(session);
		
		return user;
	}

	/**
	 * 회원가입
	 */
	@Override
	public void register(User user, String pwdCheck) throws SQLException, PwdCheckException {
		int result = userDao.register(user);
		
		if(result == 0) {
			throw new SQLException("회원가입에 실패했습니다.");
		}else if(!user.getUserPwd().equals(pwdCheck)) {
			throw new PwdCheckException("비밀번호가 일치하지 않습니다.");
		}
		
	}
}
