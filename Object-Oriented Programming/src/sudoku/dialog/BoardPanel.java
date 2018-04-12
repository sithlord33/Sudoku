package sudoku.dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import sudoku.model.Board;

/**
 * A special panel class to display a Sudoku board modeled by the
 * {@link sudoku.model.Board} class. You need to write code for
 * the paint() method.
 *
 * @see sudoku.model.Board
 * @author Yoonsik Cheon, Eduardo Jiménez Todd
 */
@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
    
	public interface ClickListener {
		
		/** Callback to notify clicking of a square. 
		 * 
		 * @param x 0-based column index of the clicked square
		 * @param y 0-based row index of the clicked square
		 */
		void clicked(int x, int y);
	}
	
	/** Active square color */
	private static final Color sqColor = new Color(66, 134, 244);

	/** Current active x and y coordinates */
	private int x = -1, y = -1;

    /** Board to be displayed. */
    private Board board;

    /** Width and height of a square in pixels. */
    private int squareSize;

    /** Create a new board panel to display the given board. */
    public BoardPanel(Board board, ClickListener listener) {
        this.board = board;
        
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	int xy = locateSquare(e.getX(), e.getY());
            	if (xy >= 0) {
            		listener.clicked(xy / 100, xy % 100);
            	}
            }
        });
    }
    
    public boolean validCell(){
    	if (x < 0 || y < 0 || x >= board.size() || y >= board.size()) return false;
    	return true;
    }

    /** Set the board to be displayed. */
    public void setBoard(Board board) {
    	this.board = board;
    	paint(this.getGraphics());
    }
    
    /**
     * Given a screen coordinate, return the indexes of the corresponding square
     * or -1 if there is no square.
     * The indexes are encoded and returned as x*100 + y, 
     * where x and y are 0-based column/row indexes.
     */
    private int locateSquare(int x, int y) {
    	if (x < 0 || x > board.size() * squareSize || y < 0 || y > board.size() * squareSize)
    		return -1;
    	int xx = x / squareSize;
    	int yy = y / squareSize;
    	return xx * 100 + yy;
    }

    /** Draw the associated board. */
    @Override
    public void paint(Graphics g) {

        // determine the square size
        Dimension dim = getSize();
        int boardLength = Math.min(dim.width, dim.height)-1;
        squareSize = boardLength / board.size();

        // paint background
        g.setColor(Color.white);
        g.fillRect(0, 0, boardLength, boardLength);
        
        //draw gray lines
        g.setColor(Color.gray);
        for (int i = squareSize; i < boardLength; i += squareSize){
        	if (i / squareSize % board.sqrt() == 0)
        		continue;
        	g.drawLine(i, 0, i, boardLength);
        	g.drawLine(0, i, boardLength, i);
        }
        
        // paint active square
        g.setColor(sqColor);
        g.fillRect(x * squareSize + 2, y * squareSize + 2, squareSize - 3, squareSize - 3);
        
        // draw black lines
        g.setColor(Color.black);
        for(int i = squareSize * board.sqrt(); i < boardLength; i += squareSize * board.sqrt()){
        	g.drawLine(i, 0, i, boardLength);
        	g.drawLine(0, i, boardLength, i);
        }
        g.drawRect(0, 0, boardLength, boardLength);
        
        // write numbers 
        for (int i = 0; i < board.size(); i++){
        	for (int j = 0; j < board.size(); j++){
        		int v = board.valueOf(i, j);
        		if (v != 0) 
        			g.drawString(String.valueOf(v), i * squareSize + squareSize / 2 - 3, j * squareSize + squareSize / 2 + 5);
        	}
        }
    }
    
    /** Set active square coordinates */
    public void setActive(int x, int y){
    	this.x = x;
    	this.y = y;
    	paint(this.getGraphics());
    }
    
    /** Insert number in board*/
    public boolean writeNumber(int n){
    	if(board.insert(x, y, n) || n == 0 || board.valueOf(x, y) == n) {
    		if (n == 0) 
    			board.delete(x, y);
    		paint(this.getGraphics());
    		return true;
    	}
    	return false;
    }
}
