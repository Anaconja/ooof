package Guns;

import javax.swing.ImageIcon;

import GameEntity.*;

public class Ambassador extends Gun {
	public Ambassador(int x, int y) {
		super("Ambassador", new ImageIcon("amby.png").getImage(), x, y, 25, 60, 40, 6, 6);
	}
	
	public void setHeadshotChance() {
		super.setHeadshotChance(40);
	}
	
	public void setPower() {
		super.setPower(25);
	}
	
	public void setAccuracy() {
		super.setAccuracy(60);
	}

	public String specialAttack(Player player, Player enemy) {
		String result = "";
		if(!player.isSpecialUsed()) {
			if(super.getPower() != 0) {
				result += player.getName() + " focused his mind! <br>" + player.getName() + "'s accuracy rose! <br>" + player.getName() + "'s headshot chance was maxed out! <br>";
				player.setSpecialUsed(true);
				setHeadshotChance(100);
				result += shoot(player, enemy);
			}else{
				player.getGun().setPower();
				return (player.getName() + " flinched and couldn't move!<br>");
			}
		}else return player.getName() + " is still charging his special attack!<br>";
		return result;
	}
}
