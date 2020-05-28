package Piceces;

import main.Board;
import main.Player;

//Bishop inherits from Queen and it simply uses valid bishop move method already defined in its parent class to determine its valid move.

public class Bishop extends Queen {

	public Bishop(int x, int y, boolean iswhite, String name) {
		super(x, y, iswhite, name);
		
	}

	@Override
	public boolean isValidMove(Board board, int finalx, int finaly, Player player) {
		if(super.validbishopmove(board, finalx, finaly, player)) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		if (iswhite) return "w" + name;
		return "b" + name;
	}

}
