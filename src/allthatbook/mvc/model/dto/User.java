package allthatbook.mvc.model.dto;

public class User {
	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String userPhone;
	private String regDate; //�������
	//private int userOverdue;
  
	public User() {}

	//���߿� userOverdue �߰�
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
		builder.append("ȸ����ȣ: ");
		builder.append(userNo);
		builder.append(", ȸ��ID: ");
		builder.append(userId);
		builder.append(", �н�����: ");
		builder.append(userPwd);
		builder.append(", ȸ���̸�: ");
		builder.append(userName);
		builder.append(", ����ó: ");
		builder.append(userPhone);
		builder.append(", �������: ");
		builder.append(regDate);
		return builder.toString();
	}
	
	
  
}
