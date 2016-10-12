package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Board {
	private int numRows;
	private int numColumns;
	final static int MAX_BOARD_SIZE=50;
	private Set<BoardCell> targets;
	private String boardConfigFile;
	private String roomConfigFile;

	private boolean properRoomConfig = false;
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

	public void initialize(){
		rooms = new HashMap<Character, String>();
		grid = new BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();

		try 
		{
			properRoomConfig = true;
			loadRoomConfig();
		}
		catch (Exception e) {
			System.out.println(e);
		}


		try 
		{
			loadBoardConfig();
		} 
		catch (Exception e) {
			System.out.println(e);
		}


	}

	public void loadRoomConfig() throws FileNotFoundException, BadConfigFormatException
	{
		if(!properRoomConfig)
		{
			throw new BadConfigFormatException("Room already loaded");
		}

		FileReader fr = new FileReader(roomConfigFile);
		Scanner input = new Scanner(fr).useDelimiter(", ");

		while(input.hasNext())
		{
			char c = input.next().charAt(0);
			String name = input.next();

			rooms.put(c,name);

			input.nextLine();
		}

	}

	public void loadBoardConfig() throws FileNotFoundException, BadConfigFormatException
	{
		FileReader fr = new FileReader(boardConfigFile);
		Scanner inputrow = new Scanner(fr);
		Scanner inputcol;

		int row = 0;
		while(inputrow.hasNextLine())
		{

			int column = 0;
			inputcol = new Scanner(inputrow.nextLine()).useDelimiter(",");

			while(inputcol.hasNext())
			{
				String s = inputcol.next();
				char d = 'X';
				if(s.length() > 1)
				{
					d = s.charAt(1);
				}

				BoardCell bc = new BoardCell(row,column, s.charAt(0), d);

				grid[row][column]= bc;
				column++;
			}

			if(numColumns == 0)
			{
				numColumns = column;
			}
			row++;
		}


		numRows = row;
		inputrow.close();
	}


	public Set<BoardCell> getAdjList(int boardRow, int boardCol) {
		BoardCell cell = getCellAt(boardRow, boardCol);

		Set<BoardCell> output = new HashSet<BoardCell> ();

		if(cell.getInitial() != 'W')
		{

			DoorDirection d = cell.getDoorDirection();

			switch (d)
			{

			case NONE:
				break;

			case UP:
				if(grid[cell.row - 1][cell.col].getInitial() == 'W')
				{
					output.add(grid[cell.row - 1][cell.col]);
				}
				break;

			case DOWN:
				if(grid[cell.row + 1][cell.col].getInitial() == 'W')
				{
					output.add(grid[cell.row + 1][cell.col]);
				}
				break;

			case LEFT:
				if(grid[cell.row][cell.col - 1].getInitial() == 'W')
				{
					output.add(grid[cell.row][cell.col - 1]);
				}
				break;

			case RIGHT: 
				if(grid[cell.row][cell.col + 1].getInitial() == 'W')
				{
					output.add(grid[cell.row][cell.col + 1]);
				}
				break;
			}

			return output;


		}

		if(cell.row > 0)
		{
			if(grid[cell.row -1][cell.col].getInitial() == 'W' ||
					grid[cell.row -1][cell.col].dir == DoorDirection.DOWN)
			{
				output.add(grid[cell.row - 1][cell.col]);
			}
		}

		if(grid[cell.row +1][cell.col] != null)
		{

			if(grid[cell.row +1][cell.col].getInitial() == 'W' ||
					grid[cell.row +1][cell.col].dir == DoorDirection.UP)
			{
				output.add(grid[cell.row + 1][cell.col]);
			}
		}

		if(cell.col > 0)
		{
			if(grid[cell.row][cell.col-1].getInitial() == 'W' ||
					grid[cell.row][cell.col-1].dir == DoorDirection.RIGHT)
			{
				output.add(grid[cell.row][cell.col - 1]);
			}
		}

		if(grid[cell.row][cell.col + 1] != null)
		{
			if(grid[cell.row][cell.col + 1].getInitial() == 'W' ||
					grid[cell.row][cell.col + 1].dir == DoorDirection.LEFT)
			{
				output.add(grid[cell.row][cell.col + 1]);
			}
		}

		return output;
	}

	public void calcTargets(int boardRow, int boardCol, int PathLength)
	{	
		targets.clear();
		visited.clear();

		visited.add(getCellAt(boardRow,boardCol));
		findAllTargets(boardRow, boardCol, PathLength);
	}

	private void findAllTargets(int boardRow, int boardCol, int pathLength) {
		Set<BoardCell> adj = getAdjList(boardRow, boardCol);
		
		for(BoardCell cell : adj)
		{
			if(visited.contains(cell))
			{
				continue;
			}

			if(pathLength  == 1)
			{
				targets.add(cell);

			}	
			
			else
			{
				visited.add(cell);
				findAllTargets(cell.row, cell.col, pathLength - 1);
				visited.remove(cell);
			}
		}

	}

	public void setConfigFiles(String inputboard, String inputroom) {
		boardConfigFile = inputboard;
		roomConfigFile = inputroom;
	}

	public Map<Character, String> getLegend()
	{
		return rooms;
	}
	public int getNumRows()
	{
		return numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}
	public BoardCell getCellAt(int i, int j) 
	{
		BoardCell output = grid[i][j];
		return output;
	}

	public Set<BoardCell> getTargets() 
	{
		return targets;
	}



}
