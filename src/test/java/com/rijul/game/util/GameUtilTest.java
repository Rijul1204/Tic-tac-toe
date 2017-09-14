package com.rijul.game.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameUtilTest {

	@Test
	public void getTurnTest() {
		assertEquals(2, GameUtil.getNextTurnNumber(1));
		assertEquals(1, GameUtil.getNextTurnNumber(2));
	}
}
