package ddnas.web.model.dao;

import java.util.List;

import ddnas.web.model.dto.VentilatorDTO;

public interface VentilatorDAO {

	List<VentilatorDTO> selectAll();

	int powerUp(int ventilCode);

	int powerDown(int ventilCode);
	
}
