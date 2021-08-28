package allthatbook.mvc.model.service;

import java.sql.SQLException;

import allthatbook.mvc.model.dao.RentalDAO;
import allthatbook.mvc.model.dao.RentalDAOImpl;
import allthatbook.mvc.model.dto.Rental;

public interface RentalService {
    RentalDAO rentalDAO = new RentalDAOImpl();
	/**
	 * 대여하기
	 * */
    public void insertRental(Rental rental) throws SQLException;
	
}
