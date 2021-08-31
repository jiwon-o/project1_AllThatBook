package allthatbook.mvc.model.service;

import java.sql.SQLException;

import allthatbook.mvc.model.dto.Rental;

public class RentalServiceImpl implements RentalService {

	@Override
	public void insertRental(Rental rental) throws SQLException {
    	int result = rentalDAO.rentalInsert(rental);
    	if(result ==0) throw new SQLException("이미 대출된 도서입니다. (대출 실패입니다) ");
	}

	
	@Override
	public void returnBook(Rental rental) throws SQLException {
		int result = rentalDAO.returnBook(rental);
		
		if(result ==0) throw new SQLException(" 반납실패 ");
		
	}
	
}
