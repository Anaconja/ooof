package mainGame;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;
import java.util.Timer;

import GameEntity.Bullet;

@SuppressWarnings({ "unused", "serial" })
public class GamePanel extends JPanel{
	private JLabel nameDisplay;
	private JLabel hpDisplay;
	private JLabel clipDisplay;
	
	private JLabel eNameDisplay;
	private JLabel eHpDisplay;
	private JLabel gamesWon;
	
	private JLabel logDisplay;
	
	private JButton victory;
	private JButton defeat;
	
	private Image currentMap;
	private Image[] maps = new Image[] {new ImageIcon("dust.jpg").getImage(), new ImageIcon("goldrush.jpg").getImage(), new ImageIcon("route66.jpg").getImage()};
	
	private JButton shoot;
	private Bullet bullet;
	private JButton reload;
	private JButton specialAttack;
	private JButton dodge;
	
	private GameData model;
	private CardLayout cardLayout;
	private JPanel statsPanel;
	private JPanel movePanel;
	
	public GamePanel(GameData data) {
		BattlePanel battlePanel = new BattlePanel();
		battlePanel.setLayout(null);
		model = data;
		bullet = new Bullet(10000, 0, this);
    	
    	nameDisplay = new JLabel();
    	nameDisplay.setFont(new Font("Consolas", Font.BOLD, 20));
    	nameDisplay.setText(model.getPlayer().getName());
		hpDisplay = new JLabel();
		hpDisplay.setFont(new Font("Consolas", Font.BOLD, 20));
		hpDisplay.setText("HEALTH: " + model.getPlayer().getMaxHp() + "/" + model.getPlayer().getMaxHp());
		clipDisplay = new JLabel();
		clipDisplay.setFont(new Font("Consolas", Font.BOLD, 20));
		clipDisplay.setText("CLIP: 6/6");
		
		eNameDisplay = new JLabel();
		eNameDisplay.setFont(new Font("Consolas", Font.BOLD, 20));
		eNameDisplay.setText(model.getComputer().getName());
		eHpDisplay = new JLabel();
		eHpDisplay.setFont(new Font("Consolas", Font.BOLD, 20));
		eHpDisplay.setText("ENEMY HEALTH: " + model.getComputer().getMaxHp() + "/" + model.getComputer().getMaxHp());
		
		gamesWon = new JLabel();
		gamesWon.setFont(new Font("Consolas", Font.BOLD, 20));
		gamesWon.setText("     GAMES WON: 0");
		
		logDisplay = new JLabel();
		logDisplay.setFont(new Font("Consolas", Font.PLAIN, 16));
		logDisplay.setText("<html></html>");
		
		currentMap = maps[(int) (Math.random() * 3)];
		
		victory = createJButtonWithImage("victory.png", 1200, 300);
		victory.setBounds(0, 600, 1200, 300);
		victory.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				startNewGame(battlePanel);
			}
		});		
		
		defeat = createJButtonWithImage("defeat.png", 1200, 300);
		defeat.setBounds(0, 600, 1200, 300);
		defeat.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				System.exit(0);
			}
		});		
		
		statsPanel = new JPanel();
		statsPanel.setLayout(new GridLayout(2, 3));
		statsPanel.add(nameDisplay);
		statsPanel.add(hpDisplay);
		statsPanel.add(clipDisplay);
		statsPanel.add(eNameDisplay);
		statsPanel.add(eHpDisplay);
		statsPanel.add(gamesWon);
		data.registerView(this);
		
		shoot = createJButtonWithImage("shoot.png", 500, 125);
		shoot.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				greyPanels();
				bullet.setWhoShot("player");
				if(model.getPlayer().isSpecialUsed()) model.setTurnsPassed(model.getTurnsPassed() + 1);
				if(model.getTurnsPassed() == 5) {
					model.getPlayer().setSpecialUsed(false);
					model.getLog().add("Your special attack is now charged.");
				}
				model.getLog().add(model.getPlayer().getGun().shoot(model.getPlayer(), model.getComputer()));
				updatePanels(battlePanel);
				Timer delay  = new Timer();
				delay.schedule(new TimerTask() {
					public void run() {
						if(model.getComputer().getHp() != 0) {
							model.getLog().add(model.computerMove());
							if(model.getComputer().isSpecialUsed()) model.seteTurnsPassed(model.geteTurnsPassed() + 1);
							if(model.geteTurnsPassed() == 5) model.getComputer().setSpecialUsed(false);
						}
						updatePanels(battlePanel);
						whitePanels();
					}
				}, 1000);
			}
		});
		reload = createJButtonWithImage("reload.png", 500, 125);
		reload.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				greyPanels();
				if(model.getPlayer().isSpecialUsed()) model.setTurnsPassed(model.getTurnsPassed() + 1);
				if(model.getTurnsPassed() == 5) {
					model.getPlayer().setSpecialUsed(false);
					model.getLog().add("Your special attack is now charged.");
				}
				model.getLog().add(model.getPlayer().getGun().reload(model.getPlayer()));
				if(!model.getPlayer().getName().equals("McCree")) {
					model.getLog().add(model.computerMove());
					if(model.getComputer().isSpecialUsed()) model.seteTurnsPassed(model.geteTurnsPassed() + 1);
					if(model.geteTurnsPassed() == 5) model.getComputer().setSpecialUsed(false);
				}
				updatePanels(battlePanel);
				whitePanels();
			}
		});
		specialAttack = createJButtonWithImage("specialAttack.png", 500, 125);
		specialAttack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				greyPanels();
				model.setTurnsPassed(0);
				model.getLog().add(model.getPlayer().getGun().specialAttack(model.getPlayer(), model.getComputer()));
				
				model.getLog().add(model.computerMove());
				if(model.getComputer().isSpecialUsed()) model.seteTurnsPassed(model.geteTurnsPassed() + 1);
				if(model.geteTurnsPassed() == 5) model.getComputer().setSpecialUsed(false);
				updatePanels(battlePanel);
				whitePanels();
			}
		});
		dodge = createJButtonWithImage("dodge.png", 500, 125);
		
		battlePanel.add(shoot);
		shoot.setBounds(0, 700, 600, 75);
		battlePanel.add(reload);
		reload.setBounds(600, 700, 600, 75);
		battlePanel.add(specialAttack);
		specialAttack.setBounds(0, 800, 600, 75);
		battlePanel.add(dodge);
		dodge.setBounds(600, 800, 600, 75);

		battlePanel.add(logDisplay);
		logDisplay.setBounds(0, -100, 1200, 500);
		logDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		this.setLayout(new BorderLayout());
		this.add(statsPanel, BorderLayout.NORTH);
		this.add(battlePanel, BorderLayout.CENTER);
		battlePanel.invalidate();
	}
	
	private class BattlePanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(currentMap, 0, 0, 1200, 1000, null);
			g.drawImage(model.getPlayer().getFightImage(), model.getPlayer().getX(), model.getPlayer().getY(), 350, 420, null);
			g.drawImage(model.getPlayer().getGun().getImage(), model.getPlayer().getGun().getX(), model.getPlayer().getGun().getY(), 180, 135, null);
			
			g.setColor(Color.BLACK);
			g.drawRect(149, 224, 201, 26);
			g.setColor(Color.RED);
			g.fillRect(150, 225, 200, 25);
			g.setColor(Color.BLACK);
			g.drawRect(149, 224, (model.getPlayer().getHp() * 2) + 1, 26);
			g.setColor(Color.GREEN);
			g.fillRect(150, 225, (model.getPlayer().getHp() * 2), 25);
			
			g.drawImage(model.getComputer().getFightImage(), model.getComputer().getX(), model.getComputer().getY(), 350, 420, null);
			g.drawImage(model.getComputer().getGun().getImage(), model.getComputer().getGun().getX(), model.getComputer().getGun().getY(), 180, 135, null);
			
			g.setColor(Color.BLACK);
			g.drawRect(849, 224, 201, 26);
			g.setColor(Color.RED);
			g.fillRect(850, 225, 200, 25);
			g.setColor(Color.BLACK);
			g.drawRect(849, 224, (model.getComputer().getHp() * 2) + 1, 26);
			g.setColor(Color.GREEN);
			g.fillRect(850, 225, (model.getComputer().getHp() * 2), 25);
			
			if(bullet.isMoving()) {
				g.drawImage(bullet.getImage(), bullet.getX() + 250, bullet.getY() + 175, 100, 50, null);
			}
		}
	}

	public void greyPanels() {
		shoot.setIcon(new ImageIcon((new ImageIcon("shoot-1.png").getImage().getScaledInstance(500, 125, 0))));
		reload.setIcon(new ImageIcon((new ImageIcon("reload-1.png").getImage().getScaledInstance(500, 125, 0))));
		specialAttack.setIcon(new ImageIcon((new ImageIcon("specialAttack-1.png").getImage().getScaledInstance(500, 125, 0))));
		dodge.setIcon(new ImageIcon((new ImageIcon("dodge-1.png").getImage().getScaledInstance(500, 125, 0))));
	}
	
	public void whitePanels() {
		shoot.setIcon(new ImageIcon((new ImageIcon("shoot.png").getImage().getScaledInstance(500, 125, 0))));
		reload.setIcon(new ImageIcon((new ImageIcon("reload.png").getImage().getScaledInstance(500, 125, 0))));
		specialAttack.setIcon(new ImageIcon((new ImageIcon("specialAttack.png").getImage().getScaledInstance(500, 125, 0))));
		dodge.setIcon(new ImageIcon((new ImageIcon("dodge.png").getImage().getScaledInstance(500, 125, 0))));
	}
	
	public void updatePanels(BattlePanel battlePanel) {
		model.getPlayer().getGun().setBullet(bullet);
		model.getComputer().getGun().setBullet(bullet);
		if(model.getPlayer().getHp() != 0) hpDisplay.setText("HEALTH: " + String.valueOf(model.getPlayer().getHp()) + "/100");
		else {
			hpDisplay.setText("HEALTH: DEAD");
			battlePanel.remove(shoot);
			battlePanel.remove(reload);
			battlePanel.remove(specialAttack);
			battlePanel.remove(dodge);
			battlePanel.add(defeat);
		}
		if(model.getComputer().getHp() != 0) eHpDisplay.setText("ENEMY HEALTH: " + String.valueOf(model.getComputer().getHp()) + "/100");
		else {
			eHpDisplay.setText("ENEMY HEALTH: DEAD");
			model.setGamesWon(model.getGamesWon() + 1);
			battlePanel.remove(shoot);
			battlePanel.remove(reload);
			battlePanel.remove(specialAttack);
			battlePanel.remove(dodge);
			battlePanel.add(victory);
		}
		logDisplay.setText("<html><div align=right>"+model.listToString(model.getLog())+"</div></html>");
		logDisplay.setBounds(0, -100, 1200, 500);
		gamesWon.setText("     GAMES WON: " + model.getGamesWon());
		clipDisplay.setText("CLIP: " + String.valueOf(model.getPlayer().getGun().getClip()) + "/6");
		battlePanel.repaint();
	}
	
	public void startNewGame(BattlePanel battlePanel) {
		model.computerChoose();
		eNameDisplay.setText(model.getComputer().getName());
		model.getComputer().setMaxHp(model.getComputer().getMaxHp() + (model.getGamesWon() * 10));
		model.getPlayer().getGun().setClip(model.getPlayer().getGun().getFullClip());
		model.getPlayer().setHp(model.getPlayer().getMaxHp());
		
		this.removeAll();
		battlePanel.removeAll();
		model.getLog().clear();
		
		battlePanel.add(logDisplay);
		
		changeMap();
		
		this.add(statsPanel, BorderLayout.NORTH);
		this.add(battlePanel, BorderLayout.CENTER);

		battlePanel.add(shoot);
		battlePanel.add(reload);
		battlePanel.add(specialAttack);
		battlePanel.add(dodge);
		
		updatePanels(battlePanel);
		battlePanel.repaint();
	}
	
	public void changeMap() {
		currentMap = maps[(int) (Math.random() * 3)];
	}
	
	public JLabel getNameDisplay() {
		return nameDisplay;
	}

	public void setNameDisplay(JLabel nameDisplay) {
		this.nameDisplay = nameDisplay;
	}
	
	public GameData getModel() {
		return model;
	}

	public JButton createJButtonWithImage(String fileName, int width, int height) {
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
		return button;
	}

	public Bullet getBullet() {
		return bullet;
	}
}

