package mainGame;

import java.util.ArrayList;

import GameEntity.Player;

public class GameData {
	private GamePanel panel;
	private Player player;
	private Player computer;
	private int gamesWon = 0;
	
	private int turnsPassed = 0;
	private int eTurnsPassed = 0;
	
	private ArrayList<String> log = new ArrayList<String>();
	
	public Player getPlayer() {
		return player;
	}
	
	public Player getComputer() {
		return computer;
	}

	public int getGamesWon() {
		return gamesWon;
	}

	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}

	public ArrayList<String> getLog() {
		return log;
	}

	public void setLog(ArrayList<String> log) {
		this.log = log;
	}

	public int getTurnsPassed() {
		return turnsPassed;
	}

	public void setTurnsPassed(int turnsPassed) {
		this.turnsPassed = turnsPassed;
	}

	public int geteTurnsPassed() {
		return eTurnsPassed;
	}

	public void seteTurnsPassed(int eTurnsPassed) {
		this.eTurnsPassed = eTurnsPassed;
	}

	public String listToString(ArrayList<String> list) {
		String result = "<html>";
		if(list.size() < 5) {
			for(int i = 0; i < list.size(); i ++) {
				result += list.get(i) + "<br>";
			}
		}else{
			for(int i = list.size() - 5; i < list.size(); i ++) {
				result += list.get(i) + "<br>";
			}
		}
		return result;
	}
	
	public String computerMove() {
		int random = (int)(Math.random() * 3);
		if(random > 1) {
			computer.getPanel().getBullet().setWhoShot("computer");
			return computer.getGun().shoot(computer, player);
		}
		else if (random > 2) return computer.getGun().reload(computer);
		else return computer.getGun().specialAttack(computer, player);
	}
	
	public void computerChoose() {
		int random = (int)(Math.random() * 3);
		if(random > 1)computer.makeMcCree(true);
		else if (random > 2) computer.makeSpy(true);
		else computer.makeTerrorist(true);
	}
	
	public GameData() {
		player = new Player(panel);
		computer = new Player(panel);
		computerChoose();
	}
	
	public void registerView(GamePanel panel) {
		this.panel = panel;
		player.setPanel(panel);
		computer.setPanel(panel);
	}
}
