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
	 * 로그인
	 */
	public User login(String userId, String userPwd) throws NotFoundException, SQLException {
		User user = userDao.login(userId, userPwd);
		if (user == null) {
			throw new NotFoundException("아이디 또는 비밀번호가 잘못 입력되었습니다 정확히 입력해주세요. ");
		}
		// 로그인 된 정보 저장하기
		Session session = new Session(userId);
		SessionSet sessionSet = SessionSet.getInstance();
		sessionSet.add(session);
		Cart cart = cartDao.createCartFromTable(user.getUserNo());
		if (cart != null) session.setAttribute("cart", cart);
		
		return user;
	}

	/**
	 * 회원가입
	 */
	@Override
	public void register(User user, String pwdCheck) throws SQLException, PwdCheckException {
		int result = userDao.register(user);

		if (result == 0) {
			throw new SQLException("회원가입에 실패했습니다. ");
		} else if (!user.getUserPwd().equals(pwdCheck)) {
			throw new PwdCheckException("비밀번호가 일치하지 않습니다. ");
		}

	}


	/**
	 * 전체회원조회
	 */
	public List<User> allSelect()  throws NotFoundException, SQLException{
		List<User> userList = userDao.allSelect();
		if(userList.size()==0) throw new NotFoundException("현재상품이없습니다. ");	
		return userList;
	}

	public User selectByUserId(String userId) throws NotFoundException, SQLException{
		User user = userDao.selectByUserId(userId);
		if(user==null)throw new NotFoundException("해당 UserId가 존재하지 않습니다. ");
		return user;
	}

	public User selectByUserNo(int userNo) throws NotFoundException, SQLException{
		User user = userDao.selectByUserNo(userNo);
		if(user==null)throw new NotFoundException("해당 UserNo가 존재하지 않습니다. ");
		return user;
	}

	
	/**
	 * 회원정보 수정
	 */
	@Override
	public void updateUserInfo(User user) throws SQLException {
		int result = userDao.updateUserInfo(user);
		if (result == 0) throw new SQLException("정보가 변경되지 않았습니다. ");
	}


	/**
	 * 회원탈퇴
	 * 
	 */
	@Override
	public void deleteUserInfo(User user) throws SQLException {
		int result = userDao.deleteUserInfo(user);
		if (result == 0) throw new SQLException("회원탈퇴가 실패했습니다. ");
	}


	/**
	 * 회원삭제
	 */
	@Override
	public void deleteAdminUserInfo(User user) throws SQLException {
		int result = userDao.deleteUserInfo(user);
		if(result==0)throw new SQLException("회원삭제가 실패했습니다. ");
	}

	@Override
	public void deleteUserInfo(int userNo) throws SQLException {
		int result = userDao.deleteUserInfo(userNo);
		if(result==0)throw new SQLException("회원삭제가 실패했습니다. ");
	}
	
	/**
	 * 회원 정보 수정
	 */
	@Override
	public int userUpdate(User updateUser) throws SQLException {
		int result = userDao.updateAdminUserInfo(updateUser);
		return result;
	}

	@Override
	public List<Rental> selectRentalByUserNo(int userNo) throws SQLException {
		List<Rental> rentalList = userDao.selectRentalByUserNo(userNo);
		if (rentalList == null || rentalList.size() ==0 ) throw new SQLException("대여중인 도서가 없습니다.");
		return rentalList;
	}

	@Override
	public List<Reservation> selectReservationByUserNo(int userNo) throws SQLException {
		List<Reservation> reservationList = userDao.selectReservationByUserNo(userNo);
		if (reservationList == null || reservationList.size() ==0 ) throw new SQLException("에약중인 도서가 없습니다.");
		return reservationList;
	}

	
}
