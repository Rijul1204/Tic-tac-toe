package com.rijul.game.player.impl;

import java.util.Scanner;

import com.rijul.game.core.Board;
import com.rijul.game.core.info.GameData;
import com.rijul.game.player.spec.Player;
import com.rijul.game.util.GameChecker;

public class HumanPlayer implements Player {

	private String name;
	private int playerNumber;
	private Scanner scanner;

	public HumanPlayer(String name, int playerNumber) {
		this.name = name;
		this.playerNumber = playerNumber;
		scanner = new Scanner(System.in);
	}

	@Override
	public int getPlayerNumber() {
		return playerNumber;
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
	public int promptForMove(Board board) {

		System.out.println(
				this.name + ", choose a box to place an '" + GameData.getPlayerMarkerInBoard(playerNumber) + "' into:");
		System.out.println(">> ");

		return getMove(board);
	}

	private int getMove(Board board) {

		String number = scanner.nextLine();

		try {
			int move = Integer.parseInt(number);

			if (GameChecker.isValidMove(move, board) == false) {
				// Print message . .
				System.out.println("Sorry " + this.name + ", This is not a valid move. Please choose again :");
				System.out.println(">> ");
				return getMove(board);
			}
			return move;
		} catch (NumberFormatException ex) {
			System.out.println("Sorry, you input invalid number. Please choose again :");
			return getMove(board);
		}
	}
}
