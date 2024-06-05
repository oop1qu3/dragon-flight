package game.entity;

import static java.awt.image.ImageObserver.HEIGHT;
import static java.lang.Math.abs;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;

import game.entity.effect.Effect;
import game.entity.effect.EnemyDeathEffect;
import game.math.Vector2f;
import game.state.Playing;
import game.state.State;

public class Enemy extends Entity {

    public static ImageIcon enemy = new ImageIcon("image/enemy_mov.gif");

    private double speed;

    public Enemy(int x) {
        super(x, -70, 60, 60, 100);      // @JW 사이즈 조절?
        this.speed = 300;
    }

    @Override
	public void update(double dt) {
    	System.out.println("e");
    	List<Enemy> enemies = gsm.state.getEnemies();
        List<Bullet> bullets = gsm.state.getBullets();
        
        // @JW : 좌표 범위내에 들어오면
        // FIXME @YDH : if(isInHitbox()) {hit()} 으로 처리
    	for(Bullet b : bullets) {
            if((abs (x - b.getX()) <= 40) && (abs (y - b.getY()) <= 40)) {
            	hit();
            }
    	}
    	
    	if (isAlive()) {
			move(dt);
		} else {
			dead();
			enemies.remove(this);
		}
    	
	}
    
    private void hit(){
        getDamage();
    }
    
    private void getDamage() {
    	hp -= 50;
    }
    
    private void move(double dt) {
        y += this.speed * dt;
    }

	@Override
	public void render(Graphics2D g) {
        enemy.paintIcon(null, g, (int)x, (int)y);
	}

    public boolean isAlive(){
        return this.hp > 0;
    }

    public boolean isOut(){     // FIXME
        return this.y > HEIGHT;
    }
    
    public void dead() {
    	int x = (int)(this.x + this.width / 2);
    	int y = (int)(this.y + this.height / 2);
    	Effect deathEffect = new EnemyDeathEffect(new Vector2f(x, y));
    	gsm.state.getEffects().add(deathEffect);
    }

    public double getX(){
        return x;
    }

    public double getY() {
        return y;
    }

    // @YCW: add getWidth and getHeight for implementing the checking collision between character and enemy
    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
    
}
