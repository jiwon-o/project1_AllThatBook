package allthatbook.mvc.model.dto;

import java.sql.Date;

public class Reservation {
    private int rezNo;  //PK
    private int bookNo; //FK
    private int userNo; //FK
    private Date rezDate;
    
    public Reservation() {}
    
    public Reservation(int bookNo, int userNo) {
    	super();
    	this.bookNo = bookNo;
    	this.userNo = userNo;
    }
    
	public Reservation(int rezNo, int bookNo, int userNo, Date rezDate) {
		super();
		this.rezNo = rezNo;
		this.bookNo = bookNo;
		this.userNo = userNo;
		this.rezDate = rezDate;
	}



	public int getRezNo() {
		return rezNo;
	}



	public void setRezNo(int rezNo) {
		this.rezNo = rezNo;
	}



	public int getBookNo() {
		return bookNo;
	}



	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}



	public int getUserNo() {
		return userNo;
	}



	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}



	public Date getRezDate() {
		return rezDate;
	}



	public void setRezDate(Date rezDate) {
		this.rezDate = rezDate;
	}





	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Reservation [rezNo=");
		builder.append(rezNo);
		builder.append(", bookNo=");
		builder.append(bookNo);
		builder.append(", userNo=");
		builder.append(userNo);
		builder.append(", rezDate=");
		builder.append(rezDate);
		builder.append("]");
		return builder.toString();
	}

    
}
