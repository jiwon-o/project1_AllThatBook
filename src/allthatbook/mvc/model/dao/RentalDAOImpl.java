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
		 String sql="INSERT INTO RENTAL(�뿩��ȣ, å��ȣ, ȸ����ȣ, �ݳ���������, �뿩����, �ݳ�����)" + 
		  		" VALUES(RENTAL_SEQ_NO.NEXTVAL, ?, ?, ?, ?, null)";
		 int result=0;
		 return result;
	}

}
