package game.main;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Loader {
	
	public static void load() {
		try {
			Resource.backgroundMap = ImageIO.read(new File("image/backgroundMap.png"));
			Resource.player = ImageIO.read(new File("image/player.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
