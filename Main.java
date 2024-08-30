/*Name : Benjamin Pozin
Date : 01/23/23
Course : ICS4U1
Teacher : Mr. Naccarato 
Program Summary : Knock-off flappy bird*/

import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.awt.Dimension;
class Main 
{
    public static void main(String[] args) 
    {
        //Creating two panels
        JSplitPane p;
        //Game panel
        Draw draw = new Draw();
        draw.setSize(320,220);
        //Score panel
        JPanel scorePanel = new JPanel();
        scorePanel.setSize(320,20);
        JFrame window = new JFrame("Flappy Bird");
        
        window.setSize(320,240);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Player losses
        JLabel playerLossesLabel = new JLabel("" + draw.getPlayerLosses() + "");
        playerLossesLabel.setLocation(0,0);
        playerLossesLabel.setSize(20,20);
        
        scorePanel.add(playerLossesLabel); 

        //Adding both panels
        p = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scorePanel, draw);
        window.add(p);
        window.setVisible(true);
        
        //Graphics Loop
        while(true)
        {
            draw.update();
            
            try
            {
                Thread.sleep(80);
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
          
            playerLossesLabel.setText("" + draw.getPlayerLosses() + "");
            
            if (draw.getPlayerLosses() == 5) 
            {
                //Pop-up message when player loses
                JOptionPane.showMessageDialog(draw, "Sorry, you lost :(");
            }
        }
    }
}