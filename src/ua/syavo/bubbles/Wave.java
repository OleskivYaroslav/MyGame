package ua.syavo.bubbles;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Wave {

	private int waveNumber;
	private long waveTimer;
	private long waveDelay;
	private long waveTimerDiff;

	private int waveMultiplayer;

	private String waveText;

	public Wave() {
		waveNumber = 1;
		waveMultiplayer = 5;

		waveDelay = 5000;
		waveTimer = 0;
		waveTimerDiff = 0;

		waveText = "Wave - ";

	}


	public void createEnemies() {
		int enemyCount = waveNumber * waveMultiplayer;

		while (enemyCount > 0) {
			int type = 1;
			int rank = 1;
			GamePanel.enemies.add(new Enemy(type, rank));
			enemyCount -= type * rank;
		}
		waveNumber++;
	}

	public void update() {
		if (GamePanel.enemies.size() == 0 && waveTimer == 0) {
			waveTimer = System.nanoTime();
		}
		if (waveTimer > 0) {
			waveTimerDiff += (System.nanoTime() - waveTimer) / 1000000;
			waveTimer = System.nanoTime();
		}
		if (waveTimerDiff > waveDelay) {
			createEnemies();
			waveTimer = 0;
			waveTimerDiff = 0;
		}
	}

	public boolean showWave() {
		if (waveTimer != 0) {
			return true;
		} else {
			return false;
		}
	}

	public void draw(Graphics2D g) {
		double divider = waveDelay / 180;
		double alpha = waveTimerDiff / divider;

		alpha = 255 * Math.sin(Math.toRadians(alpha));
		if (alpha < 0)
			alpha = 0;
		if (alpha > 255)
			alpha = 255;

		g.setFont(new Font("consolas", Font.PLAIN, 20));
		g.setColor(new Color(255, 255, 255, (int) alpha));
		String s = waveText + waveNumber;
		long length = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
		g.drawString(s, (GamePanel.Width / 2) - length / 2, (GamePanel.Height / 2)+20);
	}

}
