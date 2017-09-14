package com.rijul.game.core.info;

public enum GameMode {
	
	BotVsHuman(0),
	HumanVsBot(1),
	HumanVsHuman(2);
	
	int mode;
	
	private GameMode(int mode) {
		this.mode = mode;
	}
	public static GameMode of(int mode) {
		if(mode>2 || mode<0) return null;
		return GameMode.values()[mode];
	}
}
