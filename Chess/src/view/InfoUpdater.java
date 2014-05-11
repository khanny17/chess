package view;

import java.util.ArrayList;

import javax.swing.JLabel;

import control.Chess;

public class InfoUpdater 
{
	private model.Board myBoard;
	private InfoPanel myInfoPanel;
	
	private JLabel playLabel;
	private JLabel whiteCaptured;
	private JLabel blackCaptured;
	
	public InfoUpdater(model.Board board, InfoPanel info)
	{
		myBoard = board;
		this.myInfoPanel = info;
		
		playLabel = new JLabel();
		myInfoPanel.add(playLabel);
		playLabel.setBounds(100, 10, 200, 15);
		playLabel.setVisible(true);
		updateTurn();
		
		blackCaptured = new JLabel();
		myInfoPanel.add(blackCaptured);
		blackCaptured.setBounds(175, 50, 200, 15);
		blackCaptured.setVisible(true);
		
		whiteCaptured = new JLabel();
		myInfoPanel.add(whiteCaptured);
		whiteCaptured.setBounds(0, 50, 200, 15);
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
	
	/**
	 * Updates the info panel when a new piece is captured
	 */
	public void updateCaptured() {
		ArrayList<model.Piece> allCaptured = myBoard.getCapturedPieces();
		ArrayList<model.Piece> wCaptured = new ArrayList<model.Piece>();
		ArrayList<model.Piece> bCaptured = new ArrayList<model.Piece>();
		
		for(model.Piece p : allCaptured)
		{
			if(p.toString().substring(0, 5).equals("White"))
			{
				bCaptured.add(p);
			}
			else
			{
				wCaptured.add(p);
			}
				
		}
		
		whiteCaptured.setText("White has Captured: ");
		int y = 70;
		for(model.Piece p : wCaptured)
		{
			JLabel wCapLabel = new JLabel(p.toString());
			myInfoPanel.add(wCapLabel);
			wCapLabel.setBounds(15, y, 100, 15);
			wCapLabel.setVisible(true);
			y += 15;
			
		}
		blackCaptured.setText("Black has Captured: ");
		y = 70;
		for(model.Piece p : bCaptured)
		{
			JLabel bCapLabel = new JLabel(p.toString());
			myInfoPanel.add(bCapLabel);
			bCapLabel.setBounds(190, y, 100, 15);
			bCapLabel.setVisible(true);
			y += 15;
			
		}
	}
}	
	
