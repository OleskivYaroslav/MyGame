package ua.syavo.bubbles;

import java.awt.*;

public class Enemy {

	// Fields
	private double x;
	private double y;
	private int r;
	private double speed;
	private double dx;
	private double dy;
	private double health;

	private Color color1;

	private int type;
	private int rank;
	// Constructor

	public Enemy(int type, int rank) {
		this.type = type;
		this.rank = rank;

		switch (type) {
		case 1:
			color1 = Color.GREEN;
			switch (rank) {
			case 1:

				x = Math.random() * GamePanel.Width;
				y = 0;
				speed = 5;
				r = 7;

				health = 2;
				dx = Math.sin(Math.toRadians(Math.random() * 360)) * speed;
				dy = Math.cos(Math.toRadians(Math.random() * 360)) * speed;
			}
		}

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

	// Functions
	public boolean remove() {
		if (health <= 0) {
			return true;
		}
		return false;
	}

	public void hit() {
		health--;
	}

	public void update() {
		x += dx;
		y += dy;
		if (x < 0 && dx < 0)
			dx = -dx;
		if (x > GamePanel.Width && dx > 0)
			dx = -dx;
		if (y < 0 && dy < 0)
			dy = -dy;
		if (y > GamePanel.Height && dy > 0)
			dy = -dy;
	}

	public void draw(Graphics2D g) {

		g.setColor(color1);
		g.fillOval((int) x - r, (int) y - r, 2 * r, 2 * r);
		g.setStroke(new BasicStroke(3));
		g.setColor(color1.darker());
		g.drawOval((int) x - r, (int) y - r, 2 * r, 2 * r);
		g.setStroke(new BasicStroke(1));
	}
}
