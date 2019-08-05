package com.jhallat.codeviewide.ui.message;

import java.util.ArrayList;
import java.util.List;

public class MessageEventBus {

	private List<MessageReceiver> receivers = new ArrayList<>();
	
	public void send(MessageEvent<?> messageEvent) {
		
		for (MessageReceiver receiver : receivers) {
			receiver.onReceived(messageEvent);
		}
		
	}
	
	public void addReceiver(MessageReceiver receiver) {
		receivers.add(receiver);
	}
}
