package com.rijul.game.player;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.rijul.game.core.info.GameData;
import com.rijul.game.player.impl.Bot;
import com.rijul.game.player.impl.HumanPlayer;
import com.rijul.game.player.spec.Player;

public class HumanPlayerTest {
	
	Player firstPlayer;
	Player secondPlayer;

	public static final int DIMENSION = 3;

	@Before
	public void setUp() throws Exception {
		firstPlayer = new HumanPlayer("player1", 1);
		secondPlayer = new HumanPlayer("palyer2", 2);
	}
	
	@Test
	public void getPlayerNumberTest() {
		
		assertEquals(1, firstPlayer.getPlayerNumber());
		assertEquals(2, secondPlayer.getPlayerNumber());
	}

}
