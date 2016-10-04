package tests;
import experiment.*;
import static org.junit.Assert.*;

import java.util.HashSet;
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
		
		board = new IntBoard(grid,visited);
		
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
		
		
		//Set<BoardCell> testList = new HashSet<BoardCell>();
		Set <BoardCell> testList = board.getAdjList(cell);
		
		BoardCell testCell = board.getCell(1, 2);
		
		System.out.println(testList);
		
		System.out.println(testList.contains(board.getCell(1, 2)));
		System.out.println(testList.contains(testCell));
		System.out.println(testList.containsAll(board.getAdjList(cell))); 
		//Dont understand how it doesn't contain the collection that initializes this very object

		//testList contains correct values, getCell returns correct value,
		//testList contains correct amount of objects (passes the assertEquals test)
		//HOWEVER *using the testList.contains method returns false*;
		//most likely similar errors in other tests; once these errors are resolved, will most likely
		//be just fixing the findingAllTargets method (testCalcTarget)
		
		//*****you can right-click testMiddleGridAdj in the JUnit tab to run just this test******
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
		Set<BoardCell> targets = board.getTargets();
		
		
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 0)));
		
		assertEquals(6, targets.size());
		
		BoardCell startcell2 = board.getCell(1, 1);
		
		board.calcTargets(startcell2, 1);
		targets = board.getTargets();
		
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(1, 2)));


		assertEquals(4, targets.size());
	}
	
	


}
