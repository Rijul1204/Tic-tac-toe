package com.rijul.game.core;

import java.util.Scanner;

import com.rijul.game.core.info.GameData;
import com.rijul.game.core.info.GameMode;
import com.rijul.game.player.impl.Bot;
import com.rijul.game.player.impl.HumanPlayer;
import com.rijul.game.player.spec.Player;
import com.rijul.game.util.GameChecker;
import com.rijul.game.util.GameUtil;

public class Game {

	private GameMode gameMode;
	private Board board;

	private Player player1;
	private Player player2;

	private Scanner scanner;

	private static final int DIMENSION_FOR_BOT_GAME = 3;

	public Game() {
		scanner = new Scanner(System.in);
	}

	public void start() {

		gameMode = getMode();

		initializeGame(gameMode);

		int totalMove = board.getDimension() * board.getDimension();
		int turn = 1;
		Player currPlayer = player1;

		board.print();

		while ((totalMove--) > 0) {

			currPlayer = getPlayerAccordingToTurn(turn);

			int move = currPlayer.promptForMove(board);

			board.setMarkerAccordingToTurn(move, turn);
			
			board.print();

			if (GameChecker.isWinningPostion(GameData.getPlayerValueInBoard(currPlayer.getPlayerNumber()), board)) {
				System.out.println("Congratulations " + currPlayer.getName() + "! You have won.");
				break;
			}

			turn = GameUtil.getNextTurnNumber(turn);
		}

		// No move left
		if (totalMove < 0) {
			System.out.println("It's a Draw !!!");
		}
	}

	private Player getPlayerAccordingToTurn(int turn) {
		Player currPlayer;
		if (turn == 1) {
			currPlayer = player1;
		} else {
			currPlayer = player2;
		}
		return currPlayer;
	}

	private void initializeGame(GameMode gameMode) {

		switch (gameMode) {
		case BotVsHuman:
			board = new Board(DIMENSION_FOR_BOT_GAME);

			player1 = getBot(1);
			player2 = getHumanPlayer(2);
			break;
		case HumanVsBot:
			board = new Board(DIMENSION_FOR_BOT_GAME);

			player1 = getHumanPlayer(1);
			player2 = getBot(2);
			break;
		case HumanVsHuman:
			int dimension = getDimension();
			board = new Board(dimension);

			player1 = getHumanPlayer(1);
			player2 = getHumanPlayer(2);
			break;

		}

	}

	private GameMode getMode() {

		printMessageForFetchingGameMode();

		String nextLine = scanner.nextLine();
		if (nextLine.length() == 0) {
			return GameMode.of(0);
		}

		int mode = Integer.parseInt(nextLine);
		GameMode gameMode = GameMode.of(mode - 1);

		if (gameMode == null) {
			System.out.println("Please choose valid option");
			return getMode();
		}

		return gameMode;
	}

	private void printMessageForFetchingGameMode() {
		System.out.println("Please choose mode of the Game: ");
		System.out.println("1. Bot (1st Player)  vs Human (Support only 3x3 board)");
		System.out.println("2. Human (1st Player) vs Bot (Support only 3x3 board)");
		System.out.println("3. Human  vs Human  " + "(Support upto " + GameData.MAX_DIMENSION + "X"
				+ GameData.MAX_DIMENSION + "board");
		System.out.println(">> please choose your option. (default 1) ");
		System.out.print(">> ");
	}

	private Player getBot(int playerNumber) {
		return new Bot("bot ", playerNumber);
	}

	private Player getHumanPlayer(int playerNumber) {

		System.out.println("Enter name for Player " + playerNumber + ": ");
		System.out.print(">>  ");

		String name = scanner.nextLine();

		if (name.isEmpty()) {
			System.out.println("Please choose name of at least length 1");
			return getHumanPlayer(playerNumber);
		}

		Player player = new HumanPlayer(name, playerNumber);

		return player;

	}

	private int getDimension() {

		System.out.println("Please choose dimension of board: ");
		System.out.print(">> ");

		try {
			String number = scanner.nextLine();
			int dimension = Integer.parseInt(number);

			if (dimension < GameData.MIN_DIMENSION || dimension > GameData.MAX_DIMENSION) {
				System.out.println("Please input valid dimension.");
				return getDimension();
			}

			return dimension;

		} catch (NumberFormatException ex) {
			System.out.println("Please input valid number.");
			return getDimension();
		}
	}
}
