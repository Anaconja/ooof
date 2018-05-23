package Guns;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import GameEntity.*;

public class Peacekeeper extends Gun {

	public Peacekeeper(int x, int y) {
		super("Peacekeeper", new ImageIcon("peace.png").getImage(), x, y, 30, 60, 15, 6, 6);
	}
	
	public void setHeadshotChance() {
		super.setHeadshotChance(15);
	}
	
	public void setPower() {
		super.setPower(30);
	}
	
	public void setAccuracy() {
		super.setAccuracy(60);
	}

	public String specialAttack(Player player, Player enemy) {
		String result = "";
		if(!player.isSpecialUsed()) {
			if(super.getPower() != 0) {
				result += player.getName() + " threw his flashbang! <br>" + player.getName() + " fanned his hammer! <br>";
				player.setSpecialUsed(true);
				enemy.getGun().setPower(0);
				for(int i = 0; i < super.getClip(); i ++) {
					super.setAccuracy(45);
					super.setHeadshotChance(0);
					Timer delay  = new Timer();
					delay.schedule(new TimerTask() {
						public void run() {
							String meme  = "";
							meme += shoot(player, enemy);
						}
					}, 500);
					if(enemy.getHp() <= 0) break;
				}
			}else{
				player.getGun().setPower();
				return (player.getName() + " flinched and couldn't move!");
			}
		}else return player.getName() + " is still charging his special attack!";
		return result;
	}
}
