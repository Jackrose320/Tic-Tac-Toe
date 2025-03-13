package newGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Panel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class gui {

	private Controller controller;
	private MenuItems mi;
	private JFrame frame;
	private JLabel gameStatus;
	private JButton[] buttons = new JButton[9];
	protected char Player;
	private JButton buttonX;
	private JButton buttonO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public gui() {
		try {
		initialize();
		resetButtons();
		Model model = new Model();
		Controller controller = new Controller(model, this);
		this.controller = controller;
		} catch(IOException e) {
			System.out.println("Cannot find app icon.");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		// frame.setUndecorated(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("resources/icon.png")));
		frame.setTitle("Tic-Tac-Toe");
		
		JButton button1 = new JButton("");
		button1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		button1.setBounds(10, 1, 80, 80);
		buttons[0] = button1;
		frame.getContentPane().add(button1);
		
		JButton button2 = new JButton("");
		button2.setFont(new Font("Tahoma", Font.PLAIN, 35));
		button2.setBounds(10, 83, 80, 80);
		buttons[1] = button2;
		frame.getContentPane().add(button2);
		
		JButton button3 = new JButton("");
		button3.setFont(new Font("Tahoma", Font.PLAIN, 35));
		button3.setBounds(10, 163, 80, 80);
		buttons[2] = button3;
		frame.getContentPane().add(button3);
		
		JButton button4 = new JButton("");
		button4.setFont(new Font("Tahoma", Font.PLAIN, 35));
		button4.setBounds(91, 1, 80, 80);
		buttons[3] = button4;
		frame.getContentPane().add(button4);
		
		JButton button5 = new JButton("");
		button5.setFont(new Font("Tahoma", Font.PLAIN, 35));
		button5.setBounds(91, 83, 80, 80);
		buttons[4] = button5;
		frame.getContentPane().add(button5);
		
		JButton button6 = new JButton("");
		button6.setFont(new Font("Tahoma", Font.PLAIN, 35));
		button6.setBounds(91, 163, 80, 80);
		buttons[5] = button6;
		frame.getContentPane().add(button6);
		
		JButton button7 = new JButton("");
		button7.setFont(new Font("Tahoma", Font.PLAIN, 35));
		button7.setBounds(172, 1, 80, 80);
		buttons[6] = button7;
		frame.getContentPane().add(button7);
		
		JButton button8 = new JButton("");
		button8.setFont(new Font("Tahoma", Font.PLAIN, 35));
		button8.setBounds(172, 83, 80, 80);
		buttons[7] = button8;
		frame.getContentPane().add(button8);
		
		JButton button9 = new JButton("");
		button9.setFont(new Font("Tahoma", Font.PLAIN, 35));
		button9.setBounds(172, 163, 80, 80);
		buttons[8] = button9;
		frame.getContentPane().add(button9);
		
		for (int i=0; i < buttons.length; i++) {
			buttons[i].addActionListener(new ButtonClickListener(i));
		}
		
		Panel editPanel = new Panel();
		editPanel.setBounds(258, 10, 170, 244);
		frame.getContentPane().add(editPanel);
		editPanel.setLayout(null);
		
		JButton buttonRESET = new JButton("RESET");
		buttonRESET.setFont(new Font("Tahoma", Font.PLAIN, 35));
		buttonRESET.setBounds(10, 142, 150, 80);
		editPanel.add(buttonRESET);
		buttonRESET.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gui.this.controller.resetGame();
				gui.this.gameStatus.setText("");
				System.out.println("Reset Game.");
			}
		});
		
		JLabel pickShapeLBL = new JLabel("Pick your shape:");
		pickShapeLBL.setFont(new Font("JetBrains Mono SemiBold", Font.PLAIN, 15));
		pickShapeLBL.setBounds(10, 62, 150, 29);
		editPanel.add(pickShapeLBL);
		
		JPanel choosePanel = new JPanel();
		choosePanel.setBounds(10, 89, 150, 53);
		editPanel.add(choosePanel);
		
		buttonO = new JButton("O");
		buttonO.setForeground(new Color(255, 0, 0));
		buttonO.setFont(new Font("JetBrains Mono", Font.BOLD, 40));
		buttonO.setBounds(10, 0, 63, 51);
		choosePanel.setLayout(null);
		choosePanel.add(buttonO);
		buttonO.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gui.this.Player='O';
			}
		});
		
		buttonX = new JButton("X");
		buttonX.setForeground(new Color(0, 128, 255));
		buttonX.setFont(new Font("JetBrains Mono", Font.BOLD, 40));
		buttonX.setBounds(78, 0, 62, 51);
		choosePanel.add(buttonX);
		
		this.gameStatus = new JLabel("");
		gameStatus.setFont(new Font("JetBrains Mono SemiBold", Font.BOLD, 40));
		gameStatus.setBounds(10, 0, 150, 64);
		editPanel.add(gameStatus);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Game");
		menuBar.add(mnNewMenu);
		
		JMenuItem play = new JMenuItem("Play Tic-Tac-Toe");
		JMenuItem viewScores = new JMenuItem("View Scores");
		JMenuItem settings = new JMenuItem("Settings");
		JMenuItem close = new JMenuItem("Close Session");
		mnNewMenu.add(play);
		mnNewMenu.add(viewScores);
		mnNewMenu.add(settings);
		mnNewMenu.add(close);
		mi = new MenuItems();
		mi.addMenuListeners(frame, play,viewScores,settings,close);
		
		buttonX.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gui.this.Player='X';
			}
		});
	}
		
    public void listenPlayer(ActionListener listener) {
    	this.buttonO.addActionListener(listener);
    	this.buttonX.addActionListener(listener);
    }
    
    private class ButtonClickListener implements ActionListener {
        private int index;

        public ButtonClickListener(int index) {
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.buttonClicked(index);
        }
    }
    
    public void updateButton(int index, String label) {
    	if (!label.equals("")) {
    		buttons[index].setEnabled(false);
    	}
        buttons[index].setText(label);
    }
    
    public void resetButtons() {
        for (JButton button : buttons) {
            button.setText("");
            button.setEnabled(false);
        }
        setPlayer(true);
    }
    
    public void win(boolean status) {
    	if (status) {
    		this.gameStatus.setText("WIN!");
    		this.gameStatus.setForeground(Color.GREEN);
    		mi.updateScores(MenuItems.SCORE.PLAYER);
    	} else {
    		this.gameStatus.setText("LOSE!");
    		this.gameStatus.setForeground(Color.RED);
    		mi.updateScores(MenuItems.SCORE.AI);
    	}
    	for (JButton b : buttons) {
    		b.setEnabled(false);
    	}
    }
    public void tie() {
    	this.gameStatus.setText("TIE!");
    	mi.updateScores(MenuItems.SCORE.TIED);
    	this.gameStatus.setForeground(Color.ORANGE);
    }
    
    public void setPlayer(boolean b) {
    	this.buttonO.setEnabled(b);
    	this.buttonX.setEnabled(b);
    }
    public void setGame() {
    	for (JButton button : buttons) {
    		button.setEnabled(true);
    	}
    }
}
