package ddnas.web.model.service;

import java.util.List;

import ddnas.web.model.dto.DDDTO;

/**
 * 
 * @project  : DDNAS
 * @package  : ddnas.web.model.service
 * @filename : DDService.java
 * @author   : ���ּ�
 * @since    : 2016. 11. 30.
 * @version  : 1.0
 */
public interface DDService {

	/**
	 * 
	 * @author  : ���ּ�
	 * @method  : selectDDList
	 * @disc    : DD ����Ʈ select
	 * @date    : 2016. 11. 30.
	 * @param   : @return
	 */
	public List<DDDTO> selectDDList();

	/**
	 * 
	 * @author  : ���ּ�
	 * @param path 
	 * @method  : insertDD
	 * @disc    : DD ���
	 * @date    : 2016. 11. 30.
	 * @param   : @param ddDTO
	 * @param   : @return
	 */
	public String insertDD(DDDTO ddDTO, String path);

	/**
	 * 
	 * @author  : ���ּ�
	 * @method  : deleteDD
	 * @disc    : dd ����
	 * @date    : 2016. 12. 1.
	 * @param   : @param ddCode
	 * @param   : @param realPath
	 * @param   : @return
	 */
	public int deleteDD(int ddCode, String realPath);
}
