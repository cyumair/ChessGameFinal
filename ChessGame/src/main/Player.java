package main;
import Piceces.Piece;


//A Player is either black or white 
public class Player {
	boolean iswhite;
	//String name;
	boolean gameOver;
	
	//Piece enemy;
	
	Player(boolean iswhite){
		this.iswhite = iswhite;
		//this.name = name;
		this.gameOver = false;
	}
	
	
	//This method will Make the Move with the Help of Other methods.
	//Returns True if move is made, returns False if not
	//The Board on which move will be Made, The Destination x and y location And the Piece that needs to be Moved will be Passed as Arguments
	public boolean makemove(Board board, int finalx, int finaly, Piece piece) {
	
			if(piece.isValidMove(board, finalx, finaly, this)) {
				//check if that position we are making move to is already occupied by some other piece
				if(board.isoccupied(finalx, finaly)) {
					//if there is an enemy on that position we want to move our piece to
					if (board.isenemy(this, finalx, finaly)) {
						if (((Piece)board.board[finalx][finaly]).name.equals("1K")) {
							this.gameOver = true;
						}
						movemade(board, finalx, finaly, piece);
						return true;
					}
					else {
						//if there is an ally on that position
						//System.out.println("Cannot Move to that location");
						return false;
					}
				}
				else {
					movemade(board, finalx, finaly, piece);
					return true;
				}
			}
			else {
				return false;
			//}
			}

		}

	
	
	
	//This function is Part of the Make Move Function, It will Basically Replace the Position of the Piece with new One and Empty the Old One.
	//Board, destination x, y and Piece will passed as Arguments. Piece will be moved to Destination X and Y on the Board.
	private void movemade(Board board,int finalx, int finaly, Piece piece) {
	
		board.board[piece.x][piece.y] = " ";
		piece.x = finalx;
		piece.y = finaly;
		board.board[finalx][finaly] = piece;
		
	}

	
}
