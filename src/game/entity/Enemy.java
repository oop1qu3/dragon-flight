package game.entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.graphics.particle.ParticleSystem;

public class Enemy extends Entity {

	private ImageIcon img;
    private double speed; // FIXME @JW : double로 하는게? dt에도 쓰이기도 하고...
    private int hp;
    
    private ParticleSystem deathEffect;

    public Enemy() {
    	super(10, 0, 10, 10);
    	
    	this.img = new ImageIcon("image/enemy_mov.gif");
    	
        this.hp = 100;
        this.speed = 20;
    }

    public void move(double dt) {
        y += this.speed * dt;

        // @JW : testing
        // hp -= 2;
    }

    public boolean isAlive(){
        if (this.hp > 0)
            return true;
        else
            return false;
    }

    // @JW : gif는 스윙 ImageIcon의 paintIcon 사용
    // FIXME @YDH : gif는 사이즈 조절이 안됨. 외부 환경에서 조절하거나, sprite로 다루거나 해야할듯.
    public void render(Graphics g) {
    	if (isAlive())
    		img.paintIcon(null, g, (int)x, (int)y); 
    }

}