package game.state;

import java.awt.Graphics2D;

import game.entity.Enemy;
import game.entity.Player;
import game.map.Background;
import game.util.KeyHandler;
import game.util.MouseHandler;

public class PlayState extends GameState {

	private Background background;
	private Player player;
	private Enemy enemy;
	// TODO @YDH : private Bullet bullet;

	public PlayState() {
		super();
		background = new Background();
		player = new Player();
		enemy = new Enemy();
		// TODO @YDH : bullet = new Bullet();
	}

	@Override
	public void update(double dt) {
		background.move(dt);
		player.move(dt);
		enemy.move(dt);
		// TODO @YDH : bullet.move();
	}

	@Override
	public void input(KeyHandler key, MouseHandler mouse) {
		player.input(key, mouse);
	}
	
	@Override
	public void render(Graphics2D g) {
		background.render(g);
		player.render(g);
		enemy.render(g);
		// TODO @YDH : bullet.render(g);
	}

}

