package game.states;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.audio.AudioPlayer;
import game.entities.map.Background;

public class Intro extends State {
	
	private Background background;
	
	private BufferedImage logoImg;
	private BufferedImage illustImg;
	private BufferedImage introPhraseImg;
	
	private String logoImgPath = "images/intro/logo.png";
	private String illustImgPath = "images/intro/illust_01.png";
	private String introPhraseImgPath = "images/intro/phrase.png";
	
	private float illustImgTopY = -65;
	private float illustImgBottomY = -55;
	private float illustImgMoveSpeed = 15;
	private float illustImgY = -65;
	
	private boolean TOP = true;
	private boolean BOTTOM = false;
	private boolean STARTPLAY = false;
	
	public Intro() {
		background = new Background();
		loadImg();
	}
	
    public void loadImg() {
        try {
            logoImg = ImageIO.read(new File(logoImgPath));
            illustImg = ImageIO.read(new File(illustImgPath));
            introPhraseImg = ImageIO.read(new File(introPhraseImgPath));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    @Override
	public void update(double dt) {
		if (key.anyKey.isPressed()) {
			STARTPLAY = true;
		}
		
		background.move(dt);
		
		illustImgMove(dt);
			
		if (STARTPLAY == true) {
			Playing playing = game.getPlaying();
			
			playing.reset();
			playing.start();
			game.getAudioPlayer().playSong(AudioPlayer.PLAYING);
			gsm.setState(playing);
		}
	}
	
    @Override
	public void render(Graphics2D g) {
		background.render(g);
		
		g.drawImage(illustImg, 12, (int)illustImgY, illustImg.getWidth(), illustImg.getHeight(), null);
		g.drawImage(logoImg, 40, -5, 300, 200, null);
		g.drawImage(introPhraseImg, 50, 350, 300, 220, null);
	}
	
	public void illustImgMove(double dt) {
		if (TOP == true && BOTTOM == false) {
			illustImgY += illustImgMoveSpeed * dt;
			
			if (illustImgY >= illustImgBottomY) {
				TOP = false;
				BOTTOM = true;
			}
		}
		else if (TOP == false && BOTTOM == true) {
			illustImgY -= illustImgMoveSpeed * dt;
			
			if (illustImgY <= illustImgTopY) {
				TOP = true;
				BOTTOM = false;
			}	
		}
	}
}
