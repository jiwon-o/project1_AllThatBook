package allthatbook.mvc.model.service;

import java.sql.SQLException;

import allthatbook.mvc.exception.NotFoundException;
import allthatbook.mvc.model.dao.UserDAO;
import allthatbook.mvc.model.dao.UserDAOImpl;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.session.Session;
import allthatbook.mvc.session.SessionSet;

public class UserServiceImpl implements UserService {
	UserDAO userDao = new UserDAOImpl();
	
	/**
	 * ȸ������
	 */
	@Override
	public void SignUp(User user) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * �α���
	 * */
	public User login(String userId, String userPwd) throws NotFoundException , SQLException{
		User user=userDao.login(userId, userPwd);
		if(user==null) {
			throw new NotFoundException("���̵� �Ǵ� ��й�ȣ�� �߸� �ԷµǾ����ϴ�. ��Ȯ�� �Է����ּ���.");
		}
		
		//�α��� �� ���� �����ϱ�
		Session session = new Session(userId);
		
		SessionSet sessionSet = SessionSet.getInstance();
		sessionSet.add(session);
		
		return user;
	}

	
}
