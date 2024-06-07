package game.entity;

import java.awt.Graphics2D;

import game.state.GamestateManager;
import game.state.State;

public abstract class Entity { 
	
	protected float x;
	protected float y;
	protected int width;
	protected int height;
	
    protected boolean right = false;
    protected boolean left = false;

    protected float speed;
	protected int hp;
	
	protected static GamestateManager gsm = GamestateManager.getInstance();
	
	public Entity() {}

	public Entity(float x, float y) {
		this();
		
		this.x = x;
		this.y = y;
	}

    public Entity(float x, float y, int width, int height) {
    	this(x, y);
    	
    	this.width = width;
    	this.height = height;
    }
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void setHp(int hp)  {
		this.hp = hp;
	}
	
	public abstract void update(double dt);
	public abstract void render(Graphics2D g);
}
