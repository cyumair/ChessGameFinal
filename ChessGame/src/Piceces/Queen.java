package Piceces;

import main.Board;
import main.Player;

//The Queen class contains some methods of its own because of its complex movement on the board.

public class Queen extends Piece {

	public Queen(int x, int y, boolean iswhite, String name) {
		super(x, y, iswhite, name);
		
	}

	@Override
	public boolean isValidMove(Board board, int finalx, int finaly, Player player) {
		if((validrookmove(board, finalx, finaly, player)) || (validbishopmove(board, finalx, finaly, player))){
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	
	//This method will determine Valid Bishop move, because Queen can move on Bishop's path
	//It will return true if the move is valid and False it its not,  It will only be true if it is valid and it is not blocked
	//This method will take Board object and Target x, y location and Player Object as parameters to determine the move
	public boolean validbishopmove(Board board, int finalx, int finaly, Player player) {
		if(Math.abs(finalx - x) == Math.abs(finaly - y) && (!BishopPathBlocked(board, finalx, finaly, player))) {
			return true;
		}
		else {
			return false;
		}
	}

	//This method will determine Valid Bishop move, because Queen can move on Rook's path
	//It will return true if the move is valid and False it its not, It will only be true if it is valid and it is not blocked
	//This method will take Board object and Target x, y location and Player Object as parameters to determine the move
	public boolean validrookmove(Board board, int finalx, int finaly, Player player) {
		if(((finalx == x) || (finaly == y)) && (!RookPathBlocked(board, finalx, finaly, player))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//If any other piece comes in the way of Bishop or Rook then it cannot move, so we made two functions to determine the path is blocked or not
	
	//This method determines if the path of Rook is blocked by some other Piece on the board.
	//It will return true if the path is blocked and false if it is not.
	//This method takes the target x, y location and Board Object to determine weather path from the Piece to target x, y has some other Piece in its way
	private boolean RookPathBlocked(Board board, int finalx, int finaly, Player player) {
		
		//As a Rook can move in four different directions, so Different algorithms are used to determine the path 
		//UP
		if(finalx < x) { 
			//adding 1 so not to check the position on which it is going to move, excluding its own position.
			//only check those positions which are in the way to destination position
			for(int i = (finalx + 1); i < x; i ++) {  
				if(board.isoccupied(i, finaly)) {
					return true;
				}
			}
		}
		//Down
		else if(finalx > x) {
			//subtracting 1 so not to check the position on which it is going to move, excluding its own position
			//only check those positions which are in the way to destination position
			for(int i = (finalx - 1); i > x; i --) {
				if(board.isoccupied(i, finaly)) {
					return true;
				}
			}
		}
		//Left
		else if(finaly > y) {
			for(int i = (finaly - 1); i > y; i --) {
				if(board.isoccupied(finalx, i)) {
					return true;
				}
			}
		}
		//Right
		else if(finaly < y) {
			for(int i = (finaly + 1); i < y; i ++) {
				if(board.isoccupied(finalx, i)) {
					return true;
				}
			}
		}
		return false;
		
	}
	
	
	//This method determines if the path of Bishop is blocked by some other Piece on the board.
	//It will return true if the path is blocked and false if it is not.
	//This method takes the target x, y location and Board Object to determine weather path from the Piece to target x, y has some other Piece in its way
	private boolean BishopPathBlocked(Board board, int finalx, int finaly, Player player) {
		
		//As a Rook can move in four different directions, so Different algorithms are used to determine the path 
		//Up Left
		if((finaly < y) && (finalx < x)){
			for(int i = (finalx + 1), j = (finaly + 1); (i < x) && (j < y); i ++, j ++) {
				if(board.isoccupied(i, j)) {
					return true;
				}
			}
		}
		//Down Right
		else if((finalx > x) && (finaly > y)) {
			for(int i = (finalx - 1), j = (finaly - 1); (i > x) && (j > y); i --, j --) {
				if(board.isoccupied(i, j)) {
					return true;
				}
			}
		}
		//Down Left
		else if((finalx < x) && (finaly > y)) {
			for(int i = (finalx + 1), j = (finaly - 1); (i < x) && (j > y); i ++, j --) {
				if(board.isoccupied(i, j)) {
					return true;
				}
			}
		}
		//Up Right
		else if((finalx > x) && (finaly < y)) {
			for(int i = (finalx - 1), j = (finaly + 1); (i > x) && (j < y); i --, j ++) {
				if(board.isoccupied(i, j)) {
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	//Overriding to understandable name for displaying the Object.
	public String toString() {
		if (iswhite) return "w" + name;
		return "b" + name;
	}
}
