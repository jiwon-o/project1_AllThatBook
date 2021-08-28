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
	 * 로그인
	 */
	public User login(String userId, String userPwd) throws NotFoundException, SQLException {
		User user = userDao.login(userId, userPwd);
		if (user == null) {
			throw new NotFoundException("아이디 또는 비밀번호가 잘못 입력되었습니다. 정확히 입력해주세요.");
		}
		// 로그인 된 정보 저장하기
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

		if (result == 0) {
			throw new SQLException("회원가입에 실패했습니다.");
		} else if (!user.getUserPwd().equals(pwdCheck)) {
			throw new PwdCheckException("비밀번호가 일치하지 않습니다.");
		}

	}

	/**
	 * 전체회원조회
	 */
	public List<User> allSelect()  throws NotFoundException, SQLException{
		List<User> userList = UserDAOImpl.allSelect();
		if(userList.size()==0)throw new NotFoundException("현재상품이없습니다.");	
		return userList;
	}

	public User selectByUserId(String userId) throws NotFoundException, SQLException{
		User user = UserDAOImpl.selectByUserId(userId);
		if(user==null)throw new NotFoundException("해당 userId가 존재하지 않습니다.");
		return user;
	}

	public User selectByUserNo(int userNo) throws NotFoundException, SQLException{
		User user = UserDAOImpl.selectByUserNo(userNo);
		if(user==null)throw new NotFoundException("해당 userNo가 존재하지 않습니다.");
		return user;
	}

	
	/**
	 * 회원정보 수정
	 */
	@Override
	public int updateUserInfo(User user) throws SQLException {
		int result = userDao.updateUserInfo(user);
		
		if (result == 0) {
			System.out.print("회원정보수정에 실패했습니다.");
		} else if (result == 1) {
			System.out.print("회원정보가 변경되었습니다.");
		}
		
		return result;		

	}


	/**
	 * 회원탈퇴
	 * 
	 */
	
	@Override
	public int revoke(User user) throws SQLException {
		int result = userDao.revoke(user);
		
		if (result == 0) {
			throw new SQLException("회원탈퇴에 실패했습니다.");
		}else if(result == 1) {
			System.out.println("회원탈퇴 되었습니다.");
		}
		return result;
	}
}
