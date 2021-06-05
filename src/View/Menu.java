package View;

import CaptureTheCat.GameEngine.Game;
import CaptureTheCat.GameEngine.Handler;
import CaptureTheCat.GameEngine.ID;
import Entities.NormalCat;
import Player.Player;
import World.Map;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.util.Random;

import static CaptureTheCat.GameEngine.Game.randomID;

import Player.Inventory;

public class Menu extends MouseAdapter {

    private final Game game;
    private final Handler handler;
    private final Random r = new Random();
    private final Map map;
    private final Inventory inventory;


    public Menu(Game game, Handler handler, Map map, Inventory inventory) {
        this.game = game;
        this.handler = handler;
        this.map = map;
        this.inventory = inventory;


    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
//        System.out.println(mx);
        System.out.println("my: " + my);
        if (game.gameState == Game.STATES.Menu) {

            // Play button
//            if (mouseOver(mx, my, 611, 154, 200, 64)) {
//                game.gameState = Game.STATES.Florest;
//                handler.addObject(new Player(game, 722, 174, ID.Player, handler, map));
//                for (int i = 0; i < 10; i++) {
//                    handler.addObject(new NormalCat(game, r.nextInt(Game.WIDTH - 32) + 32, r.nextInt(Game.HEIGHT - 32) + 32, randomID(ID.SimpleCats()), handler, map, inventory));
//
//                }
//                return;
//            }
//
//            // Help Button
//
//            if (mouseOver(mx, my, 611, 253, 200, 64)) {
//                game.gameState = Game.STATES.Help;
//            }
//
//            // Quit button
//            if (mouseOver(mx, my, 611, 353, 200, 64)) {
//                System.exit(1);
//            }

        }

        //Back button for help
        if (game.gameState == Game.STATES.Help) {
            if (mouseOver(mx, my, (Game.WIDTH / 2) - 75, 350, 150, 64)) {
                game.gameState = Game.STATES.Menu;
            }
        }


    }

    public void mouseReleased(MouseEvent e) {
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void tick() {

    }

    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        float thickness = 2;
        Stroke oldStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(thickness));

        if (game.gameState == Game.STATES.Menu) {
            Font fnt = new Font("Serif", Font.BOLD, 60);
            Font fnt2 = new Font("arial", Font.BOLD, 35);
            Color bColor = new Color(159, 226, 191);


            g.setColor(bColor);
            g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

            g.setColor(new Color(153, 44, 44));
            g.fillRect(0, 20, Game.WIDTH, 95);

            String title = "CAPTURE THE CATS";
            FontRenderContext frc = g2d.getFontRenderContext();
            int titleWidth = (int) fnt.getStringBounds(title, frc).getWidth();
            int titleHeight = (int) fnt.getStringBounds(title, frc).getHeight();

            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString(title, (Game.WIDTH / 2) - (titleWidth / 2), 115 - (115 - (titleHeight)) / 2);

            // Buttons

            int gap = 100;
            int buttonWidth = 243;
            int buttonHeight = 78;
            int ox = (Game.WIDTH - buttonWidth) / 2;
            int oy = (Game.HEIGHT - buttonHeight) / 2;

//            System.out.println(oy);

            g.setColor(Color.WHITE);
            g.fillRect(ox, oy - gap, buttonWidth, buttonHeight);
            g.fillRect(ox, oy, buttonWidth, buttonHeight);
            g.fillRect(ox, oy + gap, buttonWidth, buttonHeight);

            int yi = oy + (int) fnt2.getStringBounds("Play", frc).getHeight() - 5 + ((buttonHeight - (int) fnt2.getStringBounds("Play", frc).getHeight()) / 2) - 1;

            g.setColor(Color.BLACK);
            g.setFont(fnt2);
            g.drawRect(ox, oy - gap, buttonWidth, buttonHeight);
            g.drawString("Play", ox + ((buttonWidth - (int) fnt2.getStringBounds("Play", frc).getWidth()) / 2), yi - gap);

            g.drawRect(ox, oy, buttonWidth, buttonHeight);
            g.drawString("Help", ox + ((buttonWidth - (int) fnt2.getStringBounds("Help", frc).getWidth()) / 2), yi);

            g.drawRect(ox, oy + gap, buttonWidth, buttonHeight);
            g.drawString("Quit", ox + ((buttonWidth - (int) fnt2.getStringBounds("Quit", frc).getWidth()) / 2), yi + gap);


        } else if (game.gameState == Game.STATES.Help) {
            Font fnt = new Font("SansSerif", Font.BOLD, 50);
            Font fnt2 = new Font("arial", Font.BOLD, 35);
            Font fnt3 = new Font("arial", Font.BOLD, 20);

            g.setColor(Color.gray.brighter());
            g.fillRect((Game.WIDTH / 2) - 170, 180, 399, 77);
            g.fillRect((Game.WIDTH / 2) - 75, 350, 150, 64);

            g.setFont(fnt);
            g.setColor(Color.BLACK);
            g.drawString("Help", (Game.WIDTH / 2) - 50, 70);

            g.drawRect((Game.WIDTH / 2) - 170, 180, 399, 77);

            g.setFont(fnt3);
            g.drawString("Use WASD keys to move player", (Game.WIDTH / 2) - 165, 200);
            g.drawString("Use the mouse right button to use spells", (Game.WIDTH / 2) - 165, 225);
            g.drawString("Use space to use a magic bomb", (Game.WIDTH / 2) - 165, 250);

            g.setFont(fnt2);
            g.drawRect((Game.WIDTH / 2) - 75, 350, 150, 64);
            g.drawString("Back", (Game.WIDTH / 2) - 40, 395);
        }
//             else if (game.gameState == Game.STATES.End) {
//            Font fnt = new Font("arial", Font.BOLD, 50);
//            Font fnt2 = new Font("arial", Font.BOLD, 35);
//
//            g.setFont(fnt);
//            g.setColor(Color.white);
//            g.drawString("You died", (Game.WIDTH / 2) - 100, (Game.HEIGHT / 2) - 100);
//
//            g.setFont(fnt2);
//            g.drawRect((Game.WIDTH / 2) - 90, 350, 200, 64);
//            g.drawString("Try Again", (Game.WIDTH / 2) - 70, 395);
//
//        }
//        g2d.setStroke(oldStroke);


    }


}
