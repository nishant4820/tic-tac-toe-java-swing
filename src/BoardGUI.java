
import javax.swing.JButton;

public class BoardGUI {

	private char[][] board;
	private int boardSize = 3;
	private char sym1, sym2;
	private int count = 0;
	public static final char Empty = ' ';
	public static final int Player1Wins = 1;
	public static final int Player2Wins = 2;
	public static final int Draw = 3;
	public static final int Incomplete = 4;
	public static final int InvalidMove = 5;
	
	public BoardGUI(char sym1, char sym2) {
		
		board = new char[boardSize][boardSize];
		for(int i=0; i<boardSize; i++) {
			for(int j=0; j<boardSize; j++) {
				board[i][j] = Empty;
			}
		}
		this.sym1 = sym1;
		this.sym2 = sym2;
		if(this.sym1 == this.sym2)
			System.exit(0);
	}

	
	public int move(char symbol, int x, int y, JButton[] button) {
		
		if(x<0 || x>=boardSize || y<0 || y>=boardSize || board[x][y]!=Empty) {
			return InvalidMove;
		}
		
		//Place the symbol
		board[x][y] = symbol;
		String st = ""+symbol;
		button[(3*x)+y].setText(st);
		count++;
		
		int check;
		boolean flag;
		
		//	Row Check for win
		check = board[x][y];
		flag = true;
		for(int i=0; i<boardSize; i++) {
			if(board[x][i] != check) {
				flag = false;
				break;
			}
		}
		if(flag)
			return symbol == sym1? Player1Wins : Player2Wins;
		
		//	Column Check for win
		check = board[x][y];
		flag = true;
		for(int i=0; i<boardSize; i++) {
			if(board[i][y] != check) {
				flag = false;
				break;
			}
		}
		if(flag)
			return symbol == sym1? Player1Wins : Player2Wins;
		
		
		//	Left Diagonal check for win
		if(x == y) {
			check = board[x][y];
			flag = true;
			for(int i=0; i<boardSize; i++) {
				if(board[i][i] != check) {
					flag = false;
					break;
				}
			}
			if(flag)
				return symbol == sym1? Player1Wins : Player2Wins;
		}
		
		//	Right Diagonal check for win
		if(x+y==boardSize-1) {
			check = board[x][y];
			flag = true;
			for(int i=0; i<boardSize; i++) {
				if(board[boardSize-i-1][i] != check) {
					flag = false;
					break;
				}
			}
			if(flag)
				return symbol == sym1? Player1Wins : Player2Wins;
		}
		
		//	Check for Draw
		if(count == boardSize*boardSize)
			return Draw;
		
		return Incomplete;
	}


//	public void printBoard() {
//		
//		System.out.println("---------------");
//		for(int i=0; i<boardSize; i++) {
//			for(int j=0; j<boardSize; j++) {
//				System.out.print("| " + board[i][j] + " |");
//			}
//			System.out.println();
//		}
//		System.out.println("---------------");
//	}
	
}
