package allthatbook.mvc.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import allthatbook.mvc.model.dto.Rental;

public interface RentalDAO {
	  /**
	   * 대여하기
	   *  1) 책상태 확인하기 (0이면 대여가능)
	   *  2) Rental 테이블에 insert
	   *  3) 책상태 변경하기 (1로 수정)
	   * */
	   int rentalInsert(Rental rental) throws SQLException;
	   
	   /**
	    * 반납하기
	    * */
	   int returnBook(Rental rental) throws SQLException; 
}
