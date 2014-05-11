package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JPanel;

import control.Chess;
import control.SpaceClickListener;
import model.Board;


/**
 * 
 * @author Kevin Hannigan
 */
public class BoardPanel extends JPanel{

	private static final long serialVersionUID = 4403376073822637240L;

	/**
	 * The listener used to control space selection throughout the game
	 */
	public static final SpaceClickListener selector = new SpaceClickListener();
	
	/**
	 * private reference to static game board
	 */
	private Board myBoard = Chess.board;
	
	/**
	 * Loads the images of the pieces
	 */
	public static images.Images pieceImages;

	public BoardPanel() {
		//load the piece imagesshow()
		try {
			pieceImages = new images.Images();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(ERROR);
		}
		
		GridLayout grid = new GridLayout(8,8);
		this.setLayout(grid);
		
		SpacePanel space;
		for(int row = 0; row < Board.ROWS; row++) {
			for(int col = 0; col < Board.COLS; col++) {
				//add space to grid
				space = new SpacePanel(myBoard.getSpaceAtXY(row, col));
				this.add(space);
				//color black/white
				if(row%2 == 0) {
					if(col%2 == 1) {
						space.setBackground(Color.GRAY);
					} else {
						space.setBackground(Color.WHITE);
					}
				} else {
					if(col%2 == 0) {
						space.setBackground(Color.GRAY);
					} else {
						space.setBackground(Color.WHITE);
					}
				}
			}
		}
	}

	public Board getBoard() {
		return myBoard;
	}
	
}
