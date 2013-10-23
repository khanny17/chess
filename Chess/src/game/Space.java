package game;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The class used to show the spaces in the grid.
 * 
 * The space is essentially a JPanel which is either empty or has an
 *  ImageIcon of the piece on that space
 * 
 * @author Kevin Hannigan
 */
@SuppressWarnings("serial")
public class Space extends JPanel {
	
	/**
	 * The piece which is currently on the space.
	 */
	private Piece piece;
	
	private JLabel labelImage;
	
	/**
	 * Initializes an empty space
	 */
	public Space() {
		this.piece = null;
		addMouseListener(Board.selector);
		labelImage = new JLabel("",null,JLabel.CENTER);
		add(labelImage);
	}

	/**
	 * Initializes a space with a piece image in the middle
	 */
	public Space(Piece piece) {
		this.piece = piece;
		labelImage = new JLabel("",this.piece.getImageIcon(),JLabel.CENTER);
		add(labelImage);
		addMouseListener(Board.selector);
	}

	/**
	 * Indicates the space is selected by bordering it in blue
	 */
	public void select() {
		setBorder(BorderFactory.createLineBorder(Color.BLUE));
	}

	/**
	 * "Deselects" the space by removing the border set in the select method
	 */
	public void deselect() {
		setBorder(null);
	}
	
	/**
	 * Returns the piece on the space
	 */
	public Piece getPiece() {
		return piece;
	}
	
	/**
	 * Sets the piece
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
		
	/**
	 * Prints the information about the space - its' coordinate
	 *  value and its piece
	 */
	@Override
	public String toString() {
		if(piece == null) {
			return "Coordinate: " + /*getcoordinate+ */ "\nPiece: NONE";
		}
		return "Coordinate: " + /*getCoordinate()+*/ "\n" + piece.toString();
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(piece != null) {
			labelImage.setIcon(piece.getImageIcon());
		} else {
			labelImage.setIcon(null);
		}
	}
}