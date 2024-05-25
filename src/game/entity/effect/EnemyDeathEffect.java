package game.entity.effect;

import java.awt.Graphics2D;

import game.graphics.particle.ParticleSystem;
import game.graphics.particle.shape.Circle;
import game.graphics.particle.shape.Mesh;
import game.math.Vector2f;

public class EnemyDeathEffect extends Effect {
	
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
		enemyDeathCloud.setStartLifetime(0.35);
		enemyDeathCloud.setStartSpeed(0.15f);
		enemyDeathCloud.setStartSize(0.3f, 1.0f);
		enemyDeathCloud.setStartRotation(0, 360.0f);
		
		enemyDeathCloud.setEmission(8);
		enemyDeathCloud.setShape(new Circle(8.0f, 0.8f));
	}
	
	private void setEnemyDeathGlow() {
		enemyDeathGlow.setStartLifetime(0.2);
		
		enemyDeathGlow.setShape(new Mesh());
		//enemyDeathGlow.setColorOverLifetime();
	}
	
	@Override
	public boolean isFinished() {
		return enemyDeathCloud.isFinished() && enemyDeathGlow.isFinished();
	}
	
	@Override
	public void play(double dt) {
		enemyDeathCloud.play(dt);
		enemyDeathGlow.play(dt);
	}
	
	@Override
	public void render(Graphics2D g) {
		enemyDeathCloud.render(g);
		enemyDeathGlow.render(g);
	}
	
}
