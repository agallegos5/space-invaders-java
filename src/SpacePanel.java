import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
//TO DO ADD AN EXPLOSION CLASS SO THAT YOU CAN CHANGE THE SPRITE OF THINGS WHEN THEY DISSAPEAR
public class SpacePanel extends JPanel implements KeyListener
{
    //background image
    private ImageIcon background;
    
    //ship related
    private int laserIndex;
    public Vector<Laser> shipLasers;
    private Vector<ChargedLaser> chargedLasers;
    private Vector<Ship> lifes;
    private Ship ship;       //current ship
    
    //alien related
    private Vector<Alien> aliens;
    private int alienIndex;
    public Vector<Laser> alienLasers;
    Music music = new Music();
    
    SpacePanel()
    {
        //set the background image
        background = new ImageIcon("assets/space.jpg");
        
        //initializations and other stuff
        lifes = new Vector<Ship>();
        shipLasers = new Vector<Laser>();
        alienLasers = new Vector<Laser>();
        chargedLasers = new Vector<ChargedLaser>();
        ship = new Ship(500, 600);     //initialize
        lifes.add(ship);         //first life
        aliens = new Vector<Alien>();
        spawnAliens();
        alienLasers = new Vector<Laser>();
            
        addKeyListener(this);
        setFocusable(true);
        showInstructions();
        File fileName = new File("assets/Exhilarate.wav");
        music.initializeSounds();
        music.background.start();

    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(background.getImage(), 0, 0, this);
        
        //draw lasers fired by aliens
        for(Laser l: alienLasers)
            l.draw(g);
        
        //draw aliens
        for(Alien a: aliens)
            a.draw(g);
        
        //draw lasers fired by user
        for(Laser l: shipLasers)
        {
            l.draw(g);
        }
        
        for(ChargedLaser l: chargedLasers)
            l.draw(g);
        
        //draw ship controlled by the user
        ship.draw(g);

    }
    
    //this method runs with CLThread
    public void updateChargedLasers()
    {
        alienIndex = -1;
        
        for(int i =0; i < chargedLasers.size(); i++)
        {
            chargedLasers.get(i).update();
            for(int j = 0; j < aliens.size(); j++)
            {
                if(chargedLasers.get(i).collisionWith(aliens.get(j)) == true)
                {
                    alienIndex = j;

                    //counts as two hits
                    aliens.get(j).destroyed = true;                  
                }
                
            }
            //check for collision with the ceiling
            if(chargedLasers.get(i).collisionWithWall(this) == 3)
            {                  
                    try
                    {
                        chargedLasers.remove(i);
                    }catch(Exception et){}
            }

        }
        
       try
       {
          
             if(aliens.get(alienIndex).destroyed == true)
             {
                 aliens.get(alienIndex).outOfScreen();
  //               aliens.remove(alienIndex);
             }
        }catch(Exception e){}
        
        repaint();
    }
    
    //this method runs with the ShipLaserThread
    public void updateShipLasers()
    {
        //in case none of the lasers hit
        //by setting the default index of the item to be deleted to -1
        //to prevent the program from crashing
        laserIndex = alienIndex = -1;
                   
        //move each laser generated
        for(int i = 0; i < shipLasers.size(); i++)
        {
            shipLasers.get(i).update();
            for(int j = 0; j < aliens.size(); j++)
            {
                //check for collision between any laser and any alien
               if(shipLasers.get(i).collisionWith(aliens.get(j)) == true)
               {
                   //mark the laser to remove it and the alien
                   alienIndex = j;
                   laserIndex = i;
                   //count the hit
                   aliens.get(j).getHit();
                   music.playAlienDamage();
               }
            }
            //if the laser goes past the screen save the index of that laser
            //so that it can be deleted later
            if(shipLasers.get(i).collisionWithWall(this) == 3)
            {
                laserIndex = i;
            }
        }
        //needed so that if none of the lasers hit any of the aliens, it won't have an index to delete
        try
        {
            shipLasers.remove(laserIndex);
            if(aliens.get(alienIndex).destroyed == true)
            {
                music.playAlienDestruction();
                aliens.get(alienIndex).outOfScreen();
     //           aliens.remove(alienIndex);
            }
           
        }catch(Exception e){}
        

        repaint();
    }
    
