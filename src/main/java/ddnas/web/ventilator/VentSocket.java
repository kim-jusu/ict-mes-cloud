package ddnas.web.ventilator;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class VentSocket {
	public static void main(String[] args) {
		VentData ventData = VentData.getInstance();
		ServerSocket serverSocket = null;
		new VentSerial().initialize();

		try {
			serverSocket = new ServerSocket(5000);
		} catch (IOException e) {
			e.printStackTrace();
		} // try - catch

		while (true) {
			try {
				System.out.println("대기중");
				Socket socket = serverSocket.accept();
				InputStream in = socket.getInputStream();
				DataInputStream dis = new DataInputStream(in);
				ventData.setOverlap(dis.readUTF());
				System.out.println("서버 : " + ventData.getOverlap()+"/");
				ventData.setState(1);
	            Thread.sleep(3000);
				ventData.setState(0);
				dis.close();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			} // try - catch
		} // while

	} // main

}