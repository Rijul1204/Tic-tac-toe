package com.rijul.game;

import java.util.Scanner;

public class Player {
	
	private String name;
	private int marker;
	private char markerChar;
	private Scanner scanner;
	
	public Player(String name, int marker, char markerChar) {
		this.name = name;
		this.marker = marker;
		this.markerChar = markerChar;
		scanner = new Scanner(System.in);
	}
	
	public int getMarker() {
		return marker;
	}
	public char getMarkerChar() {
		return markerChar;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

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
