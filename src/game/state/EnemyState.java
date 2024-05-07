package game.state;

import java.awt.Graphics;

import game.entity.Enemy;

public  class EnemyState extends GameState {
    private Enemy enemy;

    public EnemyState() {
        super();
        enemy = new Enemy();
    }

    @Override
    public void update() {
        // ??
    }

    @Override
    public void render(Graphics g) {
        enemy.render(g);
    }

}

