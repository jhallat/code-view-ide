package com.jhallat.codeviewide.ui.message;

public class HotKeyMessageEvent implements MessageEvent<HotKeyMessage> {

	private final HotKeyMessage message;
	
	public HotKeyMessageEvent(HotKeyMessage hotKeyMessage) {
		this.message = hotKeyMessage;
	}
	
	@Override
	public MessageType getType() {
		return MessageType.HOT_KEY;
	}

	@Override
	public HotKeyMessage getMessage() {
		return this.message;
	}

	
}
