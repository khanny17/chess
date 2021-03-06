package control;

import java.util.Map.Entry;

import view.GameFrame;
import model.Piece;
import model.Player;
import model.Space;

/**
 * Separate background thread which checks if there are any valid moves left
 *  for the player whose turn it is now
 *  
 * @author Kevin Hannigan
 */
public class CheckmateChecker extends java.util.Observable implements java.lang.Runnable {

	/**
	 * The player this checker will check for
	 */
	Player player;
	
	/**
	 * Creates new checker which will check if the passed player is in
	 *  checkmate
	 * @param player - the player who is potentially in checkmate
	 */
	public CheckmateChecker(Player player) {
		this.player = player;
	}
	
	/**
	 * Checks every potential move which the player can make.
	 *  If one passes, then the game continues. Otherwise, the player
	 *  is in checkmate.
	 */
	@Override
	public void run() {
		Piece curPiece;
		Space dest;
		
		for(Space[] curSpaceArray: GameFrame.getInstance().getBoard().getBoardArray()) {
			for(Space curSpace:curSpaceArray) {
				curPiece = curSpace.getPiece();
				if(curPiece != null && curPiece.getPlayer() == this.player) {
					for (Entry<Integer, Integer> entry : curPiece.getMoves().entrySet()) {
			            Integer dir = entry.getKey();
			            Integer radius = entry.getValue();
			            
			            int[] xy = GameFrame.getInstance().getBoard().getXYofSpace(curSpace);
						int fromX = xy[0];
						int fromY = xy[1];
						
			            //if a move doesnt put self in check and its legal, return and do nothing
						dest = GameFrame.getInstance().getBoard().getSpaceFromMove(fromY, fromX, dir, radius);
						if(dest != null) {
							if(GameFrame.getInstance().getBoard().isLegalMove(curSpace, dest) &&
									!GameFrame.getInstance().getBoard().movePutsSelfInCheck(curSpace,dest)) {
			            		return;
			            	}
						}
			        }
					
					for (Entry<Integer, Integer> entry : curPiece.getCaptures().entrySet()) {
						Integer dir = entry.getKey();
			            Integer radius = entry.getValue();
			            
			            int[] xy = GameFrame.getInstance().getBoard().getXYofSpace(curSpace);
						int fromX = xy[0];
						int fromY = xy[1];
						
			            //if a move doesnt put self in check and its legal, return and do nothing
						dest = GameFrame.getInstance().getBoard().getSpaceFromMove(fromY, fromX, dir, radius);
						if(dest != null && dest.getPiece() != null) {
							if( (GameFrame.getInstance().getBoard().isLegalCapture(curSpace, dest) != null) &&
									!GameFrame.getInstance().getBoard().movePutsSelfInCheck(curSpace,dest)) {
			            		return; //this is hit if it is not checkmate.
			            	}
						}
			        }
				}
			}
		}
		
		//It is checkmate!
		setChanged();
		notifyObservers(String.format("Checkmate, %s loses" + '\n',player.getName()));		
		clearChanged();
		
	}
	
}
