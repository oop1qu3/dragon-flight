package game.states;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.entities.map.Background;
import game.ui.GameoverButton;

public class Gameover extends State { 
	private Background background;
	private GameoverButton button;
	
	private static BufferedImage sdImg;
	private static BufferedImage gameOverPhraseImg;
	
	private static String sdImgPath = "images/gameover/sd_01.png";
	private static String gameOverPhraseImgPath = "images/gameover/gameover.png";
	
	private boolean REPLAY = false;
	
	public Gameover() {
		background = new Background();
		button = new GameoverButton();
		loadImg();
	}
	
    public void loadImg() {
        try {
            sdImg = ImageIO.read(new File(sdImgPath));
            gameOverPhraseImg = ImageIO.read(new File(gameOverPhraseImgPath));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void update(double dt) {
		background.move(dt);
		button.update();
	}
	
	public void render(Graphics2D g) {
		background.renderDarker(g);
		
		g.drawImage(sdImg, 110, 10, 180, 200, null);
		g.drawImage(gameOverPhraseImg, -160, 20, 700, 500, null);
		button.render(g);
	}
}
