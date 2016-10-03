package experiment;
	
public class BoardCell {


	int row;
	int col;
	
	
	public BoardCell(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
	
	
	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", col=" + col + "]";
	}


	
}
