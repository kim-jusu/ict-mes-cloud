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
 * @author   : ���ּ�
 * @since    : 2016. 12. 1.
 * @version  : 1.0
 */
public interface AndroidService {

	/**
	 * 
	 * @author  : ���ּ�
	 * @method  : login
	 * @disc    : �ȵ���̵� �α���
	 * @date    : 2016. 12. 1.
	 * @param   : @param userDTO
	 * @param   : @return
	 */
	public UserDTO login(UserDTO userDTO);

	/**
	 * 
	 * @author  : ���ּ�
	 * @method  : authentication
	 * @disc    : ��� ���� 
	 * @date    : 2016. 12. 1.
	 * @param   : @param parameter
	 * @param   : @param parameter2
	 * @param   : @return
	 */
	public int authentication(String userCode, String ddCode) throws NumberFormatException, IOException;

	/**
	 * 
	 * @author  : ���ּ�
	 * @method  : logout
	 * @disc    : �ȵ���̵� �α׾ƿ�
	 * @date    : 2016. 12. 3.
	 * @param   : @param userCode
	 * @param   : @throws NumberFormatException
	 * @param   : @throws IOException
	 */
	public void logout(String userCode) throws NumberFormatException, IOException ;

	/**
	 * 
	 * @author  : ���ּ�
	 * @method  : workInfo
	 * @disc    : �ȵ���̵� �ٹ�����
	 * @date    : 2016. 12. 3.
	 * @param   : @param parameter
	 * @param   : @param parameter2
	 * @param   : @return
	 */
	public List<WorkInfoDTO> workInfo(String month, String userCode);

	/**
	 * 
	 * @author  : ���ּ�
	 * @method  : insertWorkState
	 * @disc    : ���� CO�� ���
	 * @date    : 2016. 12. 4.
	 * @param   : @param userCode
	 * @param   : @param coDensity
	 */
	public void insertWorkState(String userCode, String coDensity);

	/**
	 * 
	 * @author  : ���ּ�
	 * @method  : checkDanger
	 * @disc    : �����Ȳ üũ
	 * @date    : 2016. 12. 7.
	 * @param   : @param coDensity
	 * @param   : @return
	 */
	public int checkDanger(String userCode, String coDensity);

	public void webSocketSendMessage(String userCode, String coDensity) throws IOException, InterruptedException;
}
