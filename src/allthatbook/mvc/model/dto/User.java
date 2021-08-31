package allthatbook.mvc.model.dto;

public class User {
	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String userPhone;
	private String regDate; //등록일자
	//private int userOverdue;
  
	public User() {}

	//나중에 userOverdue 추가
	public User(int userNo, String userId, String userPwd, String userName, String userPhone, String regDate) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userPhone = userPhone;
		this.regDate = regDate;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("회원번호: ");
		builder.append(userNo);
		builder.append(", 회원ID: ");
		builder.append(userId);
		builder.append(", 패스워드: ");
		builder.append(userPwd);
		builder.append(", 회원이름: ");
		builder.append(userName);
		builder.append(", 연락처: ");
		builder.append(userPhone);
		builder.append(", 등록일자: ");
		builder.append(regDate);
		return builder.toString();
	}
	
	
  
}
