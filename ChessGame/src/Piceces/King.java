package Piceces;
import main.Board;
import main.Player;

public class King extends Piece{
	

	public King(int x, int y, boolean iswhite, String name) {
		super(x, y, iswhite, name);

	}

	@Override
	public boolean isValidMove(Board board, int finalx, int finaly, Player player) {
		if ((Math.abs((x - finalx)) <= 1) && (Math.abs((y - finaly)) <=1)) {
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
