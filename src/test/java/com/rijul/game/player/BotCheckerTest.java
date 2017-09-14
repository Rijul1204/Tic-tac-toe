package com.rijul.game.player;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.rijul.game.core.Board;
import com.rijul.game.core.info.GameData;
import com.rijul.game.player.impl.Bot;
import com.rijul.game.player.spec.Player;
import com.rijul.game.util.GameChecker;

public class BotCheckerTest {

	Player firstPlayerBot;
	Player secondPlayerBot;

	public static final int FIRST = GameData.FIRST_PLAYER_VALUE;
	public static final int SECOND = GameData.SECOND_PLAYER_VALUE;
	public static final int DIMENSION = 3;

	@Before
	public void setUp() throws Exception {
		firstPlayerBot = new Bot("bot1", 1);
		secondPlayerBot = new Bot("bot2", 2);
	}

	

	@Test
	public void testMoveWhenInWinningPosition() {

		Board board = new Board(3);

		board.setMarkerAccordingToTurn(1, 1);
		board.setMarkerAccordingToTurn(4, 1);
		
		board.setMarkerAccordingToTurn(2, 2);
		board.setMarkerAccordingToTurn(5, 2);

		board.setMarkerAccordingToTurn(firstPlayerBot.promptForMove(board), 1);
		assertEquals(true, GameChecker.isWinningPostion(GameData.FIRST_PLAYER_VALUE, board));
	}

	@Test
	public void testBlockingMoveWhenInLosingPosition() {

		Board board = new Board(3);
		
		board.setMarkerAccordingToTurn(5, 1);
		board.setMarkerAccordingToTurn(7, 1);
		
		board.setMarkerAccordingToTurn(1, 2);
		board.setMarkerAccordingToTurn(secondPlayerBot.promptForMove(board), 2);
		board.setMarkerAccordingToTurn(firstPlayerBot.promptForMove(board), 1);
		assertEquals(false, GameChecker.isWinningPostion(GameData.FIRST_PLAYER_VALUE, board));
	}

}
