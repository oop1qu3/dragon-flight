// 아직 사용안한 클래스임

package junk;

import javax.swing.*;
import java.awt.*;

public class character extends JFrame{

    Toolkit imgTool = Toolkit.getDefaultToolkit();
    Image ch_drg = imgTool.getImage("../socure_screen/ch_1.png");


    public void paint(Graphics g){
        g.drawImage(ch_drg, 100, 100, this);
    }

}
