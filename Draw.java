import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.Random;

public class Draw extends JPanel implements KeyListener
{
    Bird bird;
    Pipe pipe1;
    Pipe pipe2;
    int playerLosses;
    
    public Draw()
    {
        //Constructor
        super();
        super.setFocusable(true);
        super.addKeyListener(this);
        this.bird = new Bird(10,114,30,30);
        this.pipe1 = new Pipe(250,0,30,50);
        this.pipe2 = new Pipe(300,190,30,50);
        this.playerLosses = 0;
    }

    //Function for drawing components
    public void paint(Graphics g)
    {
        g.clearRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.lightGray);
        g.fillRect(0,0,320,240);
        g.setColor(Color.black);
        bird.drawBird(g);
        pipe1.drawPipe(g);
        pipe2.drawPipe(g);
    }

    //Function for getting player losses
    public int getPlayerLosses()
    {
        return this.playerLosses;
    }

    //Function for checking if the pipes touch the bird**
    public Boolean intersects (int obj1x1, int obj1x2, int obj1y1, int obj1y2, int obj2x1, int obj2x2, int obj2y1, int obj2y2)
    {
        Boolean intersects = false;
        if ((obj1x1 >= obj2x1 && obj1x1 <= obj2x2 || 
            obj1x2 >= obj2x1 && obj1x2 <= obj2x2) && 
            (obj1y1 >= obj2y1 && obj1y1 <= obj2y2 || 
            obj1y2 >= obj2y1 && obj1y2 <= obj2y2))
            {
                intersects = true;
            }
        return intersects;
    }

    //Main function for controlling the bird, monitoring the losses, and switching the pipe height and x coordinate
    public void update()
    { 
        Random rand = new Random();
        int randInt = rand.nextInt(15);

        bird.y += 1;

        pipe1.x -= 2;  
        pipe2.x -= 4;   
        
        if (randInt == 1)
        {
            
            if (pipe1.x < 0)
            {
                pipe1.height = 115;
                pipe2.y = 240 - pipe2.height;
                pipe1.x = 150;
            }
        }
        
        if (randInt == 2)
        {
            
            if (pipe1.x < 0)
            {
                pipe1.height = 110;
                pipe2.y = 240 - pipe2.height;
                pipe1.x = 200;
            }
        }
        
        if (randInt == 3)
        {
            
            if (pipe1.x < 0)
            {
                pipe1.height = 105;
                pipe2.y = 240 - pipe2.height;
                pipe1.x = 250;
            }
        }
    
        if (randInt == 4)
        {
            if (pipe2.x < 0)
            {
                pipe2.height = 100;
                pipe2.y = 240 - pipe2.height;
                pipe2.x = 300;
            }
        }

        if (randInt == 5)
        {
            if (pipe2.x < 0)
            {
                pipe2.height = 95;
                pipe2.y = 240 - pipe2.height;
                pipe2.x = 320;
            }
        }

        //Checking if the pipes and the ball are intersecting
        if (bird.y <=0 || bird.y+bird.height>=240 ||
            intersects(bird.x, bird.x+bird.width, 
                       bird.y, bird.y+bird.height, 
                       pipe1.x, pipe1.x+pipe1.width, 
                       pipe1.y, pipe1.y+pipe1.height) || 
            intersects(bird.x, bird.x+bird.width, 
                        bird.y, bird.y+bird.height, 
                        pipe2.x, pipe2.x+pipe2.width, 
                        pipe2.y, pipe2.y+pipe2.height))
        {
            playerLosses++;
            bird.y = 114;
            bird.x = 10;
            pipe1.x = 250;
            pipe2.x = 300;            
    }
       else
        {
            //if they are not, the pipes continue to move
            pipe1.x--;
            pipe2.x-=2;
        }
        repaint();
    }

    //If user presses W, the bird goes up, if not then the bird goes down
    @Override
    public void keyPressed(KeyEvent arg0)
    {
        if(arg0.getKeyCode()== KeyEvent.VK_W)
        {
            if (bird.y >=0)
            {
                bird.y -= 5;
            }
            else
            {
                bird.y = 0;
            }
            
        }
        else
        {
            bird.y += 2;
        }
    }
     
    @Override
    public void keyReleased(KeyEvent arg0)
    {
    
    }
    @Override
    public void keyTyped(KeyEvent arg0)
    {
    
    }
}
