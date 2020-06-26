package ddnas.web.ventilator;

public class VentData {

	private volatile static VentData instance=null;

	private int ventA;
	private int ventB;
	private int state;
	private String overlap;

	public VentData() {
		ventA=0;
		ventB=0;
		state=0;
		overlap="z";
	}

	public static VentData getInstance() {
		if (instance == null) {
			synchronized (VentData.class) {
				if (instance == null) {
					instance = new VentData();
				}
			}
		}
		return instance;
	}

	public int getVentA() {
		return ventA;
	}

	public void setVentA(int ventA) {
		this.ventA = ventA;
	}

	public int getVentB() {
		return ventB;
	}

	public void setVentB(int ventB) {
		this.ventB = ventB;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getOverlap() {
		return overlap;
	}

	public void setOverlap(String overlap) {
		this.overlap = overlap;
	}

}