    //this method runs with the AlienLaserGeneratorThread
    public void generateLasers()
    {
        //this loops generates lasers fired by the enemies
        for(Alien a: aliens)
        {
            a.generateRandom();
            try
            {
                Thread.sleep(200);
            }catch(Exception e){}
            
            if(!a.destroyed && a.shoot == true)  
            {
                if(alienLasers.size() == 50)
                {
                    //stop generating lasers
                }
                else
                {
                    Laser l = new Laser(a.c_box.x + a.width/2, a.c_box.y);
                    l.AlienLaser =true;
                    alienLasers.add(l);   //generate the laser at about half of the x coordinate of the alien
                    music.playAlienLaser();
                    repaint();
                }
            }
            a.shoot = false;
        }
        repaint();
    }
    //this method runs with AlienLaserThread
    public void updateAlienLasers()
    {
        alienIndex = -1;
        
        //this loop updates the lasers and checks for collisions with the ship
        //and the bottom of the screen
        for(int i =0; i < alienLasers.size(); i++)
        {
            alienLasers.get(i).updateEnemyLasers();
            if(alienLasers.get(i).collisionWith(ship) == true)
            {
                alienIndex = i;
                music.playShipDamage();
                ship.getHit();
            }
            
            //check for ground collision
            if(alienLasers.get(i).collisionWithWall(this) == 1)
            {
                alienIndex = i;
            }
        }
        
        try
        {
            alienLasers.remove(alienIndex);
            if(ship.destroyed == true)
            {
                
                repaint();
            }
        }catch(Exception e){}
        
        repaint();
    }
    //this method runs with the AlienThread
    public void updateAliens()
    {
        int alienCount = 0;
        for(Alien a: aliens)
        {
            if(a.x == 3000)
                alienCount++;
        }
        if(alienCount == aliens.size())
        {
            GameWon();
            music.background.stop();
            System.exit(0);
        }
        
        //start of loop to update aliens
        for(Alien a: aliens)
        {
            a.update();
            
            if(a.destroyed == true)
            {
                a.outOfScreen();
            }
            //hit floor
            if(a.collisionWithWall(this) == 1)
            {
                //so the alien teleports all the way back up the screen
                a.y = 0;
            }
            //hit right wall
            if(a.collisionWithWall(this) == 2)
            {
                if(a.dx >10 || a.dx < -10)    //max speed aliens may build up to
                {
                    a.dx =-a.dx;
                }
                else
                {
                    a.dx = -1.5*a.dx;
                }
                a.y+=25;
            }
            //hit left wall
            if(a.collisionWithWall(this) == 4)
            {
                if(a.dx >10 || a.dx < -10)    //max speed aliens may build up to
                {
                    a.dx =-a.dx;
                }
                else
                {
                    a.dx = -1.5*a.dx;
                }
                a.y+=25;
            }
            if(a.collisionWith(ship) == true)
            {
                ship.destroyed =true;
                repaint();
            }
        }
        repaint();
    }
    
    //this method runs with the ShipThread
    public void updateShip()
    {
        ship.update();
        
        //hit right wall
        if(ship.collisionWithWall(this) == 2)
        {
            ship.x = 0;
        }
        //hit left wall
        if(ship.collisionWithWall(this) == 4)
        {
            ship.x = this.getWidth() - ship.width;
        }

        if(lifes.size() == 3 && ship.destroyed == true)
        {
            GameOver();
            music.background.stop();
            System.exit(0);
        }
        if(ship.destroyed == true)
        {
            ship.c_box.x = ship.c_box.y = 2000;
            music.playShipDestruction();
            repaint();            
            try
            {
                Thread.sleep(2000);    //wait 2 secs
            }catch(Exception e){}
            
            ship.outOfScreen();    
            spawnShip();
        }
        repaint();
    }
    
    public void spawnShip()
    {
        //allocate new ship
        ship = new Ship(500, 600);
        
        //store it in the lifes array
        lifes.add(ship);
    }
    //creates aliens
    public void spawnAliens()
    {
        for(int i = 0; i <5; i++)
        {
            for(int j = 1; j < 20; j++)
            {
                aliens.add(new Alien(j*65, i*95));
            }
        }
    }
    
    public void showInstructions()
        {
                    JOptionPane.showMessageDialog(null,
                                 "Move with the arrow keys left and right \n Press s to shoot lasers. \nPress d to shoot special eraser bombs wiping out anything in their path.  Only one bomb\n is allowed on the screen at any given time \n",
                                 "Instructions",JOptionPane.PLAIN_MESSAGE);
        }
        
        public void GameWon()
        {
                    JOptionPane.showMessageDialog(null,
                                 "Congratulations!!, you have single handedly\n stopped the alien horde from invading your home planet",
                                 "Victory",JOptionPane.PLAIN_MESSAGE);
        }
        
        public void GameOver()
        {
                    JOptionPane.showMessageDialog(null,
                                 "You have lost. No one will be able to\n stop the alien invasion",
                                 "Game Over",JOptionPane.PLAIN_MESSAGE);
        }
    //keyListener methods////////////////////////////////////////
    public void keyPressed(KeyEvent e)
    {
        //move right
            if(e.getKeyCode() == 39)
            {
                ship.moving = true;
                ship.dx = 6;
            }
            //move left
            if(e.getKeyCode() == 37)
            {
                ship.moving = true;
                ship.dx = -6;
            }
            
            
            repaint();
    }

    public void keyReleased(KeyEvent e)
    {
        //shoot lasers (regular weapon)
            if(e.getKeyChar() == 's' && !ship.destroyed)
            {
                //make the ship shoot
                if(shipLasers.size() == 5)
                {
                    //Can't let too many lasers be on the screen
                }
                
                else
                {
                    //create the laser
                    shipLasers.add(new Laser(ship.x + ship.width/2 - 2, ship.y + ship.height/2));
                    music.playShipLaser();
                }
            }
            //fire eraser bomb
       if(e.getKeyChar() == 'd' && !ship.destroyed)
        {
                if(chargedLasers.size() == 1)
                {
                    //do nothing...
                }

                else
                {
                    chargedLasers.add(new ChargedLaser(ship.x + ship.width/2 - 2, ship.y + ship.height/2));
                    music.playEraserBomb();
                }
        }
       
       //right
        if(e.getKeyCode() == 39)
        {
            ship.moving = false;
            ship.dx = 0;
            ship.dy = 0;
        }
        
        //left
        if(e.getKeyCode() == 37)
        {
            ship.moving = false;
            ship.dx = 0;
            ship.dy = 0;
        }
        
        

        repaint();
    }

    public void keyTyped(KeyEvent e)
    {
        repaint();
    }
}
