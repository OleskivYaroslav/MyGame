package ua.syavo.bubbles;

import java.awt.*;

public class Bullet {


	private double x;
	private double y;
	private double bulletDX;
	private double bulletDY;

	private double distX;
	private double distY;
	private double dist;
	private int r;

	private int speed;

	private Color color1;


	public Bullet() {
		x = GamePanel.player.getX();
		y = GamePanel.player.getY();
		r = 2;

		speed = 10;

		distX = GamePanel.mouseX - x;
		distY = GamePanel.mouseY - y;
		dist = Math.sqrt(distX * distX + distY * distY);
		bulletDX = distX / dist*speed;
		bulletDY = distY / dist *speed;

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

	public void setR(int r) {
		this.r = r;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
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
