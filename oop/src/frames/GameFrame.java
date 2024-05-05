package frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameFrame extends JFrame {
	ImageIcon backGround = new ImageIcon("img/map/01.png");
	Image backGroundImg = backGround.getImage();
	int backGroundImgHeight = backGroundImg.getHeight(null);
	private int WIDTH = 450;
    private int HEIGHT = 600;
    private int backGroundY = 0;
    
    public GameFrame() {
    	setTitle("Dragon Flight");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        
        JPanel backGroundPanel = new BackGround();
    	c.add(backGroundPanel, BorderLayout.CENTER);
        
    	setSize(WIDTH, HEIGHT);
        setVisible(true);
    }
    
    class BackGround extends JPanel {
    	public BackGround() {
    		new Thread(new Runnable() {
    			public void run() {
    				while (true) {
    					backGroundY++;
    					if (backGroundY == backGroundImgHeight) {
    						backGroundY = 0;
    					}
    					repaint();
    					try {
    						Thread.sleep(20);
    						
    					}catch(InterruptedException e) {
    						e.printStackTrace();
    					}
    				}
    			}
    		}).start();
    	};
    	
    	public void paintComponent(Graphics g) {
    		super.paintComponent(g);
    		
    		g.drawImage(backGroundImg, 0, backGroundY, this);
    		
    		if (backGroundY >= 0) {
				g.drawImage(backGroundImg, 0, backGroundY - backGroundImgHeight, this);  
    		}
    	}
    }
}