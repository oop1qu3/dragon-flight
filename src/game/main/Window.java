package game.main;

import static game.util.Constant.PanelConstant.*;

import javax.swing.JFrame;

public class Window extends JFrame {

	private GamePanel gp;

	public Window() {
		setTitle("Dragon Flight");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIgnoreRepaint(true);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void addNotify() {
		super.addNotify();

		gp = new GamePanel(PANEL_WIDTH, PANEL_HEIGHT);
		setContentPane(gp);
	}

}