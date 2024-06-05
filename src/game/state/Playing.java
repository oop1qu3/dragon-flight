package game.state;

import static game.util.Constant.EntityConstant.*;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.entity.Bullet;
import game.entity.Enemy;
import game.entity.Entity;
import game.entity.Obstacle;
import game.entity.Player;
import game.entity.effect.Effect;
import game.entity.map.Background;
import game.util.KeyHandler;
import game.util.MouseHandler;

public class Playing extends State {

	// @JW : enemies spawn related, milliseconds.
	private final int SPAWN_DELAY_E = 3000;
	private long lastSpawnTime_E;

	// @JW : obstacle trigger, milliseconds.
	private final int SPAWN_DELAY_O = 7000;
	private long lastSpawnTime_O;

	public Playing() {
		entities = new ArrayList<>();
		
		for (int i = 0; i < 6; i++) {
			entities.add(null);
		}
		
		backgrounds = new ArrayList<Background>();
		players = new ArrayList<Player>();
		enemies = new ArrayList<Enemy>();
		bullets = new ArrayList<Bullet>();
		effects = new ArrayList<Effect>();
		obstacles = new ArrayList<Obstacle>();
		
		entities.set(BACKGROUND, backgrounds);
		entities.set(PLAYER, players);
		//entities.set(ENEMY, enemies);
		//entities.set(BULLET, bullets);
		entities.set(EFFECT, effects);
		//entities.set(OBSTACLE, obstacles);
		
		backgrounds.add(new Background());
		players.add(new Player());
	}

	@Override
	public void update(double dt) {
//		background.move(dt);
//
//		player.move(dt);
//		player.fire(dt);
//		player.checkCollision(dt); // @YCW: pass dt to this for checking elapsed invincible time
//
		for (List<? extends Entity> entity : entities) {
			if (entity == null) continue;
			for (int i = entity.size()-1; i >= 0; i--) {
				entity.get(i).update(dt);
			}
		}
		
		updateEnemies(dt);	
		updateBullets(dt);	
		updateObstacle(dt);	
		
		/*	@YDH : 
		 *	for each문은 remove 시 ConcurrentModificationException 에러 발생
		 *	따라서 for문을 사용하되, remove 시에 인덱스가 당겨지므로
		 *	size-1부터 0까지 감소하는 방향으로 순회하면 된다. 
		 */
		
		if(isGameover()) {
			gsm.state = new Gameover();
		}
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
			
			e.update(dt);
			
			if (e.isFinished()) {
				// @YDH : 최적화 작업 ( O(n) -> O(1) )
				Collections.swap(effects, i, effects.size() - 1);
				effects.remove(effects.size()-1);
			}
		}
	}

	public void updateBullets(double dt) { 		
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

		obstacles.add(new Obstacle(players.get(0).getX()));
	}
	
	public void updateObstacle(double dt) {
		if (System.currentTimeMillis() - lastSpawnTime_O >= SPAWN_DELAY_O) {

			spawnObstacle();
		}

		for (Obstacle o : obstacles) {
			o.move(dt);
		}
		
	}

	@Override
	public void input(KeyHandler key, MouseHandler mouse) {
		for (Player p : players) {
			p.input(key, mouse);
		}
	}

	@Override
	public void render(Graphics2D g) {
		for (List<? extends Entity> entity : entities) {
			if (entity == null) continue;
			for (Entity e : entity) {
				e.render(g);
			}
		}

		for (int i = bullets.size() - 1; i >= 0; i--) {
			bullets.get(i).render(g);
		}
		
		for (int i = enemies.size() - 1; i >= 0; i--) {
			enemies.get(i).render(g);
		}
		
		for (int i = obstacles.size() - 1; i >= 0; i--) {
			obstacles.get(i).render(g);
		}
		
	}
	
	// @YCW: added below function for changing state to EndState when the player is dead
	public boolean isGameover() {
		boolean isAllDead = true;
		
		for (Player p : players) {
			if (!p.isDead()) {
				isAllDead = false;
			}
		}
		
		return isAllDead;
	}
	
}

