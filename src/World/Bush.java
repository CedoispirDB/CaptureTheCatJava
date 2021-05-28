package World;

import CaptureTheCat.GameEngine.Game;
import CaptureTheCat.GameEngine.GameObject;
import CaptureTheCat.GameEngine.Handler;
import CaptureTheCat.GameEngine.ID;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bush extends GameObject {

    private BufferedImage bushTexture;
    private Map map;

    public Bush(Game game, float x, float y, ID id, Handler handler, Map map) {
        super(game, x, y, id, handler, map);

        this.map = map;

        bushTexture = map.spriteSheet.grabImage(6, 2, 32, 32);

    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(bushTexture, (int) x, (int) y, null);
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
