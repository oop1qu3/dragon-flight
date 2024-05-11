package game.main;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import javax.swing.ImageIcon;

public class Loader {
	
	// FIXME: ArrayList를 이용하여 한번에 관리
	public static void load() {
		try {
			Resource.backgroundMap = ImageIO.read(new File("image/backgroundMap.png"));
			Resource.player = ImageIO.read(new File("image/player.png"));
			
			//	Resource.enemy = ImageIO.read(new File("enemy_mov.gif"));
			Resource.enemy = new ImageIcon("image/enemy_mov.gif");	// gif는 ImageIcon 사용


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
