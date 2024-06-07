package game.state;

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

public abstract class State {
	
	protected static GamestateManager gsm = GamestateManager.getInstance();;
	
	protected static List<List<? extends Entity>> entities;
	
	protected static List<Background> backgrounds;
	protected static List<Player> players;
	protected static List<Enemy> enemies;
	protected static List<Bullet> bullets;
	protected static List<Effect> effects;
	protected static List<Obstacle> obstacles;
	
	protected static List<Timer> timers = new ArrayList<Timer>();
	
	public List<List<? extends Entity>> getEntities() {
		return entities;
	}
	
	public List<Background> getBackgrounds() {
		return backgrounds;
	}
	
	public List<Player> getPlayers() {
		return players;
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

	public List<Bullet> getBullets() {
		return bullets;
	}

	public List<Effect> getEffects() {
		return effects;
	}

	public List<Obstacle> getObstacles() {
		return obstacles;
	}
	
	public List<Timer> getTimers() {
		return timers;
	}
	
	public State() {}

	public void update(double dt) {
		for (int i = timers.size()-1; i >= 0; i--) {
			timers.get(i).update(dt);
		}
	}
	
	public abstract void input(KeyHandler key, MouseHandler mouse);
	public abstract void render(Graphics2D g);

}
