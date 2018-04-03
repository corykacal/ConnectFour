import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ConnectFourGame extends JPanel {
	
	public int[] rowHeight;
	public RoundButton[][] slots;
	public char[][] asciiSlots;
	public int NUM_ROWS = 6;
	public int NUM_COLUMNS = 7;
	public Color currentPlayer;
	private char asciiPlayer;

	public ConnectFourGame() {
		startGame();
		makeButtons();
		setLayout(null);
		setVisible(true);
		setSize(700,500);
	}
	
	private void startGame() {
		slots = new RoundButton[NUM_ROWS][NUM_COLUMNS];
		asciiSlots = new char[NUM_ROWS][NUM_COLUMNS];
		rowHeight = new int[NUM_COLUMNS];
		asciiPlayer = 'b';
		currentPlayer = Color.BLUE;
	}

	private void makeButtons() {
		 for(int x=0; x<NUM_ROWS; x++) {
	       	 for(int y=0; y<NUM_COLUMNS; y++) {
	       		 slots[x][y] = new RoundButton("");
	       		 slots[x][y].setSize(49, 49);
	       		 slots[x][y].setLocation(y*54+10, 100 + x*54);
	       		 add(slots[x][y]);
	       		 JButton current = slots[x][y];
	       		 asciiSlots[x][y] = '.';
	       		 int xpos = x;
	       		 int ypos = y;
	       		 current.addActionListener(new ActionListener() {
	       			 public void actionPerformed(ActionEvent e) {
	       				 	if(dropPiece(ypos)) {
		                        int xpos = rowHeight[ypos];
	       				 		if(gameIsOver(xpos, ypos)) {
	       				 			displayGameOver();
	       				 		}
	       				 		switchPlayers();
	       				 	}
	       				 }
	       		 });
	       	 	}
		 }
	}
	
	private void displayGameOver() {
		System.out.println("game over");
	}
	//                                    left   right   |  up     down  | up-r    down-l   | down-r   up-l
	private final int[][][] direction = {{{1,0}, {-1,0}}, {{0,1}, {0,-1}}, {{1,1}, {-1,-1}}, {{1,-1}, {-1,1}}};
	
	private boolean gameIsOver(int xpos, int ypos) {
        System.out.println(xpos + " " + ypos);
        for(int set=0; set<4; set++) {
			int pieceCnt = 0;
            for(int x=0; x<2; x++) {
                int xDir = direction[set][x][0];
                int yDir = direction[set][x][1];
                for(int step=0; step<4; step++) {
                    int newy = ypos+yDir*step;
                    int newx = xpos+xDir*step;
                    if(inBounds(newx, newy)) {
                        char piece = asciiSlots[newx][newy];
                        if(piece==asciiPlayer) {
                            pieceCnt++;
                        } else {
                            break;
                        }
                    }
                }
            }
            if(pieceCnt-1>=4) {
                return true;
            }
        }
		return false;
	}

    private boolean inBounds(int x, int y) {
        return x<NUM_ROWS && y<NUM_COLUMNS;
    }
	
	private void switchPlayers() {
		currentPlayer = (currentPlayer==Color.BLUE) ? Color.RED : Color.BLUE;
		asciiPlayer = (asciiPlayer=='b') ? 'r' : 'b';
	}
	
	private boolean dropPiece(int columnPos) {
		int rowPos = NUM_ROWS - rowHeight[columnPos] - 1;
		if(rowPos>=0) {
			rowHeight[columnPos]++;
			slots[rowPos][columnPos].setBackground(currentPlayer);
			char piece = (currentPlayer==Color.BLUE) ? 'b' : 'r';
			asciiSlots[rowPos][columnPos] = piece;
			return true;
		} else {
			return false;
		}
	}
	
}
