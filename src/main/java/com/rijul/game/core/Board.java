package com.rijul.game.core;

import com.rijul.game.core.info.GameData;

public class Board {

	private int dimension;
	private int[][] board;

	public Board(int dimension) {
		this.dimension = dimension;
		board = new int[dimension][dimension];
		initializeBoard(dimension);
	}

	private void initializeBoard(int dimension) {
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				board[i][j] = ((i * dimension) + j) + 1;
			}
		}
	}

	public int getDimension() {
		return this.dimension;
	}

	void print() {

		System.out.println();

		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				if (j > 0) {
					System.out.print(" | ");
				}
				if (board[i][j] == GameData.FIRST_PLAYER_VALUE) {
					System.out.print(String.format("%3c", GameData.FIRST_PLAYER_MARKER));
				} else if (board[i][j] == GameData.SECOND_PLAYER_VALUE) {
					System.out.print(String.format("%3c", GameData.SECOND_PLAYER_MARKER));
				} else {
					System.out.print(String.format("%3d", board[i][j]));
				}
			}
			System.out.println();
			for (int j = 1; j <= getTotalNumberOfDashForFormating(); j++) {
				System.out.print("-");
			}
			System.out.println();
		}

		System.out.println();
	}

	private int getTotalNumberOfDashForFormating() {
		return 6 * dimension - 2;
	}

	public int getValue(int x, int y) {
		return this.board[x][y];
	}

	public int getValue(int pos) {

		pos--;
		int x = pos / dimension;
		int y = pos % dimension;
		return this.board[x][y];
	}

	private void setMarker(int pos, int value) {

		if (pos > (dimension * dimension)) {
			// TODO : throw exception
			return;
		}

		pos--;
		int x = pos / dimension;
		int y = pos % dimension;

		board[x][y] = value;
	}

	public void setMarkerAccordingToTurn(int move, int turn) {

		if (turn == 1) {
			setMarker(move, GameData.FIRST_PLAYER_VALUE);
		} else if (turn == 2) {
			setMarker(move, GameData.SECOND_PLAYER_VALUE);
		}
	}

	public void resetMarker(int pos) {

		if (pos > (dimension * dimension)) {
			// TODO : Throw exception
			return;
		}

		pos--;
		int x = pos / dimension;
		int y = pos % dimension;

		board[x][y] = pos + 1;
	}

}
