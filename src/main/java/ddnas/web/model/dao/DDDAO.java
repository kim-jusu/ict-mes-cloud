package ddnas.web.model.dao;

import java.util.List;

import ddnas.web.model.dto.DDDTO;

/**
 * 
 * @project  : DDNAS
 * @package  : ddnas.web.model.dao
 * @filename : DDDAO.java
 * @author   : ���ּ�
 * @since    : 2016. 11. 30.
 * @version  : 1.0
 */
public interface DDDAO {

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
	 * @method  : insertDD
	 * @disc    : DD ���
	 * @date    : 2016. 11. 30.
	 * @param   : @param ddDTO
	 * @param   : @return
	 */
	public int insertDD(DDDTO ddDTO);

	/**
	 * 
	 * @author  : ���ּ�
	 * @method  : getSequence
	 * @disc    : dd sequence��������
	 * @date    : 2016. 11. 30.
	 * @param   : @return
	 */
	public int getSequence();

	/**
	 * 
	 * @author  : ���ּ�
	 * @method  : deleteDD
	 * @disc    : DD ����
	 * @date    : 2016. 12. 1.
	 * @param   : @param ddCode
	 * @param   : @return
	 */
	public int deleteDD(int ddCode);
}
