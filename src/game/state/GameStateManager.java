package game.state;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.TimerTask;

import game.entity.Enemy;
import game.util.KeyHandler;
import game.util.MouseHandler;

// @JW
import java.util.Timer;

public class GameStateManager {

	private ArrayList<GameState> states;

	// @JW
	private ArrayList<EnemyState> enemies;

	public GameStateManager() {
		states = new ArrayList<GameState>();
		states.add(new PlayState());

		// @JW
		enemies = new ArrayList<EnemyState>();
		enemies.add(new EnemyState());
	}

	public void start() {
		states.add(new PlayState());
	}

	public void update(double dt) {

		for (int i = 0; i < states.size(); i++) {
			if (states.get(i) != null)
				states.get(i).update(dt);
		}

		// @JW FIXME 일단은 GSM클래스 내에서 EnemyState 배열 선언 후 그 안에서 넣고 빼고 아래 함수 통해 관리중
		for(int i = 0; i < enemies.size(); i++) {

			if (enemies.get(i).isAlive())
				enemies.get(i).update(dt);
			else
			{
				enemies.remove(i);
				i--;

				// @JW FIXME 일단은 Timer 썼음
				Timer reGen = new Timer();
				reGen.schedule(
						new TimerTask(){
							@Override
							public void run(){
								enemies.add(new EnemyState());
								reGen.cancel();
							}
						}, (3000));
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

public void render(Graphics2D g) {

	for (int i = 0; i < states.size(); i++) {
		if (states.get(i) != null) {
			states.get(i).render(g);

		}
	}

	// @JW
	for(EnemyState i : enemies)
		i.render(g);

}

}