package game.state;

import static game.util.Constant.EntityConstant.BACKGROUND;
import static game.util.Constant.EntityConstant.EFFECT;
import static game.util.Constant.EntityConstant.ENEMY;
import static game.util.Constant.EntityConstant.PLAYER;

import java.awt.Graphics2D;
import java.util.ArrayList;
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
import game.util.Timer;

public class Playing extends State {

	private Timer spawnEnemiesTimer;

	// @JW : obstacle trigger, milliseconds.
	private final int SPAWN_DELAY_O = 7000;
	private long lastSpawnTime_O;

	public Playing() {
		
		spawnEnemiesTimer = new Timer(3.0);
		
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
		entities.set(ENEMY, enemies);
		//entities.set(BULLET, bullets);
		entities.set(EFFECT, effects);
		//entities.set(OBSTACLE, obstacles);
		
		backgrounds.add(new Background());
		players.add(new Player());
	}

	@Override
	public void update(double dt) {
		
		if (spawnEnemiesTimer.isOver()) {
			spawnEnemies();
		}
		
		for (List<? extends Entity> entity : entities) {
			if (entity == null) continue;
			for (int i = entity.size()-1; i >= 0; i--) {
				entity.get(i).update(dt);
			}
		}
		
		updateBullets(dt);	
		updateObstacle(dt);
		
		if(isGameover()) {
			gsm.state = new Gameover();
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
	
	public void spawnEnemies() {
		int x = 0;
		
		for(int i = 0 ; i < 5; i++) {
			enemies.add(new Enemy(x));
			x += 78;
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
	
}

