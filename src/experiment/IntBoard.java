package experiment;

import java.util.*;


public class IntBoard {
	private BoardCell[][] grid;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	Map<BoardCell, Set<BoardCell>> adjMtx;
	
	public IntBoard(BoardCell[][] grid, Set<BoardCell> visited) {
		super();
		this.grid = grid;
		this.visited = visited;
	}

	void calcAdjacencies(){
		
		
	}
	
	void calcTargets(BoardCell startCell, int pathLength){
		
		
	}
	
	public Set<BoardCell> getTargets(){
		
		return null;
	}
	
	public Set<BoardCell> getAdjList(BoardCell cell){
		
		return null;
	}
	
	public BoardCell getCell(int row, int col){
		return grid[row][col];
		
	}
}
