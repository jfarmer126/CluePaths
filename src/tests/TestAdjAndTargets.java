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
