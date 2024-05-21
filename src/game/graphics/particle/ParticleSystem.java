package game.graphics.particle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.graphics.particle.shape.Shape;
import game.math.Range;
import game.math.Vector2f;

public class ParticleSystem {
	
	// Temp
	private BufferedImage img;  // or Sprite
	private Vector2f origin;
	
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
	
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	public void setEmission(int burstsCount) {
		this.burstsCount = burstsCount;
	}
	
	public void render(Graphics2D g) {
		for (int i = 0; i < burstsCount; i++) {
			Vector2f v = new Vector2f(3f, 5f);
			g.drawImage(img, (int)v.x, (int)v.y, 100, 100, null);
		}
	}
	
}