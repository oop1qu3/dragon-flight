package game.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
<<<<<<< main
=======

>>>>>>> main
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

<<<<<<< main
public class Bullet extends Entity {
=======
public class Bullet extends Entity{
>>>>>>> main
	private static BufferedImage bulletImg;
	private String imgPath = "image/bullet_01.png";
	private int speed;
	private int damage;
<<<<<<< main
=======

>>>>>>> main
	
	public Bullet(int playerX) {
		super(playerX - 10, 512 - 100, 100, 100);
		
		loadImg(imgPath);
		
<<<<<<< main
		this.speed = 1000;
		this.damage = 1;
	}
=======
		this.speed = 50;
		this.damage = 1;
	};
>>>>>>> main
	
	public void loadImg(String path) {
		try {
			this.bulletImg = ImageIO.read(new File(path));	
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
<<<<<<< main
	public void move(double dt) {
		y -= this.speed * dt;
=======
	public void move() {
		y -= this.speed;
>>>>>>> main
	}
	
	public boolean isOut() {
		return y < -100;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void render(Graphics g) {
<<<<<<< main
		g.drawImage(this.bulletImg, (int)x, (int)y, width, height, null);
	}
}
=======
		// @YCW: x should be always equal to x position of player
		g.drawImage(this.bulletImg, (int)x, (int)y, width, height, null);
	}
}
>>>>>>> main
