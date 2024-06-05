package game.entity;

import java.awt.Graphics2D;
import java.util.List;

import javax.swing.ImageIcon;

public class Obstacle extends Entity {

    public static ImageIcon warning_1 = new ImageIcon("image/warning_stamp.gif");
    public static ImageIcon warning_2 = new ImageIcon("image/warning_sign_35.png");
    public static ImageIcon obstacle = new ImageIcon("image/meteo_4.gif");

    private double speed;

    private final int METEO_DELAY = 3000;
    private long lastTargetTime;

    private boolean trackEnd = false;       // @JW : warning -> obstacle

    public Obstacle(double playerX) {
        super(playerX , 0);
        this.speed = 500;

        lastTargetTime = System.currentTimeMillis();
    }

    public void move(double dt) {
        List<Player> players = gsm.state.getPlayers();

        for (Player p : players) {
        	if (System.currentTimeMillis() - lastTargetTime < METEO_DELAY)
                this.x += ((p.getX() - this.x) + 11) * 0.03; // interpolation
            else {
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
        
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double hitY() { // 메테오 히트박스는 gif 상 하단 끝부분
        return this.y + 120;
    }

    @Override
	public void update(double dt) {
		move(dt);
	}
    
    @Override
    public void render(Graphics2D g) {
        if (!trackEnd)
        {
            warning_1.paintIcon(null, g, (int) x, (int) y);
            warning_2.paintIcon(null, g, (int) x, (int) y);
        }
        else
            obstacle.paintIcon(null, g, (int) x, (int) y);
    }

	
}
