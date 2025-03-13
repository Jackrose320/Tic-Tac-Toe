package newGUI;

public class opponentAI {
	private String label;
	private String playerLabel;
	private String[] board;
	
    private int[][] winConditions = {
    		{0,1,2},{3,4,5},{6,7,8}, // Columns
    		{0,3,6},{1,4,7},{2,5,8}, // Rows
    		{0,4,8},{2,4,6} // Diagonals
    };
	
	
	public opponentAI(String label, String[] board) {
		this.label=label;
		this.board=board;
		
		if (label.equals("X")) {
			playerLabel = "O";
		} else {
			playerLabel = "X";
		}
	}
	
	private int calculate() {
		int move=0;
		boolean calculated=false;
		int i=0;
		
		while (i < winConditions.length && !calculated) {
			int[] condition = winConditions[i];
				if (board[condition[0]].equals(label) && board[condition[1]].equals(label) && board[condition[2]].equals("")) {
					move=condition[2];
					calculated=true;
				} else if (board[condition[0]].equals(label) && board[condition[1]].equals("") && board[condition[2]].equals(label)) {
					move=condition[1];
					calculated=true;
				} else if (board[condition[0]].equals("") && board[condition[1]].equals(label) && board[condition[2]].equals(label)) {
					move=condition[0];
					calculated=true;
				}
				i++;
		}
		i=0;
		while (i < winConditions.length && !calculated) {
			int[] condition = winConditions[i];
			
			if (board[condition[0]].equals(playerLabel) && board[condition[1]].equals(playerLabel) && board[condition[2]].equals("")) {
				move=condition[2];
				calculated=true;
			} else if (board[condition[0]].equals(playerLabel) && board[condition[1]].equals("") && board[condition[2]].equals(playerLabel)) {
				move=condition[1];
				calculated=true;
			} else if (board[condition[0]].equals("") && board[condition[1]].equals(playerLabel) && board[condition[2]].equals(playerLabel)) {
				move=condition[0];
				calculated=true;
			}
			i++;
		}
		
		while (!calculated && (move < board.length) && !board[move].equals("")) {
			move++;
		}
		
		System.out.println("AI Played button #" + move);
		return move;
	}
	
	public int nextMove(int playerMove) {
		board[playerMove] = playerLabel;
		return calculate();
	}
	
	public String getLabel() {
		return label;
	}
}
