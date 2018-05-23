package GameEntity;

import java.awt.Image;

public abstract class Character extends GameEntity {
	private int hp;
	private int maxHp;
	private Image fightImage;
	private boolean specialUsed;
	private Gun gun;
	private int speed;
	
	public Character(String name, Image image, int x, int y, Image fightImage, int hp, int maxHp, boolean specialUsed, Gun gun, int speed) {
		super(name, image, x, y);
		this.fightImage = fightImage;
		this.hp = hp;
		this.setMaxHp(maxHp);
		this.specialUsed = specialUsed;
		this.gun = gun;
		this.speed = speed;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public Image getFightImage() {
		return fightImage;
	}

	public void setFightImage(Image fightImage) {
		this.fightImage = fightImage;
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

	public int getSpeed() {
		return speed;
	}

}
