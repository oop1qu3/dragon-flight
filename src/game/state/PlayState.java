package game.state;

import java.awt.Graphics;

import game.entity.Player;
import game.util.KeyHandler;
import game.util.MouseHandler;

public class PlayState extends GameState {

	private Player player;

	public PlayState() {
		super();
		player = new Player();
	}

	@Override
	public boolean stateUpdate() {
		player.move();
		//
		//
		//

		return true;
	}

	@Override
	public void input(KeyHandler key, MouseHandler mouse) {
		player.input(key, mouse);
	}
	
	@Override
	public void render(Graphics g) {
		player.render(g);
	}



}

