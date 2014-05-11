package control;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.SpacePanel;
import model.CheckmateChecker;

/**
 * Listener class used to listen for when the user clicks a space.
 * Will select a space if nothing is currently selected, otherwise will pass
 *  off checks to a MoveCheckThread.
 * @author Kevin Hannigan
 */
public class SpaceClickListener extends MouseAdapter {

	//keeps track of the currently selected space
	SpacePanel currentSpacePanel = null;
	
	CheckmateChecker whiteChecker = new CheckmateChecker(Chess.whitePlayer);
	CheckmateChecker blackChecker = new CheckmateChecker(Chess.blackPlayer);

	/**
	 * Handles the user input via mouse
	 */
	@Override
	public void mouseReleased(MouseEvent e) {		

		//get the space which was clicked and the piece on that space
		SpacePanel newSpacePanel = (SpacePanel)e.getSource();
		
		model.Piece newPiece = newSpacePanel.getSpace().getPiece();
		
		
		
		/*
		 * If nothing is currently selected, test if the player clicked on one
		 *  of their own pieces.
		 *  
		 * If so, select the new space and return
		 * Otherwise just return, don't select
		 */
		if(currentSpacePanel == null) {
			//If the new space is empty don't select it
			if(newPiece != null) {
				if(Chess.curPlayer && newPiece.getPlayer().equals(Chess.blackPlayer)) {
					return;
				} else if(!Chess.curPlayer && newPiece.getPlayer().equals(Chess.whitePlayer)) {
					return;
				}
				currentSpacePanel = newSpacePanel;
				currentSpacePanel.select();
			}
			return;
		} else {
			//user clicked on same space, deselect
			if(currentSpacePanel == newSpacePanel) {
				currentSpacePanel.deselect();
				currentSpacePanel = null;
			} else if(Chess.board.move(currentSpacePanel.getSpace(), newSpacePanel.getSpace())) {
				//we already have a piece selected, so move it
				currentSpacePanel.deselect();
				currentSpacePanel = null;
				//check for checkmate
				
					whiteChecker.run();
					
				
					blackChecker.run();
				
			}
		}
	}
}