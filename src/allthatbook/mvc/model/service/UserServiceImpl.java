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

	/**
	 * ȸ������
	 */
	@Override
	public void register(User user, String pwdCheck) throws SQLException, PwdCheckException {
		int result = userDao.register(user);
		
		if(result == 0) {
			throw new SQLException("ȸ�����Կ� �����߽��ϴ�.");
		}else if(!user.getUserPwd().equals(pwdCheck)) {
			throw new PwdCheckException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		
	}
}
