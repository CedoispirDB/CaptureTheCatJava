package Entities;

import CaptureTheCat.GameEngine.Game;
import CaptureTheCat.GameEngine.GameObject;
import CaptureTheCat.GameEngine.Handler;
import CaptureTheCat.GameEngine.ID;
import Player.Inventory;
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
    private GameObject player;
    private Inventory inventory;
    private final BufferedImage catDisplaySkins;
    private final BufferedImage catSittingRight;
    private final BufferedImage catSittingLeft;


    public NormalCat(Game game, float x, float y, ID id, Handler handler, Map map, Inventory inventory) {
        super(game, x, y, id, handler, map);

        this.handler = handler;
        this.inventory = inventory;

        BufferedImage[] catSkin = new BufferedImage[6];

        // Cat display skin
        int xPos = 35;
        int yPos = 38;

        if (id == ID.SimpleRed) {
            // Load red skin
            //35 x 38
            catSkin[0] = map.simpleCatSS.grabImage2(xPos, 0, 35, 38);
            catSkin[1] = map.simpleCatSS.grabImage2(xPos * 2, 0, 35, 38);
            catSkin[2] = map.simpleCatSS.grabImage2(xPos * 3, 0, 35, 38);

            catSkin[3] = map.simpleCatSS.grabImage2(xPos, yPos, 35, 38);
            catSkin[4] = map.simpleCatSS.grabImage2(xPos * 2, yPos, 35, 38);
            catSkin[5] = map.simpleCatSS.grabImage2(xPos * 3, yPos, 35, 38);

            catDisplaySkins = map.simpleCatSS.grabImage2(0, 38, 35, 38);

            catSittingRight = map.simpleCatSS.grabImage2(0, 0, 35, 38);
            catSittingLeft = map.simpleCatSS.grabImage2(0, yPos, 35, 38);


        } else if (id == ID.SimpleBlue) {
            // Load blue skin

            catSkin[0] = map.simpleCatSS.grabImage2(xPos, yPos * 2, 35, 38);
            catSkin[1] = map.simpleCatSS.grabImage2(xPos * 2, yPos * 2, 35, 38);
            catSkin[2] = map.simpleCatSS.grabImage2(xPos * 3, yPos * 2, 35, 38);

            catSkin[3] = map.simpleCatSS.grabImage2(xPos, yPos * 3, 35, 38);
            catSkin[4] = map.simpleCatSS.grabImage2(xPos * 2, yPos * 3, 35, 38);
            catSkin[5] = map.simpleCatSS.grabImage2(xPos * 3, yPos * 3, 35, 38);

            catDisplaySkins = map.simpleCatSS.grabImage2(0, 38 * 3, 35, 38);

            catSittingRight = map.simpleCatSS.grabImage2(0, yPos * 2, 35, 38);
            catSittingLeft = map.simpleCatSS.grabImage2(0, yPos * 3, 35, 38);


        } else if (id == ID.SimpleGreen) {
            // Load green skin
            catSkin[0] = map.simpleCatSS.grabImage2(xPos, yPos * 4, 35, 38);
            catSkin[1] = map.simpleCatSS.grabImage2(xPos * 2, yPos * 4, 35, 38);
            catSkin[2] = map.simpleCatSS.grabImage2(xPos * 3, yPos * 4, 35, 38);

            catSkin[3] = map.simpleCatSS.grabImage2(xPos, yPos * 5, 35, 38);
            catSkin[4] = map.simpleCatSS.grabImage2(xPos * 2, yPos * 5, 35, 38);
            catSkin[5] = map.simpleCatSS.grabImage2(xPos * 3, yPos * 5, 35, 38);

            catDisplaySkins = map.simpleCatSS.grabImage2(0, 38 * 5, 35, 38);

            catSittingRight = map.simpleCatSS.grabImage2(0, yPos * 4, 35, 38);
            catSittingLeft = map.simpleCatSS.grabImage2(0, yPos * 5, 35, 38);


        } else {
            catDisplaySkins = null;
            catSittingRight = null;
            catSittingLeft = null;
            System.out.println("skins are null");
        }


        animLeft = new Animation(3, catSkin[0], catSkin[1], catSkin[2]);
        animRight = new Animation(3, catSkin[3], catSkin[4], catSkin[5]);

        velX = r.nextInt(4) - 2;
        velY = r.nextInt(4) - 2;

        if (velX == 0 & velY == 0) {
            velX = r.nextInt(3);
            velY = r.nextInt(3);
        }


        for (GameObject temp : handler.object) {
            if (temp.getId() == ID.Player) {
                player = temp;
            }

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
            if (tempObject.getId() == ID.Wall || ID.SimpleCats().contains(tempObject.getId()) && this.getId() != tempObject.getId()) {
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
//        Graphics2D g2d = (Graphics2D) g;
//        g.setColor(Color.CYAN);
//        g2d.draw(getBounds());
        if (velX > 0) {
            animRight.drawAnimation(g, x, y, 0);
        } else {
            animLeft.drawAnimation(g, x, y, 0);
        }

        if (velX == 0 && velY == 0) {
            g.drawImage(catSittingRight, (int) x, (int) y, null);
            g.drawImage(catSittingLeft, (int) x, (int) y, null);

        }
    }

    public void renderInventory(Graphics g) {
        if (position == 1) {
            g.drawImage(catDisplaySkins, 45, 251, null);
        } else if (position == 2) {
            g.drawImage(catDisplaySkins, 45, 251 + 69, null);

        } else if (position == 3) {
            g.drawImage(catDisplaySkins, 45, 251 + 69 * 2, null);

        } else if (position == 4) {
            g.drawImage(catDisplaySkins, 45, 251 + 69 * 3, null);

        } else if (position == 5) {
            g.drawImage(catDisplaySkins, 45, 251 + 69 * 4, null);

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
