package CaptureTheCat.GameEngine;

import javax.sound.sampled.Line;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public enum ID {
    Player,
    Wall, SimpleRed, SimpleGreen, SimpleBlue;

    public static LinkedList<ID> SimpleCats() {
        LinkedList<ID> ids = new LinkedList<>();
        ids.add(SimpleRed);
        ids.add(SimpleGreen);
        ids.add(SimpleBlue);
        return ids;
    }
    public static ID[] RareCats() {
        return new ID[]{SimpleRed, SimpleBlue, SimpleGreen};
    }
    public static ID[] LegendaryCats() {
        return new ID[]{SimpleRed, SimpleBlue, SimpleGreen};
    }

}
