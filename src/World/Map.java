package World;

import CaptureTheCat.GameEngine.Game;
import CaptureTheCat.GameEngine.Handler;
import images.BufferedImageLoader;
import images.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Map {

//    Cat Measurement: var catWidth = 105; var catHeight = 114;

    private final Game game;
    private final Handler handler;

    private final BufferedImage garden;
    private final BufferedImage grass;
    private final SpriteSheet spriteSheet;

    public Map(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;

        BufferedImageLoader loader = new BufferedImageLoader();

        // Load spriteSheet
        BufferedImage spriteSheetLayout = loader.loadImage("/SpriteSheet.PNG");
        spriteSheet = new SpriteSheet(spriteSheetLayout);

        // Load garden
        garden = loader.loadImage("/Florest.png");

        // Load grass
        grass = spriteSheet.grabImage2(420, 0, 160, 160);

    }

    public void render(Graphics g) {
        for (int x = 0; x < 30 * 72; x += 32) {
            for (int y = 0; y < 30 * 72; y += 32) {
                g.drawImage(grass, x, y, null);

            }
        }

    }
}
