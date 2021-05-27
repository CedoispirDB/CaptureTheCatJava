package Player;

import CaptureTheCat.GameEngine.Game;
import CaptureTheCat.GameEngine.GameObject;
import CaptureTheCat.GameEngine.Handler;
import CaptureTheCat.GameEngine.ID;

import java.awt.*;

public class Player extends GameObject {

    private float x;
    private float y;
    private Game game;
    private Handler handler;

    public Player(Game game, float x, float y, ID id, Handler handler) {
        super(game, x, y, id, handler);

        this.x = x;
        this.y = y;
        this.game = game;
        this.handler = handler;


    }

    public void tick() {


        x += velX;
        y += velY;



        x = Game.clamp(x, 0, (64 * 32));
        y = Game.clamp(y, 0, (64 * 32));


    }

    public void render(Graphics g) {
        g.setColor(new Color(250, 0, 0));
        g.fillRect((int) x, (int) y, 32,32);
    }

    public Rectangle getBounds() {
        return null;
    }

    public Rectangle getBounds2() {
        return null;
    }

    public Rectangle getBoundX() {
        return null;
    }

    public Rectangle getBoundY() {
        return null;
    }
}
