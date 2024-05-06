package game.state;

import java.awt.Graphics;

import game.util.KeyHandler;
import game.util.MouseHandler;

public abstract class GameState {

	private GameStateManager gpm;

	public GameState(GameStateManager gpm) {
		this.gpm = gpm;
	}

	public abstract void update(); // FIXME dt를 인자로 넘겨주기
	public abstract void input(KeyHandler key, MouseHandler mouse);
	public abstract void render(Graphics g);

}
