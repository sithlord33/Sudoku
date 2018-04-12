package sudoku.model;

/** An abstraction of Sudoku puzzle.
 *  @author Yoonsik Cheon, Eduardo Jiménez Todd
 */
public class Board {

    /** Size of this board (number of columns/rows). */
    private final int SIZE, SQRT;
	private int[][] values;

    /** Create a new board of the given size. */
    public Board(int size) {
        this.SIZE = size;
		this.SQRT = (int) Math.sqrt(size);
		this.values = new int[size][size];
    }

    public int size(){
    	return this.SIZE;    	
    }
    
    public int sqrt(){
    	return this.SQRT;
    }
    
    public int valueOf(int x, int y){
    	return this.values[x][y];
    }

    /**
     * Inserts value at given coordinate
     * @param x x coordinate
     * @param y y coordinate
     * @param v value
     */
	public boolean insert(int x, int y, int v){
		if(!valid(x, y, v))
			return false;
		values[x][y] = v;
		return true;
	}
	
	/**
     * Inserts value at given coordinate
     * @param x x coordinate
     * @param y y coordinate
     * @param v value
     */
	public void delete(int x, int y){
		values[x][y] = 0;
	}

	/**
	 * Checks if insertion is valid
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param v Value
	 * @return insertion validity
	 */
	private boolean valid(int x, int y, int v){
		//if out of bounds
		if(x < 0 || x >= SIZE || y < 0 || y >= SIZE || v < 1 || v > SIZE)
			return false;
		//if repeated in column or row
		for (int i = 0; i < SIZE; i++)
			if(v == values[x][i] || v == values[i][y])
				return false;
		//if repeated in box
		int a = x / SQRT, b = y / SQRT;
		for (int i = a * SQRT; i < (a + 1) * SQRT; i++)
			for(int j = b * SQRT; j < (b + 1) * SQRT; j++)
				if(v == values[i][j])
					return false;
		return true;
	}

	/** Checks if sudoku is solved */
	public boolean isSolved(){
		for(int i = 0; i < SIZE; i++)
			for(int j = 0;j < SIZE; j++)
				if (values[i][j] == 0)
					return false;
		return true;
	}
}
