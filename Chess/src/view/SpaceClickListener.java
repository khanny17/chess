package view;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Game;
import model.MoveCheckThread;
import model.Piece;
import model.Space;
import pieces.*;

/**
 * Listener class used to listen for when the user clicks a space.
 * Will select a space if nothing is currently selected, otherwise will pass
 *  off checks to a MoveCheckThread.
 * @author Kevin Hannigan
 */
public class SpaceClickListener extends MouseAdapter {

	//keeps track of the currently selected space
	SpacePanel currentSpacePanel = null;

	/**
	 * Handles the user input via mouse
	 */
	@Override
	public void mouseReleased(MouseEvent e) {		


		//get the space which was clicked and the piece on that space
		SpacePanel newSpacePanel = (SpacePanel)e.getSource();
		Piece newPiece = newSpacePanel.getPiece();
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
				if(Game.curPlayer && newPiece.getPlayer().equals(Game.blackPlayer)) {
					return;
				} else if(!Game.curPlayer && newPiece.getPlayer().equals(Game.whitePlayer)) {
					return;
				}
				currentSpacePanel = newSpacePanel;
				currentSpacePanel.select();
			}
			return;
		} else {
			MoveCheckThread check = new MoveCheckThread(currentSpacePanel.getSpace(), newSpacePanel.getSpace());
		}



		

		/*
		 * Update graphics
		 */

		//repaint spaces
		currentSpacePanel.paintComponent(currentSpacePanel.getGraphics());
		newSpacePanel.paintComponent(newSpacePanel.getGraphics());

		/*
		 * Deselect the old space, set current space to null
		 */
		currentSpacePanel.deselect();
		currentSpacePanel = null;

	}
}