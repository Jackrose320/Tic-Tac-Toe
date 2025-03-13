package newGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Controller {
    private Model model;
    private gui view;
    private String currentPlayer = "";

    public Controller(Model model, gui view) {
        this.model = model;
        this.view = view;
        
        ActionListener choosePlayer = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton)e.getSource();
				System.out.println("Current Player: " + button.getText());
				model.setPlayer(button.getText());
				Controller.this.currentPlayer=button.getText();
				button.setEnabled(false);
				view.setPlayer(false);
				view.setGame();
			}
        };
        view.listenPlayer(choosePlayer);
    }
    
    public void buttonClicked(int index) {
    	int AIMove = model.setCell(index, model.getPlayer());
    	boolean win = model.checkWin(this.currentPlayer);
    	boolean AIwin = model.checkWin(model.getAI());
    	if (win) {
    		this.view.win(true);
    	} else if (AIwin) {
    		this.view.win(false);
    	}
    	if (this.model.checkTie() && !win && !AIwin) {
    		this.view.tie();
    	} else if (!win) {
    	this.view.updateButton(AIMove, model.getCell(AIMove));
    	}
    	
    	this.view.updateButton(index, model.getCell(index));
    }
    
    public void resetBoard() {
    	model.resetBoard();
    	for (int i=0; i < model.getBoard().length; i++) {
    		this.view.updateButton(i, model.getPlayer());
    	}
    	this.view.updateButton(0, currentPlayer);
    }

    public void resetGame() {
        model.resetBoard();
        currentPlayer = "";
        view.resetButtons();
    }
}
