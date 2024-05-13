package game.entity;

import java.awt.Graphics2D;

import game.main.Resource;
import game.util.KeyHandler;
import game.util.MouseHandler;

public class Player extends Entity {
	
	private int speed;

	public Player() {
		super((384 - 80) / 2, 512 - 100, 80, 80); // FIXME @YDH : 상수 선언
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

	public void input(KeyHandler key, MouseHandler mouse) {
		
		left = key.left.pressed;
		right = key.right.pressed;
		
		double centerX = x + 40;
		
		if(centerX <= 5) {
			x = 5 - 40;
		}
		if(centerX >= 384 - 5) { // FIXME @YDH : 상수 선언
			x = 384 - 5 - 40;
		}

	}

	public void render(Graphics2D g) {
		g.drawImage(Resource.player, (int)x, (int)y, width, height, null);
	}

}
