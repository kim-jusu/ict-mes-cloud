package ddnas.web.model.dto;

public class WorkInfoDTO {

	private int timeWage;
	private int workDay;
	private int workHour;
	private int workMin;

	public WorkInfoDTO() {
	}

	public WorkInfoDTO(int timeWage, int workDay, int workHour, int workMin) {
		this.timeWage=timeWage;
		this.workDay=workDay;
		this.workHour=workHour;
		this.workMin=workMin;
	}

	public int getTimeWage() {
		return timeWage;
	}

	public void setTimeWage(int timeWage) {
		this.timeWage = timeWage;
	}

	public int getWorkDay() {
		return workDay;
	}

	public void setWorkDay(int workDay) {
		this.workDay = workDay;
	}

	public int getWorkHour() {
		return workHour;
	}

	public void setWorkHour(int workHour) {
		this.workHour = workHour;
	}

	public int getWorkMin() {
		return workMin;
	}

	public void setWorkMin(int workMin) {
		this.workMin = workMin;
	}
	
	
}
