package allthatbook.mvc.controller;

import java.sql.SQLException;

import allthatbook.mvc.model.dto.Book;
import allthatbook.mvc.model.dto.Rental;
import allthatbook.mvc.model.dto.User;
import allthatbook.mvc.model.service.RentalService;
import allthatbook.mvc.model.service.RentalServiceImpl;

public class RentalController {
    
	private static RentalService rentalService = new RentalServiceImpl();
	
	public static void insertRental(User user, Book book) {
		try {
			Rental rental = new Rental( user.getUserNo(), book.getBookNo() );
			rentalService.insertRental(rental);
			//성공 출력 print
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
