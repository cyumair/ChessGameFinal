package main;
import Piceces.Piece;

public class Board {
	//A 2D array of type Object because 2 different data types are in it, Either an Empty String or a Piece.
	Object[][] board = new Object [8][8];
	Piece piece;  //This variable will be used to determine to keep a track of piece at destination x,y, which will be later used to determine allies and enemies
	
	Board() {  //Constructor will intiatally create a board
		this.updateboard();
	}
	
	//This Function will called imediately after creating a Board and it will fill the Board with Empty Strings.
	public void updateboard() {
	for(int i = 0; i < board.length; i++) {
		for(int j = 0 ; j < board.length ; j++) {
			board[i][j] = " ";
		}
	}
	}


	//This method will determine if the destination x , y is Occupied by someother Piece
	//Returns True if it is Occupied else returns False
	//Destination x, y will be passed as arguments
	public boolean isoccupied(int finalx, int finaly) {
		if (board[finalx][finaly] == " ") {
		return false;
	}
		else {
			this.piece = (Piece)board[finalx][finaly];
			return true;
		}
	}
	
	
	//This method will Check if the Piece that Player wants to Move is On the Board or Not
	//If it is on the Board then, return that Piece, else Just return an empty String.
	//Return type Object is used because two different Data Types could be Returned.
	//It will take Name of Piece and which White or Black player type, so To check That Piece availability on the Board.
	public Object checklocation(String name, boolean iswhite) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0 ; j < board.length ; j++) {
				if(board[i][j] != " ") {
					Piece piecesitting = (Piece) board[i][j];
					//applying xnor gate, if both are white return true or if both are not white return true, else return false;
					if (piecesitting.name.equals(name) && !(piecesitting.iswhite ^ iswhite)) {
						return piecesitting;
					}
				}
			}
		};
		return " ";
	}
	
	
	//This method will Determine if the Player on Destination x, y is an enemy or an ally.
	//It will return True if it is enemy, will return false if not.
	//Player( type: white player or black player) and destination x, y will be passed as Parameters to determine the Ally or Enemy at that location
	public boolean isenemy(Player player, int finalx, int finaly) {
		boolean playersitting = this.piece.iswhite; 
		if (playersitting == player.iswhite) {
			return false;
		}
		else {
			return true;
		}
	}
	
}

