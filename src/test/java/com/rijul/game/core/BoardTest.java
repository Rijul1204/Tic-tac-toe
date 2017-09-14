package com.rijul.game.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.rijul.game.core.info.GameData;

public class BoardTest {

	private static final int BOARD_POS1 = 4;
	private static final int BOARD_POS2 = 6;
	
	private static final int TURN1 = 1;
	private static final int TURN2 = 2;
	
	public static final int DIMENSION_FIVE = 5;
	public static final int DIMENSION_THREE = 3;
	

	@Test
	public void checkBoardSize() {
		Board board = new Board(DIMENSION_FIVE);
		assertEquals(board.getDimension(), DIMENSION_FIVE);
	}

	@Test
	public void checkSettingValueInBoard() {
		Board board = new Board(DIMENSION_FIVE);
		board.setMarkerAccordingToTurn(BOARD_POS1, TURN1);
		assertEquals(GameData.FIRST_PLAYER_VALUE, board.getValue(BOARD_POS1));
		

		board.setMarkerAccordingToTurn(BOARD_POS2, TURN2);
		assertEquals(GameData.SECOND_PLAYER_VALUE, board.getValue(BOARD_POS2));
	}

}
