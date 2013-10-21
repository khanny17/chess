package game;
/**
 * Enum file to define coordinates in the game board grid as a letter number pair
 * @author Kevin Hannigan
 */
public enum Coordinate {

	A1(7,0),
	A2(7,1),
	A3(7,2),
	A4(7,3),
	A5(7,4),
	A6(7,5),
	A7(7,6),
	A8(7,7),
	B1(6,0),
	B2(6,1),
	B3(6,2),
	B4(6,3),
	B5(6,4),
	B6(6,5),
	B7(6,6),
	B8(6,7),
	C1(5,0),
	C2(5,1),
	C3(5,2),
	C4(5,3),
	C5(5,4),
	C6(5,5),
	C7(5,6),
	C8(5,7),
	D1(4,0),
	D2(4,1),
	D3(4,2),
	D4(4,3),
	D5(4,4),
	D6(4,5),
	D7(4,6),
	D8(4,7),
	E1(3,0),
	E2(3,1),
	E3(3,2),
	E4(3,3),
	E5(3,4),
	E6(3,5),
	E7(3,6),
	E8(3,7),
	F1(2,0),
	F2(2,1),
	F3(2,2),
	F4(2,3),
	F5(2,4),
	F6(2,5),
	F7(2,6),
	F8(2,7),
	G1(1,0),
	G2(1,1),
	G3(1,2),
	G4(1,3),
	G5(1,4),
	G6(1,5),
	G7(1,6),
	G8(1,7),
	H1(0,0),
	H2(0,1),
	H3(0,2),
	H4(0,3),
	H5(0,4),
	H6(0,5),
	H7(0,6),
	H8(0,7);

	protected final int x;
	protected final int y;

	private Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

}