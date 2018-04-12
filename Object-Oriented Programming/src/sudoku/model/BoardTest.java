package sudoku.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	private Board board4;
	private Board board9;
	
	@Before
    public void setUp() {
        board4 = new Board(4);
        board9 = new Board(9);
    }
	
	@Test
	public void sizeTest() {		
		assertEquals(4, board4.size());
		assertEquals(9, board9.size());
	}
	
	@Test
	public void sqrtTest() {		
		assertEquals(2, board4.sqrt());
		assertEquals(3, board9.sqrt());
	}
	
	@Test
	public void valueOfTest() {		
		assertEquals(0, board4.valueOf(3,3));
		assertEquals(0, board9.valueOf(8,8));
	}
	
	@Test
	public void insertTest() {
		assertTrue(board4.insert(3,3,3));
		assertFalse(board4.insert(3,3,5));
		assertFalse(board4.insert(4,3,4));
		
		assertTrue(board9.insert(3,3,3));
		assertFalse(board9.insert(3,3,10));
		assertFalse(board9.insert(10,3,4));
	}
	
	@Test
	public void deleteTest() {
		board4.insert(2,3,2);
		board9.insert(2,3,2);
		
		assertEquals(2, board4.valueOf(2,3));
		assertEquals(2, board9.valueOf(2,3));
		
		board4.delete(2,3);
		board9.delete(2,3);
		
		assertEquals(0, board4.valueOf(2,3));
		assertEquals(0, board9.valueOf(2,3));
	}
	
	@Test
	public void isSolvedTest() {
		Board board = new Board(4);
		assertFalse(board.isSolved());
		board.insert(0, 0, 1);
		assertFalse(board.isSolved());
		board.insert(0, 1, 2);
		assertFalse(board.isSolved());
		board.insert(0, 2, 3);
		assertFalse(board.isSolved());
		board.insert(0, 3, 4);
		assertFalse(board.isSolved());
		board.insert(1, 0, 3);
		assertFalse(board.isSolved());
		board.insert(1, 1, 4);
		assertFalse(board.isSolved());
		board.insert(1, 2, 1);
		assertFalse(board.isSolved());
		board.insert(1, 3, 2);
		assertFalse(board.isSolved());
		board.insert(2, 0, 2);
		assertFalse(board.isSolved());
		board.insert(2, 1, 3);
		assertFalse(board.isSolved());
		board.insert(2, 2, 4);
		assertFalse(board.isSolved());
		board.insert(2, 3, 1);
		assertFalse(board.isSolved());
		board.insert(3, 0, 4);
		assertFalse(board.isSolved());
		board.insert(3, 1, 1);
		assertFalse(board.isSolved());
		board.insert(3, 2, 2);
		assertFalse(board.isSolved());
		board.insert(3, 3, 3);
		assertTrue(board.isSolved());
	}

}
