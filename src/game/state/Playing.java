package game.state;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

import game.entity.Bullet;
import game.entity.Enemy;
import game.entity.Obstacle;
import game.entity.Player;
import game.entity.effect.Effect;
import game.entity.map.Background;
import game.util.KeyHandler;
import game.util.MouseHandler;


public class Playing extends State {

	private Background background;
	private Player player;
	private ArrayList<Enemy> enemies;
	private Obstacle obstacle;
	private ArrayList<Bullet> bullets;
	private ArrayList<Effect> effects;

	// @JW : enemies spawn related, milliseconds.
	private final int SPAWN_DELAY_E = 3000;
	private long lastSpawnTime_E;

	// @JW : obstacle trigger, milliseconds.
	private final int SPAWN_DELAY_O = 7000;
	private long lastSpawnTime_O;

	public Playing(GamestateManager gsm) {
		super(gsm);
		background = new Background();
		player = new Player(this);

		enemies = new ArrayList<Enemy>();		// @JW : Enemy 객체에 state 대입은 아래 spawn 메소드에서
		bullets = new ArrayList<Bullet>();
		effects = new ArrayList<Effect>();
	}

	@Override
	public void update(double dt) {
		background.move(dt);

		player.move(dt);
		player.fire(dt);
		player.checkCollision(dt); // @YCW: pass dt to this for checking elapsed invincible time

		updateEnemies(dt);	
		updateBullets(dt);	
		updateObstacle(dt);	
		
		isGameOver(); // @YCW: check isGameOver for changing states
	}

	public void spawnEnemies() {
		lastSpawnTime_E = System.currentTimeMillis();

		int x = 0;
		for(int i = 0 ; i < 5; i++)
		{
			enemies.add(new Enemy(x, this));
			x += 78;
		}
	}
	public void updateEnemies(double dt) {
		if (System.currentTimeMillis() - lastSpawnTime_E >= SPAWN_DELAY_E){
			if(enemies.isEmpty())
				spawnEnemies();

			enemies.clear();
			spawnEnemies();
		}
		
		for (int i = enemies.size() - 1; i >= 0; i--) {
			enemies.get(i).enemyHit();

			if (enemies.get(i).isAlive()) {
				enemies.get(i).move(dt);
			} else {
				enemies.get(i).dead();
				enemies.remove(i);
			}
		}
		
		for (int i = effects.size() - 1; i >= 0; i--) {
			Effect e = effects.get(i);
			
			e.play(dt);
			
			if (e.isFinished()) {
				// @YDH : 최적화 작업 ( O(n) -> O(1) )
				Collections.swap(effects, i, effects.size() - 1);
				effects.remove(effects.size()-1);
			}
		}
	}

	public void updateBullets(double dt) {
		/*	@YDH : 
		 *	for each문은 remove 시 ConcurrentModificationException 에러 발생
		 *	따라서 for문을 사용하되, remove 시에 인덱스가 밀려나므로
		 *	size-1부터 0까지 감소하는 방향으로 순회하면 된다. 
		 */ 		
	    for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet b = bullets.get(i);
            b.move(dt);
            
            if (b.isOut()) {
				bullets.remove(i);
			}
	    }
	}

	public void spawnObstacle() {
		lastSpawnTime_O = System.currentTimeMillis();

		obstacle = new Obstacle(this, player.getX());
	}
	public void updateObstacle(double dt) {
		if (System.currentTimeMillis() - lastSpawnTime_O >= SPAWN_DELAY_O) {

			spawnObstacle();
		}

		obstacle.move(dt);
	}

	@Override
	public void input(KeyHandler key, MouseHandler mouse) {
		player.input(key, mouse);
	}

	@Override
	public void render(Graphics2D g) {
		background.render(g);
		player.render(g);

		for (int i = bullets.size() - 1; i >= 0; i--) {
			bullets.get(i).render(g);
		}
		
		for (int i = enemies.size() - 1; i >= 0; i--) {
			enemies.get(i).render(g);
		}
		
		obstacle.render(g);
		
		for (int i = effects.size() - 1; i >= 0; i--) {
			effects.get(i).render(g);
		}
	}
	
	// @YCW: added below function for changing state to EndState when the player is dead
	public void isGameOver() {
		if (player.isDead() == true) {
			gsm.setState(new Gameover(gsm));
		}
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}

	public Obstacle getObstacle(){
		return obstacle;
	}

	public Player getPlayer() {
		return player;
	}
	
	public ArrayList<Effect> getEffects(){
		return effects;
	}
}

