package ddnas.web.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ddnas.web.model.dto.DDDTO;

@Repository
public class DDDAOImpl implements DDDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<DDDTO> selectDDList() {
		return sqlSession.selectList("ddnas.web.mapper.ddMapper.selectAll");
	}

	@Override
	public int insertDD(DDDTO ddDTO) {
		return sqlSession.insert("ddnas.web.mapper.ddMapper.insert",ddDTO);
	}

	@Override
	public int getSequence() {
		return sqlSession.selectOne("ddnas.web.mapper.ddMapper.getSeq");
	}

	@Override
	public int deleteDD(int ddCode) {
		return sqlSession.delete("ddnas.web.mapper.ddMapper.delete", ddCode);
	}

	

}
