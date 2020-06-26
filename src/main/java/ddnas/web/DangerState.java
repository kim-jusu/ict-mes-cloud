package ddnas.web;

public class DangerState {
	
	private static DangerState instance = new DangerState();
	
	private int dangerState=0;
	private int userCode=0;
	
	public DangerState() {
	}
	public static DangerState getInstance(){
		return instance;
	}
	public int getDangerState() {
		return dangerState;
	}
	public void setDangerState(int dangerState) {
		this.dangerState = dangerState;
	}
	public int getUserCode() {
		return userCode;
	}
	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}
	
	
	
	
}
