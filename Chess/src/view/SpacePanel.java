package view;

import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pieces.*;
import model.Piece;
import model.Space;

/**
 * @author Kevin Hannigan
 */
public class SpacePanel extends JPanel implements java.util.Observer {

	private static final long serialVersionUID = 2L;
	
	/**
	 * The model Space
	 */
	private Space mySpace;
	
	/**
	 * The image to represent the piece on the space
	 */
	private JLabel labelImage;
	
	public SpacePanel(Space space) {
		this.mySpace = space;
		this.mySpace.addObserver(this);
		this.labelImage = new JLabel("",null,JLabel.CENTER);
		this.add(labelImage);
		this.update(null,null);
	}
	
	/**
	 * Indicates the space is selected by bordering it in blue
	 */
	public void select() {
		setBorder(BorderFactory.createLineBorder(java.awt.Color.BLUE));
	}
	
	/**
	 * "Deselects" the space by removing the border set in the select method
	 */
	public void deselect() {
		setBorder(null);
	}
/*	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(mySpace.isEmpty()) {
			labelImage.setIcon(null);
		} else {
			labelImage.setIcon(mySpace.getPiece().getImageIcon());
		}
	}
*/
	/**
	 * Updates the space with whichever piece is on it
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		Piece piece = mySpace.getPiece();
		if(piece instanceof BishopPiece) {
			if(piece.getPlayer().equals(view.GameFrame.getInstance().blackPlayer)) {
				labelImage.setIcon(images.Images.getInstance().BlackBishop);
			} else {
				labelImage.setIcon(images.Images.getInstance().WhiteBishop);
			}	
		} else if(piece instanceof KingPiece) {
			if(piece.getPlayer().equals(view.GameFrame.getInstance().blackPlayer)) {
				labelImage.setIcon(images.Images.getInstance().BlackKing);
			} else {
				labelImage.setIcon(images.Images.getInstance().WhiteKing);
			}	
		} else if(piece instanceof KnightPiece) {
			if(piece.getPlayer().equals(view.GameFrame.getInstance().blackPlayer)) {
				labelImage.setIcon(images.Images.getInstance().BlackKnight);
			} else {
				labelImage.setIcon(images.Images.getInstance().WhiteKnight);
			}	
		} else if(piece instanceof PawnPiece) {
			if(piece.getPlayer().equals(view.GameFrame.getInstance().blackPlayer)) {
				labelImage.setIcon(images.Images.getInstance().BlackPawn);
			} else {
				labelImage.setIcon(images.Images.getInstance().WhitePawn);
			}	
		} else if(piece instanceof QueenPiece) {
			if(piece.getPlayer().equals(view.GameFrame.getInstance().blackPlayer)) {
				labelImage.setIcon(images.Images.getInstance().BlackQueen);
			} else {
				labelImage.setIcon(images.Images.getInstance().WhiteQueen);
			}	
		} else if(piece instanceof RookPiece) {
			if(piece.getPlayer().equals(view.GameFrame.getInstance().blackPlayer)) {
				labelImage.setIcon(images.Images.getInstance().BlackRook);
			} else {
				labelImage.setIcon(images.Images.getInstance().WhiteRook);
			}	
		} else {
			labelImage.setIcon(null);
		}
	}

	public Space getSpace() {
		return mySpace;
	}
}
