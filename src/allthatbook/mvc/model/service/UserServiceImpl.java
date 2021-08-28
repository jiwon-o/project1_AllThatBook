package allthatbook.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

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
	 */
	public User login(String userId, String userPwd) throws NotFoundException, SQLException {
		User user = userDao.login(userId, userPwd);
		if (user == null) {
			throw new NotFoundException("���̵� �Ǵ� ��й�ȣ�� �߸� �ԷµǾ����ϴ�. ��Ȯ�� �Է����ּ���.");
		}
		// �α��� �� ���� �����ϱ�
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

		if (result == 0) {
			throw new SQLException("ȸ�����Կ� �����߽��ϴ�.");
		} else if (!user.getUserPwd().equals(pwdCheck)) {
			throw new PwdCheckException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}

	}

	/**
	 * ��üȸ����ȸ
	 */
	public List<User> allSelect()  throws NotFoundException, SQLException{
		List<User> userList = UserDAOImpl.allSelect();
		if(userList.size()==0)throw new NotFoundException("�����ǰ�̾����ϴ�.");	
		return userList;
	}

	public User selectByUserId(String userId) throws NotFoundException, SQLException{
		User user = UserDAOImpl.selectByUserId(userId);
		if(user==null)throw new NotFoundException("�ش� userId�� �������� �ʽ��ϴ�.");
		return user;
	}

	public User selectByUserNo(int userNo) throws NotFoundException, SQLException{
		User user = UserDAOImpl.selectByUserNo(userNo);
		if(user==null)throw new NotFoundException("�ش� userNo�� �������� �ʽ��ϴ�.");
		return user;
	}

	
	/**
	 * ȸ������ ����
	 */
	@Override
	public int updateUserInfo(User user) throws SQLException {
		int result = userDao.updateUserInfo(user);
		
		if (result == 0) {
			System.out.print("ȸ������������ �����߽��ϴ�.");
		} else if (result == 1) {
			System.out.print("ȸ�������� ����Ǿ����ϴ�.");
		}
		
		return result;		

	}


	/**
	 * ȸ��Ż��
	 * 
	 */
	
	@Override
	public int revoke(User user) throws SQLException {
		int result = userDao.revoke(user);
		
		if (result == 0) {
			throw new SQLException("ȸ��Ż�� �����߽��ϴ�.");
		}else if(result == 1) {
			System.out.println("ȸ��Ż�� �Ǿ����ϴ�.");
		}
		return result;
	}
}
