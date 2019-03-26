package Arkanoid;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import java.util.ArrayList;

import javax.swing.JPanel;

import Graphics.Assets;
import Menu.MenuState;

public class Arkanoid extends JPanel {

	public int width, height;
	private Ball ball;
	private Player player;

	public boolean isRunning = false, isPaused = false, won = true;
	public int tickrate = 60;
	private GameThread gameThread;
	public MenuState menuState;

	private Color[] rowColors = new Color[] { Color.gray, Color.red.darker(), Color.yellow.darker(),
			Color.blue.darker(), Color.pink, Color.green.darker() };
	public ArrayList<Block> blocks;
	public boolean menu = true;

	public long lastUpdate;

	public Arkanoid(int width, int height) {
		this.width = width;
		this.height = height;

		Assets.init();
		menuState = new MenuState(this);
		player = new Player(this);
		ball = new Ball(this);

		reset();

		this.setFocusable(true);

		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				if (!isPaused) {
					player.position.x = e.getX() - getWidth() / 2;
					if (isRunning == false) {
						ball.setBall(player);
					}
					repaint();
				}

			}
		});
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE && !isRunning)
					run();
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
					tooglePause();
				if (e.getKeyCode() == KeyEvent.VK_Q)
					quit();

			}
		});
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				if ((e.getX() >= 545 && e.getX() <= 700) && (e.getY() >= 350 && e.getY() <= 425) && !isRunning) {
					run();
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
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
		});
		repaint();

	}

	public void reset() {
		ball.setBall(player);
		ball.setBalls(3);
		createBlocks(6, 10);
	}

	public void onBlockBroken(Block b) {
		player.setScore(player.getScore() + 20);
	}

	public void run() {
		if (gameThread != null)
			if (gameThread.isAlive())
				gameThread.interrupt();
		reset();

		gameThread = new GameThread(this);
		gameThread.start();
	}

	public void tooglePause() {
		isPaused = !isPaused;

	}

	public void quit() {
		player.setScore(0);
		ball.setBall(player);
		isRunning = false;
	}

	public void createBlocks(int rows, int columns) {
		blocks = new ArrayList<Block>();
		int gap = 10;
		float w = (((float) width - 10) / columns) - 10;
		int h = 30;
		for (int x = 0; x < columns; x++) {
			for (int y = 0; y < rows; y++) {
				Block b = new Block();
				b.mainColor = rowColors[y % rowColors.length];
				b.position.x = (int) (x * (w + gap) + gap) - width / 2;
				b.position.y = (int) (y * (h + gap) + gap) - height / 2 + 10;
				b.height = (int) h;
				b.width = (int) w;
				blocks.add(b);
			}
		}
	}

	public void onGameOver(boolean won) {
	
		quit();

	}

	public void tick() {
		double deltatime = (System.nanoTime() - lastUpdate) / 1000000.0;
		if (blocks.isEmpty()) {
			onGameOver(true);
		}
		ball.tick(deltatime);

		repaint();
	}

	public void paint(Graphics g) {

		g.drawImage(Assets.background, 0, 0, null);

		if (!isRunning) {
			menuState.render(g);
		} else {
			g.setColor(Color.red);
			g.setFont(new Font("Arial", Font.BOLD, 30));

			g.drawString("Score:" + player.getScore(), 1000, 650);

			g.translate(width / 2, height / 2);

			player.render(g);
			ball.render(g);

			if (blocks != null) {
				for (int i = 0; i < blocks.size(); i++)
					blocks.get(i).render(g);
			}

		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
