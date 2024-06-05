package game.entity;

import java.awt.Graphics2D;

import game.state.GamestateManager;
import game.state.State;

public abstract class Entity { 
	
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	
    protected boolean right = false;
    protected boolean left = false;

	protected int hp;
	
	protected static GamestateManager gsm = GamestateManager.getInstance();
	
	public Entity() {}

	public Entity(double x, double y) {
		this();
		
		this.x = x;
		this.y = y;
	}

    public Entity(double x, double y, int width, int height) {
    	this(x, y);
    	
    	this.width = width;
    	this.height = height;
    }

	// @JW : Enemy로 받아올 때
	public Entity(double x, double y, int width, int height, int hp) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.hp = hp;
	}
	
	public abstract void update(double dt);
	public abstract void render(Graphics2D g);
}
