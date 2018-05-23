package GameEntity;

import java.awt.Image;

public abstract class GameEntity {
	private String name;
	private Image image;
	private int x;
	private int y;
	
	public GameEntity(String name, Image image, int x, int y) {
		this.name = name;
		this.image = image;
		this.x = x;
		this.y = y;
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

	public String toString() {
		return name;
	}
}
