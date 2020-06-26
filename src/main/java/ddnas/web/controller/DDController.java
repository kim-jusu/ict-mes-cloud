package ddnas.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ddnas.web.model.service.DDService;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ddnas.web.model.dto.DDDTO;

/**
 * 
 * @project  : DDNAS
 * @package  : ddnas.web.controller
 * @filename : DDController.java
 * @author   : ±èÁÖ¼ö
 * @since    : 2016. 11. 30.
 * @version  : 1.0
 */
@Controller
@RequestMapping("dd")
public class DDController {

	@Autowired
	private DDService dDServiceImpl;
	
	
	
	@RequestMapping("listAll")
	@ResponseBody
	public List<DDDTO> selectDDList(){
		return dDServiceImpl.selectDDList();
	}
	
	@RequestMapping(value="insert", method=RequestMethod.POST)
	public String insertDD(DDDTO ddDTO, HttpSession session){
		return dDServiceImpl.insertDD(ddDTO, session.getServletContext().getRealPath("/resources/images/dd/"));
	}
	
	@RequestMapping(value="delete", method=RequestMethod.DELETE)
	@ResponseBody
	public int deleteDD(@RequestParam int ddCode, HttpSession session){
		return dDServiceImpl.deleteDD(ddCode, session.getServletContext().getRealPath("/resources/images/dd/"));
	}
	
}
