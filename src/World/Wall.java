package World;

import CaptureTheCat.GameEngine.Game;
import CaptureTheCat.GameEngine.GameObject;
import CaptureTheCat.GameEngine.Handler;
import CaptureTheCat.GameEngine.ID;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Wall extends GameObject {

    private final BufferedImage blockTexture;
    private final Map map;

    public Wall(Game game, float x, float y, ID id, Handler handler, Map map) {
        super(game, x, y, id, handler, map);

        this.map = map;

        blockTexture = map.spriteSheet.grabImage(5, 2, 32, 32);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(blockTexture, (int) x, (int) y, null);
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.green);
        g2d.draw(getBounds());

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public Rectangle getBounds2() {
        return null;
    }

    public Rectangle getBoundX() {
        return new Rectangle((int) x - 10, (int) y - 10, 55, 55);
    }

    public Rectangle getBoundY() {
        return new Rectangle((int) x - 10, (int) y - 15, 55, 65);
    }

}

