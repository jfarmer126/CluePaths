package tests;
import experiment.*;
import static org.junit.Assert.*;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;


public class TestIntBoard 
{
	private BoardCell[][] grid;
	Set<BoardCell> visited;
	IntBoard board;
	int ROWS = 4;
	int COLS = 4;
	
	@Before
	public void setUp()
	{
		grid = new BoardCell[ROWS][COLS];
		for(int i = 0; i < ROWS; i++){
			for(int k = 0; k < COLS; k++){
				grid[i][k] = new BoardCell(i,k);
			}
		}
		IntBoard board = new IntBoard(grid,visited);
		
	}

	@Test
	public void testTopLeftCornerAdj() 
	{
		BoardCell cell = board.getCell(0,0);
		
		Set<BoardCell> testList = board.getAdjList(cell);
		
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertTrue(testList.contains(board.getCell(1, 0)));
		
		assertEquals(2, testList.size());
	}
	
	@Test 
	public void testBottomRightCornerAdj()
	{
		BoardCell cell = board.getCell(3, 3);
		
		Set<BoardCell> testList = board.getAdjList(cell);
		
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		
		assertEquals(2, testList.size());
	}
	
	@Test
	public void testRightEdgeAdj()
	{
		BoardCell cell = board.getCell(1, 3);
		
		Set<BoardCell> testList = board.getAdjList(cell);
		
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(0, 3)));
		
		assertEquals(3, testList.size());
	}

	@Test
	public void testLeftEdgeAdj()
	{
		BoardCell cell = board.getCell(1, 0);
		
		Set<BoardCell> testList = board.getAdjList(cell);
		
		assertTrue(testList.contains(board.getCell(0, 0)));
		assertTrue(testList.contains(board.getCell(1, 1)));
		assertTrue(testList.contains(board.getCell(2, 0)));
		
		assertEquals(3, testList.size());
	}
	
	@Test
	public void testSecondColumnMiddleGridAdj()
	{
		BoardCell cell = board.getCell(1, 1);
		
		Set<BoardCell> testList = board.getAdjList(cell);
		
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(1, 0)));
		
		assertEquals(4, testList.size());
	}
	
	@Test
	public void testMiddleGridAdj()
	{
		BoardCell cell = board.getCell(2, 2);
		
		Set<BoardCell> testList = board.getAdjList(cell);
		
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(3, 2)));
		
		assertEquals(4, testList.size());
	}
	
	
	@Test
	public void testCalcTarget()
	{
		BoardCell startcell1 = board.getCell(0, 0);
		
		board.calcTargets(startcell1, 3);
		Set targets = board.getTargets();
		
		assertEquals(6, targets.size());
		
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 0)));
		
		
		
		BoardCell startcell2 = board.getCell(1, 1);
		
		board.calcTargets(startcell2, 1);
		targets = board.getTargets();
		
		assertEquals(4, targets.size());
		
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(1, 2)));

	}
	
	


}
