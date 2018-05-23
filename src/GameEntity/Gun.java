package GameEntity;

import java.awt.Image;

public abstract class Gun extends GameEntity {
	private int power;
	private int accuracy;
	private int headshotChance;
	private int clip;
	private int fullClip;
	private Bullet bullet;
	
	public Gun(String name, Image image, int x, int y, int power, int accuracy, int headshotChance, int clip, int fullClip) {
		super(name, image, x, y);
		this.power = power;
		this.accuracy = accuracy;
		this.headshotChance = headshotChance;
		this.clip = clip;
		this.fullClip = fullClip;
	}
	
	public int getPower() {
		return power;
	}

	public abstract void setPower();
	
	public void setPower(int power) {
		this.power = power;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public abstract void setAccuracy();
	
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	public int getHeadshotChance() {
		return headshotChance;
	}

	public abstract void setHeadshotChance();
	
	public void setHeadshotChance(int headshotChance) {
		this.headshotChance = headshotChance;
	}
	
	public int getClip() {
		return clip;
	}

	public void setClip(int clip) {
		this.clip = clip;
	}

	public int getFullClip() {
		return fullClip;
	}

	public Bullet getBullet() {
		return bullet;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}

	public String shoot (Player player, Player enemy) {
		player.getPanel().getBullet().setMoving(true);
		String result = "";
		int random = (int)(Math.random() * 100);
		if(power != 0) {
			if(clip > 0) {
				result += player.getName() + " shot his " + player.getGun().getName() + "! ";
				if(random < accuracy) {
					player.getPanel().getBullet().setSpeed(10);
						if(random < headshotChance) {
							result += "It was a headshot!<br>";
							power *= 2;
						}
						if(enemy.getName().equals("Spy") && enemy.getHp() > 1 && enemy.getHp() - power <= 0) {
							result += (enemy.getName()  + " survived thanks to its Dead Ringer!<br>" + 
											  enemy.getName() + " lost " + (enemy.getHp() - 1) + " health and now has 1 health.");
							enemy.setHp(1);
						}else if(enemy.getHp() - power <= 0) {
							result += (enemy.getName() + " lost " + enemy.getHp() + " health and now has 0 health.");
							enemy.setHp(0);
						}else{
							enemy.setHp(enemy.getHp() - power);
							result += (enemy.getName() + " lost " + power + " health and now has " + enemy.getHp() + " health.");
						}
						clip --;
					}else{
						player.getPanel().getBullet().setSpeed(-10);
						result += (enemy.getName() + " avoided the attack!");
					}
				}else{
					player.getPanel().getBullet().setSpeed(10000);
					result += player.getGun().reload(player);
				}
			}else{
				player.getPanel().getBullet().setSpeed(10000);
				result += (player.getName() + " flinched and couldn't move!");
			}
			player.getGun().setPower();
			player.getGun().setAccuracy();
			player.getGun().setHeadshotChance();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return result;
		}
		
	
	public String reload(Character c) {
		String result  = "";
		if(clip != fullClip) {
			result += (c.getName() + " reloaded his gun!");
			setClip(fullClip);
		}else{
			result += (c.getName() + "tried to reload but already has full clip!");
		}
		return result;
	}
	
	public abstract String specialAttack(Player player, Player enemy);
	
	public String toString() {
		return String.format("%s, with %d%% accuracy, %d power, %d%% chance to headshot, and %d clip", getName(), accuracy, power, headshotChance, clip);
	}
}
