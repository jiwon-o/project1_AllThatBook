package allthatbook.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import allthatbook.mvc.model.dto.Rental;
import allthatbook.mvc.util.DbUtil;

public class RentalDAOImpl implements RentalDAO {
    

	@Override
	public int rentalInsert(Rental rental) throws SQLException {
		 Connection con=null;
		 PreparedStatement ps=null;
		 String sql="INSERT INTO RENTAL(대여번호, 책번호, 회원번호, 반납예정일자, 대여일자, 반납일자)" + 
		  		" VALUES(RENTAL_SEQ_NO.NEXTVAL, ?, ?, ?, ?, null)";
		 int result=0;
		 return result;
	}

}
