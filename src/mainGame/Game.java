package mainGame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import GameEntity.Player;
import Guns.*;

@SuppressWarnings({ "serial", "unused" })
public class Game extends JFrame{
	JPanel mainPanel;
	GamePanel gamePanel;
	CardLayout cardLayout; 
	GameData data;
	String playerName;
	
	static final int WIDTH = 1200;
	static final int HEIGHT = 1000;
	
	public static void main(String[] args) {
		Game game = new Game();
		
		game.setup();
		
		game.setContentPane(game.mainPanel);
		game.setSize(Game.WIDTH, Game.HEIGHT);
		game.setLocation(100, 100);
		game.setVisible(true);
		game.setResizable(false); 
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setup() {
		mainPanel = new JPanel();
		
		cardLayout = new CardLayout();
		mainPanel.setLayout(cardLayout);
		JPanel titleScreenPanel = setupTitleScreen(); 
		JPanel characterPanel = setupCharacterScreen();
		
		data = new GameData();
		gamePanel = new GamePanel(data);
		data.registerView(gamePanel);
		
		mainPanel.add(titleScreenPanel, "Title Screen");
		mainPanel.add(characterPanel, "Character Screen");
		mainPanel.add(gamePanel, "Game Panel");
		cardLayout.show(mainPanel, "Title Screen");
	}
	
	public JPanel setupTitleScreen() {
		JPanel title = new JPanel();
		title.setLayout(new BorderLayout());
		
		JLabel titleText = createJLabelWithImageAndText("intro.png", "", Color.BLACK, 36);
		
		title.add(titleText, BorderLayout.CENTER);
		
		title.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				cardLayout.show(mainPanel, "Character Screen");
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		
		return title;
	}
	
	public JPanel setupCharacterScreen() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(3, 1));
		
		JLabel choose = new JLabel();
		choose.setFont(new Font("Courier", Font.BOLD, 36));
		choose.setText("<html>The computer has challenged you to a duel!<br>Choose your character.</html>");
		choose.setHorizontalTextPosition(JLabel.CENTER);
		
		JLabel characters = new JLabel();
		characters.setLayout(new GridLayout(1, 3));
		
		JLabel characterInfo = new JLabel();
		characterInfo.setLayout(new GridLayout(3, 1));
		
		JLabel description = new JLabel();
		description.setFont(new Font("Courier", Font.ITALIC, 36));
		JLabel stats = new JLabel();
		stats.setFont(new Font("Courier", Font.PLAIN, 30));
		stats.setText("Hover over a character to learn more about him.");
		JLabel specialAttack = new JLabel();
		specialAttack.setFont(new Font("Courier", Font.BOLD, 20));
		
		characterInfo.add(description);
		characterInfo.add(stats);
		characterInfo.add(specialAttack);
		
		JButton mccree = createJButtonWithImageAndText("mccree.png", (Game.WIDTH - 200)  / 3, Game.HEIGHT  / 3);
		mccree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				playerName = "McCree";
				data.getPlayer().makeMcCree(false);
				gamePanel.getNameDisplay().setText(playerName);
				cardLayout.show(mainPanel, "Game Panel");
				gamePanel.requestFocusInWindow();
			}
			
			public void mouseEntered(MouseEvent e) {
				description.setText("<html>McCree: A notorious and deadly assassin hailing from Santa Fe, New Mexico.");
				stats.setText("<html>POWER: 30 HEADSHOT CHANCE: 15%<br>ACCURACY: 60% INNATE ABILITY: Reloads instantly");
				specialAttack.setText("<html>SPECIAL ATTACK: Throws a flashbang, stunning the opponent, and empties his clip into him.");
			}
			
			public void mouseExited(MouseEvent e) {
				description.setText("");
				stats.setText("Hover over a character to learn more about him.");
				specialAttack.setText("");
			}
		});
		JButton spy = createJButtonWithImageAndText("spy.png", (Game.WIDTH - 200) / 3, Game.HEIGHT / 3);
		spy.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				playerName = "Spy";
				data.getPlayer().makeSpy(false);
				gamePanel.getNameDisplay().setText(playerName);
				cardLayout.show(mainPanel, "Game Panel");
				gamePanel.requestFocusInWindow();
			}
			
			public void mouseEntered(MouseEvent e) {
				description.setText("<html>Spy: A sharp dresser with even sharper accuracy.");
				stats.setText("<html>POWER: 25 HEADSHOT CHANCE: 40%<br>ACCURACY: 60% INNATE ABILITY: Lives a fatal shot at 1 health.");
				specialAttack.setText("<html>SPECIAL ATTACK: Focuses his mind, raising his accuracy and guarenteeing a critical hit.");
			}
			
			public void mouseExited(MouseEvent e) {
				description.setText("");
				stats.setText("Hover over a character to learn more about him.");
				specialAttack.setText("");
			}
		});
		JButton terrorist = createJButtonWithImageAndText("terrorist.png", (Game.WIDTH - 200) / 3, Game.HEIGHT / 3 );
		terrorist.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				playerName = "Terrorist";
				data.getPlayer().makeTerrorist(false);
				gamePanel.getNameDisplay().setText(playerName);
				cardLayout.show(mainPanel, "Game Panel");
				gamePanel.requestFocusInWindow();
			}
			
			public void mouseEntered(MouseEvent e) {
				description.setText("<html>Terrorist: The leader of a faction dedicated to terrorizing the United States.");
				stats.setText("<html>POWER: 40 HEADSHOT CHANCE: 5%<br>ACCURACY: 50% INNATE ABILITY: Has 15 more health.");
				specialAttack.setText("<html>SPECIAL ATTACK: Channels his inner strength, guaranteeing a hit.");
			}
			
			public void mouseExited(MouseEvent e) {
				description.setText("");
				stats.setText("Hover over a character to learn more about him.");
				specialAttack.setText("");
			}
		});
		
		characters.add(mccree);
		characters.add(spy);
		characters.add(terrorist);
		
		panel.add(choose);
		panel.add(characterInfo);
		panel.add(characters);
		return panel;
	}
	
	public JLabel createJLabelWithImageAndText(String fileName, String text, Color color, int fontSize) {
		JLabel label = null;  
		try {
			Image i = ImageIO.read(new File(fileName));
			label = new JLabel(new ImageIcon(i.getScaledInstance(Game.WIDTH, Game.HEIGHT, 0)));
		} catch (IOException e) {
			e.printStackTrace();
			label = new JLabel(text);
		}
		label.setFont(new Font("Courier", Font.BOLD, fontSize));
		label.setForeground(color);
		label.setText(text);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		return label;
	}
	
	public JButton createJButtonWithImageAndText(String fileName, int width, int height) {
		JButton button = null;  
		try {
			Image i = ImageIO.read(new File(fileName));
			button = new JButton(new ImageIcon(i.getScaledInstance(width, height, 0)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setHorizontalTextPosition(JButton.CENTER);
		//button.setVerticalAlignment(SwingConstants.BOTTOM);
		return button;
	}
}
