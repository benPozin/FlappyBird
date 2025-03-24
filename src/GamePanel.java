import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    private final int WIDTH = 800, HEIGHT = 600;
    private final int GROUND_HEIGHT = 50;
    private final int PIPE_GAP = 150;
    private final int PIPE_SPEED = 5;
    private final int BIRD_SIZE = 40;

    private Timer timer;
    private Image background;
    private Bird bird;
    private ArrayList<Pipe> pipes;
    private int score = 0;
    private boolean gameOver = false;
    private Random rand;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        //setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE)
                    bird.jump();
                if (e.getKeyCode() == KeyEvent.VK_R && gameOver)
                    restartGame();
            }
        });

        loadImages();
        bird = new Bird(100, HEIGHT / 2, BIRD_SIZE); //Creates a new Bird in the middle of the screen
        pipes = new ArrayList<>();
        rand = new Random();
        spawnPipes();
        timer = new Timer(20, this);
        timer.start();
    }

    private void loadImages() {
        background = new ImageIcon("resources/background.png").getImage();
    }

    private void spawnPipes() {
        pipes.clear();

        int pipeSpacing = 300 + rand.nextInt(100); // Ensure enough horizontal space
        int minGapSize = 150; // Minimum gap to prevent impossible jumps
        int maxGapSize = 220; // Prevent too easy gaps

        for (int i = 0; i < 5; i++) {
            int x = WIDTH + i * pipeSpacing; // Ensure enough horizontal space

            // Make sure the height is within a reasonable range
            int minPipeHeight = 100;
            int maxPipeHeight = HEIGHT - GROUND_HEIGHT - maxGapSize - minPipeHeight;
            int pipeHeight = rand.nextInt(maxPipeHeight - minPipeHeight + 1) + minPipeHeight;

            int gapSize = minGapSize + rand.nextInt(maxGapSize - minGapSize + 1); // Random but fair gap

            pipes.add(new Pipe(x, pipeHeight, gapSize)); // Pass adjusted gap size
        }
    }


    private void restartGame() {
        bird.reset();
        pipes.clear();
        spawnPipes();
        score = 0;
        gameOver = false;
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            bird.update();
            for (int i = 0; i < pipes.size(); i++) {
                pipes.get(i).move(PIPE_SPEED);

                // Score updates when bird passes a pipe
                if (!pipes.get(i).isPassed() && bird.getX() > pipes.get(i).getX() + pipes.get(i).getWidth()) {
                    pipes.get(i).setPassed(true);
                    score++;
                }

                // Remove off-screen pipes and add new ones
                if (pipes.get(i).isOffScreen()) {
                    pipes.remove(i);
                    pipes.add(new Pipe(WIDTH, rand.nextInt(HEIGHT - GROUND_HEIGHT - PIPE_GAP - 100) + 100, PIPE_GAP));
                }
            }
            checkCollisions();
            repaint();
        }
    }

    private void checkCollisions() {
        if (bird.checkCollision(pipes)) {
            gameOver = true;
            timer.stop();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);

        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }

        bird.draw(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 20, 40);

        if (gameOver) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("Game Over! Press 'R' to Restart", WIDTH / 4, HEIGHT / 2);
        }
    }
}
