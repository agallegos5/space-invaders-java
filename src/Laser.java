import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Laser extends MovingSprite
{
    //in case a laser is fired by an alien the value will change to true
    //and the color will change
    protected boolean AlienLaser;
    Laser(int xpos, int ypos)
    {
        super(xpos, ypos);
        
        width = 5;
        height = 20;
        
        AlienLaser = false;
        //set new parameters for the collision box of this object
        c_box.x = x;
        c_box.y = y;
        c_box.width = width;
        c_box.height = height;
        
        //lasers have no horizontal velocity
        dx = 0;
        dy = 3;
    }
    
    //move laser
    public void update()
    {
        y+=-dy;
        c_box.x = x;
        c_box.y = y;
    }
    
    //move alien lasers
    public void updateEnemyLasers()
    {
        y+=dy;
        c_box.x = x;
        c_box.y = y;
    }
    public boolean hit(Rectangle r)
    {
        if(this.intersects(r) == true)
            return true;
        else
            return false;
    }
    public void draw(Graphics g)
    {
        if(!AlienLaser)
        {
            g.setColor(Color.green);   
        }
        else
        {
            g.setColor(Color.red);
            
        }
        g.fillRect(c_box.x, c_box.y, c_box.width, c_box.height);
    }
    
}
