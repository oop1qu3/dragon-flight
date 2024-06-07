package game.entity;

import static java.lang.Math.abs;

import static game.util.Constant.PanelConstant.*;

import java.awt.Graphics2D;
import java.util.List;

import javax.swing.ImageIcon;

import game.entity.effect.Effect;
import game.entity.effect.EnemyDeathEffect;
import game.math.Vector2f;

public class Enemy extends Entity {

    public static ImageIcon enemy = new ImageIcon("image/enemy_mov.gif");

    private double speed;

    public Enemy(int x) {
        super(x, -70, 60, 60);  // @JW 사이즈 조절?
        this.hp = 100;
        this.speed = 300;
    }

    @Override
	public void update(double dt) {
    	
    	List<Enemy> enemies = gsm.getState().getEnemies();
        List<Bullet> bullets = gsm.getState().getBullets();
        
        
    	for (int i = bullets.size()-1; i >= 0; i--) {
    		Bullet b = bullets.get(i);
    		
    		// @JW : 좌표 범위내에 들어오면
            // FIXME @YDH : if(isInHitbox()) {hit()} 으로 처리
            if((abs (x - b.getX()) <= 40) && (abs (y - b.getY()) <= 40)) {
            	b.hit(this);
            	bullets.remove(b);
            }
    	}
    	
		move(dt);
    	
		if (isOut()) {
			enemies.remove(this);
			return;
		}
		
		if (isDead()) {
			die();
			return;
		}
    	
	}
    
    private void move(double dt) {
        y += this.speed * dt;
    }

	@Override
	public void render(Graphics2D g) {
        enemy.paintIcon(null, g, (int)x, (int)y);
	}

    public boolean isDead() {
        return this.hp <= 0;
    }

    public boolean isOut() {
        return this.y > PANEL_HEIGHT;
    }
    
    public void die() {
    	int x = (int)(this.x + this.width / 2);
    	int y = (int)(this.y + this.height / 2);
    	Effect deathEffect = new EnemyDeathEffect(new Vector2f(x, y));
    	gsm.getState().getEffects().add(deathEffect);
		gsm.getState().getEnemies().remove(this);
    }
    
}
