import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MovingSprite extends Rectangle
{
    //collision box
    protected Rectangle c_box;
    
    //velocity
    public double dx;
    public double dy;
    
    //recgular constructor
    MovingSprite(int xpos, int ypos)
    {
        x = xpos;
        y = ypos;


        //default width and height for any MovingSprite
        width = 50;
        height = 50;
        c_box = new Rectangle(x, y,width, height );
    }
    
    //checks for collision will all four walls
    public int collisionWithWall( SpacePanel p)
    {
        //collision with rightwall
        if(this.c_box.x + this.c_box.width >= p.getWidth())
        {
 //           this.x = 0;
            return 2;
        }
        
        //collision with left wall
        else if(this.c_box.x < 0)
        {
  //          this.x = p.getWidth() - this.width;
            return 4;
        }
        
        //collision with ceiling
        else if(this.c_box.y < 0)
        {
            //do nothing...for now
            return 3;
        }
            
        //collision with ground
        else if(this.c_box.y + this.c_box.height > p.getHeight())
        {
            //do nothing...for now
            return 1;
        }
        
        else
        {
            return 0;
        }
    }
    //checks for collision with another sprite
    public boolean collisionWith(MovingSprite r)
    {
        //if there is a collision between
        //the ship's collision box and some other object's collision box
        if( r.c_box.intersects(c_box) == true)
        {
           //recognize the collision 
            return true;
        }
        //else
        return false;
    }
    
}
