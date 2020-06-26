package ddnas.web.controller;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import ddnas.web.DangerState;
import ddnas.web.model.dto.UserDTO;
import ddnas.web.model.dto.WorkInfoDTO;
import ddnas.web.model.service.AndroidService;
import ddnas.web.websocket.WebSocketSessionList;

import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Controller
 * @project : DDNAS
 * @package : ddnas.web.controller
 * @filename : AndroidController.java
 * @author : ���ּ�
 * @since : 2016. 12. 3.
 * @version : 1.0
 */
@Controller
@RequestMapping("/android")
public class AndroidController {

	@Autowired
	private AndroidService androidServiceImpl;

	/**
	 * 
	 * @author : ���ּ�
	 * @method : login
	 * @disc : �ȵ���̵� �α���
	 * @date : 2016. 12. 1.
	 * @param :
	 * @param request
	 * @param :
	 * @return
	 * @param :
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public UserDTO login(HttpServletRequest request) throws UnsupportedEncodingException {
		System.err.println(request.getParameter("id"));
		System.err.println(request.getParameter("password"));
		return androidServiceImpl.login(new UserDTO(request.getParameter("id"), request.getParameter("password")));
	}

	/**
	 * 
	 * @author : ���ּ�
	 * @method : logout
	 * @disc : �ȵ���̵� �α׾ƿ�
	 * @date : 2016. 12. 1.
	 * @param :
	 * @param request
	 * @param :
	 * @return
	 * @param :
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public void logout(HttpServletRequest request) throws NumberFormatException, IOException {
		androidServiceImpl.logout(request.getParameter("userCode"));
	}

	/**
	 * 
	 * @author : ���ּ�
	 * @method : authentication
	 * @disc : �ȵ���̵� DD ����
	 * @date : 2016. 12. 1.
	 * @param :
	 * @param request
	 * @param :
	 * @return
	 * @param :
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	@RequestMapping(value = "authentication", method = RequestMethod.POST)
	@ResponseBody
	public int authentication(HttpServletRequest request) throws NumberFormatException, IOException {
		System.err.println(request.getParameter("userCode"));
		System.err.println(request.getParameter("ddCode"));

		return androidServiceImpl.authentication(request.getParameter("userCode"), request.getParameter("ddCode"));
	}

	/**
	 * 
	 * @author : ���ּ�
	 * @method : coDensity
	 * @disc : �ȵ���̵忡�� CO ��������
	 * @date : 2016. 12. 3.
	 * @param :
	 * @param request
	 * @param :
	 * @throws UnsupportedEncodingException
	 * @param :
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@RequestMapping(value = "coDensity", method = RequestMethod.POST)
	@ResponseBody
	public int coDensity(HttpServletRequest request)
			throws UnsupportedEncodingException, IOException, InterruptedException {
		String userCode = request.getParameter("userCode");
		String coDensity = request.getParameter("coDensity");
		System.err.println(userCode);
		System.err.println(coDensity);
		// androidServiceImpl.insertWorkState(userCode, coDensity);
		// androidServiceImpl.webSocketSendMessage(userCode, coDensity);
		if (userCode.length() == 2 && coDensity.length() < 10) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						WebSocketSessionList.getInstance().getSession().get(0)
								.sendMessage(new TextMessage(userCode + "/" + coDensity));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		return androidServiceImpl.checkDanger(userCode, coDensity);
	}

	/**
	 * 
	 * @author : ���ּ�
	 * @method : regist
	 * @disc : �뵿�� ȸ������
	 * @date : 2016. 12. 3.
	 * @param :
	 * @param request
	 * @param :
	 * @param response
	 * @param :
	 * @return
	 * @param :
	 * @throws UnsupportedEncodingException
	 * @param :
	 * @throws IOException
	 */
	@RequestMapping(value = "regist", method = RequestMethod.POST)
	@ResponseBody
	public int regist(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException {
		System.err.println(request.getParameter("id"));
		System.err.println(request.getParameter("password"));
		System.err.println(request.getParameter("name"));
		System.err.println(request.getParameter("phone"));
		System.err.println(request.getParameter("picture"));
		return 1;
	}

	/**
	 * 
	 * @author : ���ּ�
	 * @method : workInfo
	 * @disc : �ȵ���̵� �ٹ� ����
	 * @date : 2016. 12. 3.
	 * @param :
	 * @param request
	 * @param :
	 * @return
	 * @param :
	 * @throws UnsupportedEncodingException
	 * @param :
	 * @throws IOException
	 */
	@RequestMapping(value = "workInfo", method = RequestMethod.POST)
	@ResponseBody
	public List<WorkInfoDTO> workInfo(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
		System.err.println(request.getParameter("month"));
		System.err.println(request.getParameter("userCode"));

		return androidServiceImpl.workInfo(request.getParameter("month"), request.getParameter("userCode"));
	}

}
