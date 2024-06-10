package game;

import game.main.Game;
import game.states.GamestateManager;
import game.utils.KeyHandler;
import game.utils.MouseHandler;

public class GameObject {

	protected static Game game;
	
	protected static KeyHandler key;
	protected static MouseHandler mouse;
	protected static GamestateManager gsm;
	
    public static void setGame(Game game) {
    	GameObject.game = game;
    
		key = game.getGamePanel().getKey();
		mouse = game.getGamePanel().getMouse();
		gsm = game.getGamePanel().getGamestateManager();
    }
    
}
