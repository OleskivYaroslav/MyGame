package ua.syavo.bubbles;

import java.awt.*;

public class Player {



	private double x;
	private double y;
	private int r;
	private double health;

	private double dx;
	private double dy;

	private int speed;

	private Color color1;


	public static boolean up;

	public static boolean down;
	public static boolean left;
	public static boolean right;
	public static boolean isFire;


	public Player() {
		x = GamePanel.Width / 2;
		y = GamePanel.Height / 2;

		r = 5;

		speed = 5;
		dx = 0;
		dy = 0;
		health = 3;

		color1 = Color.WHITE;

		up = false;
		down = false;
		right = false;
		left = false;
		isFire = false;
	}



	public void update() {

		if (up && y > r) {
			dy = -speed;
		}
		if (down && y < GamePanel.Height - r) {
			dy = speed;
		}
		if (left && x > r) {
			dx = -speed;
		}
		if (right && x < GamePanel.Width - r) {
			dx = speed;
		}
		if (up && left || up && right || down && left || down && right) {
			dy *= Math.sin(Math.toRadians(45));
			dx *= Math.cos(Math.toRadians(45));
		}
		y += dy;
		x += dx;
		dy = 0;
		dx = 0;

		if (isFire) {
			GamePanel.bullets.add(new Bullet());
		}

	}

	public void hit() {
		x = GamePanel.Width / 2;
		y = GamePanel.Height / 2;
		health--;
	}

	public boolean remove() {
		if (health <= 0) {
			return true;
		}
		return false;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
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

	public void draw(Graphics2D g) {

		g.setColor(color1);
		g.fillOval((int) x - r, (int) y - r, 2 * r, 2 * r);
		g.setStroke(new BasicStroke(3));
		g.setColor(color1.darker());
		g.drawOval((int) x - r, (int) y - r, 2 * r, 2 * r);
		g.setStroke(new BasicStroke(1));
	}

	public void drawLives(Graphics2D g) {
		String s = "lives " + (int)health;
		g.setColor(color1);
		g.setFont(new Font("Consolas", Font.BOLD, 15));
		g.drawString(s, 10, 30);
	}

}
