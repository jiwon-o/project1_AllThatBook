package allthatbook.mvc.model.service;

import java.sql.SQLException;

import allthatbook.mvc.exception.NotFoundException;
import allthatbook.mvc.model.dao.UserDAO;
import allthatbook.mvc.model.dao.UserDAOImpl;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.session.Session;
import allthatbook.mvc.session.SessionSet;

public class UserService {
	UserDAO userDao = new UserDAOImpl();
	
	/**
	 * 로그인
	 * */
	public User login(String userId, String userPwd)throws NotFoundException , SQLException{
		User user=userDao.login(userId, userPwd);
		if(user==null) {
			throw new NotFoundException("정보를 다시 확인해주세요.");
		}
		
		//로그인 된 정보 저장하기
		Session session = new Session(userId);
		
		
		SessionSet sessionSet = SessionSet.getInstance();
		sessionSet.add(session);
		
		return user;
	}
}
