package game.main;

public class GameLauncher {

	public static void main(String[] args) {
		new GameLauncher();
	}

	public GameLauncher() {
		Loader.load();
		Engine.init();
		Engine.start();
	}
	
}