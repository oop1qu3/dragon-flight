package game.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import game.main.GameObject;

public abstract class Entity extends GameObject { 
	
	protected float x;
	protected float y;
	protected float width;
	protected float height;
	
	protected float centerX;
	protected float centerY;

    protected float speed;
	protected int hp;
	
	protected List<Rectangle2D.Float> hitboxs;
	private static boolean hitboxEnabled = false;
	private static boolean f3Pressed = false;
	
    public Entity() {
    	hitboxs = new ArrayList<Rectangle2D.Float>();
    }

	public Entity(float x, float y) {
		this();
		
		this.x = x;
		this.y = y;
	}

    public Entity(float x, float y, float width, float height) {
    	this(x, y);
    	
    	this.width = width;
    	this.height = height;
    	
    	this.centerX = x + width / 2;
    	this.centerY = y + height / 2;
    }

	public void update(double dt) {
		centerX = x + width / 2;
		centerY = y + height / 2;
	}
	
	public static void update() {
		if (key.f3.isPressed()) {
			f3Pressed = true;
		}
		
		if (!key.f3.isPressed()) {
			if (f3Pressed) {
				hitboxEnabled = !hitboxEnabled;
				f3Pressed = false;
			}	
		}
	}
	
	public void render(Graphics2D g) {
		if (hitboxEnabled) {
			if (hitboxs != null) {
				drawHitboxs(g);
			}
		}
	}
    
    protected void drawHitboxs(Graphics2D g) {
    	for (Rectangle2D.Float h : hitboxs) {
    		g.setColor(Color.PINK);
    		g.drawRect((int)h.x, (int)h.y, (int)h.width, (int)h.height);
    	}
	}
    
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
	
	public float getCenterX() {
		return centerX;
	}

	public float getCenterY() {
		return centerY;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void setHp(int hp)  {
		this.hp = hp;
	}
	
	public static void setHitboxEnabled(boolean hitboxEnabled) {
		Entity.hitboxEnabled = hitboxEnabled;
	}
	
}
