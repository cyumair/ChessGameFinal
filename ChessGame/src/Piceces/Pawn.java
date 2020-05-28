package Piceces;
import main.Board;
import main.Player;

//Movement of pawn is a little tricky which uses complex algorithm to determine its move.
public class Pawn extends Piece{

	public Pawn(int x, int y, boolean iswhite, String name) {
		super(x, y, iswhite, name);
	}

	
	
	@Override
	public boolean isValidMove(Board board, int finalx, int finaly, Player player) {
		//For a white pawn it can only go upward. 
		if(iswhite) {
			if (board.isoccupied(finalx, finaly)) {
				//Pawn can move one block diagonally if there is an enemy on the target block
					if(board.isenemy(player, finalx, finaly) && (x - finalx == 1) && ((Math.abs(y-finaly)) == 1)) {
						return true;
					}
					else {
						return false;
					}
				}
			
			//normal movement of white pawn
			else {
				if((x - finalx == 1) && (y == finaly)) {
					return true;
				}//A pawn can move 2 blocks on its first move.
				else if (x == 6 && (x - finalx == 2) && (y == finaly) && (!board.isoccupied(finalx + 1, finaly))) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		//for the black pawn, it moves downward only.
		else { // if player 2 turn, iswhite is false then calculations will be a little different
			if (board.isoccupied(finalx, finaly)) {
				//Pawn can move one block diagonally if there is an enemy on the target block
					if(board.isenemy(player, finalx, finaly) && (finalx - x == 1) && ((Math.abs(y-finaly)) == 1)) {
						return true;
					}
					else {
						return false;
					}
			}
			//normal movement of white pawn
			else {
				if((x - finalx == -1) && (y == finaly)) {
					return true;
				}
				//A pawn can move 2 blocks on its first move.
				else if(x == 1 && (x - finalx == -2) && (y == finaly) && (!board.isoccupied(finalx - 1, finaly))) {
					return true;
				}
				else {
					return false;
				}
			}
		}
	
	}
	
	
	@Override
	//Overriding the to String method for understandable name of our object.
	public String toString() {
		if (iswhite) return "w" + name;
		return "b" + name;
	}

}