package game.state;

import java.awt.*;
import java.util.ArrayList;

import game.entity.Enemy;
import game.util.KeyHandler;
import game.util.MouseHandler;

public class EnemyState extends GameState {

    private Enemy enemy;

    public EnemyState() {
        super();
        enemy = new Enemy();
    }

    @Override
    public void update(double dt) {
        enemy.move(dt);
    }

    public boolean isAlive(){
        if (enemy.isAlive())
            return true;
        else
            return false;
    }

    @Override
    public void input(KeyHandler key, MouseHandler mouse) {
        // @JW : staying empty
    }


    @Override
    public void render(Graphics2D g) {
        enemy.render(g);
    }

}

