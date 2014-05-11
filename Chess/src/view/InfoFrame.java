package view;

import javax.swing.JFrame;
import javax.swing.JLabel;

import control.Chess;

public class InfoFrame  extends JFrame
{

	private String player;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InfoFrame() 
	{
		super("Game Info");
		setBounds(660, 0, 350, 620);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		repaint();
		setVisible(true);
	}
}
