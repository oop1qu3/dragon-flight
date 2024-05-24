package game.state;

import java.awt.Graphics2D;
import java.util.ArrayList;

import game.entity.Bullet;
import game.entity.Enemy;
import game.entity.Obstacle;
import game.entity.Player;
import game.map.Background;
import game.util.KeyHandler;
import game.util.MouseHandler;


public class PlayState extends GameState {

	private Background background;
	private Player player;
	private ArrayList<Enemy> enemies;
	private ArrayList<Bullet> bullets;
	private ArrayList<Obstacle> obstacles;

	// @JW : enemies spawn related, milliseconds.
	private final int SPAWN_DELAY_E = 3000;
	private long lastSpawnTime_E;

	// @JW : obstacles spawn related, milliseconds.
	private final int SPAWN_DELAY_O = 3000;
	private long lastSpawnTime_O;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		background = new Background();
		player = new Player(this);

		enemies = new ArrayList<Enemy>();		// @JW : Enemy 객체에 state 대입은 아래 spawn 메소드에서
		spawnE();

		bullets = new ArrayList<Bullet>();

		obstacles = new ArrayList<Obstacle>();
		spawnO();
	}

	@Override
	public void update(double dt) {
		background.move(dt);

		player.move(dt);
		player.fire(dt);
		player.checkCollision(dt); // @YCW: pass dt to this for checking elapsed invincible time

		updateE(dt);	// Enemy
		updateB(dt);	// Bullet
		updateO(dt);	// Obstacle
	}

	public void spawnE() {
		lastSpawnTime_E = System.currentTimeMillis();

		int x = 0;
		for(int i = 0 ; i < 5; i++)
		{
			Enemy tempE = new Enemy(x, this);
			enemies.add(tempE);
			x += 78;
		}
	}
	public void updateE(double dt) {
		if (System.currentTimeMillis() - lastSpawnTime_E >= SPAWN_DELAY_E){

			if(enemies.isEmpty())
				spawnE();

			enemies.clear();
			spawnE();
		}
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).enemyHit();

			if (enemies.get(i).isAlive())
				enemies.get(i).move(dt);
			else
				enemies.remove(i);
		}
	}

	public void updateB(double dt) {
		for (int i = 0; i < bullets.size();) {
			Bullet bullet = bullets.get(i);
			bullet.move(dt);

			if (bullet.isOut()) {
				bullets.remove(bullet);
				continue;
			}
			i++;
		}
	}

	public void spawnO() {
		lastSpawnTime_O = System.currentTimeMillis();

		int x = 0;
		for (int i = 0; i < 5; i++)
		{
			if((int)(5 * Math.random()) == 1) {
				Obstacle tempO = new Obstacle(x);
				obstacles.add(tempO);
			}

			x += 78;
		}
	}
	public void updateO(double dt) {
		if (System.currentTimeMillis() - lastSpawnTime_O >= SPAWN_DELAY_O) {
			obstacles.clear();
			spawnO();
		}

		for(Obstacle o : obstacles)
			if(o != null)
				o.move(dt);
	}

	@Override
	public void input(KeyHandler key, MouseHandler mouse) {
		player.input(key, mouse);
	}

	@Override
	public void render(Graphics2D g) {
		background.render(g);
		player.render(g);

		for(Bullet b: bullets)
			b.render(g);

		for(Enemy e : enemies)
		{
			if (e != null)
				e.render(g);
		}

		for(Obstacle o : obstacles)
		{
			if (o != null)
				o.render(g);
		}
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}

	public ArrayList<Obstacle> getObstacles(){
		return obstacles;
	}

}

