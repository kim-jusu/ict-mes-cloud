package ddnas.web.model.dao;

import java.util.List;

import ddnas.web.model.dto.DDDTO;

/**
 * 
 * @project  : DDNAS
 * @package  : ddnas.web.model.dao
 * @filename : DDDAO.java
 * @author   : 김주수
 * @since    : 2016. 11. 30.
 * @version  : 1.0
 */
public interface DDDAO {

	/**
	 * 
	 * @author  : 김주수
	 * @method  : selectDDList
	 * @disc    : DD 리스트 select
	 * @date    : 2016. 11. 30.
	 * @param   : @return
	 */
	public List<DDDTO> selectDDList();

	/**
	 * 
	 * @author  : 김주수
	 * @method  : insertDD
	 * @disc    : DD 등록
	 * @date    : 2016. 11. 30.
	 * @param   : @param ddDTO
	 * @param   : @return
	 */
	public int insertDD(DDDTO ddDTO);

	/**
	 * 
	 * @author  : 김주수
	 * @method  : getSequence
	 * @disc    : dd sequence가져오기
	 * @date    : 2016. 11. 30.
	 * @param   : @return
	 */
	public int getSequence();

	/**
	 * 
	 * @author  : 김주수
	 * @method  : deleteDD
	 * @disc    : DD 삭제
	 * @date    : 2016. 12. 1.
	 * @param   : @param ddCode
	 * @param   : @return
	 */
	public int deleteDD(int ddCode);
}
