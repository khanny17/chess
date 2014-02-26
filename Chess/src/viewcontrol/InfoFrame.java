package viewcontrol;

import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Chess;

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
		setBounds(660, 0, 450, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		repaint();
		setVisible(true);
	}
}
