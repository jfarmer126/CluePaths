package clueGame;
	
public class BoardCell {


	public int row;
	public int col;
	char initial;
	
	
	public BoardCell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	
	@Override
	public String toString() {
		return "[row=" + row + ", col=" + col + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;   
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardCell other = (BoardCell) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
	
	public boolean isWalkway(){
		return true;
	}
	
	public boolean isRoom(){
		return true;
	}
	
	public boolean isDoorway(){
		return true;
	}


	public Object getDoorDirection() {
		// TODO Auto-generated method stub
		return null;
	}


	public char getInitial() {
		// TODO Auto-generated method stub
		return 'a';
	}


	
}
