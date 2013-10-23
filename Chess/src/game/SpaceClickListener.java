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
		
		if(newSpace != currentSpace) {
			//If the space clicked on is empty...
			if(newPiece == null) {
				Piece currentPiece = currentSpace.getPiece();
				//Test for legal move
				//move current space piece to new space
				currentSpace.setPiece(null);
				newSpace.setPiece(currentPiece);
				//return null, no piece captured;
				
			//If the space clicked on is occupied
			} else {
				
				//test for legal move
				
				
				try {
					currentSpace.deselect();
					currentSpace = newSpace;
					currentSpace.select();
				//on 1st click curSpace will be null so just select new space
				} catch(NullPointerException n) { 
					currentSpace = newSpace;
					currentSpace.select();
				}
			}
		}

	}

}