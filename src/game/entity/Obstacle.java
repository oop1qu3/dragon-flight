package game.entity;

import game.state.GameState;
import game.state.PlayState;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import static java.lang.Math.abs;

public class Obstacle extends Entity{

    public static ImageIcon obstacle = new ImageIcon("image/meteo_4.gif");
    private double speed;

    public Obstacle(int x) {
        super(x - 15, -300, 60, 200);
        this.speed = 500;
    };

    public void move(double dt) {
        y += this.speed * dt;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double hitY(){       // @JW : 메테오 히트박스는 gif상 하단 끝부분
        return this.y + 120;
    }

    public void render(Graphics g) {
        obstacle.paintIcon(null, g, (int)x, (int)y);
    }
}
