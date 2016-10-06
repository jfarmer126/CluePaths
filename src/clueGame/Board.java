package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Board {
	int numRows;
	int numColumns;
	final static int MAX_BOARD_SIZE=50;
	public Map<BoardCell, Set<BoardCell>> adjMatrix;
	public Set<BoardCell> targets;
	public String boardConfigFile;
	public String roomConfigFile;

	private Set<BoardCell> visited;
	private BoardCell[][] grid;
	private Map<Character, String> rooms;

	// variable used for singleton pattern
	private static Board theInstance = new Board();
	// ctor is private to ensure only one can be created
	private Board() 
	{
		
	}
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;

	}

	public void initialize() throws FileNotFoundException{
		loadRoomConfig();
		loadBoardConfig();
	}

	public void loadRoomConfig() throws FileNotFoundException
	{
		FileReader fr = new FileReader(roomConfigFile);
		Scanner input = new Scanner(fr).useDelimiter(", ");
		
		rooms = new HashMap<Character, String>();
		
		while(input.hasNext())
		{
			char c = input.next().charAt(0);
			String name = input.next();
		
			rooms.put(c,name);
			
			System.out.println(c);
			System.out.println(name);
			
			input.nextLine();
		}
		
	}

	public void loadBoardConfig() throws FileNotFoundException
	{
		FileReader fr = new FileReader(boardConfigFile);
		Scanner input = new Scanner(fr);
		
		int row = 0;
		while(input.hasNextLine())
		{

			StringTokenizer s = new StringTokenizer(input.nextLine());
			
			int column = 0;
			
			while(s.hasMoreTokens())
			{
				
				grid[row][column]= new BoardCell(row,column);
				column++;
			}
			
			
			row++;
		}
	}

	public void calcAdjacencies()
	{

		for(BoardCell[] row : grid)
		{
			for(BoardCell column: row)
			{
				adjMatrix.put(column, getAdjList(column));
			}
		}
	}

	private Set<BoardCell> getAdjList(BoardCell cell) {
		Set<BoardCell> output = new HashSet<BoardCell> ();

		if(cell.row > 0)
		{
			output.add(new BoardCell(cell.row - 1, cell.col));
		}

		if(cell.row < grid.length - 1)
		{
			output.add(new BoardCell(cell.row + 1, cell.col));
		}

		if(cell.col > 0)
		{
			output.add(new BoardCell(cell.row, cell.col - 1));
		}

		if(cell.col < grid[0].length - 1)
		{
			output.add(new BoardCell(cell.row, cell.col + 1));
		}

		return output;
	}
	
	public void calcTargets(BoardCell cell, int PathLength)
	{	
		visited.clear();

		visited.add(cell);

		findAllTargets(cell, PathLength);
	}

	private void findAllTargets(BoardCell startcell, int pathLength) {

		Set<BoardCell> adj = getAdjList(startcell);
		for(BoardCell cell : adj)
		{
			if(visited.contains(cell))
			{
				continue;
			}

			if(pathLength  == 1){

				targets.add(cell);

			}	
			else
			{
				visited.add(cell);
				findAllTargets(cell, pathLength - 1);
				visited.remove(cell);
			}



		}

	}
	public void setConfigFiles(String string, String string2) {

		boardConfigFile = string;
		roomConfigFile = string2;

	}

	public Map<Character, String> getLegend()
	{
		return rooms;
	}
	public int getNumRows()
	{
		// TODO Auto-generated method stub
		return numRows;
	}
	public int getNumColumns() {
		// TODO Auto-generated method stub
		return numColumns;
	}
	public BoardCell getCellAt(int i, int j) {
		// TODO Auto-generated method stub
		BoardCell output = grid[i][j];
		return output;
	}
	
	
	
	
	
	/*public static int main (String args[]) throws FileNotFoundException
	{

		Board board = Board.getInstance();
		board.setConfigFiles("cluelayout.csv", "legend.txt");
		board.loadRoomConfig();
		
		return 0;*/
		
	//}

}
