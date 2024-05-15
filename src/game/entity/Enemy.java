package game.entity;

import java.awt.Graphics;
import game.main.Resource;

import javax.swing.*;
import java.awt.image.BufferedImage;

import static game.main.Window.HEIGHT;
import static game.main.Window.WIDTH;


public class Enemy extends Entity {

    // @JW : gif는 ImageIcon 사용
    public static ImageIcon enemy = new ImageIcon("image/enemy_mov.gif");

    private double speed;   // @JW FIXME double로 하는게? dt에도 쓰이기도 하고...

    public Enemy(int x) {

        super(x, -70, 60, 60, 100);      // @JW 사이즈 조절?
        this.speed = 3;
    }

    public void move(double dt) {
        y += this.speed * (dt * 100);   // @JW FIXME dt는 100을 곱해야 1.6, 1.5 이런식으로 쓸수 있는데?

        // @JW : testing
        hp -= 2;
    }

    public boolean isAlive(){
        if (this.hp > 0)
            return true;
        else
            return false;
    }

    public boolean isOut(){
        if (this.y > HEIGHT)
            return true;
        else
            return false;
    }

    // @JW : gif는 스윙 ImageIcon의 paintIcon 사용
    public void render(Graphics g) {
        enemy.paintIcon(null, g, (int)x, (int)y);
    }

}
