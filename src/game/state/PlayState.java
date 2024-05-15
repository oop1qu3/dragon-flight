package game.state;

import java.awt.Graphics2D;
<<<<<<< main
import java.util.ArrayList;
=======
import java.util.concurrent.CopyOnWriteArrayList;
>>>>>>> main

import game.entity.Bullet;
import game.entity.Enemy;
import game.entity.Player;
import game.entity.Bullet;
import game.map.Background;
import game.util.KeyHandler;
import game.util.MouseHandler;

public class PlayState extends GameState {

	private Background background;
	private Player player;
<<<<<<< main
	private ArrayList<Enemy> enemies;
	private ArrayList<Bullet> bullets;
=======
	// @YDH TODO private Enemy enemy;
	
	private double elapsed = 0;
	private double bulletPeriod = 0.08; // 80ms
	private Bullet bullet;
	private CopyOnWriteArrayList<Bullet> bullets = new CopyOnWriteArrayList<>();
>>>>>>> main

	public PlayState(GameStateManager gsm) {
		super(gsm);
		background = new Background();
<<<<<<< main
		player = new Player(this);
		enemies = new ArrayList<Enemy>();
		bullets = new ArrayList<Bullet>();
=======
		player = new Player();
		// @YDH TODO enemy = new Enemy();
>>>>>>> main
	}

	@Override
	public void update(double dt) {
		
		background.move(dt);
		
		player.move(dt);
<<<<<<< main
		player.fire(dt);
		
		// enemies.move(dt);
		
	    for (int i = 0; i < bullets.size();) {
            Bullet bullet = bullets.get(i);
            bullet.move(dt);
            
            if (bullet.isOut()) {
				bullets.remove(bullet);
				continue;
			}
            
            ++i;
	    }
	    
=======
		// @YDH TODO enemy.move();
		
		fireBullet(dt);
		
		for (Bullet bullet: bullets) {
			bullet.move();
			
			if (bullet.isOut()) {
				bullets.remove(bullet);
			}
		}
>>>>>>> main
	}

	@Override
	public void input(KeyHandler key, MouseHandler mouse) {
		player.input(key, mouse);
	}

	@Override
	public void render(Graphics2D g) {
		background.render(g);
		player.render(g);
<<<<<<< main
		// enemy.render(g);
=======
		// @YDH TODO enemy.render(g);
>>>>>>> main
		
		for(Bullet bullet: bullets) {
			bullet.render(g);
		}
<<<<<<< main
=======
	}
	
	public void fireBullet(double dt) {
		elapsed = elapsed + dt;
		if (elapsed > bulletPeriod) {
			Bullet bullet = new Bullet((int)player.getX());
			bullets.add(bullet);
			
			elapsed = 0;
		}
>>>>>>> main
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
}
