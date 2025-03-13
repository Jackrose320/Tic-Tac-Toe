package newGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuItems {
	public int[] scores = new int[3];
	
	public enum SCORE {
		PLAYER,
		AI,
		TIED;
	}
	
	public MenuItems() {
	}
	
	public void updateScores(SCORE x) {
	    if (x == SCORE.PLAYER) {
	        scores[0]++; // Player wins
	    } else if (x == SCORE.AI) {
	        scores[1]++; // AI wins
	    } else if (x == SCORE.TIED) {
	        scores[2]++; // Tie
	    }
	}
	
	@SuppressWarnings("serial")
	private class SettingsFrame extends JFrame {
		 private Color backgroundColor = Color.WHITE;
				 
		public SettingsFrame(JFrame main) throws IOException {
			this.setIconImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("resources/gear.png")));
			this.setTitle("Settings");
			this.setSize(500,400);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setLocationRelativeTo(main);
			this.setVisible(true);
            JPanel settingsPanel = new JPanel();
            settingsPanel.setLayout(new GridLayout(2, 1));
            JButton backgroundColorButton = new JButton("Change Background Color");
            backgroundColorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Open a color chooser for background color
                    Color newColor = JColorChooser.showDialog(SettingsFrame.this, "Choose Background Color", backgroundColor);
                    
                        backgroundColor = newColor;
                        main.getContentPane().setBackground(backgroundColor);
                }
            });
            
            JButton buttonColorButton = new JButton("Change Button Color");
            buttonColorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Open a color chooser for button color
                    Color newButtonColor = JColorChooser.showDialog(SettingsFrame.this, "Choose Button Color", Color.LIGHT_GRAY);
                    if (newButtonColor != null) {
                        // Iterate over all buttons in the main frame and set the new color
                        for (Component component : main.getContentPane().getComponents()) {
                            if (component instanceof JButton) {
                                component.setBackground(newButtonColor);
                            }
                        }
                    }
                }
            });
            JButton resetSettingsButton = new JButton("Reset Settings");
            resetSettingsButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					main.getContentPane().setBackground(null);
                    for (Component component : main.getContentPane().getComponents()) {
                        if (component instanceof JButton) {
                            component.setBackground(null);
                        }
                    }
				}
            	
            });
            
            JButton closeSettingsButton = new JButton("Close Settings");
            closeSettingsButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					SettingsFrame.this.dispose();
				}
            	
            });

            settingsPanel.add(backgroundColorButton);
            settingsPanel.add(buttonColorButton);
            settingsPanel.add(resetSettingsButton);
            settingsPanel.add(closeSettingsButton);
            add(settingsPanel, BorderLayout.CENTER);

            setVisible(true);
		}
		
	}
	
	public void addMenuListeners(JFrame main, JMenuItem... items) {
		for (JMenuItem item : items) {
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (item.getText().equals("View Scores")) {
						JOptionPane.showMessageDialog(main, "Player score: " + scores[0] + "\n AI score: " + scores[1] + "\n Ties: " + scores[2]);
					} else if (item.getText().equals("Settings")) {
						try {
							new SettingsFrame(main);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if (item.getText().equals("Close Session")) {
						main.setVisible(false);
						main.dispose();
					}
				}
				
			});
		}
	}

	public void PlayActionListener(JFrame main) {
		JOptionPane.showMessageDialog(main, "Starting a new game...");
	}
}
