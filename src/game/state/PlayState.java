package game.state;

import java.awt.Graphics2D;
import java.util.ArrayList;

import game.effect.Effect;
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
	private ArrayList<Bullet> bullets;
	private ArrayList<Effect> effects;

	// @JW : spawn related, milliseconds.
	private final int SPAWN_DELAY = 3000;
	private long lastSpawnTime;

	public PlayState(GameStateManager gsm) {
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
		
	    for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);
            bullet.move(dt);
            
            if (bullet.isOut()) {
				bullets.remove(i);
			}
	    }

		// @JW : enemies related
		if (System.currentTimeMillis() - lastSpawnTime >= SPAWN_DELAY) {
			if(enemies.isEmpty())
				spawn();

			enemies.clear();
			spawn();
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
		
		for (Effect e : effects)
			e.play(dt);
		
		for (int i = effects.size() - 1; i >= 0; i--) {
			Effect e = effects.get(i);
			
			if (e.isFinished()) {
				effects.remove(i);
			}
		}
	}

	public void spawn() {
		lastSpawnTime = System.currentTimeMillis();

		int x = 0;
		for(int i = 0 ; i < 5; i++)
		{
			Enemy tempE = new Enemy(x, this);
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

		for(Bullet b: bullets)
			b.render(g);

		for(Enemy e : enemies)
			e.render(g);
		
		for (Effect e : effects) 
			e.render(g);
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}
	
	public ArrayList<Effect> getEffects(){
		return effects;
	}
}

