package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class TestAdjAndTargets {
	private static Board board;
	
	@BeforeClass
	public static void setup(){
		board = Board.getInstance();
		board.setConfigFiles("cluelayout.csv", "legend.txt");
		board.initialize();
	}
	
	//Locations with only walkways as adjacent locations
	@Test
	public void testAdjacencyWalkways(){
		//with only one walkway adjacency 
		Set<BoardCell> testList = board.getAdjList(21, 5);
		assertTrue(testList.contains(board.getCellAt(20, 5)));
		
		//with three walkway pieces
		testList = board.getAdjList(5, 0);
		assertTrue(testList.contains(board.getCellAt(4,0)));
		assertTrue(testList.contains(board.getCellAt(6,0)));
		assertTrue(testList.contains(board.getCellAt(5,1)));
		
		
	}
	
	//Locations that are at each edge of the board
	@Test
	public void testEdgePieces(){
		
		//test left edge
		Set<BoardCell> testList = board.getAdjList(5, 0);
		assertTrue(testList.contains(board.getCellAt(4, 0)));
		assertTrue(testList.contains(board.getCellAt(6, 0)));
		assertTrue(testList.contains(board.getCellAt(5, 1)));
		
		//test right edge
		testList = board.getAdjList(10, 22);
		assertTrue(testList.contains(board.getCellAt(9, 22)));
		assertTrue(testList.contains(board.getCellAt(11, 22)));
		assertTrue(testList.contains(board.getCellAt(10, 21)));
		
		//test bottom edge
		testList = board.getAdjList(21, 13);
		assertTrue(testList.contains(board.getCellAt(20, 13)));
		
		//test top edge
		testList = board.getAdjList(0, 12);
		assertTrue(testList.contains(board.getCellAt(0, 13)));
		assertTrue(testList.contains(board.getCellAt(0, 11)));
		assertTrue(testList.contains(board.getCellAt(1, 12)));
	}
	
	//test locations that are beside a room cell that's not a doorway
	@Test
	public void testAdjacencyRoom(){
		Set<BoardCell> testList = board.getAdjList(5, 19);
		assertTrue(testList.contains(board.getCellAt(4, 19)));
		assertTrue(testList.contains(board.getCellAt(5, 18)));
		
		testList = board.getAdjList(18, 13);
		assertTrue(testList.contains(board.getCellAt(17, 13)));
		assertTrue(testList.contains(board.getCellAt(19, 13)));
	}
	
	
	//test locations that are adjacent to a doorway with the direction
	@Test
	public void testAdjacencyDoorways(){
		//Test door direction RIGHT
		Set<BoardCell> testList = board.getAdjList(3, 4);
		assertTrue(testList.contains(board.getCellAt(3, 3)));
		assertTrue(testList.contains(board.getCellAt(2, 4)));
		assertTrue(testList.contains(board.getCellAt(3, 5)));
		assertTrue(testList.contains(board.getCellAt(4, 4)));
		
		//Test door direction LEFT
		testList = board.getAdjList(13, 13);
		assertTrue(testList.contains(board.getCellAt(13, 14)));
		assertTrue(testList.contains(board.getCellAt(12, 13)));
		assertTrue(testList.contains(board.getCellAt(14, 13)));
		assertTrue(testList.contains(board.getCellAt(13, 12)));
		
		//Test door direction UP
		testList = board.getAdjList(13, 2);
		assertTrue(testList.contains(board.getCellAt(14, 2)));
		assertTrue(testList.contains(board.getCellAt(12, 2)));
		assertTrue(testList.contains(board.getCellAt(13, 1)));
		assertTrue(testList.contains(board.getCellAt(13, 3)));
		
		//Test door direction DOWN
		testList = board.getAdjList(9, 15);
		assertTrue(testList.contains(board.getCellAt(8, 15)));
		assertTrue(testList.contains(board.getCellAt(10, 15)));
		assertTrue(testList.contains(board.getCellAt(9, 14)));
		assertTrue(testList.contains(board.getCellAt(9, 16)));
	}
	
	@Test
	public void testDoorways(){
		//Test UP
		Set<BoardCell> testList = board.getAdjList(14, 2);
		
		assertEquals(1,testList.size());
		assertTrue(testList.contains(board.getCellAt(13, 2)));
		
		//Test RIGHT
		testList = board.getAdjList(10, 3);
		assertEquals(1,testList.size());
		assertTrue(testList.contains(board.getCellAt(10, 4)));
		
		
		//Test LEFT
		testList = board.getAdjList(13, 14);
		assertEquals(1,testList.size());
		assertTrue(testList.contains(board.getCellAt(13, 13)));
		
		//Test DOWN
		testList = board.getAdjList(8, 15);
		assertEquals(1,testList.size());
		assertTrue(testList.contains(board.getCellAt(9, 15)));
	}
	
	
	
	
	//Target test for one pathlength away
	@Test
	public void testOnePathLength() {
		board.calcTargets(5, 5, 1);
		Set<BoardCell> targets= board.getTargets();
		
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCellAt(5,6)));
		assertTrue(targets.contains(board.getCellAt(5,4)));	
		assertTrue(targets.contains(board.getCellAt(4,5)));
		assertTrue(targets.contains(board.getCellAt(6,5)));
		
		board.calcTargets(4, 0, 1);
		targets= board.getTargets();
		
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(4, 1)));
		assertTrue(targets.contains(board.getCellAt(5, 0)));		
	}
	
	
	//Target test for two pathlength away
	@Test
	public void testTwoPathLength() {
		board.calcTargets(4, 8, 2);
		Set<BoardCell> targets= board.getTargets();
		
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCellAt(6, 8)));
		assertTrue(targets.contains(board.getCellAt(4, 10)));
		assertTrue(targets.contains(board.getCellAt(3, 8)));
		assertTrue(targets.contains(board.getCellAt(5, 9)));
		assertTrue(targets.contains(board.getCellAt(5, 7)));
		assertTrue(targets.contains(board.getCellAt(4, 6)));
		
		board.calcTargets(13, 11, 2);
		targets= board.getTargets();
		
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCellAt(12, 12)));
		assertTrue(targets.contains(board.getCellAt(13, 13)));	
		assertTrue(targets.contains(board.getCellAt(12, 10)));
		assertTrue(targets.contains(board.getCellAt(13, 9)));
	}
	
	
	//Target test for four pathlength away
	@Test
	public void testFourPathLength() {
		
		board.calcTargets(5,0,4);
		Set<BoardCell> targets= board.getTargets();
		
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCellAt(4,3)));
		assertTrue(targets.contains(board.getCellAt(5,4)));
		assertTrue(targets.contains(board.getCellAt(5,2)));
		assertTrue(targets.contains(board.getCellAt(4,1)));
		assertTrue(targets.contains(board.getCellAt(6,3)));
		assertTrue(targets.contains(board.getCellAt(6,1)));
		
		board.calcTargets(12, 13, 4);
		targets= board.getTargets();
		
		assertEquals(13, targets.size());
		assertTrue(targets.contains(board.getCellAt(13,14)));
		assertTrue(targets.contains(board.getCellAt(12,11)));	
		assertTrue(targets.contains(board.getCellAt(14,13)));	
		assertTrue(targets.contains(board.getCellAt(13,12)));
		assertTrue(targets.contains(board.getCellAt(16,13)));	
		assertTrue(targets.contains(board.getCellAt(13,10)));	
		assertTrue(targets.contains(board.getCellAt(12,9)));	
		assertTrue(targets.contains(board.getCellAt(8,13)));	
		assertTrue(targets.contains(board.getCellAt(9,14)));	
		assertTrue(targets.contains(board.getCellAt(10,15)));	
		assertTrue(targets.contains(board.getCellAt(11,16)));	
		assertTrue(targets.contains(board.getCellAt(11,14)));		
		assertTrue(targets.contains(board.getCellAt(10,13)));	
		
	}	
	
	//Target test for six pathlength away
	@Test
	public void testSixPathLength() {
		board.calcTargets(5, 0, 6);
		Set<BoardCell> targets= board.getTargets();
	
		assertEquals(12, targets.size());
		assertTrue(targets.contains(board.getCellAt(3,4)));
		assertTrue(targets.contains(board.getCellAt(4,5)));	
		assertTrue(targets.contains(board.getCellAt(5,6)));	
		assertTrue(targets.contains(board.getCellAt(5,4)));	
		assertTrue(targets.contains(board.getCellAt(4,3)));	
		assertTrue(targets.contains(board.getCellAt(6,5)));	
		assertTrue(targets.contains(board.getCellAt(6,3)));
		assertTrue(targets.contains(board.getCellAt(5,2)));
		assertTrue(targets.contains(board.getCellAt(4,1)));
		assertTrue(targets.contains(board.getCellAt(7,4)));
		assertTrue(targets.contains(board.getCellAt(6,1)));
		assertTrue(targets.contains(board.getCellAt(8,3)));
		
	}	
	
	
	
		//Target Test for Entering Room
		@Test 
		public void testEnteringRoom()
		{
			board.calcTargets(13,2,2);
			Set<BoardCell> targets= board.getTargets();
			
			assertEquals(5, targets.size());
			assertTrue(targets.contains(board.getCellAt(12,3)));
			assertTrue(targets.contains(board.getCellAt(13,4)));
			assertTrue(targets.contains(board.getCellAt(12,1)));
			assertTrue(targets.contains(board.getCellAt(14,2))); //door
			assertTrue(targets.contains(board.getCellAt(13,0)));
		}
		
		
		//Target Test for Exiting Room
		@Test
		public void testExitingRoom()
		{
			board.calcTargets(14, 2, 1);
			Set<BoardCell> targets= board.getTargets();
			
			assertEquals(1, targets.size());
			assertTrue(targets.contains(board.getCellAt(13, 2)));
			
			
			board.calcTargets(14, 2, 2);
			targets= board.getTargets();
			
			assertEquals(3, targets.size());
			assertTrue(targets.contains(board.getCellAt(12,2)));
			assertTrue(targets.contains(board.getCellAt(13,3)));
			assertTrue(targets.contains(board.getCellAt(13,1)));
		}

}
