package com.Jan.webSocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyHandler extends TextWebSocketHandler {

	public MyHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO Auto-generated method stub
		super.handleTextMessage(session, message);
		TextMessage returnMessage = new TextMessage("回复：" + message.getPayload() + " received at server");
		session.sendMessage(returnMessage);
	}

}
