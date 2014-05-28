package model;

/**
 * Created when a player makes a move.
 * Saves data from that move
 * @author Kevin Hannigan
 */
public class Move {
	
	private Piece mover;
	private Piece captured;
	
	private Space from;
	private Space to;
	
	public Move(Piece mover, Piece captured, Space from, Space to) {
		this.setMover(mover);
		this.setCaptured(captured);
		this.setFrom(from);
		this.setTo(to);
	}

	@Override
	public String toString() {
		if(captured == null) {
			return String.format("%s moves from %s to %s",mover,from,to);
		} else {
			return String.format("%s captures %s from %s to %s",mover,captured,from,to);
		}
	}
	
	
	public Piece getMover() {
		return mover;
	}

	public void setMover(Piece mover) {
		this.mover = mover;
	}

	public Piece getCaptured() {
		return captured;
	}

	private void setCaptured(Piece captured) {
		this.captured = captured;
	}

	public Space getFrom() {
		return from;
	}

	private void setFrom(Space from) {
		this.from = from;
	}

	public Space getTo() {
		return to;
	}

	private void setTo(Space to) {
		this.to = to;
	}

}
