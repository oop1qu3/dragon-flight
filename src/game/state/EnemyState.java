package game.state;

import java.awt.Graphics;

import game.entity.Enemy;
import game.util.KeyHandler;
import game.util.MouseHandler;

public  class EnemyState extends GameState {

    private Enemy enemy;

    public EnemyState() {
        super();
        enemy = new Enemy();
    }

    @Override
    public boolean stateUpdate() {
        if (enemy.isAlive())    // 살아있는경우
        {
            enemy.move();
            return true;
        }
        else                    // 죽은경우
            return false;
    }

    @Override
    public void input(KeyHandler key, MouseHandler mouse) {
        // stay empty
    }

    @Override
    public void render(Graphics g) {
        enemy.render(g);
    }

}

