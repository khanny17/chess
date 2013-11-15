package game;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import pieces.*;

/**
 * Listener class used to listen for when the user clicks a space
 * @author Kevin Hannigan
 */
public class SpaceClickListener extends MouseAdapter {

	//keeps track of the currently selected space
	Space currentSpace = null;

	/**
	 * Handles the user input via mouse
	 */
	@Override
	public void mouseReleased(MouseEvent e) {		


		//get the space which was clicked and the piece on that space
		Space newSpace = (Space)e.getSource();
		Piece newPiece = newSpace.getPiece();

		/*
		 * If nothing selected, test if the player clicked on one of their own pieces.
		 *  If so, select the new space and return
		 *  Otherwise just return, don't select
		 */
		
		if(currentSpace == null) {
			//If the new space is empty don't select it
			if(newPiece != null) {
				if(Game.curPlayer && newPiece.getPlayer().equals(Game.blackPlayer)) {
					return;
				} else if(!Game.curPlayer && newPiece.getPlayer().equals(Game.whitePlayer)) {
					return;
				}
				currentSpace = newSpace;
				currentSpace.select();
			}
			return;
		}



		Piece currentPiece = currentSpace.getPiece();

		if(newSpace != currentSpace) {

			//If the space clicked on is empty...
			if(newPiece == null) {



				//Test for legal move
				if(GameFrame.board.isLegalMove(currentSpace, newSpace)) {
					//move current space piece to new space
					currentSpace.setPiece(null);
					newSpace.setPiece(currentPiece);
					if(currentPiece instanceof PawnPiece) {
						((PawnPiece) currentPiece).moved();
					} else if(currentPiece instanceof PawnPiece) {
						((RookPiece) currentPiece).moved();
					} else if(currentPiece instanceof PawnPiece) {
						((KingPiece) currentPiece).moved();
					}
				
				} else {
					//System.err.println("illegal move!");
				}

				//flip player's turns
				Game.curPlayer = !Game.curPlayer;
				
			} else { //If the space clicked on is occupied...


				//Test for legal capture
				Piece captured =
						GameFrame.board.isLegalCapture(currentSpace, newSpace);
				if(captured != null) {
					//add captured piece to array
					
					//move current space piece to new space
					currentSpace.setPiece(null);
					newSpace.setPiece(currentPiece);
					
					if(currentPiece instanceof PawnPiece) {
						((PawnPiece) currentPiece).moved();
					} else if(currentPiece instanceof PawnPiece) {
						((RookPiece) currentPiece).moved();
					} else if(currentPiece instanceof PawnPiece) {
						((KingPiece) currentPiece).moved();
					}
					
					//flip player's turns
					Game.curPlayer = !Game.curPlayer;
					
				} else {
					//System.err.println("illegal capture!");
				}

			}
		}

		/*
		 * Update graphics
		 */

		//repaint spaces
		currentSpace.paintComponent(currentSpace.getGraphics());
		newSpace.paintComponent(newSpace.getGraphics());

		/*
		 * Deselect the old space, set current space to null
		 */
		currentSpace.deselect();
		currentSpace = null;

	}
}