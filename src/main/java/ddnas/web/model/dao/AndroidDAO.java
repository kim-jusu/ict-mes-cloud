package ddnas.web.model.dao;

import java.util.List;

import ddnas.web.model.dto.UserDTO;
import ddnas.web.model.dto.WorkInfoDBDTO;
import ddnas.web.model.dto.WorkInfoDTO;

/**
 * 
 * @project  : DDNAS
 * @package  : ddnas.web.model.dao
 * @filename : AndroidDAO.java
 * @author   : 김주수
 * @since    : 2016. 12. 1.
 * @version  : 1.0
 */
public interface AndroidDAO {

	/**
	 * 
	 * @author  : 김주수
	 * @method  : validate
	 * @disc    : 안드로이드 로그인 유효성 검사
	 * @date    : 2016. 12. 1.
	 * @param   : @param userDTO
	 * @param   : @return
	 */
	int validate(UserDTO userDTO);

	/**
	 * 
	 * @author  : 김주수
	 * @method  : login
	 * @disc    : 안드로이드 로그인
	 * @date    : 2016. 12. 1.
	 * @param   : @param userDTO
	 * @param   : @return
	 */
	UserDTO login(UserDTO userDTO);

	/**
	 * 
	 * @author  : 김주수
	 * @method  : authentication
	 * @disc    : 안드로이드 dd 인증
	 * @date    : 2016. 12. 1.
	 * @param   : @param userCode
	 * @param   : @param ddCode
	 * @param   : @return
	 */
	int authentication(int userCode, int ddCode);

	/**
	 * 
	 * @author  : 김주수
	 * @param i 
	 * @method  : insertWorkerRecord
	 * @disc    : 노동자 기록 insert
	 * @date    : 2016. 12. 1.
	 * @param   : @param string
	 * @param   : @param userCode
	 * @param   : @return
	 */
	int insertWorkerRecord(int date, int hour, int minutes, int userCode);

	/**
	 * 
	 * @author  : 김주수
	 * @method  : logout
	 * @disc    : 안드로이드 로그아웃
	 * @date    : 2016. 12. 3.
	 * @param   : @param userCode
	 * @param   : @return
	 */
	int logout(int userCode);

	/**
	 * 
	 * @author  : 김주수
	 * @method  : workInfo
	 * @disc    : 안드로이드 근무기록
	 * @date    : 2016. 12. 3.
	 * @param   : @param parseInt
	 * @param   : @param parseInt2
	 * @param   : @return
	 */
	List<WorkInfoDBDTO> workInfo(int month, int userCode);

	void insertWorkState(String userCode, String coDensity);


}
