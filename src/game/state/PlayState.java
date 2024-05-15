package game.state;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import game.entity.Bullet;
import game.entity.Enemy;
import game.entity.Player;
import game.map.Background;
import game.util.KeyHandler;
import game.util.MouseHandler;

public class PlayState extends GameState {

	private Background background;
	private Player player;
	private ArrayList<Enemy> enemies;

	// bullet
	private double elapsed = 0;
	private double bulletPeriod = 0.08; // 80ms
	private Bullet bullet;
	private CopyOnWriteArrayList<Bullet> bullets = new CopyOnWriteArrayList<>();


	public PlayState() {
		super();
		background = new Background();
		player = new Player();

		// @JW
		enemies = new ArrayList<Enemy>();
		spawn();
	}

	@Override
	public void update(double dt) {
		background.move(dt);
		player.move(dt);

		// @JW : enemies 업데이트 함수
		for(int i = 0; i < enemies.size(); i++)
		{
			if (enemies.get(i).isAlive() || !(enemies.get(i).isOut()))
				enemies.get(i).move(dt);
			else
			{
				enemies.clear();
				spawn();
			}
		}

		fireBullet(dt);

		for (Bullet bullet: bullets) {
			bullet.move();

			if (bullet.isOut()) {
				bullets.remove(bullet);
			}
		}

	}

	// @JW
	public void spawn() {
		int x = 0;

		for(int i = 0 ; i < 5; i++)
		{
			Enemy tempE = new Enemy(x);
			enemies.add(tempE);
			x += 78;
		}
	}

	@Override
	public void input(KeyHandler key, MouseHandler mouse) {
		player.input(key, mouse);
	}
	
	@Override
	public void render(Graphics2D g) {
		background.render(g);
		player.render(g);

		for(Bullet bullet: bullets) {
			bullet.render(g);
		}

		// @JW
		for(Enemy i : enemies)
			i.render(g);
	}

	public void fireBullet(double dt) {
		elapsed = elapsed + dt;
		if (elapsed > bulletPeriod) {
			Bullet bullet = new Bullet((int)player.getX());
			bullets.add(bullet);

			elapsed = 0;
		}
	}



}

