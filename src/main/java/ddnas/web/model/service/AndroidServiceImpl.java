package ddnas.web.model.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.springframework.beans.factory.annotation.Autowired;

import ddnas.web.DangerState;
import ddnas.web.model.dao.AndroidDAO;
import ddnas.web.model.dto.UserDTO;
import ddnas.web.model.dto.WorkInfoDBDTO;
import ddnas.web.model.dto.WorkInfoDTO;
import ddnas.web.websocket.WebSocketSessionList;

import org.springframework.web.socket.TextMessage;
import org.springframework.stereotype.Service;

@Service
public class AndroidServiceImpl implements AndroidService {
	@Autowired
	private AndroidDAO androidDAOImpl;
	public static final Semaphore mutex = new Semaphore(1);

	@Override
	public UserDTO login(UserDTO userDTO) {
		if (androidDAOImpl.validate(userDTO) != 0) {
			return androidDAOImpl.login(userDTO);
		} else {
			return new UserDTO(0, "fail");
		}

	}

	@Override
	public int authentication(String userCode, String ddCode) throws NumberFormatException, IOException {
		if (androidDAOImpl.authentication(Integer.parseInt(userCode), Integer.parseInt(ddCode.substring(2))) > 0) {
			Date date = new Date();
			int day = date.getDate();
			String strDay = "";
			if (day < 10)
				strDay = "0" + day;
			String today = ("" + date.getYear()).substring(1) + "" + (date.getMonth() + 1) + strDay;
			String minutes = "" + date.getMinutes();
			if (date.getMinutes() < 10)
				minutes = "0" + date.getMinutes();
			int result = androidDAOImpl.insertWorkerRecord(Integer.parseInt(today), date.getHours(),
					Integer.parseInt(minutes), Integer.parseInt(userCode));
			WebSocketSessionList.getInstance().getSession().get(0).sendMessage(new TextMessage("start" + userCode));
			return result;
		}
		return 0;
	}

	@Override
	public void logout(String userCode) throws NumberFormatException, IOException {
		androidDAOImpl.logout(Integer.parseInt(userCode));
		WebSocketSessionList.getInstance().getSession().get(0).sendMessage(new TextMessage("stop" + userCode));
	}

	@Override
	public List<WorkInfoDTO> workInfo(String month, String userCode) {

		List<WorkInfoDBDTO> workInfoDBDTO = androidDAOImpl.workInfo(Integer.parseInt(month),
				Integer.parseInt(userCode));
		List<WorkInfoDTO> workInfoDTO = new ArrayList<WorkInfoDTO>();
		int hour = 0;
		int min = 0;
		for (WorkInfoDBDTO work : workInfoDBDTO) {
			hour = work.getEndHour() - work.getStartHour();
			min = work.getEndMin() - work.getStartMin();
			if (min < 0) {
				min = 60 + min;
				hour = hour - 1;
			}
			workInfoDTO.add(new WorkInfoDTO(work.getTimeWage(), work.getDay(), hour, min));
			System.err.println(work.getTimeWage() + "/" + work.getDay() + "/" + hour + "/" + min);
		}
		return workInfoDTO;
	}

	@Override
	public void insertWorkState(String userCode, String coDensity) {
		androidDAOImpl.insertWorkState(userCode, coDensity);
	}

	@Override
	public synchronized int checkDanger(String userCode, String coDensity) {
		int danger = DangerState.getInstance().getDangerState();
		float co = Float.parseFloat(coDensity);
		int result = 0;

		if (co < 400.0)
			result = 0;
		else if (co >= 400)
			result = 1;

		if (result == 0 && DangerState.getInstance().getUserCode() == Integer.parseInt(userCode)) {
			System.err.println("danger=" + result);
			DangerState.getInstance().setDangerState(result);
			DangerState.getInstance().setUserCode(0);
			return result;
		}
		if (result > danger) {
			System.err.println("danger=" + result);
			DangerState.getInstance().setDangerState(result);
			DangerState.getInstance().setUserCode(Integer.parseInt(userCode));
			return result;
		} else {
			System.err.println("danger=" + danger);
			DangerState.getInstance().setDangerState(danger);
			DangerState.getInstance().setUserCode(Integer.parseInt(userCode));
			return danger;
		}
	}

	@Override
	public void webSocketSendMessage(String userCode, String coDensity) throws IOException, InterruptedException {
		if (userCode.length() == 2 && coDensity.length() < 10) {
			System.err.println("semaphore permits:"+mutex.availablePermits());
			mutex.acquire();
			try {
				WebSocketSessionList.getInstance().getSession().get(0)
						.sendMessage(new TextMessage(userCode + "/" + coDensity));
			} finally {
				mutex.release();
			}
		}
	}
}
