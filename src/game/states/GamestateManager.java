package game.states;

import java.awt.Graphics2D;

import game.utils.KeyHandler;
import game.utils.MouseHandler;

public class GamestateManager {

	private State state;

	public void update(double dt) {
		state.update(dt);
	}

	public void render(Graphics2D g) {
		state.render(g);
	}

	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
}