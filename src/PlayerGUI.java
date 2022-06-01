
public class PlayerGUI {

	private String name;
	private char symbol;
	int numWins;
	
	public PlayerGUI(String name) {
		
		setName(name);
	}

	public void setName(String name) {
		if(!name.isEmpty()) {
			this.name = name;
		}
	}

	public String getName() {
		return name;
	}

	public void setSymbol(char symbol) {
		if(symbol!='\0') {
			this.symbol = symbol;
		}
	}

	public char getSymbol() {
		return symbol;
	}
}
