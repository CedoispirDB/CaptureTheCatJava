package CaptureTheCat.GameEngine;

import java.util.ArrayList;
import java.util.Arrays;

public enum ID {
    Player,
    Wall, SimpleRed, SimpleGreen, SimpleBlue;

    public static ID[] SimpleCats() {
        return new ID[]{SimpleRed, SimpleBlue, SimpleGreen};
    }
    public static ID[] RareCats() {
        return new ID[]{SimpleRed, SimpleBlue, SimpleGreen};
    }
    public static ID[] LegendaryCats() {
        return new ID[]{SimpleRed, SimpleBlue, SimpleGreen};
    }

}
