package GameEntity;

import java.awt.*;
import javax.swing.*;
import Guns.*;
import mainGame.GamePanel;

@SuppressWarnings("unused")
public class Player extends Character{
	private String name;
	private Image image;
	private int hp;
	private int maxHp;
	private boolean specialUsed;
	private Gun gun;
	private int speed;
	private GamePanel panel;
	
	public Player(GamePanel panel) {
		super("default name", new ImageIcon("placeholder.png").getImage(), 0, 0, new ImageIcon("placeholder.png").getImage(), 100, 100, false, new Ambassador(0, 0), 1);
		this.panel = panel;
	}
	
	public void makeMcCree(boolean isCPU) {
		setName("McCree");
		setImage(new ImageIcon("mccree.png").getImage());
		setX(75);
		setY(275);
		super.setFightImage(new ImageIcon("mccree-1.png").getImage());
		setHp(100);
		setMaxHp(100);
		setSpecialUsed(false);
		setGun(new Peacekeeper(300, 425));
		setSpeed(1);
		if(isCPU) {
			setX(800);
			setY(275);
			setGun(new Peacekeeper(725, 425));
			setName("Enemy McCree");
			super.setFightImage(new ImageIcon("mccree-2.png").getImage());
			getGun().setImage(new ImageIcon("peace-1.png").getImage());
		}
	}
	
	public void makeSpy(boolean isCPU) {
		setName("Spy");
		setImage(new ImageIcon("spy.png").getImage());
		setX(75);
		setY(275);
		super.setFightImage(new ImageIcon("spy-1.png").getImage());
		setHp(100);
		setMaxHp(100);
		setSpecialUsed(false);
		setGun(new Ambassador(300, 425));
		setSpeed(1);
		if(isCPU) {
			setX(800);
			setY(275);
			setGun(new Ambassador(725, 425));
			setName("Enemy Spy");
			super.setFightImage(new ImageIcon("spy-2.png").getImage());
			getGun().setImage(new ImageIcon("amby-1.png").getImage());
		}
	}
	
	public void makeTerrorist(boolean isCPU) {
		setName("Terrorist");
		setImage(new ImageIcon("terrorist.gif").getImage());
		setX(75);
		setY(275);
		super.setFightImage(new ImageIcon("terrorist-1.png").getImage());
		setHp(115);
		setMaxHp(115);
		setSpecialUsed(false);
		setGun(new DesertEagle(300, 425));
		setSpeed(0);
		if(isCPU) {
			setX(800);
			setY(275);
			setGun(new DesertEagle(725, 425));
			setName("Enemy Terrorist");
			super.setFightImage(new ImageIcon("terrorist-2.png").getImage());
			getGun().setImage(new ImageIcon("deagle-1.gif").getImage());
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public boolean isSpecialUsed() {
		return specialUsed;
	}

	public void setSpecialUsed(boolean specialUsed) {
		this.specialUsed = specialUsed;
	}

	public Gun getGun() {
		return gun;
	}

	public void setGun(Gun gun) {
		this.gun = gun;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public GamePanel getPanel() {
		return panel;
	}
	
	public void setPanel(GamePanel panel) {
		this.panel = panel;
	}
}
