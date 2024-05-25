package game.entity;

import static java.awt.image.ImageObserver.HEIGHT;
import static java.lang.Math.abs;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.entity.effect.Effect;
import game.entity.effect.EnemyDeathEffect;
import game.math.Vector2f;
import game.state.State;
import game.state.Playing;

public class Enemy extends Entity {

    public static ImageIcon enemy = new ImageIcon("image/enemy_mov.gif");

    private double speed;

    // FIXME
    private State state;

    public Enemy(int x, State state) {
        super(x, -70, 60, 60, 100);      // @JW 사이즈 조절?
        this.speed = 3;

        this.state = state;
    }

    public void move(double dt) {
        y += this.speed * (dt * 100);
    }

    public boolean isAlive(){
        return this.hp > 0;
    }

    public boolean isOut(){     // FIXME
        return this.y > HEIGHT;
    }

    public void enemyHit(){
        ArrayList<Bullet> bullets = ((Playing)state).getBullets();

        // @JW : 좌표 범위내에 들어오면
        for(int i = 0; i < bullets.size(); i++)
            if((abs (x - bullets.get(i).getX()) <= 40) &&
                    (abs (y - bullets.get(i).getY()) <= 40))
                this.hp -= bullets.get(i).getDam();
    }
    
    public void dead() {
    	int x = (int)(this.x + this.width / 2);
    	int y = (int)(this.y + this.height / 2);
    	Effect deathEffect = new EnemyDeathEffect(new Vector2f(x, y));
    	((Playing)state).getEffects().add(deathEffect);
    }

    public void render(Graphics g) {
        enemy.paintIcon(null, g, (int)x, (int)y);
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
