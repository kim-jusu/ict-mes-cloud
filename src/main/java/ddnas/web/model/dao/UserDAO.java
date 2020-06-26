package ddnas.web.model.dao;

import java.util.List;

import ddnas.web.model.dto.UserDTO;

/**
 * 
 * @project  : DDNAS
 * @package  : ddnas.web.model.dao
 * @filename : UserDAO.java
 * @author   : 김주수
 * @since    : 2016. 11. 7.
 * @version  : 1.0
 */
public interface UserDAO {

	/**
	 * 
	 * @author  : 김주수
	 * @method  : login
	 * @disc    : 로그인 기능
	 * @date    : 2016. 11. 7.
	 * @param   : @param userDTO
	 * @param   : @return
	 */
	public String login(UserDTO userDTO);

	/**
	 * 
	 * @author  : 김주수
	 * @method  : selectWorkerInfo
	 * @disc    : 노동자 정보 가져오기
	 * @date    : 2016. 11. 28.
	 * @param   : @return
	 */
	public List<UserDTO> selectWorkerInfo();
}
