package model;

/**
 * This is the interface which defines a player in the chess game
 * 
 * @author Kevin Hannigan
 */
public class Player {
	
	/**
	 * True = White
	 * False = Black
	 */
	boolean side;
	
	String name;
	
	public Player(String name, boolean side) {
		this.name = name;
		this.side = side;
	}

	public String getName() {
		return name;
	}
	
	/**
	 * Returns the side the player is on
	 * @return true if the white player, false = black
	 */
	public boolean getSide() {
		return side;
	}
}
