package ddnas.web.model.service;

import java.io.IOException;
import java.util.List;

import ddnas.web.model.dto.UserDTO;
import ddnas.web.model.dto.WorkInfoDTO;

/**
 * 
 * @project  : DDNAS
 * @package  : ddnas.web.model.service
 * @filename : AndroidService.java
 * @author   : 김주수
 * @since    : 2016. 12. 1.
 * @version  : 1.0
 */
public interface AndroidService {

	/**
	 * 
	 * @author  : 김주수
	 * @method  : login
	 * @disc    : 안드로이드 로그인
	 * @date    : 2016. 12. 1.
	 * @param   : @param userDTO
	 * @param   : @return
	 */
	public UserDTO login(UserDTO userDTO);

	/**
	 * 
	 * @author  : 김주수
	 * @method  : authentication
	 * @disc    : 기기 인증 
	 * @date    : 2016. 12. 1.
	 * @param   : @param parameter
	 * @param   : @param parameter2
	 * @param   : @return
	 */
	public int authentication(String userCode, String ddCode) throws NumberFormatException, IOException;

	/**
	 * 
	 * @author  : 김주수
	 * @method  : logout
	 * @disc    : 안드로이드 로그아웃
	 * @date    : 2016. 12. 3.
	 * @param   : @param userCode
	 * @param   : @throws NumberFormatException
	 * @param   : @throws IOException
	 */
	public void logout(String userCode) throws NumberFormatException, IOException ;

	/**
	 * 
	 * @author  : 김주수
	 * @method  : workInfo
	 * @disc    : 안드로이드 근무정보
	 * @date    : 2016. 12. 3.
	 * @param   : @param parameter
	 * @param   : @param parameter2
	 * @param   : @return
	 */
	public List<WorkInfoDTO> workInfo(String month, String userCode);

	/**
	 * 
	 * @author  : 김주수
	 * @method  : insertWorkState
	 * @disc    : 현재 CO농도 기록
	 * @date    : 2016. 12. 4.
	 * @param   : @param userCode
	 * @param   : @param coDensity
	 */
	public void insertWorkState(String userCode, String coDensity);

	/**
	 * 
	 * @author  : 김주수
	 * @method  : checkDanger
	 * @disc    : 위험상황 체크
	 * @date    : 2016. 12. 7.
	 * @param   : @param coDensity
	 * @param   : @return
	 */
	public int checkDanger(String userCode, String coDensity);

	public void webSocketSendMessage(String userCode, String coDensity) throws IOException, InterruptedException;
}
