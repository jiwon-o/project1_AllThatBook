package allthatbook.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import allthatbook.mvc.exception.NotFoundException;
import allthatbook.mvc.exception.PwdCheckException;
import allthatbook.mvc.model.dto.Rental;
import allthatbook.mvc.model.dto.Reservation;
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

	/**
	 * 전체회원 조회
	 */
	List<User> allSelect()  throws NotFoundException, SQLException;

	User selectByUserId(String userId) throws NotFoundException, SQLException;

	User selectByUserNo(int userNo) throws NotFoundException, SQLException;
	
	
	/**
	 * 회원수정
	 */
	void updateUserInfo(User user) throws SQLException;
	
	
	/**
	 * 회원탈퇴
	 */
	void deleteUserInfo(User user)throws SQLException;

	void deleteUserInfo(int userNo)throws SQLException;
	
	
	/**
	 * 회원삭제
	 */
	void deleteAdminUserInfo(User user)throws SQLException;

	/**
	 * 회원 정보 수정 
	 * @return
	 */
	 int userUpdate(User updateUser) throws SQLException;
	
	 
	/**
	 * 대여중인 목록 
	 * */ 
	List<Rental> selectRentalByUserNo(int userNo) throws SQLException;
	 
	 /**
	  * 예약중인 목록
	  * */
	List<Reservation> selectReservationByUserNo(int userNo) throws SQLException;
}
