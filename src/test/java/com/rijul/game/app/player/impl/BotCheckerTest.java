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
		bot = getBot(1);
		board = new Board(3);
	}

	private Player getBot(int playerNumber) {
		return new Bot("bot ", playerNumber);

	}

	@Test
	public void testMoveForSavingGame() {
		assert(true);
	}

}
