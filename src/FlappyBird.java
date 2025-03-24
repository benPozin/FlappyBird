import javax.swing.*;

public class FlappyBird {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        frame.add(new GamePanel());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
