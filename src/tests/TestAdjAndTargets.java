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
		Set<BoardCell> testList = board.getAdjList(9, 0);
		assertTrue(testList.contains(board.getCellAt(8, 0)));
		assertTrue(testList.contains(board.getCellAt(10, 0)));
		assertTrue(testList.contains(board.getCellAt(9, 1)));
		
		//test right edge
		testList = board.getAdjList(10, 22);
		assertTrue(testList.contains(board.getCellAt(9, 22)));
		assertTrue(testList.contains(board.getCellAt(11, 22)));
		assertTrue(testList.contains(board.getCellAt(10, 21)));
		
		//test bottom edge
		testList = board.getAdjList(21, 15);
		assertTrue(testList.contains(board.getCellAt(21, 14)));
		assertTrue(testList.contains(board.getCellAt(21, 16)));
		assertTrue(testList.contains(board.getCellAt(20, 15)));
		
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
		assertTrue(testList.contains(board.getCellAt(14, 13)));
		
		//Test DOWN
		testList = board.getAdjList(8, 15);
		assertEquals(1,testList.size());
		assertTrue(testList.contains(board.getCellAt(9, 15)));
	}
}
