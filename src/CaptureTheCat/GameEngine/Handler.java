package CaptureTheCat.GameEngine;

import java.awt.*;
import java.util.LinkedList;

public class Handler {
    public LinkedList<GameObject> object = new LinkedList<>();

    public Handler() {

    }


    public void tick() {
        for (GameObject tempObject : object) {
            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i< object.size(); i++) {
            object.get(i).render(g);
        }

    }


    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }


    public void clear() {
        if (object.size() > 0) {
            this.object.clear();
        }
    }

}
