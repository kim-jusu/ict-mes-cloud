package ddnas.web.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ddnas.web.model.dto.VentilatorDTO;

@Repository
public class VentilatorDAOImpl implements VentilatorDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<VentilatorDTO> selectAll() {
		return sqlSession.selectList("ddnas.web.mapper.ventilMapper.selectAll");
	}

	@Override
	public int powerUp(int ventilCode) {
		return sqlSession.update("ddnas.web.mapper.ventilMapper.powerUp",ventilCode);

	}

	@Override
	public int powerDown(int ventilCode) {
		return sqlSession.update("ddnas.web.mapper.ventilMapper.powerDown",ventilCode);
	}

}
