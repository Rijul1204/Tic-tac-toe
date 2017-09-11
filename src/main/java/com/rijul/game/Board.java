package com.rijul.game;

public class Board {

	private int dimension;
	private int[][] board;

	public Board(int dimension) {
		this.dimension = dimension;
		board = new int[dimension][dimension];
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
				if (j>0) {
					System.out.print(" | ");
				}
				if (board[i][j] == GameData.FIRST_PLAYER_VALUE) {
					System.out.print(String.format("%2c", GameData.FIRST_PLAYER_MARKER));
				} else if (board[i][j] == GameData.SECOND_PLAYER_VALUE) {
					System.out.print(String.format("%2c", GameData.SECOND_PLAYER_MARKER));
				} else {
					System.out.print(String.format("%2d", board[i][j]));
				}
			}
			System.out.println();
			for (int j = 1; j <= 5 * dimension - 2; j++) {
				System.out.print("-");
			}
			System.out.println();
		}

		System.out.println();
	}
	
	public int getValue(int x, int y) {
		return this.board[x][y];
	}

	public void setMarker(int pos, int value) {

		if (pos > (dimension * dimension)) {
			// TODO : Throw exception
			return;
		}

		pos--;
		int x = pos / dimension;
		int y = pos % dimension;

		board[x][y] = value;
	}

}
