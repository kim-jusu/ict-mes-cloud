package ddnas.web.model.service;

import java.util.List;

import ddnas.web.model.dto.VentilatorDTO;

public interface VentService {
	public void send(String str);

	public List<VentilatorDTO> selectAll();

	public int powerUp(int ventilCode);

	public int powerDown(int ventilCode);
	
	public boolean powerControl(int ventilCode, int state);
}
