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
		
		enemyDeathCloud.setStartLifetime(1.0);
		enemyDeathCloud.setStartRotation(0, 360);
		
		enemyDeathCloud.setShape(new Circle(20.0));
		enemyDeathCloud.setEmission(8);
	}
	
	public void play(double dt) {
		
	}
	
	public void render(Graphics2D g) {
		enemyDeathCloud.render(g);
		enemyDeathGlow.render(g);
	}
	
}
