package experiment;
	
public class BoardCell {


	int row;
	int col;
	
	
	public BoardCell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	
	@Override
	public String toString() {
		return "[row=" + row + ", col=" + col + "]";
	}


	
}
