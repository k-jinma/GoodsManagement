package goods;

public class License {

	private String serviceName; // サービス名（Zoom）
	private String id; // ID
	private String password; // Password
	private String mailAddress; // MailAddress
	private String startDate; // 開始日時
	private String startTime; // 　　時間
	private String endDate; // 終了日時
	private String endTime; // 　　時間
	private String userName; // 使用者名
	private String purpose; // 使用目的
	
	public License() {
		
	}
	
	public License(String serviceName, String id, String password, String mailAddress, String startDate, String endDate,
			String userName, String purpose) {
		this.serviceName = serviceName;
		this.id = id;
		this.password = password;
		this.mailAddress = mailAddress;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userName = userName;
		this.purpose = purpose;
	}
	
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	
}
