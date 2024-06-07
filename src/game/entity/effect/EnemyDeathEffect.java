package game.entity.effect;

import java.awt.Graphics2D;

import game.graphic.particle.ParticleSystem;
import game.graphic.particle.shape.Circle;
import game.graphic.particle.shape.Mesh;
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
		enemyDeathCloud.setStartLifetime(0.45);
		enemyDeathCloud.setStartSpeed(0.15f);
		enemyDeathCloud.setStartSize(0.5f, 1f);
		enemyDeathCloud.setStartRotation(0, 360.0f);
		
		enemyDeathCloud.setEmission(8);
		enemyDeathCloud.setShape(new Circle(8.0f, 0.8f));
	}
	
	private void setEnemyDeathGlow() {
		enemyDeathGlow.setStartLifetime(0.25);
		
		enemyDeathGlow.setShape(new Mesh());
		//enemyDeathGlow.setColorOverLifetime();
	}
	
	@Override
	public boolean isFinished() {
		return enemyDeathCloud.isFinished() && enemyDeathGlow.isFinished();
	}
	
	@Override
	public void update(double dt) {
		enemyDeathCloud.play(dt);
		enemyDeathGlow.play(dt);
	}
	
	@Override
	public void render(Graphics2D g) {
		enemyDeathCloud.render(g);
		enemyDeathGlow.render(g);
	}
	
}
