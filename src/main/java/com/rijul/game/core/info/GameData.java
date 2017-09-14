package com.rijul.game.core.info;

import java.util.Scanner;

public class GameData {

	public static final int FIRST_PLAYER_VALUE = -1;
	public static final int SECOND_PLAYER_VALUE = -2;

	public static final char FIRST_PLAYER_MARKER = 'x';
	public static final char SECOND_PLAYER_MARKER = 'o';
	
	public static int getPlayerValueInBoard(int playerNumber) {
		if(playerNumber == 1) {
			return FIRST_PLAYER_VALUE;
		}
		else {
			return SECOND_PLAYER_VALUE;
		}
	}
	
	public static char getPlayerMarkerInBoard(int playerNumber) {
		if(playerNumber == 1) {
			return FIRST_PLAYER_MARKER;
		}
		else {
			return SECOND_PLAYER_MARKER;
		}
	}
}