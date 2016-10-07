package tests;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.*;

public class TestClueBoard {

	private static Board board;
	public static final int LEGEND_SIZE = 11;
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLUMNS = 23;
	
	@BeforeClass
	public static void setUp() {
		board = Board.getInstance();
		board.setConfigFiles("cluelayout.csv", "legend.txt");
		board.initialize();
	}

	
	
	@Test
	public void testRooms() {
		Map<Character, String> legend = board.getLegend();
		// Ensure we read the correct number of rooms
		assertEquals(LEGEND_SIZE, legend.size());
		// To ensure data is correctly loaded, test retrieving a few rooms 
		// from the hash, including the first and last in the file and a few others
		assertEquals("Closet", legend.get('X'));
		assertEquals("Kitchen", legend.get('K'));
		assertEquals("Ballroom", legend.get('B'));
		assertEquals("Billiard Room", legend.get('R'));
		assertEquals("Library", legend.get('L'));
		assertEquals("Research Room", legend.get('E'));
		assertEquals("Green Room", legend.get('G'));
		assertEquals("Bedroom", legend.get('D'));
		assertEquals("Theater", legend.get('T'));
		assertEquals("Workout Room", legend.get('O'));
		assertEquals("Walkway", legend.get('W'));
	}
	
	@Test
	public void testBoardDimensions() {
		// Ensure we have the proper number of rows and columns
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());		
	}
	
	@Test
	public void FourDoorDirections() {
		//test right
		BoardCell room = board.getCellAt(3, 3);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.RIGHT, room.getDoorDirection());
		//test down
		room = board.getCellAt(3, 8);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.DOWN, room.getDoorDirection());
		//test left
		room = board.getCellAt(3, 20);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.LEFT, room.getDoorDirection());
		//test up
		room = board.getCellAt(16, 20);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.UP, room.getDoorDirection());
		// Test that room pieces that aren't doors know it
		room = board.getCellAt(10, 10);
		assertFalse(room.isDoorway());	
		// Test that walkways are not doors
		BoardCell cell = board.getCellAt(10, 19);
		assertFalse(cell.isDoorway());		

	}
	
	@Test
	public void testNumberOfDoorways() 
	{
		int numDoors = 0;
		for (int row=0; row<board.getNumRows(); row++)
			for (int col=0; col<board.getNumColumns(); col++) {
				BoardCell cell = board.getCellAt(row, col);
				if (cell.isDoorway())
					numDoors++;
			}
		Assert.assertEquals(9, numDoors);
	}
	
	@Test
	public void testRoomInitials() {
		//Test the Kitchen
		assertEquals('K', board.getCellAt(1, 2).getInitial());
		//Test the Ballroom
		assertEquals('B', board.getCellAt(17, 16).getInitial());
		//Test the Billiard Room
		assertEquals('R', board.getCellAt(8, 1).getInitial());
		// Test Library
		assertEquals('L', board.getCellAt(18, 1).getInitial());
		//Test the Research Room
		assertEquals('E', board.getCellAt(5, 15).getInitial());
		//Test the Green Room
		assertEquals('G', board.getCellAt(4, 20).getInitial());
		//Test the Bedroom
		assertEquals('D', board.getCellAt(20, 20).getInitial());
		//Test the Theater
		assertEquals('T', board.getCellAt(20, 10).getInitial());
		//Test the workout room
		assertEquals('O', board.getCellAt(1, 7).getInitial());
		// Test a walkway
		assertEquals('W', board.getCellAt(13, 4).getInitial());
		// Test the closet
		assertEquals('X', board.getCellAt(10,10).getInitial());
	}

	
	

	
}
