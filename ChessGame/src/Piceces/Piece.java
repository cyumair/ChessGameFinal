package Piceces;
import main.Board;
import main.Player;

//Every Piece has an X and Y location which will help to figure out its Valid Moves
//The iswhite will be used to Check if the Piece is Black or is White to differentiate Ally or Enemy type.
//Piece will have a Unique Name which will help user to identify and do action with it.
public abstract class Piece {
	public int x;
	public int y;
	public boolean iswhite;
	public String name;

	//These values are assigned on initializing the Game, because a Piece once created, does not change its properties the whole Game.
	Piece(int x, int y, boolean iswhite, String name){
		this.x = x;
		this.y = y;
		this.iswhite = iswhite;
		this.name = name;
	}
	
	//A function the will figure out weather the Player can make a move with it or not
	//It will return boolean True or False, if move is valid then it returns True else False. This value will be used to figure out weather to make move or not.
	//It will take Board, destination x,y and Player as parameters. Board and x, y will used to check valid move and Player to determine ally or an enemy. 
	public abstract boolean isValidMove(Board board, int finalx, int finaly, Player player);
			
	
}