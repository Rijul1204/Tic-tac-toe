package com.rijul.game.app;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.rijul.game.core.BoardTest;
import com.rijul.game.player.BotCheckerTest;
import com.rijul.game.player.HumanPlayerTest;
import com.rijul.game.util.GameCheckerTest;
import com.rijul.game.util.GameUtil;
import com.rijul.game.util.GameUtilTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ BotCheckerTest.class, HumanPlayerTest.class, BoardTest.class, GameCheckerTest.class,
		GameUtilTest.class
})
public class AppTest {

}
