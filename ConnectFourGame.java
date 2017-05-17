import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		asciiSlots = new char[NUM_ROWS+8][NUM_COLUMNS+8];
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
	
	private final int[][] direction = {{1,0}, {-1,0}, {0,1}, {0,-1}, {1,1}, {1,-1}, {-1,-1}, {-1,1}};
	
	private boolean gameIsOver(int xpos, int ypos) {
		for(int x=0; x<8; x++) {
			int xDir = direction[x][0];
			int yDir = direction[x][1];
			int pieceCnt = 0;
			for(int y=0; y<4; y++) {
				char piece = asciiSlots[xpos+xDir][ypos+yDir];
				if(piece==asciiPlayer) {
					pieceCnt++;
				}
				if(pieceCnt==4) {
					return true;
				}
			}
		}
		return false;
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
			asciiSlots[rowPos+4][columnPos+4] = piece;
			return true;
		} else {
			return false;
		}
	}
	
}
