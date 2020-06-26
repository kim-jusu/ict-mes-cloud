package ddnas.web.model.service;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.List;

import ddnas.web.model.dao.VentilatorDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ddnas.web.model.dto.VentilatorDTO;
import ddnas.web.ventilator.VentData;

@Service
public class VentServiceImpl implements VentService {

	@Autowired
	private VentilatorDAO ventilatorDAOImpl;
	private VentData ventData = VentData.getInstance();

	@Override
	public void send(String str) {
		try {
			String serverIP = "127.0.0.1";

			Socket socket = new Socket(serverIP, 5000);

			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			VentData ventData = VentData.getInstance();
			System.err.println(ventData.getOverlap() + "전송한다.");
			dos.writeUTF(ventData.getOverlap());
			dos.close();
			socket.close();
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<VentilatorDTO> selectAll() {
		return ventilatorDAOImpl.selectAll();
	}

	@Override
	public int powerUp(int ventilCode) {
		if (!powerControl(ventilCode, 1))
			return 0;
		return ventilatorDAOImpl.powerUp(ventilCode);
	}

	@Override
	public int powerDown(int ventilCode) {
		if (!powerControl(ventilCode, 0))
			return 0;
		return ventilatorDAOImpl.powerDown(ventilCode);
	}

	/**
	 * 
	 * @author : 김주수
	 * @method : powerControl
	 * @disc : w:1번 up x:1번 down y:2번 up z:2번 down
	 * @date : 2016. 12. 3.
	 * @param :
	 * @param ventilCode
	 * @param :
	 * @param state
	 */
	@Override
	public boolean powerControl(int ventilCode, int state) {
		System.err.println(ventilCode + "////" + state);
		if (ventilCode == 1 && state == 1) {// w
			ventData.setOverlap("w");
			this.send("w");
		} else if (ventilCode == 1 && state == 0) {// x
			ventData.setOverlap("x");
			this.send("x");
		} else if (ventilCode == 2 && state == 1) {// y
			ventData.setOverlap("y");
			this.send("y");
		} else if (ventilCode == 2 && state == 0) {// z
			ventData.setOverlap("z");
			this.send("z");
		} else if (ventilCode == 3 && state == 1) {
			ventData.setOverlap("u");
			this.send("u");
		} else if (ventilCode == 3 && state == 0) {
			ventData.setOverlap("v");
			this.send("v");
		} else
			return false;

		return true;
	}

}