package ddnas.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ddnas.web.model.dto.VentilatorDTO;

import ddnas.web.model.service.VentService;
import ddnas.web.ventilator.VentData;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @project : DDNAS
 * @package : ddnas.web.controller
 * @filename : VentController.java
 * @author : ±èÁÖ¼ö
 * @since : 2016. 11. 23.
 * @version : 1.0
 */
@Controller
@RequestMapping("vent")
public class VentController {

	@Autowired
	private VentService ventServiceImpl;
	
	@RequestMapping(value="selectAll")
	@ResponseBody
	public List<VentilatorDTO> selectAll(){
		return ventServiceImpl.selectAll();
	}
	
	@RequestMapping(value="powerUp", method=RequestMethod.PUT)
	@ResponseBody
	public int powerUp(int ventilCode, int power){
		return ventServiceImpl.powerUp(ventilCode);
	}
	
	@RequestMapping(value="powerDown", method=RequestMethod.PUT)
	@ResponseBody
	public int powerDown(int ventilCode, int power){
		return ventServiceImpl.powerDown(ventilCode);
	}
	
}
