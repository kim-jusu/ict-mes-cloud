package ddnas.web.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebsocketHandler extends TextWebSocketHandler {

	private WebSocketSessionList sessionList;
	
	public WebsocketHandler() {
		sessionList = WebSocketSessionList.getInstance();
	}

	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.err.println("receive message:" + message.toString());
		session.sendMessage(new TextMessage(message.getPayload()));
	};

	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionList.getSession().add(session);
		System.err.println("立加/技记眠啊");
	}

	public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
		sessionList.getSession().remove(session);
	};
}
