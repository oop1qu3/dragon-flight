package junk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class DragonFlight extends JFrame{

    // 캐릭터 위치값
    int xpos = 200;
    int ypos = 600;

    // resoultion
    public static final int SCREEN_WIDTH = 500;
    public static final int SCREEN_HEIGHT = 800;

    // 실사용 이미지
    private Image backImg_01 =new ImageIcon(DragonFlight.class.getResource("../source_screen/Fasr3fDWAAICkqZ.jpg")).getImage();
    private Image ch_01 =new ImageIcon(DragonFlight.class.getResource("../source_screen/ch_1.png")).getImage();

    //  더블버퍼링
    private Image scrImg;       // 도화지 변수
    private Graphics scrGpc;    // 도화지의 객체 변수


    public void MyFrame(){
        setTitle("Dragon Flight");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);


        setResizable(false);
        setLocationRelativeTo(null);    // 실행시 정중앙
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // 닫을시 프로그램 종료
        setVisible(true);

        // 키 처리, awt 오버라이딩
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        ypos -= 10;
                        break;
                    case KeyEvent.VK_DOWN:
                        ypos += 10;
                        break;
                    case KeyEvent.VK_LEFT:
                        xpos -= 10;
                        break;
                    case KeyEvent.VK_RIGHT:
                        xpos += 10;
                        break;
                }
            }
        });




    }


    // awt 오버라이딩, 컴포넌트의 그래픽을 그리는 용도, 자동으로 호출됨
    public void paint(Graphics g){
        // 오프스크린(백그라운드) 생성
        scrImg = createImage(SCREEN_WIDTH, SCREEN_HEIGHT);
        scrGpc = scrImg.getGraphics();
        scrDraw(scrGpc);
        // 오프스크린에 그린 내용을 실제 화면에 그림
        g.drawImage(scrImg, 0, 0, null);

    }

    // 자체제작, 오프스크린에 그리는 메소드
    public void scrDraw(Graphics g){
        // 선택한 이미지를 오프스크린에 그림
        g.drawImage(backImg_01, 0, 0, null);    // 마지막파라미터는 옵저버, 보통 null
        g.drawImage(ch_01, xpos, ypos, null);

        // 위의 paint 함수로 돌아감
        this.repaint();
    }


}
