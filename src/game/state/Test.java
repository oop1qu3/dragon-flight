package game.state;

import java.awt.Graphics2D;

import game.math.Vector2f;

public class Test {
	private EnemyDeathEffect deathEffect;
	
	public Test(Vector2f origin) {
		deathEffect = new EnemyDeathEffect(origin);
	}
	
	public void update(double dt) {
		deathEffect.play(dt);
	}
	
	public void render(Graphics2D g) {
		deathEffect.render(g);
	}
}
