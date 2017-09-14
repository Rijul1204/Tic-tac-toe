package com.rijul.game.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rijul.game.core.Board;
import com.rijul.game.core.info.GameData;

public class GameCheckerTest {

	private static final int DIMENSION_THREE = 3;
	private static final int DIMENSION_FIVE = 5;

	private static final int TURN1 = 1;
	private static final int TURN2 = 2;

	@Test
	public void checkDiagonalWinningPos() {

		Board board = new Board(DIMENSION_THREE);

		// diagonal
		board.setMarkerAccordingToTurn(1, TURN1);
		board.setMarkerAccordingToTurn(5, TURN1);
		board.setMarkerAccordingToTurn(9, TURN1);

		assertEquals(true, GameChecker.isWinningPostion(GameData.FIRST_PLAYER_VALUE, board));
		assertEquals(false, GameChecker.isWinningPostion(GameData.SECOND_PLAYER_VALUE, board));

		board = new Board(DIMENSION_THREE);

		// diagonal
		board.setMarkerAccordingToTurn(3, TURN2);
		board.setMarkerAccordingToTurn(5, TURN2);
		board.setMarkerAccordingToTurn(7, TURN2);

		assertEquals(false, GameChecker.isWinningPostion(GameData.FIRST_PLAYER_VALUE, board));
		assertEquals(true, GameChecker.isWinningPostion(GameData.SECOND_PLAYER_VALUE, board));
	}

	@Test
	public void checkVerticalWinningPos() {

		Board board = new Board(DIMENSION_FIVE);

		board.setMarkerAccordingToTurn(15, TURN1);
		board.setMarkerAccordingToTurn(20, TURN1);
		board.setMarkerAccordingToTurn(25, TURN1);

		assertEquals(true, GameChecker.isWinningPostion(GameData.FIRST_PLAYER_VALUE, board));
		assertEquals(false, GameChecker.isWinningPostion(GameData.SECOND_PLAYER_VALUE, board));

		board = new Board(DIMENSION_FIVE);
		board.setMarkerAccordingToTurn(1, TURN2);
		board.setMarkerAccordingToTurn(6, TURN2);
		board.setMarkerAccordingToTurn(11, TURN2);

		assertEquals(false, GameChecker.isWinningPostion(GameData.FIRST_PLAYER_VALUE, board));
		assertEquals(true, GameChecker.isWinningPostion(GameData.SECOND_PLAYER_VALUE, board));
	}
	
	@Test
	public void checkHorizontalWinningPos() {

		Board board = new Board(DIMENSION_FIVE);

		board.setMarkerAccordingToTurn(3, TURN1);
		board.setMarkerAccordingToTurn(4, TURN1);
		board.setMarkerAccordingToTurn(5, TURN1);

		assertEquals(true, GameChecker.isWinningPostion(GameData.FIRST_PLAYER_VALUE, board));
		assertEquals(false, GameChecker.isWinningPostion(GameData.SECOND_PLAYER_VALUE, board));
	}
	
	@Test
	public void checkNoWinningPos() {

		Board board = new Board(DIMENSION_THREE);

		board.setMarkerAccordingToTurn(1, TURN1);
		board.setMarkerAccordingToTurn(3, TURN1);
		board.setMarkerAccordingToTurn(5, TURN1);
		

		board.setMarkerAccordingToTurn(2, TURN2);
		board.setMarkerAccordingToTurn(4, TURN2);
		board.setMarkerAccordingToTurn(7, TURN2);
		

		board.setMarkerAccordingToTurn(6, TURN1);
		board.setMarkerAccordingToTurn(8, TURN1);
		board.setMarkerAccordingToTurn(9, TURN2);

		assertEquals(false, GameChecker.isWinningPostion(GameData.FIRST_PLAYER_VALUE, board));
		assertEquals(false, GameChecker.isWinningPostion(GameData.SECOND_PLAYER_VALUE, board));
	}
	
	@Test
	public void validMoveChecker() {
		
		Board board = new Board(DIMENSION_THREE);
		
		assertFalse(GameChecker.isValidMove(-1, board));
		assertFalse(GameChecker.isValidMove(10, board));
		

		assertTrue(GameChecker.isValidMove(2, board));
		board.setMarkerAccordingToTurn(2, 1);
		assertFalse(GameChecker.isValidMove(2, board));
		
		board.setMarkerAccordingToTurn(4, 2);		
		assertFalse(GameChecker.isValidMove(4, board));
		
		assertTrue(GameChecker.isValidMove(5, board));
	}
}
