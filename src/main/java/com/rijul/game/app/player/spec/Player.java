package com.rijul.game.app.player.spec;

import com.rijul.game.core.Board;

public interface Player {

	public void setName(String name);

	public String getName();
	
	public int getPlayerNumber();

	public int promptForMove(Board board);

}
