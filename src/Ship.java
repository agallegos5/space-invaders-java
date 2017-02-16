import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Ship extends MovingSprite
{
    private int HP;
    private ImageIcon S1;      //static
    private ImageIcon S2;       //moving
    private ImageIcon S3;      //destroyed
    protected boolean moving;
    protected boolean destroyed;
    
    
    Ship(int xpos, int ypos)
    {
       super(xpos, ypos);
       S3 = new ImageIcon("assets/ship_explosion.png");
        S1 = new ImageIcon("assets/ship2.png");
        S2 = new ImageIcon("assets/ship_move.png");
        HP = 100;
       moving = false;
       
        c_box.x = x + 15;
        c_box.y = y + 35;  
       dx = 0;
       dy = 0;
        height = S1.getIconHeight();
        width = S1.getIconWidth();
        c_box.height = height - 80;
        c_box.width = width - 20;
        try
        {
            //allow the width and height to get initialized
            Thread.sleep(60);
        }catch(Exception e)
        {}
    }
    
    public void update()
    {
        x+=dx;
        y+=dy;
        c_box.x = x + 10;
        c_box.y = y + 40;        
    }
    
    public void outOfScreen()
    {
        //kill its velocity
        dx = dy = 0;
        
        //set coordinates to outside of the screen

        c_box.x = 2000;
        c_box.y = 2000;
        x = y = c_box.x;
    }
    
    //take a hit
    public void getHit()
    {
        HP+=-10;
        if(HP <=0)
        {
            destroyed =true;
        }
    }
    public void draw(Graphics g)
    {
        //fill the collision box (testing purposes)
  //      g.fillRect(c_box.x,c_box.y, c_box.width, c_box.height);
        if(destroyed)
        {
            g.drawImage(S3.getImage(), x, y, null);
        }
        if(moving && !destroyed)
        {
            //moving sprite
            g.drawImage(S2.getImage(), x, y, null);
        }
        if(!destroyed && !moving)
        {
            //static sprite
            g.drawImage(S1.getImage(), x, y, null);
        }
    }
    
    
}
