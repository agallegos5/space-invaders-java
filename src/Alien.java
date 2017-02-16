import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Alien extends MovingSprite
{
    private ImageIcon A;
    
    private int HP;
    private Random generator;
    protected boolean destroyed;
    protected boolean shoot;
   
    protected Color laserColor;
    //generate a random value to see if it will shoot
    protected int chance;
    
   Alien(int xpos, int ypos)
    {
        super(xpos, ypos);
        A = new ImageIcon("assets/alien.png");
        
        generator = new Random();
        
        //chance to shoot of 1 in 7
        chance = generator.nextInt(7) + 1;
        shoot =false;
        dx = 1;
        dy = 0;
        //starting health points of each alien
        HP = 100;
        destroyed = false;
        //height = A.getIconHeight();
        //width = A.getIconWidth();

        c_box.height = A.getIconHeight() - 5;
        c_box.width = A.getIconWidth() -5;
        laserColor = Color.YELLOW;
        

    }
   
   public void generateRandom()
   {
       chance = generator.nextInt(30)+1;
       if(chance == 5)
       {
           shoot = true;
       }
   }
   public void update()
   {
       if(!destroyed)
       {
           x+=dx;
           generateRandom();
           c_box.x = x+2;
           c_box.y = y+2;
       }
   }
   
   public void getHit()
   {
       HP += -25;
       if(HP <= 0)
       {
           destroyed = true;
           this.outOfScreen();
       }
       
   }


    public void draw(Graphics g)
    {
    //        g.setColor(Color.red);
    //         g.fillRect(c_box.x, c_box.y, c_box.width, c_box.height);
        
           g.drawImage(A.getImage(), x, y, null);
    }
    
    //instead of deleting the alien and having to deal with problems regarding iterators
    //at each for each loop, simply draw the aliens out of the screen and kill their speed
    public void outOfScreen()
    {
        //kill its velocity
        dx = dy = 0;
        chance = 0;
        //set coordinates to outside of the screen
        x = y =3000;
        c_box.x = x;
        c_box.y = y;
    }
}
