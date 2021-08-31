package allthatbook.mvc.model.service;

import java.sql.SQLException;

import allthatbook.mvc.model.dto.Rental;

public class RentalServiceImpl implements RentalService {

	@Override
	public void insertRental(Rental rental) throws SQLException {
    	int result = rentalDAO.rentalInsert(rental);
    	if(result ==0) throw new SQLException("*** '" + rental.getBookNo() + "'번 도서는 이미 대출중인 도서이므로 대출하실 수 없습니다."
				+ " 처음 메뉴로 돌아갑니다. ***");
	}

	
	@Override
	public void returnBook(Rental rental) throws SQLException {
		int result = rentalDAO.returnBook(rental);
		
		if(result ==0) throw new SQLException(" 반납실패 ");
		
	}
	
}
