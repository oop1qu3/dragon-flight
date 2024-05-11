package game.entity;

import java.awt.Graphics;

import game.main.Resource;
import game.main.WindowManager;

// 일단 state들이 enemystate로 나누어져있지 않고, playingstate 하나로 뭉쳐져있음
// playingstate.class 안에서 room.class -> spawnenemy method 순으로 감

public class Enemy extends Entity {

    private int speed;

    public Enemy() {
        super(10, 0, 60, 60, 100, true);
        this.speed = 5;
    }

   public void move() {
        y += this.speed;

        // testing
        hp -= 7;
   }

   public boolean isAlive(){
        if (this.hp > 0)
            return true;
        else
            return false;
   }

    public void render(Graphics g) {
        //g.drawImage(Resource.enemy, x, y, width, height, null);
        Resource.enemy.paintIcon(null, g, x, y);    // gif라 스윙 ImageIcon의 paintIcon 사용
    }

}
