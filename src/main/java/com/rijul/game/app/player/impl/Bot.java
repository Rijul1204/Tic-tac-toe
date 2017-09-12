package com.rijul.game.app.player.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.rijul.game.app.player.spec.Player;
import com.rijul.game.core.Board;
import com.rijul.game.core.info.GameData;

/**
 * This bot is an implementation of 1st player in a 3X3 board
 * 
 * @author rijul
 *
 */
public class Bot implements Player {

	private String name;
	private int marker;
	private char markerChar;

	private static final int[][] WINNING_POS = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 1, 4, 7 },
			{ 2, 5, 8 }, { 3, 6, 9 }, { 1, 5, 9 }, { 3, 5, 7 } };

	private static final int LOSING_POSITION = -1;
	private static final int WINNING_POSITION = 1;
	private static final int DRAW_POSITION = 0;

	private Map<String, Integer> moveMap;
	private Map<String, Integer> gameResultMap;
	private Random random;

	public Bot(String name, int marker, char markerChar) {
		this.name = name;
		this.marker = marker;
		this.markerChar = markerChar;
		moveMap = new HashMap<String, Integer>();
		gameResultMap = new HashMap<String, Integer>();
		random = new Random(System.currentTimeMillis());
	}

	@Override
	public int promptForMove(Board board) {

		System.out.println(this.name + ", is making a move now : ");
		int move = getMove(board);
		System.out.println(">> " + move);

		return move;
	}

	private int getMove(Board board) {
		if (marker == GameData.FIRST_PLAYER_VALUE) {
			getOptimal(board, 1);
		} else {
			getOptimal(board, 2);
		}
		String str = getBoardString(board);
		return moveMap.get(str);
	}

	private String getBoardString(Board board) {

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < board.getDimension(); i++) {
			for (int j = 0; j < board.getDimension(); j++) {
				if (board.getValue(i, j) == GameData.FIRST_PLAYER_VALUE) {
					builder.append(GameData.FIRST_PLAYER_MARKER);
				} else if (board.getValue(i, j) == GameData.SECOND_PLAYER_VALUE) {
					builder.append(GameData.SECOND_PLAYER_MARKER);
				} else {
					builder.append(board.getValue(i, j));
				}
			}
		}

		return builder.toString();
	}

	private int getOptimal(Board board, int turn) {

		if (isWinning(board)) {
			return LOSING_POSITION;
		}

		String str = getBoardString(board);

		if (gameResultMap.containsKey(str)) {
			return gameResultMap.get(str);
		}

		int n = board.getDimension() * board.getDimension();

		int move = -1, result = LOSING_POSITION;
		boolean finish = true;
		for (int i = 1; i <= n; i++) {
			if (board.getValue(i) == GameData.FIRST_PLAYER_VALUE || board.getValue(i) == GameData.SECOND_PLAYER_VALUE) {
				continue;
			}
			finish = false;

			if (turn == 1) {
				board.setMarker(i, GameData.FIRST_PLAYER_VALUE);
			} else if (turn == 2) {
				board.setMarker(i, GameData.SECOND_PLAYER_VALUE);
			}

			int currResult = getOptimal(board, 3 - turn);

			if (currResult == LOSING_POSITION) {
				move = i;
				board.resetMarker(i);
				gameResultMap.put(str, WINNING_POSITION);
				moveMap.put(str, i);
				return WINNING_POSITION;
			} else if (currResult == WINNING_POSITION && move == -1) {
				move = i;
			} else if (currResult == DRAW_POSITION) {
				if (result == DRAW_POSITION) {
					if(random.nextInt()%2 == 1) {
						move = i;
					}
				} else {
					move = i;
				}
				result = DRAW_POSITION;
			}
			board.resetMarker(i);
		}

		if (finish == true) {
			gameResultMap.put(str, DRAW_POSITION);
			moveMap.put(str, -1);
			return DRAW_POSITION;
		}

		gameResultMap.put(str, result);
		moveMap.put(str, move);

		return result;
	}

	private boolean isWinning(Board board) {

		for (int i = 0; i < WINNING_POS.length; i++) {

			int pos1 = WINNING_POS[i][0];
			int pos2 = WINNING_POS[i][1];
			int pos3 = WINNING_POS[i][2];

			if (board.getValue(pos1) == board.getValue(pos2) && board.getValue(pos2) == board.getValue(pos3)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getMarker() {
		return marker;
	}

	@Override
	public char getMarkerChar() {
		return markerChar;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
