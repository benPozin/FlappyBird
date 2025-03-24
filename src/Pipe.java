import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Pipe {
    private int x, height, width, gap;
    private boolean passed;
    private Image pipeUp, pipeDown;

    public Pipe(int x, int height, int gap) {
        this.x = x;
        this.height = height;
        this.width = 80;
        this.gap = gap;
        this.passed = false;
        pipeDown = new ImageIcon("resources/pipe.png").getImage();
        pipeUp = createFlippedImage(pipeDown);
    }

    private Image createFlippedImage(Image original) {
        BufferedImage flipped = new BufferedImage(original.getWidth(null), original.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = flipped.createGraphics();
        g.translate(0, original.getHeight(null));
        g.scale(1, -1);
        g.drawImage(original, 0, 0, null);
        g.dispose();
        return flipped;
    }

    public void move(int speed) {
        x -= speed;
    }

    public boolean isOffScreen() {
        return x + width < 0;
    }

    public boolean collidesWith(int bx, int by, int bSize) {
        return (bx + bSize > x && bx < x + width && (by < height || by + bSize > height + gap));
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public void draw(Graphics g) {
        int pipeImageHeight = pipeUp.getHeight(null); // Get image height for tiling

        // Draw top pipe properly tiled
        for (int y = 0; y < height; y += pipeImageHeight) {
            int drawHeight = Math.min(pipeImageHeight, height - y);
            g.drawImage(pipeUp, x, y, width, drawHeight, null);
        }

        // Draw bottom pipe properly tiled
        int bottomStart = height + gap;
        for (int y = bottomStart; y < 600; y += pipeImageHeight) { // 600 is assumed screen height
            int drawHeight = Math.min(pipeImageHeight, 600 - y);
            g.drawImage(pipeDown, x, y, width, drawHeight, null);
        }
    }
}