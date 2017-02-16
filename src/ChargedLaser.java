import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ChargedLaser extends Laser
{
    ChargedLaser(int xpos, int ypos)
    {
        super(xpos, ypos);
        
        width = 20;
        height = 20;
           
        c_box.x = x;
        c_box.y = y;
        c_box.width = width;
        c_box.height = height;
        
    }
    
    public void draw(Graphics g)
    {
        g.setColor(Color.green);
        g.fillRect(c_box.x, c_box.y, c_box.width, c_box.height);
        g.setColor(Color.blue);      
        g.fillOval(x, y, width, height);
    }
}
