package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
		this.setLayout(new javax.swing.BoxLayout(this,javax.swing.BoxLayout.Y_AXIS));
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);

		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new java.awt.Dimension(150,300));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.add(scrollPane);
		
		JTextField input = new JTextField();
		input.setPreferredSize(new java.awt.Dimension(150,25));
		input.setMaximumSize(new java.awt.Dimension(10000,25));
		
		input.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!((JTextField)arg0.getSource()).getText().trim().equals("")) {
					InfoPanel.this.textArea.append(((JTextField)arg0.getSource()).getText()+'\n');
					((JTextField)arg0.getSource()).setText("");
				}
			}
			
		});
		
		this.add(input);
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
			if(caller.equals(GameFrame.getInstance().whiteChecker)) {
				textArea.append( arg1.toString() );
			} else {
				textArea.append( arg1.toString() );
			}
		}
	}
	
}
