package ddnas.web.model.dto;

public class VentilatorDTO {

	private int ventilCode;
	private int power;
	private String area;
	
	public VentilatorDTO() {
	}

	public int getVentilCode() {
		return ventilCode;
	}

	public void setVentilCode(int ventilCode) {
		this.ventilCode = ventilCode;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}


}
