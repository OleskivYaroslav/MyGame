package ua.syavo.bubbles;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.*;

public class Menu extends IOException{


	private int buttonWidth;
	private int buttonHeight;
	private Color color1;
	private String s;
	private int transp = 0;

	public Menu() throws IOException {
		buttonHeight = 60;
		buttonWidth = 120;
		Score.SCORE.tryReadingElseCreate();
		color1 = Color.WHITE;
		s = "PLAY!";
	}

	public void update() {
		if (GamePanel.mouseX > GamePanel.Width / 2 - buttonWidth / 2
				&& GamePanel.mouseX < GamePanel.Width / 2 + buttonWidth / 2
				&& GamePanel.mouseY > GamePanel.Height / 2 - buttonHeight / 2
				&& GamePanel.mouseY < GamePanel.Height / 2 + buttonHeight / 2) {
			transp = 60;
			if (GamePanel.leftMouse) {
				GamePanel.state = GamePanel.States.PLAY;
			}
		} else {
			transp = 0;
		}
	}

	public void draw(Graphics2D g) {
		g.setColor(color1);
		g.setStroke(new BasicStroke(3));
		g.drawRect(GamePanel.Width / 2 - buttonWidth / 2, GamePanel.Height / 2 - buttonHeight / 2, buttonWidth,
				buttonHeight);

		g.setColor(new Color(255, 255, 255, transp));
		g.fillRect(GamePanel.Width / 2 - buttonWidth / 2, GamePanel.Height / 2 - buttonHeight / 2, buttonWidth,
				buttonHeight);

		g.setStroke(new BasicStroke(1));

		g.setColor(color1);
		g.setFont(new Font("Consolas", Font.BOLD, 40));
		long length = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
		g.drawString(s, (int) (GamePanel.Width / 2 - length / 2), (int) (GamePanel.Height / 2 + buttonHeight / 4));

        String S1="High Score: " + Score.SCORE.getHighScore();
        long length1 = (int) g.getFontMetrics().getStringBounds(S1, g).getWidth();
        g.drawString(S1, (int) (GamePanel.Width / 2 - length1 / 2), (int) (GamePanel.Height / 2 + buttonHeight / 4)-200);
	}
}
