package allthatbook.mvc.model.dto;

import java.sql.Date;

public class Rental {
	  private int rentNo; //pk
	  private int bookNo; //fk
	  private int userNo; //fk
	  private Date exreturnDate; //�ݳ���������
	  private Date rentDate;   //��������
	  private Date returnDate; //�ݳ�����
	  private int returnState; //�ݳ�����
	  private int overdueState; //��ü����
	
	  
	  public Rental() {}
	  
	  public Rental(int bookNo, int userNo) {
		  this.bookNo = bookNo;
		  this.userNo = userNo;
		  
	  }
	  
	  public Rental (int rentNo, int bookNo, int userNo, Date exreturnDate) {
		  super();
			this.rentNo = rentNo;
			this.bookNo = bookNo;
			this.userNo = userNo;
			this.exreturnDate = exreturnDate;
	  }
	  
	  public Rental(int rentNo, int bookNo, int userNo, Date exreturnDate, Date rentDate, Date returnDate,
			int returnState, int overdueState) {
		super();
		this.rentNo = rentNo;
		this.bookNo = bookNo;
		this.userNo = userNo;
		this.exreturnDate = exreturnDate;
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
	
	
	public Date getExreturnDate() {
		return exreturnDate;
	}


	public void setExreturnDate(Date exreturnDate) {
		this.exreturnDate = exreturnDate;
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
		builder.append("�뿩��ȣ: ");
		builder.append(rentNo);
		builder.append(", å��ȣ: ");
		builder.append(bookNo);
		builder.append(", ȸ����ȣ: ");
		builder.append(userNo);
		builder.append(", �ݳ���������: ");
		builder.append(exreturnDate);
		builder.append(", �뿩����: ");
		builder.append(rentDate);
		builder.append(", �ݳ�����: ");
		builder.append(returnDate);
		builder.append(", �ݳ�����: ");
		builder.append(returnState);
		builder.append(", ��ü����: ");
		builder.append(overdueState);
		return builder.toString();
	}

}
