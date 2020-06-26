package ddnas.web.model.dto;

/**
 * 
 * @project  : DDNAS
 * @package  : ddnas.web.model.dto
 * @filename : UserDTO.java
 * @author   : ±èÁÖ¼ö
 * @since    : 2016. 11. 7.
 * @version  : 1.0
 */
public class UserDTO {

	private int userCode;
	private String userId;
	private String userPw;
	private String userName;
	private int userPhone;
	private String userType;
	private int logState;
	
	public UserDTO() {
	}

	public UserDTO(String userId, String userPw) {
		this.userId=userId;
		this.userPw=userPw;
	}
	public UserDTO(int userCode, String userName) {
		this.userCode=userCode;
		this.userName=userName;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(int userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getLogState() {
		return logState;
	}

	public void setLogState(int logState) {
		this.logState = logState;
	}
	
	
	
}
