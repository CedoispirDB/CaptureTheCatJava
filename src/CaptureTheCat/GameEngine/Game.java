package CaptureTheCat.GameEngine;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.util.Random;

import Entities.NormalCat;
import Movement.KeyInput;
import Movement.MouseInput;
import Player.Player;
import Player.Inventory;
import World.Camera;
import World.Map;

public class Game extends Canvas implements Runnable {

    public static int WIDTH = 1400;
    public static int HEIGHT = 800;

    private Thread thread;
    private boolean running = false;


    private final Handler handler;
    private final Map map;
    private final Camera camera;
    private final Inventory inventory;

    public enum STATES {
        Menu,
        Help,
        Options,
        Storage,
        PetShop,
        Florest
    }


    public Game() {

        Random r = new Random();

        handler = new Handler();
        map = new Map(this, handler);
        camera = new Camera(0, 0);
        inventory = new Inventory(handler, map);
        handler.addObject(new Player(this, 500, 500, ID.Player, handler, map));

        for (int i = 0; i < 7; i++) {
            handler.addObject(new NormalCat(this, r.nextInt(WIDTH - 32) + 32, r.nextInt(HEIGHT - 32) + 32, randomID(ID.SimpleCats()), handler, map, inventory));

        }
//        handler.addObject(new NormalCat(this, 500, 500, ID.SimpleRed, handler, map));


        this.addKeyListener(new KeyInput(handler, this, map, inventory));

        MouseInput mouseInput = new MouseInput(this, handler, map);
        this.addMouseListener(mouseInput);


        new Window(WIDTH, HEIGHT, "Capture The Cats", this);

    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Game loop
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
//                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    public void tick() {

        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ID.Player) {
                camera.tick(handler.object.get(i));
            }
        }
        handler.tick();

    }

    // Render everything
    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;


        g2d.translate(-camera.getX(), -camera.getY());



        map.render(g);

        handler.render(g);

        g2d.translate(camera.getX(), camera.getY());
        inventory.render(g);

        g.dispose();
        bs.show();
    }

    public static float clamp(float var, float min, float max) {
        if (var >= max) {
            return max;
        } else if (var <= min) {
            return min;
        }
        return var;
    }

    public static ID randomID(LinkedList<ID> IDList) {
        Random r = new Random();
        return IDList.get(r.nextInt(IDList.size()));
    }

    public static void main(String[] args) {
        new Game();
    }
}
