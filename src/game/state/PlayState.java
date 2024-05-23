package game.state;

import java.awt.Graphics2D;
import java.util.ArrayList;

import game.entity.Bullet;
import game.entity.Enemy;
import game.entity.Player;
import game.map.Background;
import game.math.Vector2f;
import game.util.KeyHandler;
import game.util.MouseHandler;

public class PlayState extends GameState {

	private Background background;
	private Player player;
	private ArrayList<Enemy> enemies;
	private ArrayList<Bullet> bullets;
	
	private Test test;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
		background = new Background();
		player = new Player(this);
		enemies = new ArrayList<Enemy>();
		bullets = new ArrayList<Bullet>();
		
		tests = new ArrayList<Test>();
	}

	private double elapsed;
	private ArrayList<Test> tests;
	@Override
	public void update(double dt) {
		
		background.move(dt);
		
		player.move(dt);
		player.fire(dt);
		
		// enemies.move(dt);
		
	    for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);
            bullet.move(dt);
            
            if (bullet.isOut()) {
				bullets.remove(i);
			}
	    }
	    
	    elapsed += dt;
		if (elapsed > 1) {
			tests.clear();
			
			Test test = new Test(new Vector2f(100.0f, 100.0f));
			tests.add(test);

			elapsed = 0;
		}
		
		for (Test t : tests) {
			t.update(dt);
		}
	    
	}

	@Override
	public void input(KeyHandler key, MouseHandler mouse) {
		player.input(key, mouse);
	}

	@Override
	public void render(Graphics2D g) {
//		background.render(g);
//		player.render(g);
//		
//		for(Bullet bullet: bullets) {
//			bullet.render(g);
//		}
		
		for (Test t : tests) {
			t.render(g);
		}
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
}
