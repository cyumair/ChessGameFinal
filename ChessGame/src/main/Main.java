 package main;
import java.util.Scanner;

import Piceces.Bishop;
import Piceces.King;
import Piceces.Knight;
import Piceces.Pawn;
import Piceces.Piece;
import Piceces.Queen;
import Piceces.Rook;
public class Main {

	public static void main(String[] args) {
		
		Player player1 = new Player(true);
		Player player2 = new Player(false);
		Board board = initilizeBoard(player1, player2);
		
		
		Scanner input = new Scanner(System.in);
		
		
		boolean gameRuns = true;
		boolean iswhite = true;  // game will start with white player's turn
		//an infinite loop to keep the game running
		System.out.println("How to Play :");
		System.out.println("1. Player Name contains two characters, a number as its Id followed by a Alphabet as its name/type");
		System.out.println("2. The x or y positions that user enters can only be numbers between 0 and 7 as labelled on the board");
		System.out.println("3. w before each player name indicates a White Player and b indicates a Black Player");
		while (gameRuns) {
			displayBoard(board);
			
			if (iswhite) {
				System.out.println("Player 1's Turn"); 
			}
			else {
				System.out.println("Player 2's Turn"); 
			}
			//this is where we ask the use to tell his piece and the destination x , y location. 
			// the piece name can contain 2 characters, a number and a alphanumeric character which is visible name of every piece on the board
			
			System.out.print("Which Piece do you want to move(y to quit): ");
			String piece = input.next().toUpperCase();
			if (piece.equals("Y")) {
				break;
			}
			if (board.checklocation(piece, iswhite).equals(" ")) { //if piece entered by user is not on the board
				System.out.println(piece + " not Found on the board, Please enter a valid Piece Name");
				continue;
			}
			else {
				Piece piecesitting = (Piece)board.checklocation(piece, iswhite);
				
				System.out.print("Enter x position(Input Invalid Position to Stop this Move): ");
				//int askx = input.nextInt();
				int askx;
				try{askx = parseInt(input.next());} //parseInt method is defined below
				catch(RuntimeException ex) {
					System.out.println(ex.getMessage());
					continue;
				}
				
				System.out.print("Enter y position(Input Invalid Position to Stop this Move): ");
				//int asky = input.nextInt();
				int asky;
				try{asky = parseInt(input.next());}
				catch(RuntimeException ex) {
					System.out.println(ex.getMessage());
					continue;
				}
				
				input.nextLine();
				if (iswhite) {	
					boolean movemade = player1.makemove(board, askx, asky, piecesitting);
					if (!movemade) {
						System.out.println("Cannot make this move");
						continue;
					}
					//if game overs during player1 turn
					if (player1.gameOver) {
						displayBoard(board); // Display the board one last time showing the captured King
						System.out.println("The Game is Over, Player 1 wins");
						break;
					}
					iswhite = false;
					
				}
				else {
					boolean movemade = player2.makemove(board, askx, asky, piecesitting);
					//when the player2 turn is complete then set the iswhite to true (player 1 turn comes next)			
					if (!movemade) {
						System.out.println("Cannot make this move");
						continue;
					}
					if (player2.gameOver) {
						displayBoard(board);
						System.out.println("The Game is Over, Player 2 wins");
						break;
					}
					iswhite = true;
				}
			}
			
			}
		
		}

	
	//this method will use SOP to display the chess board in an interactive
	//it will take board object as parameter
	private static void displayBoard(Board board) {
		int rownumber = 0;   // row number is required to keep the track of orignal x coordinates because loop will be hardcoded to make display look attracive
		// most the lines below are hardcoded for a perfect display
		System.out.println();
		System.out.println("             0       1       2       3       4       5       6       7");
		System.out.println();
		for(int i = 0; i < 33 ; i++) {
			if(i == 2 || i == 6 || i == 10 || i == 14 || i == 18 || i == 22 || i == 26 || i == 30) {
				System.out.print("    " + rownumber +"    " + "#");
			}
			else {
				System.out.print("         #");
			}
			for(int j = 0; j < 8 ; j++) {
				if(i == 2 || i == 6 || i == 10 || i == 14 || i == 18 || i == 22 || i == 26 || i == 30) {
					if(board.board[rownumber][j] == " ") {
						System.out.print("   .   " + "#" );
					}
					else {
						System.out.print("  " + board.board[rownumber][j] + "  " + "#" );
					}
					
				}
				else if(i % 4 == 0) {
					System.out.print("########");
				}
				else {
				System.out.print("       " + "#");
				}
			//	System.out.print(board.board[i][j]+ " ");
			}
			if(i == 2 || i == 6 || i == 10 || i == 14 || i == 18 || i == 22 || i == 26 || i == 30) {
				rownumber += 1;
			}
			System.out.println();
		//	System.out.println();
		}
		
	}


