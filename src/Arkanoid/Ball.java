package Arkanoid;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Ball {

	private Arkanoid game;
	private int balls = 3;
	private int radius = 10;
	Point position = new Point(0, 0);
	Point movement = new Point(1, 1);
	float speed = 0.6f;

	public Ball(Arkanoid game) {

		this.game = game;

	}

	public void setBall(Player player) {
		this.position.setLocation(player.position.x - 10, player.position.y - 15);
	}

	public void tick(double deltatime) {

		position.translate((int) (movement.x * (speed * deltatime)), (int) (movement.y * (speed * deltatime)));
		if (Math.abs(position.x) >= Math.abs(game.width / 2 - 10))
			movement.x = -movement.x;
		if (position.y <= -game.height / 2)
			movement.y = -movement.y;
		if (position.y >= game.height / 2)
			onBallLost();
		Rectangle hitbox = new Rectangle(position.x - radius, position.y - radius, radius * 2, radius * 2);

		Point pv = game.getPlayer().bounceVector(hitbox);
		movement.x *= pv.x;
		movement.y *= pv.y;

		for (int i = 0; i < game.blocks.size(); i++) {
			Block b = game.blocks.get(i);
			pv = b.bounceVector(hitbox);
			movement.x *= pv.x;
			movement.y *= pv.y;
			if (pv.x < 0 || pv.y < 0) {

				game.onBlockBroken(b);
				game.blocks.remove(b);

			}

		}

	}

	public void onBallLost() {
		balls--;
		if (balls <= 0)
			game.onGameOver(false);
		else
			setBall(game.getPlayer());
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(position.x, position.y, radius * 2, radius * 2);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString("Balls:", game.width / 2-1200, game.height / 2-55);
		int posX = game.width/2 - 1050;
		for (int i = 0; i < balls; i++) {

			g.fillOval(posX, game.height/2-72, 10 * 2, 10 * 2);
			posX -= 25;
			// g.fillOval(-475 , 350, 10 * 2, 10 * 2);
			// g.fillOval(-450 , 350, 10 * 2, 10 * 2);

		}
	}

	public int getBalls() {
		return balls;
	}

	public void setBalls(int balls) {
		this.balls = balls;
	}
}
