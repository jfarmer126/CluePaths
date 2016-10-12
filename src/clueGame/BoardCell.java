package clueGame;

public class BoardCell {


	public int row;
	public int col;
	public char initial;
	public char dchar;
	public DoorDirection dir;


	public BoardCell(int row, int col, char initial, char door) {
		this.row = row;
		this.col = col;
		this.initial = initial;
		this.dchar = door;
		setDoorDirection();
	}


	@Override
	public String toString() {
		return "[row=" + row + ", col=" + col + ", char=" + initial + "]";
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

		if(initial == 'w')
		{
			return true;
		}

		return false;
	}

	public boolean isRoom(){

		if(initial != 'w')
		{
			return true;
		}

		return false;
	}

	public boolean isDoorway(){

		if(dchar != 'X')
		{
			if(dchar != 'N')
			{
				return true;
			}
		}
		return false;
	}

	public void setDoorDirection()
	{
		switch(dchar)
		{
		case 'U':
			dir = DoorDirection.UP;
			break;
		case 'D':
			dir = DoorDirection.DOWN;
			break;
		case 'R':
			dir = DoorDirection.RIGHT;
			break;
		case 'L':
			dir = DoorDirection.LEFT;
			break;
		default:
			dir = DoorDirection.NONE;
			break;
				
		}
	}

	public DoorDirection getDoorDirection() {
		return dir;
	}


	public char getInitial() {
		return initial;
	}



}
