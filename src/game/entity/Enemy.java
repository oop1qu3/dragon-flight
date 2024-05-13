package game.entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Enemy extends Entity {

    
	private ImageIcon img;
    private double speed; // FIXME @JW : double로 하는게? dt에도 쓰이기도 하고...
    private int hp;

    public Enemy() {
    	super(10, 0, 10, 10);
    	
		ImageIcon temp = new ImageIcon("image/player.png");
		Image img = temp.getImage();
		Image updateImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		this.img = new ImageIcon(updateImg);
    	
        this.hp = 100;
        this.speed = 200;
    }

    public void move(double dt) {
        y += this.speed * dt; // FIXME @JW : dt는 100을 곱해야 1.6, 1.5 이런식으로 쓸수 있는데?

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
    // FIXME @YDH : 더블 버퍼링 적용 안됨. sprite를 만들어야 할지도..
    public void render(Graphics g) {
    	if (isAlive())
    		img.paintIcon(null, g, (int)x, (int)y); 
    }

}