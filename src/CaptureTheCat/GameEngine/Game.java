package CaptureTheCat.GameEngine;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public static int WIDTH = 1400;
    public static int HEIGHT = 800;

    private Thread thread;
    private boolean running = false;

    public enum STATES {
        Menu,
        Help,
        Options,
        Storage,
        PetShop,
        Florest
    }

    public Game() {

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

    // CaptureTheCat.GameEngine.Game loop
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
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    public void tick() {

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

    }

    public static void main(String[] args) {
        new Game();
    }
}
