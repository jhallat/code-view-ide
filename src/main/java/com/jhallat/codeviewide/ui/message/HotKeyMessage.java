package com.jhallat.codeviewide.ui.message;

public class HotKeyMessage implements Message {

	public static enum HotKey {
		CTRL_M
	}
	
	private final HotKey hotKey;
	
	public HotKeyMessage(HotKey hotKey) {
		this.hotKey = hotKey;
	}
	
	public HotKey getHotKey() {
		return this.hotKey;
	}
}
