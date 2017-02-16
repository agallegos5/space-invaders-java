import javax.swing.*;

public class Driver
{
    public static void main(String [] args)
    {
        //create a frame
                        JFrame frame = new JFrame("Space Invaders");

                         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        //create a panel, put in frame
                         SpacePanel panel = new SpacePanel();
                        frame.getContentPane().add(panel);

                        //set frame size, make visible
                        frame.setExtendedState(frame.MAXIMIZED_BOTH);
                        frame.setVisible(true);
                        //let everything load
                        try
                        {
                            Thread.sleep(100);
                        }catch(Exception e){}
                        //Threads
                        AlienLaserGeneratorThread g = new AlienLaserGeneratorThread(panel);
                        AlienLaserThread al = new AlienLaserThread(panel);
                        CLThread cl = new CLThread(panel);
                        AlienThread a = new AlienThread(panel);
                        ShipThread s = new ShipThread(panel);
                        ShipLaserThread t = new ShipLaserThread(panel);
                        g.start();
                        al.start();    //thread involving alien lasers
                        cl.start();    //thread involving chargedLasers
                        t.start();      //thread involving ship lasers
                        s.start();    //ship thread
                        a.start();    //aliens thread
    }
}
