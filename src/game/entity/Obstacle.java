package game.entity;

import static game.util.Constant.PanelConstant.*;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import game.util.Timer;

public class Obstacle extends Entity {

    public static ImageIcon warning_1 = new ImageIcon("image/warning_stamp.gif");
    public static ImageIcon warning_2 = new ImageIcon("image/warning_sign_35.png");
    public static ImageIcon obstacle = new ImageIcon("image/meteo_4.gif");
    
    private Timer trackTimer = new Timer(4.0, this);

    private boolean trackEnd = false;  // @JW : warning -> obstacle

    public Obstacle(float playerX) {
        super(playerX , 0);
        this.speed = 500;
    }

    public void move(double dt) {
        Player p = gsm.getState().getPlayers().get(0);

    	if (!trackTimer.isOver()) {
            this.x += ((p.getX() - this.x) + 11) * 0.03;  // interpolation
    	} else {
            if (!trackEnd) {        // @JW : warning 딜레이가 끝나고, trackEnd가 바뀌기 직전;
                this.x -= 23;
                this.y = -300;
                trackEnd = true;
            }
            else {
                this.y += this.speed * dt;
            }
        }
    }

    public float hitY() { // 메테오 히트박스는 gif 상 하단 끝부분
        return this.y + 120;
    }

    @Override
	public void update(double dt) {
		move(dt);
		
		if (isOut()) {
			gsm.getState().getObstacles().remove(this);
		}
	}
    
    private boolean isOut() {
        return y > PANEL_HEIGHT;
    }
    
    @Override
    public void render(Graphics2D g) {
        if (!trackEnd) {
            warning_1.paintIcon(null, g, (int) x, (int) y);
            warning_2.paintIcon(null, g, (int) x, (int) y);
        } else {
            obstacle.paintIcon(null, g, (int) x, (int) y);
        }
    }

	
}
