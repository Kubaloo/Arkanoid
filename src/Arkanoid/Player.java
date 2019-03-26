package Arkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Player {
	private Arkanoid game;
	int height = 10;
	int width = 100;
	private int score = 0;
	public Point position = new Point(0, 0);

	public Player(Arkanoid game) {
		this.game = game;
		position = new Point(0, game.height / 2 - height - 75);
	}

	public Point bounceVector(Rectangle hitbox) {
		Point p = new Point(1, 1);

		Rectangle hb_t = new Rectangle(position.x - width / 2, (position.y - height / 2), width, height / 3);

		Rectangle hb_b = new Rectangle(position.x - width / 2, (position.y + height / 2) - height / 3, width,
				height / 3);

		Rectangle hb_l = new Rectangle(position.x - width / 2, position.y + height / 2, width / 10, height);

		Rectangle hb_r = new Rectangle((position.x + width / 2) - width / 3, (position.y - height / 2) - width / 10,
				width / 10, height);

		if (hb_t.intersects(hitbox) || hb_b.intersects(hitbox))
			p.y = -1;
		if (hb_r.intersects(hitbox) || hb_l.intersects(hitbox))
			p.x = -1;

		return p;

	}

	public void render(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(position.x - width / 2, position.y - height / 2, width, height);
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
