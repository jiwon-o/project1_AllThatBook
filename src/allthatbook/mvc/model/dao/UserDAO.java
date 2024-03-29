package allthatbook.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import allthatbook.mvc.model.dto.Rental;
import allthatbook.mvc.model.dto.Reservation;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.util.DbUtil;

public interface UserDAO {
	
	/**
	 * 로그인하기
	 * */
	User login(String userId, String userPwd) throws SQLException;
	
	/**
	 * 회원가입
	 */
	int register(User user) throws SQLException;
	
	/**
	 * 회원수정하기
	 */
	int updateUserInfo(User user) throws SQLException;

	int updateAdminUserInfo(User updateUser)throws SQLException; 
	/**
	 * 회원탈퇴하기 
	 */
	

	int deleteUserInfo(User user) throws SQLException;

	
	List<User> allSelect() throws SQLException ;

	/**
	 * userNo로 조회
	 */
	User selectByUserNo(int userNo) throws SQLException;

	/**
	 * userId로 조회
	 */
	User selectByUserId(String userId) throws SQLException;


	int deleteUserInfo(int userNo) throws SQLException;
   
	
	/**
	 * userNo에 해당하는 대여목록 조회
	 * */
	List<Rental> selectRentalByUserNo(int userNo) throws SQLException;
	
	/**
	 * userNo에 해당하는 예약목록 조회
	 * */
	List<Reservation> selectReservationByUserNo(int UserNo) throws SQLException;
	
}
