package game;
import java.util.ArrayList;
/**
 * This is the interface which defines a player in the chess game
 * 
 * @author Kevin Hannigan
 */
public abstract class Player {
	
	public ArrayList<Piece> myPieces;
	
	public abstract Coordinate getMove();

}
