package allthatbook.mvc.model.service;

import java.sql.SQLException;

import allthatbook.mvc.model.dto.Rental;

public class RentalServiceImpl implements RentalService {

	@Override
	public void insertRental(Rental rental) throws SQLException {
    	int result = rentalDAO.rentalInsert(rental);

    	if(result ==0) throw new SQLException("대출실패");

	}

}
