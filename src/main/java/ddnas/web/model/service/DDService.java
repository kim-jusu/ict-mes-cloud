package ddnas.web.model.service;

import java.util.List;

import ddnas.web.model.dto.DDDTO;

/**
 * 
 * @project  : DDNAS
 * @package  : ddnas.web.model.service
 * @filename : DDService.java
 * @author   : 김주수
 * @since    : 2016. 11. 30.
 * @version  : 1.0
 */
public interface DDService {

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
	 * @param path 
	 * @method  : insertDD
	 * @disc    : DD 등록
	 * @date    : 2016. 11. 30.
	 * @param   : @param ddDTO
	 * @param   : @return
	 */
	public String insertDD(DDDTO ddDTO, String path);

	/**
	 * 
	 * @author  : 김주수
	 * @method  : deleteDD
	 * @disc    : dd 삭제
	 * @date    : 2016. 12. 1.
	 * @param   : @param ddCode
	 * @param   : @param realPath
	 * @param   : @return
	 */
	public int deleteDD(int ddCode, String realPath);
}
