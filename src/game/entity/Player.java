package game.entity;

import static java.lang.Math.abs;

import java.awt.Graphics2D;
import java.util.List;

import javax.swing.ImageIcon;

import game.state.GamestateManager;
import game.state.Playing;
import game.state.State;
import game.util.KeyHandler;
import game.util.MouseHandler;

public class Player extends Entity {

	public static ImageIcon player = new ImageIcon("image/player.png");
	public static ImageIcon player_inv = new ImageIcon("image/player_inv.gif");
	
	private int hp;
	private int speed;

	private double firePeriod = 0.08; // 80ms
	private double elapsed = 0;

	private int invincibleTime = 1; // @YCW: current invincible time is 2 seconds, enemy에게 맞으면 발동
	private double elapsedInvincibleTime = 0;
	private boolean isCollision = false;
	private boolean isInvincible = false;

	public Player() {
		super((384 - 80) / 2, 512 - 100, 50, 50); // FIXME @YDH : 상수 선언
		this.hp = 2; // @YCW: default hp value = 2
		this.speed = 500;
	}

	public void move(double dt) {
		if (left) {
			x -= this.speed * dt;
		}
		if (right) {
			x += this.speed * dt;
		}
	}
	
	
	public void fire(double dt) {
		elapsed += dt;
		if (elapsed > firePeriod) {
			Bullet b = new Bullet((int) x);
			gsm.state.getBullets().add(b);

			elapsed = 0;
		}
	}

	public void input(KeyHandler key, MouseHandler mouse) {
		left = key.left.pressed;
		right = key.right.pressed;

		double centerX = x + 40;

		if (centerX <= 5) {
			x = 5 - 40;
			if (left && right) {}
			else left = false;
		}
		if (centerX >= 384 - 5) { // FIXME @YDH : 상수 선언
			x = 384 - 5 - 40;
			if (left && right) {}
			else right = false;
		}
	}

	// @YCW: add checkColision for interaction between Character and Enemy ( + Character and Obstacle )
	public void checkCollision(double dt) {
		List<Enemy> enemies = gsm.state.getEnemies();
		List<Obstacle> obstacles = gsm.state.getObstacles();

		for(int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			
			if((abs((this.x + this.width / 2) - (e.getX() + this.width / 2)) < (e.getWidth() / 2 + this.width / 2) &&
					abs((this.y + this.height / 2) - (e.getY() + e.getHeight() / 2)) < (e.getHeight() / 2 + this.height / 2)) &&
					isInvincible == false)
			{
				isCollision = true;
			}
		}

		for(int i = 0; i < obstacles.size(); i++)
		if(obstacles.get(i) != null)
			if((abs (this.x - obstacles.get(i).getX()) <= 50) &&
					((obstacles.get(i).hitY() <= this.y) && (obstacles.get(i).hitY() + 20 >= this.y)) &&
					isInvincible == false)
			{
				isCollision = true;
			}

		if (isCollision == true) {
			hp = hp - 1;
			isCollision = false;
			isInvincible = true; // if isInvincible = true, then move, fire, render functions are stopped.
		}

		if (isInvincible == true) {
			elapsedInvincibleTime += dt;
			if (elapsedInvincibleTime > invincibleTime) {
				isInvincible = false;

				elapsedInvincibleTime = 0;
			}
		}
	}
	
	@Override
	public void update(double dt) {
		if (gsm.state instanceof Playing) {
			if (!isInvincible) {
				move(dt);
				fire(dt);
			}
			checkCollision(dt);
		}
	}

	@Override
	public void render(Graphics2D g) {
		if (!isInvincible)
			player.paintIcon(null,g,(int)x,(int)y);
		else
			player_inv.paintIcon(null,g,(int)x,(int)y);
	}
	
	// @YCW: add isDead for checking player is dead
	public boolean isDead() {
		if (getHp() <= 0) {
			return true;
		}
		
		return false;
	}

	// @YCW: add getX for x position of bullet
	public double getX() {
		return x;
	}

	public int getHp() {
		return hp;
	}
}