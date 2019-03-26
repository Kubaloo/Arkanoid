package Menu;

import java.awt.Color;
import java.awt.Graphics;



import Arkanoid.Arkanoid;
import Graphics.Assets;

public class MenuState {

	private Arkanoid game;

	

	public MenuState(Arkanoid game) {
		this.game = game;
		
	}

	public void render(Graphics g) {

		g.drawImage(Assets.menuBackground, 0, 0, null);
		g.drawImage(Assets.startButton, game.width / 2 - 95, game.height / 2, null);

	}

}
