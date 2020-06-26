package ddnas.web.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ddnas.web.model.dto.UserDTO;
import ddnas.web.model.dto.WorkInfoDBDTO;

@Repository
public class AndroidDAOImpl implements AndroidDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int validate(UserDTO userDTO) {
		return sqlSession.selectOne("ddnas.web.mapper.androidMapper.validate", userDTO);
	}

	@Override
	public UserDTO login(UserDTO userDTO) {
		return sqlSession.selectOne("ddnas.web.mapper.androidMapper.login", userDTO);
	}

	@Override
	public int authentication(int userCode, int ddCode) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("userCode", userCode);
		map.put("ddCode", ddCode);
		if (sqlSession.update("ddnas.web.mapper.androidMapper.authenUser", userCode) <= 0)
			return 0;
		return sqlSession.update("ddnas.web.mapper.androidMapper.authenDD", map);
	}

	@Override
	public int insertWorkerRecord(int date, int hour, int minutes, int userCode) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("today", date);
		map.put("hour", hour);
		map.put("minutes", minutes);
		map.put("userCode", userCode);

		if ((int) sqlSession.selectOne("ddnas.web.mapper.androidMapper.checkWorkerRecord", map) > 0)
			return 0;
		return sqlSession.insert("ddnas.web.mapper.androidMapper.insertWorkerRecord", map);
	}

	@Override
	public int logout(int userCode) {
		return sqlSession.update("ddnas.web.mapper.androidMapper.logout", userCode);
	}

	@Override
	public List<WorkInfoDBDTO> workInfo(int month, int userCode) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("month", month);
		map.put("userCode", userCode);
		return sqlSession.selectList("ddnas.web.mapper.androidMapper.workInfo", map);
	}

	@Override
	public void insertWorkState(String userCode, String coDensity) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userCode", Integer.parseInt(userCode));
		map.put("coDensity", coDensity);
		sqlSession.insert("ddnas.web.mapper.androidMapper.insertWorkState", map);
		
	}

}
