package game.entity;

import java.awt.Graphics;

import game.main.Resource;
import game.main.WindowManager;

// 일단 state들이 enemystate로 나누어져있지 않고, playingstate 하나로 뭉쳐져있음
// playingstate.class 안에서 room.class -> spawnenemy method 순으로 감

public class Enemy extends Entity {

    private int speed;

    public Enemy() {
        super(10, 0, 60, 60);
        this.speed = 5;
    }

   public void move() {
        y += this.speed;

   }

    public void render(Graphics g) {
        g.drawImage(Resource.enemy, x, y, width, height, null);
    }

}
