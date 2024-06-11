package game.entities;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.ImageIcon;

import game.audio.AudioPlayer;
import game.entities.effect.Effect;
import game.entities.effect.EnemyDeathEffect;
import game.main.Game;
import game.math.Vector2f;

public class Enemy extends Entity {

    public static ImageIcon enemy = new ImageIcon("images/entities/enemy_mov.gif");  // FIXME
    private static float size = 70.0f;

    private Rectangle2D.Float hitbox;

    private static float startSpeed = 200.0f;
    
    public Enemy(float x) {
        super(x, -70, (int)size, (int)size);
        
        hp = 100;
        speed = startSpeed;
        
        float hitboxWidth = 42.0f;
        float hitboxHeight = 60.0f;
        hitbox = new Rectangle2D.Float(
        		centerX - hitboxWidth, centerY - hitboxHeight, hitboxWidth, hitboxHeight);
        hitboxs.add(hitbox);
    }

    @Override
	public void update(double dt) {
    	
    	super.update(dt);
		
		hitbox.x = centerX - hitbox.width / 2;
		hitbox.y = centerY - hitbox.height / 2;
    	
    	List<Enemy> enemies = game.getPlaying().getEnemies();
        List<Bullet> bullets = game.getPlaying().getBullets();
        
    	for (int i = bullets.size()-1; i >= 0; i--) {
    		Bullet b = bullets.get(i);
    		
            if(hitbox.intersects(b.getHitbox())) {
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
		super.render(g);
		
        enemy.paintIcon(null, g, (int)x, (int)y);
	}

    public boolean isDead() {
        return this.hp <= 0;
    }

    public boolean isOut() {
        return this.y > Game.HEIGHT;
    }
    
    public void die() {
    	game.getPlaying().getEnemies().remove(this);
    	
    	int x = (int)(this.x + this.width / 2);
    	int y = (int)(this.y + this.height / 2);
    	
    	Effect deathEffect = new EnemyDeathEffect(new Vector2f(x, y));
    	game.getPlaying().getEffects().add(deathEffect);
    	
    	game.getAudioPlayer().playEffect(AudioPlayer.ENEMY_DEATH);
    }
    
	public Rectangle2D.Float getHitbox() {
		return hitbox;
	}

	public static float getStartSpeed() {
		return startSpeed;
	}

	public static void setStartSpeed(float startSpeed) {
		Enemy.startSpeed = startSpeed;
	}
    
}
