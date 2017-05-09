import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ConnectFourGame extends JPanel {
	
	public int[] rowHeight;
	public RoundButton[][] slots;
	public int NUM_ROWS = 6;
	public int NUM_COLUMNS = 7;
	public Color currentPlayer;

	public ConnectFourGame() {
		startGame();
		makeButtons();
		setLayout(null);
		setVisible(true);
		setSize(700,500);
	}
	
	private void startGame() {
		slots = new RoundButton[NUM_ROWS][NUM_COLUMNS];
		rowHeight = new int[NUM_COLUMNS];
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
	       		 int xpos = x;
	       		 int ypos = y;
	       		 current.addActionListener(new ActionListener() {
	       			 public void actionPerformed(ActionEvent e) {
	       				 	if(canDropPiece(ypos)) {
	       				 		checkWin();
	       				 		switchPlayers();
	       				 	}
	       				 }
	       		 });
	       	 	}		
		 }
	}
	
	private void checkWin() {
		
	}
	
	private void switchPlayers() {
		currentPlayer = (currentPlayer==Color.BLUE) ? Color.RED : Color.BLUE;
	}
	
	private boolean canDropPiece(int columnPos) {
		int rowPos = NUM_ROWS - rowHeight[columnPos] - 1;
		if(rowPos>=0) {
			rowHeight[columnPos]++;
			slots[rowPos][columnPos].setBackground(currentPlayer);
			return true;
		} else {
			return false;
		}
	}
	
}
