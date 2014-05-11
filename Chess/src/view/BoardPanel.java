package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;



/**
 * 
 * @author Kevin Hannigan
 */
public class BoardPanel extends JPanel{

	private static final long serialVersionUID = 4403376073822637240L;
	

	public BoardPanel(control.SpaceClickListener selector, model.Board myBoard) {
			
		GridLayout grid = new GridLayout(8,8);
		this.setLayout(grid);
		
		SpacePanel space;
		for(int row = 0; row < model.Board.ROWS; row++) {
			for(int col = 0; col < model.Board.COLS; col++) {
				//add space to grid
				space = new SpacePanel(myBoard.getSpaceAtXY(row, col));
				space.addMouseListener(selector);
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
}
