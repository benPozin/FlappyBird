import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Bird {
    private int x, y, size, velocity;
    private final int GRAVITY = 2;
    private final int JUMP_STRENGTH = -15;
    private Image birdImage;

    public Bird(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.velocity = 0;
        birdImage = new ImageIcon("resources/bird.png").getImage();
    }

    public void jump() {
        velocity = JUMP_STRENGTH;
    }

    public void update() {
        velocity += GRAVITY;
        y += velocity;
    }

    public boolean checkCollision(java.util.List<Pipe> pipes) {
        for (Pipe pipe : pipes) {
            if (pipe.collidesWith(x, y, size)) {
                return true;
            }
        }
        return y >= 600 - 50 - size || y < 0;
    }

    public void reset() {
        y = 600 / 2;
        velocity = 0;
    }

    public int getX() {
        return x;
    }

    public void draw(Graphics g) {
        g.drawImage(birdImage, x, y, size, size, null);
    }
}
