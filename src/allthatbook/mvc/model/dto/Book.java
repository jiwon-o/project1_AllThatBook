package allthatbook.mvc.model.dto;


public class Book {
	private int bookNo;
	private String bookName;
	private String bookWriter;
	private String bookPublisher;
	private String pubDate; //출간일
	private String regDate; //등록일
	private int bookState; //대여가능: 0, 대여중: 1, 예약 대기상태: 2
  
    public Book() {}

	public Book(int bookNo, String bookName, String bookWriter, String bookPublisher, String pubDate, String regDate,
			int bookState) {
		super();
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.bookWriter = bookWriter;
		this.bookPublisher = bookPublisher;
		this.pubDate = pubDate;
		this.regDate = regDate;
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

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getBookState() {
		return bookState;
	}

	public void setBookState(int bookState) {
		this.bookState = bookState;
	}

	/**
	*  객체가 다르더라도 상품번호가 같으면 무조건 같다라고 이해하기 위해 오버라이드
	*/

    @Override
	public int hashCode() {
    	
		return 0;
	}
	 
	@Override
	public boolean equals(Object obj) {
		return false;
	}
  
}
