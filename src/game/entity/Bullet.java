package game.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullet extends Entity {
    private static BufferedImage bulletImg;
    private String imgPath = "image/bullet_01.png";
    private int speed;
    private int damage;


    public Bullet(int playerX) {
        super(playerX - 10, 512 - 150, 100, 100);

        loadImg(imgPath);

        this.speed = 1000;
        this.damage = 30; // current enemy's hp is 100
    };

    public void loadImg(String path) {
        try {
            bulletImg = ImageIO.read(new File(path));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move(double dt) {
        y -= this.speed * dt;
    }

    public boolean isOut() {
        return y < -100;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
    
    public int getDamage() {
        return damage;
    }
    
    public void hit(Enemy e) {
    	int currentHp = e.getHp();
    	e.setHp(currentHp - damage);
    }

    @Override
    public void render(Graphics2D g) {
        // @YCW: x should be always equal to x position of player
        g.drawImage(bulletImg, (int)x, (int)y, width, height, null);
    }

	@Override
	public void update(double dt) {
		move(dt);
        
        if (isOut()) {
			gsm.getState().getBullets().remove(this);
		}
	}

}
