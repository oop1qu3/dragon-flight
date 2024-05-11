package game.state;

import java.awt.Graphics2D;
import java.util.ArrayList;

import game.entity.Enemy;
import game.entity.Player;
import game.map.Background;
import game.util.KeyHandler;
import game.util.MouseHandler;

public class PlayState extends GameState {

	private Background background;
	private Player player;
	// @YDH TODO private Enemy enemy;
	// @YDH TODO private Bullet bullet;




	public PlayState() {
		super();
		background = new Background();
		player = new Player();
		// @YDH TODO enemy = new Enemy();
		// @YDH TODO bullet = new Bullet();
	}

	@Override
	public void update(double dt) {
		background.move(dt);
		player.move(dt);
		// @YDH TODO enemy.move();
		// @YDH TODO bullet.move();
	}

	@Override
	public void input(KeyHandler key, MouseHandler mouse) {
		player.input(key, mouse);
	}
	
	@Override
	public void render(Graphics2D g) {
		background.render(g);
		player.render(g);
		// @YDH TODO enemy.render(g);
		// @YDH TODO bullet.render(g);
	}

}

