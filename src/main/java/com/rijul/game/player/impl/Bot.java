package com.rijul.game.player.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.rijul.game.core.Board;
import com.rijul.game.core.info.GameData;
import com.rijul.game.player.spec.Player;
import com.rijul.game.util.GameUtil;

/**
 * This bot is an implementation of 1st player in a 3X3 board
 * 
 * @author rijul
 *
 */
public class Bot implements Player {

	private String name;
	private int playerNumber;

	private static final int[][] WINNING_POS = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 1, 4, 7 },
			{ 2, 5, 8 }, { 3, 6, 9 }, { 1, 5, 9 }, { 3, 5, 7 } };
	private static final int[] INITIAL_MOVES = new int[] { 1, 3, 5, 7, 9 };
	private static final String INITIAL_BOARD = "123456789";

	private static final int LOSING_POSITION = -1;
	private static final int WINNING_POSITION = 1;
	private static final int DRAW_POSITION = 0;

	private Map<String, Integer> optimalMoveFromBoardPosition;
	private Map<String, Integer> optimalResultForBoardPosition;
	private Random random;

	public Bot(String name, int playerNumber) {
		this.name = name;
		this.playerNumber = playerNumber;
		optimalMoveFromBoardPosition = new HashMap<String, Integer>();
		optimalResultForBoardPosition = new HashMap<String, Integer>();
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

		String boardAsString = getBoardAsString(board);
		
		// Optimizing for first move only . .
		if (boardAsString.equals(INITIAL_BOARD)) {
			int ind = random.nextInt(INITIAL_MOVES.length);
			return INITIAL_MOVES[ind];
		}
		getOptimalResultForBoardPosition(board, 1);

		return optimalMoveFromBoardPosition.get(boardAsString);
	}

	private String getBoardAsString(Board board) {

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

	private int getOptimalResultForBoardPosition(Board board, int turn) {

		if (isWinning(board)) {
			return LOSING_POSITION;
		}

		String boardAsString = getBoardAsString(board);

		// If already calculated
		if (optimalResultForBoardPosition.containsKey(boardAsString)) {
			return optimalResultForBoardPosition.get(boardAsString);
		}

		// If game finished
		if (isFinishedGame(boardAsString)) {
			optimalResultForBoardPosition.put(boardAsString, DRAW_POSITION);
			optimalMoveFromBoardPosition.put(boardAsString, -1);
			return DRAW_POSITION;
		}

		int totalMoves = board.getDimension() * board.getDimension();

		int optimalMove = -1, result = LOSING_POSITION;

		for (int move = 1; move <= totalMoves; move++) {

			if (notValidMove(board, move)) {
				continue;
			}

			board.setMarkerAccordingToTurn(move, turn);

			int currResult = getOptimalResultForBoardPosition(board, GameUtil.getNextTurnNumber(turn));

			if (currResult == LOSING_POSITION) {
				board.resetMarker(move);
				optimalResultForBoardPosition.put(boardAsString, WINNING_POSITION);
				optimalMoveFromBoardPosition.put(boardAsString, move);
				return WINNING_POSITION;
			} else if (currResult == WINNING_POSITION && optimalMove == -1) {
				optimalMove = move;
			} else if (currResult == DRAW_POSITION) {
				if (result == DRAW_POSITION) {
					// To provide randomness
					if (random.nextInt() % 2 == 1) {
						optimalMove = move;
					}
				} else {
					optimalMove = move;
				}
				result = DRAW_POSITION;
			}
			board.resetMarker(move);
		}

		optimalResultForBoardPosition.put(boardAsString, result);
		optimalMoveFromBoardPosition.put(boardAsString, optimalMove);

		return result;
	}

	private boolean notValidMove(Board board, int i) {
		return board.getValue(i) == GameData.FIRST_PLAYER_VALUE || board.getValue(i) == GameData.SECOND_PLAYER_VALUE;
	}

	private boolean isFinishedGame(String boardAsString) {

		for (char ch : boardAsString.toCharArray()) {
			if (Character.isDigit(ch))
				return false;
		}
		return true;
	}

	private boolean isWinning(Board board) {

		for (int ind = 0; ind < WINNING_POS.length; ind++) {

			int pos1 = WINNING_POS[ind][0];
			int pos2 = WINNING_POS[ind][1];
			int pos3 = WINNING_POS[ind][2];

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
	public String getName() {
		return this.name;
	}

	@Override
	public int getPlayerNumber() {
		return this.playerNumber;
	}
}
