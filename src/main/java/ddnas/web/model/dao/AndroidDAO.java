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
 * @author   : ���ּ�
 * @since    : 2016. 12. 1.
 * @version  : 1.0
 */
public interface AndroidDAO {

	/**
	 * 
	 * @author  : ���ּ�
	 * @method  : validate
	 * @disc    : �ȵ���̵� �α��� ��ȿ�� �˻�
	 * @date    : 2016. 12. 1.
	 * @param   : @param userDTO
	 * @param   : @return
	 */
	int validate(UserDTO userDTO);

	/**
	 * 
	 * @author  : ���ּ�
	 * @method  : login
	 * @disc    : �ȵ���̵� �α���
	 * @date    : 2016. 12. 1.
	 * @param   : @param userDTO
	 * @param   : @return
	 */
	UserDTO login(UserDTO userDTO);

	/**
	 * 
	 * @author  : ���ּ�
	 * @method  : authentication
	 * @disc    : �ȵ���̵� dd ����
	 * @date    : 2016. 12. 1.
	 * @param   : @param userCode
	 * @param   : @param ddCode
	 * @param   : @return
	 */
	int authentication(int userCode, int ddCode);

	/**
	 * 
	 * @author  : ���ּ�
	 * @param i 
	 * @method  : insertWorkerRecord
	 * @disc    : �뵿�� ��� insert
	 * @date    : 2016. 12. 1.
	 * @param   : @param string
	 * @param   : @param userCode
	 * @param   : @return
	 */
	int insertWorkerRecord(int date, int hour, int minutes, int userCode);

	/**
	 * 
	 * @author  : ���ּ�
	 * @method  : logout
	 * @disc    : �ȵ���̵� �α׾ƿ�
	 * @date    : 2016. 12. 3.
	 * @param   : @param userCode
	 * @param   : @return
	 */
	int logout(int userCode);

	/**
	 * 
	 * @author  : ���ּ�
	 * @method  : workInfo
	 * @disc    : �ȵ���̵� �ٹ����
	 * @date    : 2016. 12. 3.
	 * @param   : @param parseInt
	 * @param   : @param parseInt2
	 * @param   : @return
	 */
	List<WorkInfoDBDTO> workInfo(int month, int userCode);

	void insertWorkState(String userCode, String coDensity);


}
