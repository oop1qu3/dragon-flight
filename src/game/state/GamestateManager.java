package game.state;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import game.util.KeyHandler;
import game.util.MouseHandler;
import game.util.Timer;

public class GamestateManager {

	private static GamestateManager gsm;
	private State state;
	
	private GamestateManager() {}
	
	public static GamestateManager getInstance() {
        if (gsm == null) {
            gsm = new GamestateManager();
        }
        return gsm;
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

	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
}