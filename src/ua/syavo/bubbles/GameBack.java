package ua.syavo.bubbles;

import java.awt.*;

public class GameBack {

	private Color color;

	// Constructor
	public GameBack() {
		color = Color.BLUE;
	}

	// Functions

	public void update() {

	}

	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect(0, 0, GamePanel.Width, GamePanel.Height);
	}

}
