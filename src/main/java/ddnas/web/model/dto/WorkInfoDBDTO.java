package ddnas.web.model.dto;

public class WorkInfoDBDTO {

	private int timeWage;
	private int day;
	private int startHour;
	private int startMin;
	private int endHour;
	private int endMin;
	
	public WorkInfoDBDTO() {
	}

	public int getTimeWage() {
		return timeWage;
	}

	public void setTimeWage(int timeWage) {
		this.timeWage = timeWage;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getStartHour() {
		return startHour;
	}

	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	public int getStartMin() {
		return startMin;
	}

	public void setStartMin(int startMin) {
		this.startMin = startMin;
	}

	public int getEndHour() {
		return endHour;
	}

	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}

	public int getEndMin() {
		return endMin;
	}

	public void setEndMin(int endMin) {
		this.endMin = endMin;
	}

}
