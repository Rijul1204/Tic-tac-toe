package com.rijul.game.app.player.spec;

import com.rijul.game.core.Board;

public interface Player {

	public int getMarker();

	public char getMarkerChar();

	public void setName(String name);

	public String getName();

	public int promptForMove(Board board);

}
