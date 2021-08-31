package allthatbook.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import allthatbook.mvc.exception.NotFoundException;
import allthatbook.mvc.exception.PwdCheckException;
import allthatbook.mvc.model.dao.CartDAO;
import allthatbook.mvc.model.dao.CartDAOImpl;
import allthatbook.mvc.model.dao.UserDAO;
import allthatbook.mvc.model.dao.UserDAOImpl;
import allthatbook.mvc.model.dto.Cart;
import allthatbook.mvc.model.dto.Rental;
import allthatbook.mvc.model.dto.Reservation;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.session.Session;
import allthatbook.mvc.session.SessionSet;

public class UserServiceImpl implements UserService {
	UserDAO userDao = new UserDAOImpl();
    CartDAO cartDao = new CartDAOImpl();
	/**
	 * �α���
	 */
	public User login(String userId, String userPwd) throws NotFoundException, SQLException {
		User user = userDao.login(userId, userPwd);
		if (user == null) {
			throw new NotFoundException("���̵� �Ǵ� ��й�ȣ�� �߸� �ԷµǾ����ϴ� ��Ȯ�� �Է����ּ���. ");
		}
		// �α��� �� ���� �����ϱ�
		Session session = new Session(userId);
		SessionSet sessionSet = SessionSet.getInstance();
		sessionSet.add(session);
		Cart cart = cartDao.createCartFromTable(user.getUserNo());
		if (cart != null) session.setAttribute("cart", cart);
		
		return user;
	}

	/**
	 * ȸ������
	 */
	@Override
	public void register(User user, String pwdCheck) throws SQLException, PwdCheckException {
		int result = userDao.register(user);

		if (result == 0) {
			throw new SQLException("ȸ�����Կ� �����߽��ϴ�. ");
		} else if (!user.getUserPwd().equals(pwdCheck)) {
			throw new PwdCheckException("��й�ȣ�� ��ġ���� �ʽ��ϴ�. ");
		}

	}


	/**
	 * ��üȸ����ȸ
	 */
	public List<User> allSelect()  throws NotFoundException, SQLException{
		List<User> userList = userDao.allSelect();
		if(userList.size()==0) throw new NotFoundException("�����ǰ�̾����ϴ�. ");	
		return userList;
	}

	public User selectByUserId(String userId) throws NotFoundException, SQLException{
		User user = userDao.selectByUserId(userId);
		if(user==null)throw new NotFoundException("�ش� UserId�� �������� �ʽ��ϴ�. ");
		return user;
	}

	public User selectByUserNo(int userNo) throws NotFoundException, SQLException{
		User user = userDao.selectByUserNo(userNo);
		if(user==null)throw new NotFoundException("�ش� UserNo�� �������� �ʽ��ϴ�. ");
		return user;
	}

	
	/**
	 * ȸ������ ����
	 */
	@Override
	public void updateUserInfo(User user) throws SQLException {
		int result = userDao.updateUserInfo(user);
		if (result == 0) throw new SQLException("������ ������� �ʾҽ��ϴ�. ");
	}


	/**
	 * ȸ��Ż��
	 * 
	 */
	@Override
	public void deleteUserInfo(User user) throws SQLException {
		int result = userDao.deleteUserInfo(user);
		if (result == 0) throw new SQLException("ȸ��Ż�� �����߽��ϴ�. ");
	}


	/**
	 * ȸ������
	 */
	@Override
	public void deleteAdminUserInfo(User user) throws SQLException {
		int result = userDao.deleteUserInfo(user);
		if(result==0)throw new SQLException("ȸ�������� �����߽��ϴ�. ");
	}

	@Override
	public void deleteUserInfo(int userNo) throws SQLException {
		int result = userDao.deleteUserInfo(userNo);
		if(result==0)throw new SQLException("ȸ�������� �����߽��ϴ�. ");
	}
	
	/**
	 * ȸ�� ���� ����
	 */
	@Override
	public int userUpdate(User updateUser) throws SQLException {
		int result = userDao.updateAdminUserInfo(updateUser);
		return result;
	}

	@Override
	public List<Rental> selectRentalByUserNo(int userNo) throws SQLException {
		List<Rental> rentalList = userDao.selectRentalByUserNo(userNo);
		if (rentalList == null || rentalList.size() ==0 ) throw new SQLException("�뿩���� ������ �����ϴ�.");
		return rentalList;
	}

	@Override
	public List<Reservation> selectReservationByUserNo(int userNo) throws SQLException {
		List<Reservation> reservationList = userDao.selectReservationByUserNo(userNo);
		if (reservationList == null || reservationList.size() ==0 ) throw new SQLException("�������� ������ �����ϴ�.");
		return reservationList;
	}

	
}
