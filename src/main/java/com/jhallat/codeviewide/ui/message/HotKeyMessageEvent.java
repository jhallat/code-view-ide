package com.jhallat.codeviewide.ui.message;

public class HotKeyMessageEvent implements MessageEvent {

	public static enum HotKey {
		CTRL_M
	}
	
	private final HotKey hotKey;
	
	public HotKeyMessageEvent(HotKey hotKey) {
		this.hotKey = hotKey;
	}
	
	@Override
	public MessageType getType() {
		return MessageType.HOT_KEY;
	}

	public HotKey getHotKey() {
		return this.hotKey;
	}

	
}
