package Piceces;

import main.Board;
import main.Player;

//Rook inherits from Queen and it simply uses valid bishop move method already defined in its parent class to determine its valid move.

public class Rook extends Queen {

	public Rook(int x, int y, boolean iswhite, String name) {
		super(x, y, iswhite, name);
	}

	@Override
	public boolean isValidMove(Board board, int finalx, int finaly, Player player) {
		if(super.validrookmove(board, finalx, finaly, player)) {
			return true;
		}
		else {
			return false;
		}
	}
	

	
	@Override
	public String toString() {
		if (iswhite) return "w" + name;
		return "b" + name;
	}

}
