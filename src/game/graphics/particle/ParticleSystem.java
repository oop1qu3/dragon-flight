package game.graphics.particle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import game.graphics.particle.shape.Circle;
import game.graphics.particle.shape.Shape;
import game.math.Range;
import game.math.Vector2f;

public class ParticleSystem {
	
	// Temp
	private BufferedImage img;  // or Sprite
	private Vector2f origin;
	
	private ArrayList<Particle> particles;
	
	private double elapsed;
	
	// Main
	private Range<Double> startLifetime;
	// private Range<Double> startSpeed; (later..)
	private Range<Double> startSize;
	private Range<Double> startRotation;
	private Range<Color> startColor;
	
	// Emission
	private int burstsCount;
	
	// Shape
	private Shape shape;
	
	// Color over lifetime
	// Size over lifetime
	// Velocity over lifetime (later..)
	// Rotation over lifetime
	
	public ParticleSystem(String imgPath, Vector2f origin) {
		try {
			img = ImageIO.read(new File(imgPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.origin = origin;
		this.burstsCount = 0;
	}
	
	public void setStartLifetime(double start) {
		startLifetime = new Range(start);
	}
	
	public void setStartRotation(double start, double end) {
		startRotation = new Range(start, end);
	}
	
	public void setStartColor(Color start) {
		startColor =  new Range(start);
	}
	
	public void setEmission(int burstsCount) {
		this.burstsCount = burstsCount;
	}
	
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	public void init() {
		particles = new ArrayList<Particle>(burstsCount);
		
		if (shape instanceof Circle) {
			initCircle();
		}
	}
	
	private void initCircle() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		
		for (int i = 0; i < burstsCount; i++) {
			// angle
			double angle = random.nextDouble(startRotation.start, startRotation.end);
			
			// startPos
			float radius = ((Circle)shape).getRadius();
			float radiusThickness = ((Circle)shape).getRadiusThickness();
			float scale = random.nextFloat(radius * (1 - radiusThickness), radius);
			
			Vector2f dPos = Vector2f.createRandom(scale);
			Vector2f startPos = this.origin.add(dPos);
			
			// add particle
			Particle p = new Particle(angle, startPos);
			particles.add(p);
		}
	}
	
	public void play(double dt) {
		// elapsed += dt;
	}
	
	public void render(Graphics2D g) {
		for (int i = 0; i < particles.size(); i++) {
			Vector2f pos = particles.get(i).getOrigin();
			g.drawImage(img, (int)pos.x, (int)pos.y, 30, 30, null);
		}
	}
	
}