package viewcontrol;

import javax.swing.JLabel;

import model.Chess;

public class InfoUpdater 
{
	private model.Board myBoard;
	private InfoFrame myInfoFrame;
	
	private JLabel playLabel;
	private JLabel whiteCaptured;
	private JLabel blackCaptured;
	
	public InfoUpdater(model.Board board)
	{
		myBoard = board;
		myInfoFrame = new InfoFrame();
		
		playLabel = new JLabel();
		myInfoFrame.add(playLabel);
		playLabel.setBounds(0, 10, 200, 15);
		playLabel.setVisible(true);
		updateTurn();
		
		blackCaptured = new JLabel();
		myInfoFrame.add(blackCaptured);
		blackCaptured.setBounds(200, 50, 150, 15);
		blackCaptured.setVisible(true);
		
		whiteCaptured = new JLabel();
		myInfoFrame.add(whiteCaptured);
		whiteCaptured.setBounds(0, 50, 150, 15);
		whiteCaptured.setVisible(true);
		updateCaptured();
	}
	
	public void updateTurn()
	{
		if(Chess.curPlayer)
		{
			playLabel.setText("It's White's turn");
		}
		else 
		{
			playLabel.setText("It's Black's turn");
		}		
	}
	
	public void updateCaptured()
	{
		whiteCaptured.setText("White Captured: " + myBoard.getCapturedPieces());
		blackCaptured.setText("Black Captured: " + myBoard.getCapturedPieces());
	}
}	
	
