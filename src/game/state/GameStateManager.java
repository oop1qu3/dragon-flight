package game.state;

import java.awt.Graphics;
import java.util.ArrayList;

import game.util.KeyHandler;
import game.util.MouseHandler;

public class GameStateManager {
	
	private ArrayList<GameState> states;
	
	public GameStateManager() {
		states = new ArrayList<GameState>();    // state 리스트 객체생성
		
		states.add(new PlayState());    // player state 객체를 리스트에 추가
        states.add(new EnemyState());   // enemy state 객체를 리스트에 추가
	}
	
	public void update() {
		for (int i = 0; i < states.size(); i++) {
            if (states.get(i) != null) {
                states.get(i).update();
            }
        }
	}
	
	public void input(KeyHandler key, MouseHandler mouse) {
		for (int i = 0; i < states.size(); i++) {
            if (states.get(i) != null) {
                states.get(i).input(key, mouse);
            }
        }     
	}
	
	public void render(Graphics g) {
        // g.setFont(GamePanelManager.fontf.getFont("MeatMadness"));
        for (int i = 0; i < states.size(); i++) {
            if (states.get(i) != null) {
                states.get(i).render(g);
            }
        }
    }
	
}
