package game.entities;

import static java.lang.Math.abs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import game.main.Game;
import game.utils.Timer;

public class Player extends Entity {

	private static BufferedImage player;
	
	static {
		loadImg("images/entities/player.png");
	}
	
	private static void loadImg(String path) {
		try {
			player = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Rectangle2D.Float bodyHitbox;
	private Rectangle2D.Float wingHitbox;
	
    private boolean right;
    private boolean left;

	private Timer fireTimer;
	private Timer invisibleTimer;
	private Timer invincibleTimer;
	private Timer blinkTimer;

	private boolean visible;
	private boolean invincible;
	private boolean colliding;

	public Player() {
		super((Game.WIDTH - player.getWidth()) / 2, Game.HEIGHT - 100, player.getWidth(), player.getHeight());
		hp = 3;
		speed = 400.0f;
		
		float bodyWidth = 17.0f;
		bodyHitbox = new Rectangle2D.Float(centerX - bodyWidth / 2, y, bodyWidth, height);
		hitboxs.add(bodyHitbox);
		
		float wingWidth = 75.0f;
		float wingHeight = 25.0f;
		wingHitbox = new Rectangle2D.Float(
				centerX - wingWidth / 2, centerY - wingHeight, wingWidth, wingHeight);
		hitboxs.add(wingHitbox);
		
		right = false;
		left = false;
		
		fireTimer = new Timer(0.08, e -> {fire();});
		invisibleTimer = new Timer(2.0);
		invincibleTimer = new Timer(2.0);
		blinkTimer = new Timer(0.2);
		
		visible = true;
		invincible = false;
		colliding = false;
		
		game.getPlaying().getTimers().add(fireTimer);
		game.getPlaying().getTimers().add(invisibleTimer);
		game.getPlaying().getTimers().add(invincibleTimer);
		game.getPlaying().getTimers().add(blinkTimer);
		
		fireTimer.start();
		
	}
	
	@Override
	public void update(double dt) {
		super.update(dt);
		
		bodyHitbox.x = centerX - bodyHitbox.width / 2;
		bodyHitbox.y = centerY - bodyHitbox.height / 2;

		wingHitbox.x = centerX - wingHitbox.width / 2;
		wingHitbox.y = centerY - wingHitbox.height / 2;
		
		left = key.left.isPressed();
		right = key.right.isPressed();

		if (centerX <= 0) { 
			x = -(width / 2);
			if (left && right) {}
			else left = false;
		}
		
		if (centerX >= Game.WIDTH) { 
			x = Game.WIDTH - width / 2;
			if (left && right) {}
			else right = false;
		}
		
		if (visible) {
			move(dt);
		} else {
			if (invisibleTimer.getCount() >= 1) {
				visible = true;
				
				invisibleTimer.reset();
				fireTimer.start();
				invincibleTimer.start();
				blinkTimer.start();
			}
		}

		if (invincible) {
			if (invincibleTimer.getCount() >= 1) {
				invincible = false;
				
				invincibleTimer.reset();
				blinkTimer.reset();
			}
		}
		
		checkCollision();
	}

	public void move(double dt) {
		if (left) {
			x -= this.speed * dt;
		}
		if (right) {
			x += this.speed * dt;
		}
	}
	
	
	public void fire() {
		Bullet b = new Bullet((int)x + player.getWidth() / 2);
		game.getPlaying().getBullets().add(b);
	}

	// @YCW: add checkColision for interaction between Character and Enemy ( + Character and Obstacle )
	public void checkCollision() {
		List<Enemy> enemies = game.getPlaying().getEnemies();
		List<Obstacle> obstacles = game.getPlaying().getObstacles();

		for(int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			
			if(bodyHitbox.intersects(e.getHitbox()) || wingHitbox.intersects(e.getHitbox())) {
				if(invincible == false) {
					colliding = true;
				}
			}
		}

		for(int i = 0; i < obstacles.size(); i++) {
			Obstacle o = obstacles.get(i);
			
			if(bodyHitbox.intersects(o.getHitbox()) || wingHitbox.intersects(o.getHitbox())) {
				if(invincible == false) {
					colliding = true;
				}
			}
		}

		if (colliding == true) {
			hp--;
			
			colliding = false;
			visible = false;
			invincible = true;
			
			invisibleTimer.start();
			fireTimer.reset();
		}
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
		
		if (visible) {
			if (blinkTimer.getCount() % 2 == 0) {
				g.drawImage(player, (int)x, (int)y, player.getWidth(), player.getHeight(), null);
			}
		}
	}
	
	// @YCW: add isDead for checking player is dead
	public boolean isDead() {
		if (getHp() <= 0) {
			return true;
		}
		
		return false;
	}

	// @YCW: add getX for x position of bullet
	public float getX() {
		return x;
	}

	public int getHp() {
		return hp;
	}
	
}