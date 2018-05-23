package GameEntity;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import mainGame.GamePanel;

public class Bullet extends GameEntity{
	private Image image = new ImageIcon("bullet.png").getImage();
	private Image image1 = new ImageIcon("bullet-1.png").getImage();
	private static Image currentImage = new ImageIcon("placeholder.png").getImage();
	private GamePanel panel;
	private boolean isMoving = false;
	private int x;
	private int y;
	private int speed = 10;
	private String whoShot = "";
	
	public Bullet(int x, int y, GamePanel panel) {
		super("placeholder", currentImage, x, y);
		this.x = x;
		this.y = y;
		this.panel = panel;
		Timer timer = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isMoving) {
					if(whoShot.equals("player")) {
						currentImage = image;
						move(panel.getModel().getPlayer(), panel.getModel().getComputer());
					}
					else {
						currentImage = image1;
						move(panel.getModel().getComputer(), panel.getModel().getPlayer());
					}
				}
				else{
					setX(10000);
					panel.repaint();
				}
			}
		});
		timer.start(); 
	}

	public Image getImage() {
		return currentImage;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getWhoShot() {
		return whoShot;
	}

	public void setWhoShot(String whoShot) {
		this.whoShot = whoShot;
	}

	public void move(Player p, Player e) {
		if(x == 10000) {
			setX(p.getX());
			setY(p.getY());
			return;
		}
		if(p.getX() < e.getX()) {
			if(Math.abs(getX() - e.getX()) > 10 && getX() > 0 && getX() < 1200) {
				setX(getX() + speed);
				panel.repaint();
			}
			else isMoving = false;
		}else {
			if(Math.abs(getX() - e.getX()) > 10 && getX() > 0 && getX() < 1200) {
				setX(getX() - speed);
				panel.repaint();
			}
			else isMoving = false;
		}
	}
}
