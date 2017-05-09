import java.awt.Color;

import javax.swing.JFrame;

public class ConnectFourBoard extends JFrame {

	
	public ConnectFourBoard() {
		setSize(700,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(Color.WHITE);
		ConnectFourGame game = new ConnectFourGame();
		setContentPane(game);
	}
	
	
	public static void main(String[] args) {
		new ConnectFourBoard();
	}
}
