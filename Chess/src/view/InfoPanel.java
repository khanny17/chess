package view;

import java.util.Observable;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import control.Chess;

/**
 * Displays output during the game
 * @author Nick Monteleone & Kevin Hannigan
 */
public class InfoPanel extends javax.swing.JPanel implements java.util.Observer {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The area for text output during the game
	 */
	JTextArea textArea;

	public InfoPanel() {
		this.setPreferredSize(new java.awt.Dimension(200,0));
		this.setLayout(new java.awt.BorderLayout());
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new java.awt.Dimension(150,300));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.add(scrollPane, java.awt.BorderLayout.SOUTH);
	}

	/**
	 * Prints out what happens during the game
	 */
	@Override
	public void update(Observable caller, Object arg1) {
		if(caller instanceof model.Board) {
			String newLine = ((model.Board)caller).getLastMove().toString()+'\n';
			this.textArea.append( newLine );
		} else if(caller instanceof control.CheckmateChecker) {
			if(caller.equals(Chess.whiteChecker)) {
				textArea.append( arg1.toString() );
			} else {
				textArea.append( arg1.toString() );
			}
		}
	}
	
}
