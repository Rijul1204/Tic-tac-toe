package com.rijul.game;

import java.util.Scanner;

public class Game {

	private Player player1;
	private Player player2;

	private Scanner scanner;

	public Game() {
		scanner = new Scanner(System.in);
	}

	public void start() {

		int dimension = getDimension();
		Board board = new Board(dimension);

		player1 = getHumanPlayer(1, GameData.FIRST_PLAYER_VALUE, GameData.FIRST_PLAYER_MARKER);
		player2 = getHumanPlayer(2, GameData.SECOND_PLAYER_VALUE, GameData.SECOND_PLAYER_MARKER);

		int turn = 1;
		Player currPlayer = player1;
		int totalMove = dimension*dimension;
		
		board.print();
		
		while (totalMove-- >0) {			

			if (turn == 1) {
				currPlayer = player1;
			} else {
				currPlayer = player2;
			}

			int move = currPlayer.promptForMove(board);
			
			board.setMarker(move, currPlayer.getMarker());
			
			board.print();
			
			if(GameChecker.isWon(currPlayer.getMarker(), board)) {
				System.out.println("Congratulations " + currPlayer.getName() + "! You have won.");
				break;
			}

			turn = 3 - turn;
		}
		
		if(totalMove <0) {
			System.out.println("It's a Draw !!!");
		}
	}

	private Player getHumanPlayer(int playerNumber, int marker, char markerChar) {

		System.out.println("Enter name for Player " + playerNumber + ": ");
		System.out.print(">>  ");

		String name = scanner.nextLine();

		Player player = new Player(name, marker, markerChar);

		return player;

	}

	private int getDimension() {

		System.out.println("Please choose number of players: ");
		System.out.print(">> ");

		System.out.println("Please choose dimension of board: ");
		System.out.print(">> ");

		String number = scanner.nextLine();

		return Integer.parseInt(number);
	}
}
