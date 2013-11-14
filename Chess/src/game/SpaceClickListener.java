package game;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

		//if nothing selected, just select the new space and return
		if(currentSpace == null) {
			//If the new space is empty don't select it
			if(newPiece != null) {
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
					System.out.println("Legal move!");
					//move current space piece to new space
					currentSpace.setPiece(null);
					newSpace.setPiece(currentPiece);
				
				} else {
					System.out.println("illegal move!");
				}



			} else { //If the space clicked on is occupied...


				//Test for legal capture
				Piece captured =
						GameFrame.board.isLegalCapture(currentSpace, newSpace);
				if(captured != null) {
					System.out.println("Legal capture!");
					//add captured piece to array
					
					//move current space piece to new space
					currentSpace.setPiece(null);
					newSpace.setPiece(currentPiece);
				} else {
					System.out.println("illegal capture!");
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