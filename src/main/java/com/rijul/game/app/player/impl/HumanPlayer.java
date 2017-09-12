package com.rijul.game.app.player.impl;

import java.util.Scanner;

import com.rijul.game.app.player.spec.Player;
import com.rijul.game.core.Board;
import com.rijul.game.util.GameChecker;

public class HumanPlayer implements Player{
	
	private String name;
	private int marker;
	private char markerChar;
	private Scanner scanner;
	
	public HumanPlayer(String name, int marker, char markerChar) {
		this.name = name;
		this.marker = marker;
		this.markerChar = markerChar;
		scanner = new Scanner(System.in);
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
	public void setName(String name) {
		this.name = name;
	}	

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int promptForMove(Board board) {
		
		System.out.println(this.name +", choose a box to place an '"+ this.markerChar + "' into:");
		System.out.println(">> ");
		
		return getMove(board);		
	}

	private int getMove(Board board) {
		
		String number = scanner.nextLine();
		int move = Integer.parseInt(number);
		
		if (GameChecker.isValidMove(move, board) == false) {
			// Print message . . 
			System.out.println("Sorry " + this.name +", This is not a valid move. Please choose again :");
			System.out.println(">> ");
			return getMove(board);
		}
		return move;
	}

	
}
