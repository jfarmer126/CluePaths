package experiment;

import java.util.*;


public class IntBoard {
	private BoardCell[][] grid;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	Map<BoardCell, Set<BoardCell>> adjMtx;
	
	public IntBoard(BoardCell[][] grid, Set<BoardCell> visited) 
	{
		super();
		this.grid = grid;
		this.visited = visited;
	}

	public void calcAdjacencies()
	{
		for(BoardCell[] row : grid)
		{
			for(BoardCell column: row)
			{
				adjMtx.put(column, getAdjList(column));
			}
		}
	}
	
	public void calcTargets(BoardCell startcell, int pathlength)
	{
		visited.clear();
		visited.add(startcell);
		
		findAllTargets(startcell, pathlength);
		
	}
	
	private void findAllTargets(BoardCell startcell, int numsteps)
	{
		Set<BoardCell> adj = getAdjList(startcell);
		for(BoardCell cell : adj)
		{
			if(visited.contains(cell))
			{
				break;
			}
			
			else
			{
				visited.add(cell);
				if(numsteps == 1)
				{
					targets.add(cell);
				}
				
				else
				{
					findAllTargets(cell, numsteps - 1);
				}
				
			}
			
			visited.remove(cell);
		}
	}
	
	public Set<BoardCell> getTargets(){
		
		return null;
	}
	
	public Set<BoardCell> getAdjList(BoardCell cell){
		
		return null;
	}
	
	public BoardCell getCell(int row, int col)
	{
		return grid[row][col];
		
	}
}
