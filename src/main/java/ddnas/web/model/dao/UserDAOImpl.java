package ddnas.web.model.dao;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import ddnas.web.model.dto.UserDTO;

/**
 * 
 * @project  : DDNAS
 * @package  : ddnas.web.model.dao
 * @filename : UserDAOImpl.java
 * @author   : ±èÁÖ¼ö
 * @since    : 2016. 11. 7.
 * @version  : 1.0
 */
@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public String login(UserDTO userDTO) {
		return sqlSession.selectOne("ddnas.web.mapper.userMapper.login",userDTO);
	}

	@Override
	public List<UserDTO> selectWorkerInfo() {
		return sqlSession.selectList("ddnas.web.mapper.userMapper.workerList");
	}

}
