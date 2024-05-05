package game.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.main.Resource;
import game.main.WindowManager;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player extends Rectangle {

	protected boolean up;
	protected boolean down;
	protected boolean left;
	protected boolean right;

	protected int speed;

	public Player() {

		super((WindowManager.WIDTH) / 2 - 40, 450, 80, 80);
		this.speed = 10;

	}

	public void move() {

		if (left) {
			x -= this.speed;
		}
		if (right) {
			x += this.speed;
		}

	}

	public void render(Graphics graphics) {

		graphics.drawImage(Resource.player, x, y, width, height, null);

	}

}
