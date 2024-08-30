import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Pipe 
{
    int x;
    int y;
    int width;
    int height;

    public Pipe(int x, int y, int width, int height) 
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void drawPipe(Graphics g)
    {
        Image img = null;
        File imageFile = new File("pipes.png");
        try 
        {
            img = ImageIO.read(imageFile);
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        g.drawImage(img, this.x,this.y,this.width,this.height,null);
    }
}