package game.state;

import java.awt.Graphics2D;
import java.util.ArrayList;

import game.util.KeyHandler;
import game.util.MouseHandler;

public class GamestateManager {

	private State state;

	public GamestateManager() {
//		state = new PlayState(this);
		state = new Intro(this);
	}

	public void update(double dt) {
		state.update(dt);
	}

	public void input(KeyHandler key, MouseHandler mouse) {
		state.input(key, mouse);
	}

	public void render(Graphics2D g) {
		state.render(g);
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

	// PR test
}
