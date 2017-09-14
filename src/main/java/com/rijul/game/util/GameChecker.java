package com.rijul.game.util;

import com.rijul.game.core.Board;
import com.rijul.game.core.info.GameData;

public class GameChecker {

	public static final int dirx[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
	public static final int diry[] = { -1, 0, 1, 1, -1, -1, 0, 1 };

	public static boolean isWinningPostion(int markerValue, Board board) {

		for (int i = 0; i < board.getDimension(); i++) {
			for (int j = 0; j < board.getDimension(); j++) {
				if (board.getValue(i, j) != markerValue)
					continue;
				for (int k = 0, l = 7; k < l; k++, l--) {
					int x = i + dirx[k];
					int y = j + diry[k];
					if (outOfBound(x, y, board.getDimension()) || board.getValue(x, y) != markerValue) {
						continue;
					}
					int x1 = i + dirx[l];
					int y1 = j + diry[l];
					if (outOfBound(x1, y1, board.getDimension()) || board.getValue(x1, y1) != markerValue) {
						continue;
					}
					return true;
				}
			}
		}

		return false;
	}

	private static boolean outOfBound(int x, int y, int dimension) {

		if (x < 0 || y < 0 || x >= dimension || y >= dimension)
			return true;
		return false;
	}

	public static boolean isValidMove(int move, Board board) {
		
		move--;
		
		int x = move / board.getDimension();
		int y = move % board.getDimension();
		
		if(outOfBound(x, y, board.getDimension())) return false;
		if(board.getValue(x, y)==GameData.FIRST_PLAYER_VALUE
				|| board.getValue(x, y)==GameData.SECOND_PLAYER_VALUE) return false;
		
		return true;
	}

}
