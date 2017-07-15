package ua.syavo.bubbles;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {


	public static int Width = 600;
	public static int Height = 600;



	public static int mouseX;
	public static int mouseY;
	public static boolean leftMouse;

	private Thread thread;

	private BufferedImage image;
	private Graphics2D g;

	public static enum States {
		MENU, PLAY, END
	}

	public static States state = States.MENU;

	private int FPS;
	private double millisToFPS;
	private long timerFPS;
	private int sleepTime;

	public static GameBack backGround;
	public static Player player;
	public static ArrayList<Bullet> bullets;
	public static ArrayList<Enemy> enemies;
	public static Wave wave;
	public static Menu menu;
	public static GameEnd endGame;


	public GamePanel() {
		super();

		setPreferredSize(new Dimension(Width, Height));
		setFocusable(true);
		requestFocus();

		addKeyListener(new Listeners());
		addMouseMotionListener(new Listeners());
		addMouseListener(new Listeners());
	}

	public void start() {
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		FPS = 30;
		millisToFPS = 1000 / FPS;
		sleepTime = 0;

		image = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		leftMouse = false;
		backGround = new GameBack();
		player = new Player();
		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		wave = new Wave();
		try {
			menu = new Menu();
		} catch (IOException e) {
			e.printStackTrace();
		}
		endGame = new GameEnd();

		Toolkit kit = Toolkit.getDefaultToolkit();
		BufferedImage buffered = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g3 = (Graphics2D) buffered.getGraphics();
		g3.setColor(new Color(255, 255, 255));
		g3.drawOval(0, 0, 4, 4);
		g3.drawLine(2, 0, 2, 4);
		g3.drawLine(0, 2, 4, 2);
		Cursor cursor = kit.createCustomCursor(buffered, new Point(3, 3), "myCursor");
		g3.dispose();

		while (true) {

			this.setCursor(cursor);

			timerFPS = System.nanoTime();
			if (state.equals(States.END)) {
				backGround.update();
				backGround.draw(g);
				endGame.update();
				endGame.draw(g);
				gameDraw();
				player = new Player();
				enemies = new ArrayList<Enemy>();
				wave = new Wave();
			}
			if (state.equals(States.MENU)) {
				backGround.update();
				backGround.draw(g);
				menu.update();
				menu.draw(g);
				gameDraw();
			}
			if (state.equals(States.PLAY)) {
				gameUpdate();
				gameRender();
				gameDraw();
			}

			timerFPS = (System.nanoTime() - timerFPS) / 1000000;
			if (millisToFPS > timerFPS) {
				sleepTime = (int) (millisToFPS - timerFPS);
			} else {
				sleepTime = 1;
			}
			try {
				thread.sleep(sleepTime);

			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			timerFPS = 0;
			sleepTime = 1;
		}

	}

	public void gameUpdate() {

		backGround.update();
		player.update();


		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update();
		}

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
		}

		bulletsEnemiesCollide();
		playerEnemyCollides();

		wave.update();

	}


	private void bulletsEnemiesCollide(){
		for (int i = 0; i < enemies.size(); i++) {
			Enemy en = enemies.get(i);
			double ex = en.getX();
			double ey = en.getY();

			for (int j = 0; j < bullets.size(); j++) {
				Bullet b = bullets.get(j);
				double bx = b.getX();
				double by = b.getY();
				double dx = ex - bx;
				double dy = ey - by;
				double dist = Math.sqrt(dx * dx + dy * dy);
				if (dist <= en.getR() + b.getR()) {
					en.hit();
					bullets.remove(j);
					j--;
					if (en.remove()) {
						enemies.remove(i);
						Score.SCORE.scoreOfGame++;
						i--;
						break;
					}

				}
			}

		}
	}

	private void playerEnemyCollides(){
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			double ex = e.getX();
			double ey = e.getY();

			double px = player.getX();
			double py = player.getY();
			double dx = ex - px;
			double dy = ey - py;
			double dist = Math.sqrt(dx * dx + dy * dy);
			if (dist <= e.getR() + player.getR()) {
				e.hit();
				player.hit();
				if(player.remove()){
					GamePanel.state = GamePanel.States.END;
					try {
						Score.SCORE.updatingScore();

					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (e.remove()) {
					enemies.remove(i);
					i--;
					break;
				}
			}
		}
	}


	public void gameRender() {

		backGround.draw(g);

		player.draw(g);


		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g);
			boolean remove = bullets.get(i).remove();
			if (remove) {
				bullets.remove(i);
				i--;
			}
		}

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}
		if (wave.showWave()) {
			wave.draw(g);
		}
		player.drawLives(g);
	}

	private void gameDraw() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
}
