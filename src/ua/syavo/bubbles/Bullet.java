package ua.syavo.bubbles;

import java.awt.*;

public class Bullet {


	private double x;
	private double y;
	private double bulletDX;
	private double bulletDY;

	private double distanceX;
	private double distanceY;
	private double distance;
	private int r;

	private int speed;

	private Color color1;


	public Bullet() {
		x = GamePanel.player.getX();
		y = GamePanel.player.getY();
		r = 2;

		speed = 10;

		distanceX = GamePanel.mouseX - x;
		distanceY = GamePanel.mouseY - y;
		distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
		bulletDX = distanceX / distance*speed;
		bulletDY = distanceY / distance *speed;

		color1 = Color.WHITE;
	}



	public boolean remove() {
		if (y < 0)
			return true;

		return false;
	}

	public int getR() {
		return r;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void update() {
		y += bulletDY;
		x += bulletDX;
	}

	public void draw(Graphics2D g) {

		g.setColor(color1);
		g.fillOval((int) x, (int) y, r, r * 2);

	}
}
