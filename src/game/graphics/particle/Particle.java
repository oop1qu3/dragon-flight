package game.graphics.particle;

import java.awt.Color;

import game.math.Vector2f;

public class Particle {
	private double lifetime;
	private double speed;
	private double angle;
	
	private Color color;
	private Vector2f origin;
	private Vector2f dPos;
	
	public Particle(double angle, Vector2f origin) {
		this.angle = angle;
		this.origin = origin;
	}

	// Getters and Setters
	public double getLifetime() {
		return lifetime;
	}

	public void setLifetime(double lifetime) {
		this.lifetime = lifetime;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Vector2f getOrigin() {
		return origin;
	}

	public void setOrigin(Vector2f origin) {
		this.origin = origin;
	}

	public Vector2f getdPos() {
		return dPos;
	}

	public void setdPos(Vector2f dPos) {
		this.dPos = dPos;
	}
}
