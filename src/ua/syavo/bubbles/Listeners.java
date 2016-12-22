package ua.syavo.bubbles;

import java.awt.event.*;

public class Listeners implements KeyListener, MouseListener, MouseMotionListener {

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP) {
			Player.up = true;
		}
		if (key == KeyEvent.VK_DOWN) {
			Player.down = true;
		}
		if (key == KeyEvent.VK_LEFT) {
			Player.left = true;
		}
		if (key == KeyEvent.VK_RIGHT) {
			Player.right = true;
		}
		if (key == KeyEvent.VK_SPACE) {
			Player.isFire = true;
		}

		if (key == KeyEvent.VK_ESCAPE) {
			GamePanel.state = GamePanel.States.MENU;
		}
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP) {
			Player.up = false;
		}
		if (key == KeyEvent.VK_DOWN) {
			Player.down = false;
		}
		if (key == KeyEvent.VK_LEFT) {
			Player.left = false;
		}
		if (key == KeyEvent.VK_RIGHT) {
			Player.right = false;
		}
		if (key == KeyEvent.VK_SPACE) {
			Player.isFire = false;
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public void mouseDragged(MouseEvent e) {
		GamePanel.mouseX = e.getX();
		GamePanel.mouseY = e.getY();
	}

	public void mouseMoved(MouseEvent e) {
		GamePanel.mouseX = e.getX();
		GamePanel.mouseY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			GamePanel.player.isFire = true;
			GamePanel.leftMouse = true;
		}

	}

	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			GamePanel.player.isFire = false;
			GamePanel.leftMouse = false;
		}
	}

}
