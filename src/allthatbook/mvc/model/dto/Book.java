package allthatbook.mvc.model.dto;


public class Book {
	private int bookNo;
	private String bookName;
	private String bookWriter;
	private String bookPublisher;
	private String pubDate; //출간일
	private String bookField; //책 분야
	private int bookState; //대여가능: 0, 대여중: 1, 예약 대기상태: 2
  
    public Book() {}

	public Book(int bookNo, String bookName, String bookWriter, String bookPublisher,  String bookField, 
				int bookState) {
		super();
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.bookWriter = bookWriter;
		this.bookPublisher = bookPublisher;
		this.bookField = bookField;
		this.bookState = bookState;
	}
	
	

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookWriter() {
		return bookWriter;
	}

	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}

	public String getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public String getPubDate() {
		return pubDate;
	}
	

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public int getBookState() {
		return bookState;
	}

	public void setBookState(int bookState) {
		if(bookState == 0 || bookState == 1 || bookState ==2) {
			this.bookState = bookState;
		}
	}
	
	public String getBookField() {
		return bookField;
	}

	public void setBookField(String bookField) {
		this.bookField = bookField;
	}

	/**
	*  객체가 다르더라도 상품번호가 같으면 무조건 같다라고 이해하기 위해 오버라이드
	*/

    @Override
	public int hashCode() {
    	
		return bookName.hashCode();
	}
	 
	@Override
	public boolean equals(Object obj) {
		Book other = (Book) obj;
		if(bookName.equals(other.bookName)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [bookNo=");
		builder.append(bookNo);
		builder.append(", bookName=");
		builder.append(bookName);
		builder.append(", bookWriter=");
		builder.append(bookWriter);
		builder.append(", bookPublisher=");
		builder.append(bookPublisher);
		builder.append(", pubDate=");
		builder.append(pubDate);
		builder.append(", bookField=");
		builder.append(bookField);
		builder.append(", bookState=");
		builder.append(bookState);
		builder.append("]");
		return builder.toString();
	}
  
	
}
