package newGUI;

public class Model {
    private String[] board = new String[9];  // Store the game board
    private opponentAI AI;
    private String player = "";
    
    int[][] winConditions = {
    		{0,1,2},{3,4,5},{6,7,8}, // Columns
    		{0,3,6},{1,4,7},{2,5,8}, // Rows
    		{0,4,8},{2,4,6} // Diagonals
    };

    public Model() {
        for (int i = 0; i < 9; i++) {
            board[i] = "";  // Initialize the board with empty strings
        }
    }

    public String getCell(int index) {
        return board[index];
    }

    public int setCell(int index, String value) {
        board[index] = value;
        System.out.println("Set button #" + index + " to " + value);
        
        int AIMove = AI.nextMove(index);
        
        if (AIMove < 9) {
        board[AIMove] = AI.getLabel();
        } else {
        	AIMove = -1;
        }
        
        return AIMove;
    }

    public void resetBoard() {
        for (int i = 0; i < 9; i++) {
            board[i] = "";
        }
    }
    public String[] getBoard() {
    	return board;
    }
    public String getPlayer() {
    	return player;
    }
    public void setPlayer(String p) {
    	this.player = p;
    	if (p.equals("X")) {
    		AI = new opponentAI("O", board);
    	} else {
    		AI = new opponentAI("X", board);
    	}
    }
    
    public boolean checkWin(String checkPlayer) {
    	boolean win = false;
    	for (int[] condition : winConditions) {
    		if (board[condition[0]].equals(checkPlayer)
    				&& board[condition[1]].equals(checkPlayer)
    				&& board[condition[2]].equals(checkPlayer)) {
    			win = true;
    			System.out.println(checkPlayer + " is the Winner!");
    		}
    	}
    	
    	return win;
    }
    
    public boolean checkTie() {
    	boolean tied=true;
    	int i=0;
    	
    	while (tied && i < board.length) {
    		if (board[i].equals("")) {
    			tied=false;
    		}
    		i++;
    	}
    	return tied;
    }
    
    public String getAI() {
    	return this.AI.getLabel();
    }
}