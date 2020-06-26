package ddnas.web.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ddnas.web.model.dao.UserDAO;
import ddnas.web.model.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAOImpl;
	
	@Override
	public String login(UserDTO userDTO) {
		return userDAOImpl.login(userDTO);
	}

	@Override
	public List<UserDTO> selectWorkerInfo() {
		return userDAOImpl.selectWorkerInfo();
	}

}
