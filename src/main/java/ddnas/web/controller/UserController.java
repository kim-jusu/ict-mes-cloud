package ddnas.web.controller;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import ddnas.web.model.dto.UserDTO;
import ddnas.web.model.service.UserService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 
 * @project  : DDNAS
 * @package  : ddnas.web.controller
 * @filename : UserController.java
 * @author   : 김주수
 * @since    : 2016. 11. 7.
 * @version  : 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userServiceImpl;
	/**
	 * 
	 * @author  : 김주수
	 * @method  : login
	 * @disc    : 로그인 기능
	 * @date    : 2016. 11. 7.
	 * @param   : @param userDTO
	 * @param   : @return
	 */
	@RequestMapping(value="login", method=RequestMethod.POST)
	@ResponseBody
	public String login(@ModelAttribute("userDTO") UserDTO userDTO){
		return userServiceImpl.login(userDTO);
		
	}
	
	/**
	 * 
	 * @author  : 김주수
	 * @method  : goMainView
	 * @disc    : 로그인 성공 시 근무현황으로 이동
	 * @date    : 2016. 11. 21.
	 * @param   : @return
	 */
	@RequestMapping(value="goMain", method=RequestMethod.GET)
	public ModelAndView goMainView(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/working");
		return mav;
	}

	/**
	 * 
	 * @author  : 김주수
	 * @method  : selectWorkerInfo
	 * @disc    : 노동자 정보 가져오기
	 * @date    : 2016. 11. 28.
	 * @param   : @return
	 */
	@RequestMapping(value="workerInfo", method=RequestMethod.GET)
	@ResponseBody
	public List<UserDTO> selectWorkerInfo(){
		
		return userServiceImpl.selectWorkerInfo();
	}


}
