package Piceces;

import main.Board;
import main.Player;

public class Knight extends Piece {

	public Knight(int x, int y, boolean iswhite, String name) {
		super(x, y, iswhite, name);
		
	}

	@Override
	public boolean isValidMove(Board board, int finalx, int finaly, Player player) {
		if (((Math.abs(finalx - x) == 1) && (Math.abs(finaly - y) == 2)) || ((Math.abs(finalx - x) == 2) && (Math.abs(finaly - y) == 1))) {
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
