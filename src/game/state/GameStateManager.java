package game.state;

import java.awt.Graphics;
import java.util.ArrayList;

import game.util.KeyHandler;
import game.util.MouseHandler;

//
import java.util.Timer;
import java.util.TimerTask;

public class GameStateManager {

    private ArrayList<GameState> states;

    public GameStateManager() {
        states = new ArrayList<GameState>();    // state 리스트 객체생성

        states.add(new PlayState());    // player state 객체를 리스트에 추가
        states.add(new EnemyState());   // enemy state 객체를 리스트에 추가
    }

    // FIXME 몬스터 state 지우는거를 enemystate에서? 여기서하는건 좀 이상한데
    public void listUpdate() {
        for (int i = 0; i < states.size(); i++) {
            //if (states.get(i) != null)
            //states.get(i).stateUpdate();

            if(!(states.get(i).stateUpdate()))      // FIXME
            {
                states.remove(i);   // 삭제되기 때문에 뒤에있던 원소 앞으로 쉬프트됨
                i--;                // 그래서 인덱스 하나 줄임

                // FIXME
                // Timer 객체 이용하여 3초후에 EnemyState 재생성
                {   
                    Timer reGen = new Timer();
                    reGen.schedule(
                            new TimerTask(){
                                @Override
                                public void run(){
                                    states.add(new EnemyState());
                                    reGen.cancel();
                                }}, 3000);
                }

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
