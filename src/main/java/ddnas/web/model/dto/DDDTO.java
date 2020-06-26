package ddnas.web.model.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @project  : DDNAS
 * @package  : ddnas.web.model.dto
 * @filename : DDDTO.java
 * @author   : ±èÁÖ¼ö
 * @since    : 2016. 11. 30.
 * @version  : 1.0
 */
public class DDDTO {

	private int ddCode;
	private int userCode;
	private String userName;
	private String ddSerialNo;
	private String ddRegistDate;
	private String ddRegistrant;
	private String ddNote;
	private MultipartFile file;
	
	public DDDTO() {
	}

	public DDDTO(String ddSerialNo, String ddRegistrant, String ddNote, MultipartFile file) {
		this.ddSerialNo=ddSerialNo;
		this.ddRegistrant=ddRegistrant;
		this.ddNote=ddNote;
		this.file=file;
	}

	public int getDdCode() {
		return ddCode;
	}

	public void setDdCode(int ddCode) {
		this.ddCode = ddCode;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public String getDdSerialNo() {
		return ddSerialNo;
	}

	public void setDdSerialNo(String ddSerialNo) {
		this.ddSerialNo = ddSerialNo;
	}

	public String getDdRegistDate() {
		return ddRegistDate;
	}

	public void setDdRegistDate(String ddRegistDate) {
		this.ddRegistDate = ddRegistDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDdRegistrant() {
		return ddRegistrant;
	}

	public void setDdRegistrant(String ddRegistrant) {
		this.ddRegistrant = ddRegistrant;
	}

	public String getDdNote() {
		return ddNote;
	}

	public void setDdNote(String ddNote) {
		this.ddNote = ddNote;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
}
