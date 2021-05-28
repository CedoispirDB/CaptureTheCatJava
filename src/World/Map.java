package World;

import CaptureTheCat.GameEngine.Game;
import CaptureTheCat.GameEngine.Handler;
import CaptureTheCat.GameEngine.ID;
import images.BufferedImageLoader;
import images.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Map {

//    Cat Measurement: var catWidth = 105; var catHeight = 114;

    private final Game game;
    private final Handler handler;

    private final BufferedImage florest;
    private final BufferedImage grass;
    public final SpriteSheet spriteSheet;
    public final SpriteSheet simpleCatSS;

    public Map(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;

        BufferedImageLoader loader = new BufferedImageLoader();

        // Load spriteSheet
        BufferedImage spriteSheetLayout = loader.loadImage("/SpriteSheet.png");
        spriteSheet = new SpriteSheet(spriteSheetLayout);

        // Load garden
        florest = loader.loadImage("/Florest.png");

        // Load grass
        grass = spriteSheet.grabImage(4, 2,32, 32);
        loadLevel(florest);

        // Load simple cat spriteSheet
        BufferedImage simpleSS = loader.loadImage("/NormalCatSS.png");
        simpleCatSS =  new SpriteSheet(simpleSS);


    }

    public void render(Graphics g) {
        for (int x = 0; x < 30 * 72; x += 32) {
            for (int y = 0; y < 30 * 72; y += 32) {
                g.drawImage(grass, x, y, null);

            }
        }

    }


    private void loadLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int pixel = image.getRGB(x, y);

                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 255) {
                    handler.addObject(new Wall(game, x * 32, y * 32, ID.Wall, handler, this));

                }
                if (blue == 255 && green == 0) {
//                        handler.addObject(new DemonKing(game, x * 20, y * 20, ID.Player, handler, hud, null, this));
                }


                if (green == 255 && blue == 0) {
//                    handler.addObject(new Enemy(this, x * 32, y * 32, ID.Enemy, handler, hud, ss));
                }

                if (green == 255 && blue == 255) {

                }

            }
        }

    }
}
