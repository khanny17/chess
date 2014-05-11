package model;

/**
 * This is the interface which defines a player in the chess game
 * 
 * @author Kevin Hannigan
 */
public class Player {
	
	String name;
	
	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
