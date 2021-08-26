package allthatbook.mvc.model.dto;


public class Book {
	private int bookNo;
	private String bookName;
	private String bookWriter;
	private String bookPublisher;
	private String pubDate; //�Ⱓ��
	private String regDate; //�����
	private int bookState; //�뿩����: 0, �뿩��: 1, ���� ������: 2
  
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
	*  ��ü�� �ٸ����� ��ǰ��ȣ�� ������ ������ ���ٶ�� �����ϱ� ���� �������̵�
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
