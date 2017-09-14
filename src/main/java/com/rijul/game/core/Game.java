package com.rijul.game.core;

import java.util.Scanner;

import com.rijul.game.app.player.impl.Bot;
import com.rijul.game.app.player.impl.HumanPlayer;
import com.rijul.game.app.player.spec.Player;
import com.rijul.game.core.info.GameData;
import com.rijul.game.core.info.GameMode;
import com.rijul.game.util.GameChecker;
import com.rijul.game.util.GameUtil;

public class Game {

	private GameMode gameMode;

	private Board board;

	private Player player1;
	private Player player2;

	private Scanner scanner;

	public Game() {
		scanner = new Scanner(System.in);
	}

	public void start() {

		gameMode = getMode();

		initializeGame(gameMode);

		int turn = 1;
		Player currPlayer = player1;

		int totalMove = board.getDimension() * board.getDimension();

		board.print();

		while ((totalMove--) > 0) {

			if (turn == 1) {
				currPlayer = player1;
			} else {
				currPlayer = player2;
			}

			int move = currPlayer.promptForMove(board);

			board.setMarker(move, GameData.getPlayerValueInBoard(currPlayer.getPlayerNumber()));

			board.print();

			if (GameChecker.isWinningPostion(GameData.getPlayerValueInBoard(currPlayer.getPlayerNumber()), board)) {
				System.out.println("Congratulations " + currPlayer.getName() + "! You have won.");
				break;
			}

			turn = GameUtil.getNextTurnForTwoPlayer(turn);
		}
		
		// No move left 
		if (totalMove < 0) {
			System.out.println("It's a Draw !!!");
		}
	}

	private void initializeGame(GameMode gameMode) {

		int dimension = 3;
		switch (gameMode) {
		case BotVsHuman:
			board = new Board(3);

			player1 = getBot(1);
			player2 = getHumanPlayer(2);
			break;
		case HumanVsBot:
			board = new Board(3);

			player1 = getHumanPlayer(1);
			player2 = getBot(2);
			break;
		case HumanVsHuman:
			dimension = getDimension();
			board = new Board(dimension);

			player1 = getHumanPlayer(1);
			player2 = getHumanPlayer(2);
			break;

		}

	}

	private GameMode getMode() {

		System.out.println("Please choose mode of the Game: ");
		System.out.println("1. Bot (1st Player)  vs Human (Support only 3x3 board)");
		System.out.println("2. Human (1st Player) vs Bot (Support only 3x3 board)");
		System.out.println("3. Human  vs Human  (Support upto 500x500 board)");
		System.out.println(">> please choose your option. (default 1) ");
		System.out.print(">> ");

		String nextLine = scanner.nextLine();
		if (nextLine.length() == 0) {
			return GameMode.of(0);
		}

		int mode = Integer.parseInt(nextLine);
		return GameMode.of(mode - 1);
	}

	private Player getBot(int playerNumber) {
		return new Bot("bot ", playerNumber);
	}

	private Player getHumanPlayer(int playerNumber) {

		System.out.println("Enter name for Player " + playerNumber + ": ");
		System.out.print(">>  ");

		String name = scanner.nextLine();

		Player player = new HumanPlayer(name, playerNumber);

		return player;

	}

	private int getDimension() {

		System.out.println("Please choose dimension of board: ");
		System.out.print(">> ");

		String number = scanner.nextLine();

		return Integer.parseInt(number);
	}
}
