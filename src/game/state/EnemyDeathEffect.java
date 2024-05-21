package game.state;

import java.awt.Graphics2D;

import game.graphics.particle.ParticleSystem;
import game.graphics.particle.shape.Circle;
import game.math.Vector2f;

public class EnemyDeathEffect {
	
	private ParticleSystem enemyDeathCloud;
	private ParticleSystem enemyDeathGlow;

	public EnemyDeathEffect(Vector2f origin) {
		enemyDeathCloud = new ParticleSystem("image/effect/enemyDeathCloud.png", origin);
		enemyDeathGlow = new ParticleSystem("image/effect/enemyDeathGlow.png", origin);
		
		setEnemyDeathCloud();
		setEnemyDeathGlow();
		
		enemyDeathCloud.init();
		enemyDeathGlow.init();
	}
	
	private void setEnemyDeathCloud() {
		enemyDeathCloud.setStartLifetime(0.5);
		enemyDeathCloud.setStartSize(20.0f, 60.0f);
		enemyDeathCloud.setStartRotation(0, 360.0f);
		
		enemyDeathCloud.setEmission(8);
		enemyDeathCloud.setShape(new Circle(15.0f));
	}
	
	private void setEnemyDeathGlow() {
		
	}
	
	public void play(double dt) {
		enemyDeathCloud.play(dt);
	}
	
	public void render(Graphics2D g) {
		enemyDeathCloud.render(g);
		enemyDeathGlow.render(g);
	}
	
}
