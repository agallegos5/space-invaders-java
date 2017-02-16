import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.applet.*;
import java.io.*;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JApplet;

public class Music
{
    
    Clip background;
    AudioClip ship_laser;
    AudioClip alien_laser;
    AudioClip ship_destruction;
    AudioClip ship_damage;
    AudioClip alien_destruction;
    AudioClip alien_damage;
    AudioClip eraser_bomb;
    
    File file2;
    File file3;
    File file4;
    File file5;
    File file6;
    File file7;
    File file8;


    Music()
    {
        try
        {
            File file1 = new File("assets/Exhilarate.wav");
            background = AudioSystem.getClip();
            
            
            background.open(AudioSystem.getAudioInputStream(file1));
            
        }catch(LineUnavailableException  e)
        {
            e.printStackTrace();
        }catch(IOException e)
        {
            e.printStackTrace();
        }catch(UnsupportedAudioFileException e)
        {
            e.printStackTrace();
        }
    }
    
    public void initializeSounds()
    {
       file2 = new File("assets/alien_destruction.wav");
       file3 = new File("assets/laser.wav");
       file4 = new File("assets/ship_damage.wav");
       file5 = new File("assets/ship_destruction.wav");
       file6 = new File("assets/alien_laser.wav");
       file7 = new File("assets/alien_damage.wav");
       file8 = new File("assets/eraser_bomb.wav");
       try
       {
           alien_destruction = JApplet.newAudioClip(file2.toURL());
           ship_laser = JApplet.newAudioClip(file3.toURL());
           ship_damage = JApplet.newAudioClip(file4.toURL());
           ship_destruction = JApplet.newAudioClip(file5.toURL());
           alien_laser = JApplet.newAudioClip(file6.toURL());
           alien_damage = JApplet.newAudioClip(file7.toURL());
           eraser_bomb = JApplet.newAudioClip(file8.toURL());
       }catch(Exception e){e.getMessage();}
    }
    
     public void playAlienDestruction()
    {  
       try
       {
           alien_destruction.play();
       }catch(Exception e){e.getMessage();}
    }

    public void playShipLaser()
    {  
       try
       {
           ship_laser.play();
       }catch(Exception e){e.getMessage();}
    }
    
    public void playAlienLaser()
    {  
       try
       {
           alien_laser.play();
       }catch(Exception e){e.getMessage();}
    }
    
     public void playShipDamage()
    {  
       try
       {
           ship_damage.play();
       }catch(Exception e){e.getMessage();}
    }
     
      public void playShipDestruction()
    {  
       try
       {
           ship_destruction.play();
       }catch(Exception e){e.getMessage();}
    }

      public void playAlienDamage()
    {  
       try
       {
           alien_damage.play();
       }catch(Exception e){e.getMessage();}
    }
    
       public void playEraserBomb()
    {  
       try
       {
           eraser_bomb.play();
       }catch(Exception e){e.getMessage();}
    }
}
