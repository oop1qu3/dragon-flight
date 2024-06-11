package game.states;

import java.awt.Graphics2D;

import game.main.GameObject;

public abstract class State extends GameObject {
	
	public State() {}
	
	protected abstract void update(double dt);
	protected abstract void render(Graphics2D g);
	
}
