package Entities;

import CaptureTheCat.GameEngine.Game;
import CaptureTheCat.GameEngine.GameObject;
import CaptureTheCat.GameEngine.Handler;
import CaptureTheCat.GameEngine.ID;
import World.Map;
import images.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class NormalCat extends GameObject {

    private final Animation animLeft;
    private final Animation animRight;

    private final Random r = new Random();

    private final Handler handler;


    public NormalCat(Game game, float x, float y, ID id, Handler handler, Map map) {
        super(game, x, y, id, handler, map);

        this.handler = handler;

        BufferedImage[] poppySkin = new BufferedImage[6];

        int xPos  = 35;
        int yPos = 38;

        if (id == ID.SimpleRed) {
            // Load red skin
            //35 x 38
            poppySkin[0] = map.simpleCatSS.grabImage2(xPos, 0, 35, 38);
            poppySkin[1] = map.simpleCatSS.grabImage2(xPos * 2 , 0, 35, 38);
            poppySkin[2] = map.simpleCatSS.grabImage2(xPos * 3 , 0, 35, 38);

            poppySkin[3] = map.simpleCatSS.grabImage2(xPos, yPos, 35, 38);
            poppySkin[4] = map.simpleCatSS.grabImage2(xPos * 2, yPos, 35, 38);
            poppySkin[5] = map.simpleCatSS.grabImage2(xPos * 3, yPos, 35, 38);

        } else if (id == ID.SimpleBlue) {
            // Load blue skin

            poppySkin[0] = map.simpleCatSS.grabImage2(xPos, yPos * 2, 35, 38);
            poppySkin[1] = map.simpleCatSS.grabImage2(xPos * 2 , yPos * 2, 35, 38);
            poppySkin[2] = map.simpleCatSS.grabImage2(xPos * 3 , yPos * 2, 35, 38);

            poppySkin[3] = map.simpleCatSS.grabImage2(xPos, yPos * 3, 35, 38);
            poppySkin[4] = map.simpleCatSS.grabImage2(xPos * 2, yPos * 3, 35, 38);
            poppySkin[5] = map.simpleCatSS.grabImage2(xPos * 3, yPos * 3, 35, 38);

        } else if (id == ID.SimpleGreen) {
            // Load green skin
            poppySkin[0] = map.simpleCatSS.grabImage2(xPos, yPos * 4, 35, 38);
            poppySkin[1] = map.simpleCatSS.grabImage2(xPos * 2 , yPos * 4, 35, 38);
            poppySkin[2] = map.simpleCatSS.grabImage2(xPos * 3 , yPos * 4, 35, 38);

            poppySkin[3] = map.simpleCatSS.grabImage2(xPos, yPos * 5, 35, 38);
            poppySkin[4] = map.simpleCatSS.grabImage2(xPos * 2, yPos * 5, 35, 38);
            poppySkin[5] = map.simpleCatSS.grabImage2(xPos * 3, yPos * 5, 35, 38);

        }


        animLeft = new Animation(3, poppySkin[0], poppySkin[1], poppySkin[2]);
        animRight = new Animation(3, poppySkin[3], poppySkin[4], poppySkin[5]);

        velX = r.nextInt(4) - 2;
        velY = r.nextInt(4) - 2 ;

        if (velX == 0 & velY == 0) {
            velX = r.nextInt(3) ;
            velY = r.nextInt(3);
        }
    }

    public void tick() {

        x += velX;
        y += velY;



        if (velX > 0) {
            animRight.runAnimation();
        } else {
            animLeft.runAnimation();
        }

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Wall) {
                if (this.getBounds().intersects(tempObject.getBounds())) {
//                    System.out.println("hit");
                    velX = velX * -1;
                    velY = velY * -1;
                    break;
                }
            }
        }
//        System.out.println(velY);

    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.CYAN);
        g2d.draw(getBounds());
        if (velX > 0) {
            animRight.drawAnimation(g, x, y, 0);
        } else {
            animLeft.drawAnimation(g, x, y, 0);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 35, 38);
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
