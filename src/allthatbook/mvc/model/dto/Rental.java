package allthatbook.mvc.model.dto;

import java.sql.Date;

public class Rental {
	  private int rentNo; //pk
	  private int bookNo; //fk
	  private int userNo; //fk
	  private Date rentDate;   //��������
	  private Date returnDate; //�ݳ�����
	  private int returnState; //�ݳ�����
	  private int overdueState; //��ü����
	
	  
	  public Rental() {}
	  public Rental(int rentNo, int bookNo, int userNo, Date rentDate, Date returnDate, int returnState, int overdueState) {
		super();
		this.rentNo = rentNo;
		this.bookNo = bookNo;
		this.userNo = userNo;
		this.rentDate = rentDate;
		this.returnDate = returnDate;
		this.returnState = returnState;
		this.overdueState = overdueState;
	  }
	
	 
	  
	  
	public int getRentNo() {
		return rentNo;
	}
	public void setRentNo(int rentNo) {
		this.rentNo = rentNo;
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
	public Date getRentDate() {
		return rentDate;
	}
	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public int getReturnState() {
		return returnState;
	}
	public void setReturnState(int returnState) {
		this.returnState = returnState;
	}
	public int getOverdueState() {
		return overdueState;
	}
	public void setOverdueState(int overdueState) {
		this.overdueState = overdueState;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Rental [rentNo=");
		builder.append(rentNo);
		builder.append(", bookNo=");
		builder.append(bookNo);
		builder.append(", userNo=");
		builder.append(userNo);
		builder.append(", rentDate=");
		builder.append(rentDate);
		builder.append(", returnDate=");
		builder.append(returnDate);
		builder.append(", returnState=");
		builder.append(returnState);
		builder.append(", overdueState=");
		builder.append(overdueState);
		builder.append("]");
		return builder.toString();
	}
}