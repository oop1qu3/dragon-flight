package game.graphics.particle;

import java.awt.Color;

import game.math.Vector2f;

public class Particle {
	private double lifetime;
	private float speed;
	private float size;
	private float angle;
	
	private Color color;
	private Vector2f position;
	private Vector2f velocity;
	
	private double lifetimeTimer = 0;
	private double sizeTimer = 0;
	
	public Particle(double lifetime, float size, float angle, Vector2f position, Vector2f velocity) {
		this.lifetime = lifetime;
		this.size = size;
		this.angle = angle;
		
		this.position = position;
		this.velocity = velocity;
	}
	
	public void updateTimer(double dt) {
		lifetimeTimer += dt;
		sizeTimer += dt;
	}
	
	public boolean isLifetimeTimerOver(double interval) {
		if (lifetimeTimer > interval) {
			lifetimeTimer = 0;
			return true;
		} else {
			return false;	
		}
	}
	
	public boolean isSizeTimerOver(double interval) {
		if (sizeTimer > interval) {
			sizeTimer = 0;
			return true;
		} else {
			return false;	
		}
	}

	public double getLifetime() {
		return lifetime;
	}

	public void setLifetime(double lifetime) {
		this.lifetime = lifetime;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public Vector2f getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2f velocity) {
		this.velocity = velocity;
	}

}
	
