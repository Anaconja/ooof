package Guns;

import javax.swing.ImageIcon;

import GameEntity.*;

public class DesertEagle extends Gun {

	public DesertEagle(int x, int y) {
		super("Desert Eagle", new ImageIcon("deagle.gif").getImage(), x, y, 40, 50, 5, 6, 6);
	}
	
	public void setHeadshotChance() {
		super.setHeadshotChance(5);
	}
	
	public void setPower() {
		super.setPower(40);
	}
	
	public void setAccuracy() {
		super.setAccuracy(50);
	}

	public String specialAttack(Player player, Player enemy) {
		String result = "";
		if(!player.isSpecialUsed()) {
			if(super.getPower() != 0) {
				result += player.getName() + " channeled his inner strength! <br>" + player.getName() + "'s accuracy was maxed out! ";
				player.setSpecialUsed(true);
				setAccuracy(100);
				setHeadshotChance(0);
				result += shoot(player, enemy);
			}else {
				player.getGun().setPower();
				return (player + " flinched and couldn't move! <br>");
			}
		}else return player.getName() + " is still charging his special attack! <br>";
		return result;
	}
}