	//this method will convert the string into an integer and will throw an exception if it is undone
	// This method is modified in a way to prevent exceptions, so even if the input is not between 0 and 7 it throws exception to prevent index out of bound exception(board has only 7 indices)
	//it takes a string input and converts it into integer and returns the output, or it will throw exceptions which will be handled by main function
	// String input by the user as passed as parameter
	private static int parseInt(String input) throws NumberFormatException{
		int position;
			try {
				position = Integer.parseInt(input);
				if (position < 0 || position > 7) {
					throw new RuntimeException("Enter a number between 0 and 7");
				} 
			}
			catch(NumberFormatException ex){
				throw new NumberFormatException("Input must be a number between 0 and 7");
			}
			
			return position;
	}
	
	//This method will just Place all the Pieces of both players on the Board
	//It will return Board Object, a board full of pieces which will be used in the Whole Game.
	//Player1 and Player are parameters to set some Pieces for Player 1 and some for Player 2 
	private static Board initilizeBoard(Player player1,Player player2) {
		Board board = new Board();

		board.board[6][0] = new Pawn(6,0, player1.iswhite, "1P");
		board.board[6][1] = new Pawn(6,1, player1.iswhite, "2P");
		board.board[6][2] = new Pawn(6,2, player1.iswhite, "3P");
		board.board[6][3] = new Pawn(6,3, player1.iswhite, "4P");
		board.board[6][4] = new Pawn(6,4, player1.iswhite, "5P");
		board.board[6][5] = new Pawn(6,5, player1.iswhite, "6P");
		board.board[6][6] = new Pawn(6,6, player1.iswhite, "7P");
		board.board[6][7] = new Pawn(6,7, player1.iswhite, "8P");
		board.board[1][0] = new Pawn(1,0, player2.iswhite, "1P");
		board.board[1][1] = new Pawn(1,1, player2.iswhite, "2P");
		board.board[1][2] = new Pawn(1,2, player2.iswhite, "3P");
		board.board[1][3] = new Pawn(1,3, player2.iswhite, "4P");
		board.board[1][4] = new Pawn(1,4, player2.iswhite, "5P");
		board.board[1][5] = new Pawn(1,5, player2.iswhite, "6P");
		board.board[1][6] = new Pawn(1,6, player2.iswhite, "7P");
		board.board[1][7] = new Pawn(1,7, player2.iswhite, "8P");
		
		
		board.board[7][4] = new King(7,4, player1.iswhite, "1K");
		board.board[0][4] = new King(0,4, player2.iswhite, "1K");

		board.board[7][3] = new Queen(7,3, player1.iswhite, "1Q");
		board.board[0][3] = new Queen(0,3, player2.iswhite, "1Q");
		
		board.board[7][0] = new Rook(7,0, player1.iswhite, "1R");
		board.board[7][7] = new Rook(7,7, player1.iswhite, "2R");
		board.board[0][0] = new Rook(0,0, player2.iswhite, "1R");
		board.board[0][7] = new Rook(0,7, player2.iswhite, "2R");
		
		board.board[7][2] = new Bishop(7,2, player1.iswhite, "1B");
		board.board[7][5] = new Bishop(7,5, player1.iswhite, "2B");
		board.board[0][2] = new Bishop(0,2, player2.iswhite, "1B");
		board.board[0][5] = new Bishop(0,5, player2.iswhite, "2B");
		
		board.board[7][1] = new Knight(7,1, player1.iswhite, "1H");
		board.board[7][6] = new Knight(7,6, player1.iswhite, "2H");
		board.board[0][1] = new Knight(0,1, player2.iswhite, "1H");
		board.board[0][6] = new Knight(0,6, player2.iswhite, "2H");
		
		return board;
	}
	

}





