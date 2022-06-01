
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class TicTacToeGUI extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlayerGUI p1, p2;
	private BoardGUI board;
	private int xCord, yCord;
	JFrame frame = new JFrame();
//	JPanel row[] = new JPanel[5];
	JLabel display = new JLabel("TIC TAC TOE", SwingConstants.CENTER), gameStatusText = new JLabel("", SwingConstants.CENTER);
//	JTextArea gameStatusText = new JTextArea();
	Font font = new Font("Bradley Hand", Font.BOLD, 40);
	JButton[] button = new JButton[9];
	ImageIcon icon = new ImageIcon("/Users/nishantmittal/Downloads/tictactoeicon.png");
	ImageIcon imageIcon = new ImageIcon(icon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
	JRadioButton b1 = new JRadioButton("O", true), b2 = new JRadioButton("X");
	ButtonGroup bgroup = new ButtonGroup();
	JButton resetButton = new JButton("Reset"), exitButton = new JButton("Exit");
	int totalMatches = 0, choice;

	
	boolean buttonPressed=false;
	
	public TicTacToeGUI() {
		
		try {
			 UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}

		p1 = playerInput(1);
		p2 = playerInput(2);

		bgroup.add(b1);
		bgroup.add(b2);
		int symbolChosen = JOptionPane.showOptionDialog(frame, new Object[] {new JLabel("Choose symbol for "+ p1.getName()+"\n"), b1, b2, "\nThe other is for "+p2.getName()}, 
									"Choose Symbol..", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, imageIcon, null, null);
		if(symbolChosen == JOptionPane.OK_OPTION) {
			p1.setSymbol(b1.isSelected()?'O':'X');
			p2.setSymbol(!b1.isSelected()?'O':'X');
		}
		else if(symbolChosen == JOptionPane.CANCEL_OPTION)
			System.exit(0);
		
		
//		while(p1.getSymbol() == p2.getSymbol()) {
//			String symbol = JOptionPane.showInputDialog(frame, "Symbol already chosen!! Choose again!", "Aahhhh....", JOptionPane.WARNING_MESSAGE);
//			while(symbol == null || (symbol != null && ("".equals(symbol)))) {
//				if(symbol == null)
//					System.exit(0);
//				symbol = JOptionPane.showInputDialog(frame, "Enter "+ p2.getName() +"'s Symbol", "Symbol cannot be empty", JOptionPane.ERROR_MESSAGE);
//			}
//			p2.setSymbol(symbol.charAt(0));
//		}
		
		Border buttonBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		Border displayBorder = BorderFactory.createCompoundBorder(buttonBorder, BorderFactory.createLineBorder(Color.ORANGE, 3));		
		frame.setTitle("Tic Tac Toe");
		frame.setIconImage(icon.getImage());
		setSize(350, 520);
//		setBackground(new Color(120,20,124));
		setResizable(false);
//		frame.setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		for(int i=0; i<9; i++) {
			button[i] = new JButton();
			button[i].addActionListener(this);
			button[i].setBackground(Color.cyan);
			button[i].setBorder(buttonBorder);
			button[i].setPreferredSize(new Dimension(100, 100));
		}
		
		display.setFont(font);
		display.setBorder(displayBorder);
		display.setPreferredSize(new Dimension(290, 80));
		
//		gameStatusText.setEditable(false);
		gameStatusText.setSize(250, 80);
		gameStatusText.setPreferredSize(new Dimension(400, 60));
		gameStatusText.setFont(new Font("Apple Chancery", Font.BOLD, 20));
		
		resetButton.addActionListener(this);
		exitButton.addActionListener(this);
		
//		for(int i=0; i<5; i++)
//			row[i] = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
//		add(display);
//		row[0].add(display);
//		row[0].setLayout(new GridLayout(2, 1));
//		row[0].add(separator);
//		row[0].add(gameStatusText);
//		add(row[0]);
////		add(separator);
////		row[1].add(gameStatusText);
////		add(row[1]);
//		row[2].setLayout(grid);
//		for(int i=0; i<3; i++)
//			row[2].add(button[i]);
//		add(row[2]);
//		row[3].setLayout(grid);
//		for(int i=3; i<6; i++)
//			row[3].add(button[i]);
//		add(row[3]);
//		row[4].setLayout(grid);
//		for(int i=6; i<9; i++)
//			row[4].add(button[i]);
//		add(row[4]);
		
		add(display);
		add(gameStatusText);
		for(int i=0; i<9; i++) {
			add(button[i]);
		}
		add(resetButton);
		add(exitButton);
		setVisible(true);
		startGame();
	
	}

	
	
	private void cleanBoardGUI() {
		
		gameStatusText.setText("");
		for(int i=0; i<9; i++) 
			button[i].setText("");
		
	}



	private PlayerGUI playerInput(int num) {
		
		String name = JOptionPane.showInputDialog("Enter Player "+ num +" Name");
		while(name == null || (name != null && ("".equals(name)))) {
			if(name == null)
				System.exit(0);
			name = JOptionPane.showInputDialog(frame, "Enter Player "+ num +" Name", "Name cannot be empty", JOptionPane.ERROR_MESSAGE);
		}
		
//		String symbol = JOptionPane.showInputDialog("Enter " + name + "'s Symbol");
//		while(symbol == null || (symbol != null && ("".equals(symbol)))) {
//			if(symbol == null)
//				System.exit(0);
//			symbol = JOptionPane.showInputDialog(frame, "Enter "+ name +"'s Symbol", "Symbol cannot be empty", JOptionPane.ERROR_MESSAGE);
//		}
//		char sym = symbol.charAt(0);
		PlayerGUI p = new PlayerGUI(name);
		return p;
	}
	
	
	private void startGame() {
		
		do {
			cleanBoardGUI();
			board = new BoardGUI(p1.getSymbol(), p2.getSymbol());
			conductGame();
			totalMatches++;
			choice = JOptionPane.showOptionDialog(getParent(), "Total Matches played: " + totalMatches + "\n" +
																	p1.getName() + " won: " + p1.numWins + " times.\n" + 
																	p2.getName() + " won: " + p2.numWins + " times.\n" + 
																	"Matches Draw: " + (totalMatches-p1.numWins-p2.numWins) + "\n" + 
																	"Do you want to play more?", "GAME OVER", 
																	JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, imageIcon, null, null);
			
		}
		while(choice == JOptionPane.YES_OPTION);
		
		if(choice == JOptionPane.NO_OPTION)
			System.exit(0);
	}
	
	private void conductGame() {
		
		boolean player1turn = true;
		int gameStatus = BoardGUI.Incomplete;
		/*
		 *  1 = Player 1 won
		 *  2 = Player 2 won
		 *  3 = Draw
		 *  4 = Incomplete
		 *  5 = Invalid Move
		 */
		
		while (gameStatus == BoardGUI.Incomplete || gameStatus == BoardGUI.InvalidMove) {
			
			
			if(player1turn) {
				
				gameStatusText.setText("\nPlayer 1 - " + p1.getName() + "'s turn");		
				while(!buttonPressed) {
					try {
					       Thread.sleep(200);
					    } catch(InterruptedException e) {
					    }
				}
				gameStatus = board.move(p1.getSymbol(), getxCord(), getyCord(), button);
				if(gameStatus == BoardGUI.InvalidMove) {
					gameStatusText.setText("Invalid Move!! Try Again");
				}
				else {
					player1turn = false;
				}
			}
			else {
				
				gameStatusText.setText("\nPlayer 2 - " + p2.getName() + "'s turn");
				while(!buttonPressed) {
					try {
					       Thread.sleep(200);
					    } catch(InterruptedException e) {
					    }
				}

				gameStatus = board.move(p2.getSymbol(), getxCord(), getyCord(), button);
				if(gameStatus == BoardGUI.InvalidMove) {
					gameStatusText.setText("Invalid Move!! Try Again");
				}
				else {
					player1turn = true;
				}
				
			}
			buttonPressed = false;
		}
		
		if(gameStatus == BoardGUI.Player1Wins) {
			p1.numWins++;
			gameStatusText.setText("\n\nHoorayy !! " + p1.getName() + " won!!");
		}
		else if(gameStatus == BoardGUI.Player2Wins) {
			p2.numWins++;
			gameStatusText.setText("\n\nHoorayy !! " + p2.getName() + " won!!");
		}
		else {
			gameStatusText.setText("\n\nGame is Draw!!");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buttonPressed = true;
		if(e.getSource() == button[0]) {
			setxCord(0);
			setyCord(0);
		}
		else if(e.getSource() == button[1]) {
			setxCord(0);
			setyCord(1);
		}
		else if(e.getSource() == button[2]) {
			setxCord(0);
			setyCord(2);
		}
		else if(e.getSource() == button[3]) {
			setxCord(1);
			setyCord(0);
		}
		else if(e.getSource() == button[4]) {
			setxCord(1);
			setyCord(1);
		}
		else if(e.getSource() == button[5]) {
			setxCord(1);
			setyCord(2);
		}
		else if(e.getSource() == button[6]) {
			setxCord(2);
			setyCord(0);
		}
		else if(e.getSource() == button[7]) {
			setxCord(2);
			setyCord(1);
		}
		else if(e.getSource() == button[8]) {
			setxCord(2);
			setyCord(2);
		}	
		else if(e.getActionCommand() == "Reset") {
			startGame();
		}
		else if(e.getActionCommand() == "Exit") {
			System.exit(0);
		}
	}
	
	public int getxCord() {
		return xCord;
	}
	public void setxCord(int xCord) {
		this.xCord = xCord;
	}
	public int getyCord() {
		return yCord;
	}
	public void setyCord(int yCord) {
		this.yCord = yCord;
	}
	
	
	public static void main(String[] args) {
		new TicTacToeGUI();
	}
}
