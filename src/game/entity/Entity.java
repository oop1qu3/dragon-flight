package game.entity;

// import java.awt.Rectangle;

public abstract class Entity /*extends Rectangle*/ {
	
    protected boolean right = false;
    protected boolean left = false;

    //
    public int hp;
    public boolean isAlive;

    public int x,y,width,height;

    public Entity(int x, int y, int width, int height, int hp, boolean isAlive){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hp = hp;
        this.isAlive =isAlive;
    }

//    public Entity(int x, int y, int width, int height) {
//    	this(x, y, width, height);
//    }
//


}
