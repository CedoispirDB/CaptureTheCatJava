package CaptureTheCat.GameEngine;
import World.Map;

import java.awt.*;

public abstract class GameObject {

    protected float x, y;
    protected ID id;
    protected float velX, velY;
    private Handler handler;
    private float h;
    private float w;

    public GameObject(Game game, float x, float y, ID id, Handler handler, Map map) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

    public abstract Rectangle getBounds2();

    public abstract  Rectangle getBoundX();

    public abstract  Rectangle getBoundY();

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public float getVelX() {
        return velX;
    }

    public float getVelY() {
        return velY;
    }

    public float getW(){
        return w;
    }

    public float getH(){
        return h;
    }

}
