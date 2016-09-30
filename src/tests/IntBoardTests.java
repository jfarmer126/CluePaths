package tests;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import experiment.*;

public class IntBoardTests {
	private BoardCell[][] grid;
	Set<BoardCell> visited;
	IntBoard board;
	int ROWS = 3;
	int COLS = 3;
	
	@Before
	public void setUp() throws Exception {
		grid = new BoardCell[ROWS][COLS];
		for(int i = 0; i < ROWS; i++){
			for(int k = 0; k < COLS; k++){
				grid[i][k] = new BoardCell(i,k);
			}
		}
		IntBoard board = new IntBoard(grid,visited);
		
	}

	@Test
	public void testTopLeftCorner() {
		BoardCell cell = board.getCell(0,0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
	}
	
	@Test 
	public void testBottomRightCorner(){
		BoardCell cell = board.getCell(2, 2);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertEquals(2, testList.size());
	}

}
