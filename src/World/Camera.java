package World;

import CaptureTheCat.GameEngine.Game;
import CaptureTheCat.GameEngine.GameObject;

public class Camera {
    private float x;
    private float y;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;

    }

    public void tick(GameObject object) {
//        System.out.println("x: " + object.getX());
        x = object.getX() - 700;
        y = object.getY() - 400;

//        System.out.println("x: " + x);
//        System.out.println("w: " + Game.WIDTH);
//        System.out.println("y: " + y);
//        System.out.println("h: " + Game.HEIGHT);

        if (x <= 0) {
            x = 0;
        }
        if (x + Game.WIDTH >= 64 * 32) {
            x = (64 * 32) - Game.WIDTH;
        }

        if (y <= 0) {
            y = 0;
        }
        if (y + Game.HEIGHT >= 40.5 * 32) {
            y = (float)(40.5 * 32) - Game.HEIGHT;
        }
    }

    public float getX() {
//        System.out.println(x);
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

}
