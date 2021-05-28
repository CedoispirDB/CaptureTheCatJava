package Movement;

import CaptureTheCat.GameEngine.Game;
import CaptureTheCat.GameEngine.GameObject;
import CaptureTheCat.GameEngine.Handler;
import CaptureTheCat.GameEngine.ID;
import Entities.NormalCat;
import Player.Player;
import World.Map;
import images.SpriteSheet;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private final Handler handler;
    private final boolean[] keyDown = new boolean[4];
    private int dir;
    private final Game game;
    protected SpriteSheet ss;
    private Map map;
    private GameObject player;
    private GameObject[] cats;

    private int previousKey = 0;

    public KeyInput(Handler handler, Game game, Map map) {
        this.handler = handler;
        this.game = game;
        this.map = map;

        // 'w' key
        keyDown[0] = false;
        // 's' key
        keyDown[1] = false;
        // 'd' key
        keyDown[2] = false;
        // 'a' key
        keyDown[3] = false;

        for (GameObject temp : handler.object) {
            if (temp.getId() == ID.Player) {
                player = temp;
            }

        }

    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Player) {
                //All keys events for player 1

                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                    tempObject.setVelY(-5);
                    keyDown[0] = true;
                    dir = 0;

                }

                if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                    tempObject.setVelY(5);
                    keyDown[1] = true;
                    dir = 1;
                }
                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                    tempObject.setVelX(5);
                    keyDown[2] = true;
                    dir = 2;
                }
                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                    tempObject.setVelX(-5);
                    keyDown[3] = true;
                    dir = 3;
                }


            }

        }

        if (key == KeyEvent.VK_SPACE) {
            for (GameObject temp : handler.object) {
                if (temp.getId() == ID.SimpleRed || temp.getId() == ID.SimpleBlue || temp.getId() == ID.SimpleGreen) {
                    if (player.getBounds().intersects(temp.getBounds())) {
                        System.out.println("Get cat");
                    }
                }
            }

        }

        // Debugg
        if (key == KeyEvent.VK_L) {
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject cat = handler.object.get(i);
                if(cat.getId() == ID.SimpleRed) {
                    if (cat.getVelY() == 5){
                        cat.setVelY(1);
                    } else if (cat.getVelY() == -5){
                         cat.setVelY(-1);
                    }  else if (cat.getVelY() == 1){
                        cat.setVelY(5);
                    }  else if (cat.getVelY() == -1){
                        cat.setVelY(-5);
                    }
                }
            }
        }
        if (key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_Q) {
            System.exit(1);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Player) {
                //All keys events for player 1
                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                    keyDown[0] = false;
                }
                if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                    keyDown[1] = false;
                }
                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                    keyDown[2] = false;
                }
                if (key == KeyEvent.VK_A | key == KeyEvent.VK_LEFT) {
                    keyDown[3] = false;
                }

                // Vertical movement
                if (!keyDown[0] && !keyDown[1]) {
                    tempObject.setVelY(0);
                }
                // Horizontal movement

                if (!keyDown[2] && !keyDown[3]) {
                    tempObject.setVelX(0);
                }


            }
        }
    }
}
