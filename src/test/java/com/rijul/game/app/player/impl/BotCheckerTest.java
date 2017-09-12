package com.rijul.game.app.player.impl;

import org.junit.Before;
import org.junit.Test;

import com.rijul.game.app.player.spec.Player;
import com.rijul.game.core.Board;
import com.rijul.game.core.info.GameData;

public class BotCheckerTest {

	Player bot;
	Board board;
	
	public static final int FIRST = GameData.FIRST_PLAYER_VALUE;
	public static final int SECOND = GameData.SECOND_PLAYER_VALUE;

	@Before
	public void setUp() throws Exception {
		bot = getBot(1, GameData.FIRST_PLAYER_VALUE, GameData.FIRST_PLAYER_MARKER);
		board = new Board(3);
	}

	private Player getBot(int i, int firstPlayerValue, char firstPlayerMarker) {
		return new Bot("bot ", firstPlayerValue, firstPlayerMarker);

	}

	@Test
	public void testMove() {
		
		board.setMarker(8, FIRST);
		board.setMarker(6, FIRST);
		board.setMarker(9, SECOND);
		board.setMarker(5, SECOND);
		
		int move = bot.promptForMove(board);
		System.out.println(move);
	}

}
