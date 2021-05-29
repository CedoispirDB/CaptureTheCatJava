package Player;

import CaptureTheCat.GameEngine.Game;
import CaptureTheCat.GameEngine.GameObject;
import CaptureTheCat.GameEngine.Handler;
import CaptureTheCat.GameEngine.ID;
import World.Map;
import images.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject {

    private Game game;
    private Handler handler;
    private Map map;

    private final BufferedImage[] playerSkin = new BufferedImage[3];
    private Animation anim;


    public Player(Game game, int x, int y, ID id, Handler handler, Map map) {
        super(game, x, y, id, handler, map);

        this.game = game;
        this.handler = handler;
        this.map = map;

        //140 x 220
        // 76 x 33
        // 54 x 25

        playerSkin[0] = map.spriteSheet.grabImage(1, 1, 32, 48);
        playerSkin[1] = map.spriteSheet.grabImage(2, 1, 32, 48);
        playerSkin[2] = map.spriteSheet.grabImage(3, 1, 32, 48);


//
        anim = new Animation(3, playerSkin[0], playerSkin[1], playerSkin[2]);


    }

    public void tick() {

        x += velX;
        y += velY;

        x = Game.clamp(x, 0, (64 * 32));
        y = Game.clamp(y, 0, (64 * 32));


        collision();
        anim.runAnimation();
    }

    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

//        g.drawImage(map.testPlayer.grabImage2(0 ,0, 30, 75), (int) x, (int) y, null);

        if (velX == 0 && velY == 0) {
            g.drawImage(playerSkin[0], (int) x, (int) y, null);
        } else {
            anim.drawAnimation(g, x, y, 0);

        }

    }

    private void collision() {

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Wall) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    x += velX * -1;
                    y += velY * -1;
                }
            }
        }
    }

    public Rectangle getBounds() {
        float bx = x + velX + 2;
        float by = y;
        float bw = 32 + (velX - 5) / 2;
        float bh = 48;

        return new Rectangle((int) bx, (int) by, (int) bw, (int) bh);
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
