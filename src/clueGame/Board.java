package clueGame;

import java.util.*;

public class Board {
	int numRows;
	int numColumns;
	final static int MAX_BOARD_SIZE=50;
	public Map<BoardCell, Set<BoardCell>> adjMatrix;
	public Set<BoardCell> targets;
	public String boardConfigFile;
	public String roomConfigFile;
	
	private BoardCell[][] grid;
	private Map<Character, String> rooms;
	
	// variable used for singleton pattern
	private static Board theInstance = new Board();
	// ctor is private to ensure only one can be created
	private Board() {
		
	}
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
		
	}
	
	public void initialize(){
		
	}
	
	public void loadRoomConfig(){
		
	}
	
	public void loadBoardConfig(){
		
	}
	
	public void calcAdjacencies(){
		
	}
	
	public void calcTargets(BoardCell cell, int PathLength){
		
	}
	public void setConfigFiles(String string, String string2) {
		// TODO Auto-generated method stub
		
	}
	public Map<Character, String> getLegend() {
		// TODO Auto-generated method stub
		return null;
	}
	public int getNumRows() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getNumColumns() {
		// TODO Auto-generated method stub
		return 0;
	}
	public BoardCell getCellAt(int i, int j) {
		// TODO Auto-generated method stub
		BoardCell output = new BoardCell(0,0);
		return output;
	}
	
}
