package allthatbook.mvc.model.dto;

public class User {
	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String userPhone;
	private String regDate; //∞°¿‘¿œ
	private int userOverdue;
  
	public User() {}

	public User(int userNo, String userId, String userPwd, String userName, String userPhone, String regDate,
			int userOverdue) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userPhone = userPhone;
		this.regDate = regDate;
		this.userOverdue = userOverdue;
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

	public int getUserOverdue() {
		return userOverdue;
	}

	public void setUserOverdue(int userOverdue) {
		this.userOverdue = userOverdue;
	}
	
  
}
