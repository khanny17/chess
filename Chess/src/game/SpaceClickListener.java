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
		
		try {
			
			Piece currentPiece = currentSpace.getPiece();

			if(newSpace != currentSpace) {

				//If the space clicked on is empty...
				if(newPiece == null) {


					//Test for legal move

					//move current space piece to new space
					currentSpace.setPiece(null);
					newSpace.setPiece(currentPiece);



				} else { //If the space clicked on is occupied...


					//Test for legal move

					//move current space piece to new space
					currentSpace.setPiece(null);
					newSpace.setPiece(currentPiece);




				}
			}


			/*
			 * Update graphics
			 */

			//repaint spaces
			currentSpace.paintComponent(currentSpace.getGraphics());
			newSpace.paintComponent(newSpace.getGraphics());
			
			/*
			 * Select new space, deselect the old.
			 * This is where we update the current space to be the new one
			 */
			currentSpace.deselect();
			currentSpace = null;
			
		//If current space is null, just select the new space.
		} catch(NullPointerException n) { 
			currentSpace = newSpace;
			currentSpace.select();
		}
	}
}