package game.state;

import java.awt.Graphics;

import game.util.KeyHandler;
import game.util.MouseHandler;

public abstract class GameState {

	public abstract boolean stateUpdate(); // 원본 메소드명 : update()
	public abstract void input(KeyHandler key, MouseHandler mouse);
	public abstract void render(Graphics g);

}
