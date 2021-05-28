package Player;

import CaptureTheCat.GameEngine.Game;
import CaptureTheCat.GameEngine.GameObject;
import CaptureTheCat.GameEngine.Handler;

import java.util.LinkedList;

public class Inventory {

    private final int x, y, w, h;
    private final Handler handler;

    public LinkedList<GameObject> inventoryItems = new LinkedList<>();

    public Inventory (int x, int y, int w, int h, Handler handler) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.handler = handler;
    }
}
