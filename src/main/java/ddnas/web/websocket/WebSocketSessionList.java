package ddnas.web.websocket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.WebSocketSession;

public class WebSocketSessionList {
	
	private static WebSocketSessionList instance = new WebSocketSessionList();
	
	private List<WebSocketSession> session;
	
	public WebSocketSessionList() {
		session = new ArrayList<WebSocketSession>();
	}
	public static WebSocketSessionList getInstance(){
		return instance;
	}
	public List<WebSocketSession> getSession() {
		return session;
	}
	public void setSession(List<WebSocketSession> session) {
		this.session = session;
	}
	
	
}
