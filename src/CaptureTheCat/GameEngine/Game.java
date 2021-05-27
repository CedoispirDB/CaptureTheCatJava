package CaptureTheCat.GameEngine;

import java.awt.*;
import java.awt.image.BufferStrategy;

import Movement.KeyInput;
import Movement.MouseInput;
import Player.Player;
import World.Map;

public class Game extends Canvas implements Runnable {

    public static int WIDTH = 1400;
    public static int HEIGHT = 800;

    private Thread thread;
    private boolean running = false;

    private final Handler handler;
    private final Map map;

    public enum STATES {
        Menu,
        Help,
        Options,
        Storage,
        PetShop,
        Florest
    }


    public Game() {

        handler = new Handler();
//        handler.addObject(new Player(this, 500, 500, ID.Player, handler));
        map = new Map(this, handler);

        this.addKeyListener(new KeyInput(handler, this, map));

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

    // CaptureTheCat.CaptureTheCat.Player.Player.GameEngine.Player.Player.GameEngine.Game loop
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

        handler.render(g);
        map.render(g);
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

    public static void main(String[] args) {
        new Game();
    }
}
