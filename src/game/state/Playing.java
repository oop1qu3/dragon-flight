package game.state;

import static game.util.Constant.EntityConstant.*;

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
import game.util.Timer.TimerState;

public class Playing extends State {

	private Timer spawnEnemiesTimer = new Timer(2.0, this, TimerState.END);
	private Timer spawnObstacleTimer = new Timer(7.0, this);

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
		entities.set(ENEMY, enemies);
		entities.set(BULLET, bullets);
		entities.set(EFFECT, effects);
		entities.set(OBSTACLE, obstacles);
		
		backgrounds.add(new Background());
		players.add(new Player());
		
	}

	@Override
	public void update(double dt) {
		
		super.update(dt);
		
		if (spawnEnemiesTimer.isOver()) {
			spawnEnemies();
			spawnEnemiesTimer.reset();
		}
		
		if (spawnObstacleTimer.isOver()) {
			spawnObstacle();
			spawnObstacleTimer.reset();
		}
		
		for (List<? extends Entity> entity : entities) {
			if (entity == null) continue;
			
			for (int i = entity.size()-1; i >= 0; i--) {
				entity.get(i).update(dt);
			}
		}
		
		if(isGameover()) {
			gsm.setState(new Gameover());
		}
		
	}
	
	public void spawnEnemies() {
		for(int i = 0 ; i < 5; i++) {
			enemies.add(new Enemy(78 * i));  // FIXME @YDH: 78 <- use Constant 
		}
	}
	
	public boolean isGameover() {
		boolean isAllDead = true;
		
		for (Player p : players) {
			if (!p.isDead()) {
				isAllDead = false;
			}
		}
		
		return isAllDead;
	}

	public void spawnObstacle() {
		obstacles.add(new Obstacle(players.get(0).getX()));
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
			
			for (int i = entity.size()-1; i >= 0; i--) {
				entity.get(i).render(g);
			}
		}
	}
	
}

