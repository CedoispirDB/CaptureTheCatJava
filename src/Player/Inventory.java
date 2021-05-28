package Player;

import CaptureTheCat.GameEngine.Game;
import CaptureTheCat.GameEngine.GameObject;
import CaptureTheCat.GameEngine.Handler;
import CaptureTheCat.GameEngine.ID;
import World.Map;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Inventory {

    private final Handler handler;
    private final BufferedImage[] catDisplaySkins;

    public LinkedList<GameObject> inventoryItems = new LinkedList<>();

    public Inventory(Handler handler, Map map) {
        this.handler = handler;

        // Cat display skin
        catDisplaySkins = new BufferedImage[3];

        // Red
        catDisplaySkins[0] = map.simpleCatSS.grabImage2(0, 38, 35, 38);
        // Blue
        catDisplaySkins[1] = map.simpleCatSS.grabImage2(0, 38 * 3, 35, 38);
        // Green
        catDisplaySkins[2] = map.simpleCatSS.grabImage2(0,  38 * 5,35,  38);

    }

    public void getItem(GameObject obj) {
            inventoryItems.add(obj);
            obj.setPosition(inventoryItems.size());
    }

    public GameObject removeItem() {
        if (inventoryItems.size() != 0) {
            GameObject temp = inventoryItems.get(0);
            inventoryItems.remove(temp);
            return temp;
        }
        return null;
    }


    public void render(Graphics g) {



        g.setColor(new Color((float) 0, (float) 0, (float) 0, (float) 0.3));
        g.fillRect(25, 234 , 78 ,353);
        g.setColor(new Color( (float) 1, (float) 1, (float) 1, (float) 0.8));
        int r = 30;
        int w = 64;
        int h = 64;
        g.fillRect(32, 270 - r, w, h);
        g.fillRect(32, 275 + h - r,w, h);
        g.fillRect(32, 280 + 2 * h - r, w, h);
        g.fillRect(32, 285 + 3 * h - r, w, h);
        g.fillRect(32, 290 + 4 * h - r, w, h);


//        g.drawImage(catDisplaySkins[0], 45, 251 + 69 * inventoryItems.size(), null);
//        g.drawImage(catDisplaySkins[0], 45, 320, null);
//        g.drawImage(catDisplaySkins[0], 45, 389, null);
//        g.drawImage(catDisplaySkins[0], 45, 458, null);
//        g.drawImage(catDisplaySkins[0], 45, 527, null);


        for(GameObject temp : inventoryItems) {
            temp.renderInventory(g);
        }

    }
}
